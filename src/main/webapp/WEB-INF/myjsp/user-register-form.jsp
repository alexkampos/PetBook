<%-- 
    Document   : welcome
    Created on : Oct 23, 2020, 6:32:48 PM
    Author     : Alkis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Register</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
              integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
                integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
                integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" />
    </head>
    <body>
        <div class="mybody">
            <div class="dog">
                <img src="images/dog.png" width="200px">
            </div>

            <div class="cat">
                <img src="images/cat2.png" width="180px">
            </div>

            <div class="myform">
                <div class="register">
                    <img src="images/stamp.png" width="30x">
                    <h2>Register</h2>
                </div>
                <form:form method="POST" action="/insertuser" modelAttribute="newuser">

                    <div class="input-group">
                        <i class="fas fa-user username-icon"></i>
                        <form:input type="text" name="username" path="userName"  id="username" class="form-control" placeholder="Username" aria-label="Username"
                                    aria-describedby="basic-addon1"/> 
                    </div>

                    <div class="input-group">
                        <i class="fas fa-envelope"></i>

                        <form:input type="text" name="email" path="email" id="email" class="form-control" placeholder="Email" aria-label="Email"
                                    aria-describedby="basic-addon1"/> 
                    </div>
                    <div class="input-group">
                        <i class="fas fa-key"></i>
                        <form:input type="password" name="password" path="password" id="password" class="form-control" placeholder="Password" aria-label="Username"
                                    aria-describedby="basic-addon1"/> 
                    </div>
                    <div class="input-group mb-3">
                        <i class="fas fa-key"></i>
                        <input type="password" class="form-control" id="password2" placeholder="Re-type Password" aria-label="Username"
                               aria-describedby="basic-addon1">
                    </div>
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">

                            <img src="images/vet-dog.png" width="35px">
                            <div class="input-group-text noborder">
                                <input type="checkbox" onclick="onChangeHandler()"
                                       aria-label="Checkbox for following text input" >
                            </div>
                        </div>
                        <input type="text" value="Create a Vet Acount" class="form-control"
                               aria-label="Text input with checkbox" readonly>

                    </div>
                    <div class="input-group mb-3"  id="myDIV" hidden>
                        <i class="fas fa-certificate"></i>
                        <input type="text" class="form-control" placeholder="Enter Credential Id" aria-label="Username"
                               aria-describedby="basic-addon1">
                    </div>
                    <div class="center">
                        <button type="submit" class=" btn btn-primary">Submit</button>
                    </div>
                </form:form>
            </div>
            <div class="dog1">

                <img src="images/squirle.png" width="450px">
            </div>
        </div>


        <script>
            function onChangeHandler() {
                var x = document.getElementById("myDIV");
                if (x.hidden === true) {
                    x.hidden = false;
                } else {
                    x.hidden = true
                }
            }
        </script>
    </body>
</html>








