
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org/" >
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <form th:action="@{/login.html}" method="post">
            <p>
                user name :<input type="text" name="username"/>
            </p>
            <p>
                password :<input type="text" name="password"/>
            </p>

            <input type="submit" value="Login"/>
        </form>
    </body>
</html>               
