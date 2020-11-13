
package com.Project.PetBook;

import com.Project.PetBook.Services.MyUserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class MywebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserServiceInterface myUserServiceInterface;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authorivider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasAnyRole("USER")
                .antMatchers("/vet").hasAnyRole("VET")
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
                .permitAll();

    }

    @Bean
    public DaoAuthenticationProvider authorivider() {
        DaoAuthenticationProvider daoauth = new DaoAuthenticationProvider();
        daoauth.setUserDetailsService(myUserServiceInterface);
        daoauth.setPasswordEncoder(myencoder());
        return daoauth;
    }

    @Bean
    public PasswordEncoder myencoder() {

        return new BCryptPasswordEncoder();
    }

}
