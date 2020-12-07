package com.Project.PetBook.security;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import com.Project.PetBook.Services.MyUserService;
import javax.sql.DataSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@EnableWebSecurity
public class MywebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserService myUserService;
    
    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authorivider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable().authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasRole("USER")
                .antMatchers("/vet").hasRole("VET")
                .antMatchers("/home").hasAnyRole("USER", "ADMIN", "VET")
                .antMatchers("/requests/**").hasAnyRole("USER", "ADMIN", "VET")
                .antMatchers("/").permitAll()
                .antMatchers("/home").permitAll()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .successForwardUrl("/home")
                    .failureUrl("/login-error")
                    .permitAll()
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login")
                    .permitAll()
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .userDetailsService(myUserService);

    }
    
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }

    @Bean
    public DaoAuthenticationProvider authorivider() {
        DaoAuthenticationProvider daoauth = new DaoAuthenticationProvider();
        daoauth.setUserDetailsService(myUserService);
        daoauth.setPasswordEncoder(myencoder());
        return daoauth;
    }

    @Bean
    public PasswordEncoder myencoder() {

        return new BCryptPasswordEncoder();
    }

}
