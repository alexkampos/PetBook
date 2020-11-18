
var usernameError = " ";
let emailError = " ";
let passwordError = " ";
let passwordError2 = " ";

let submitbtn = document.getElementById("submitbtn");


function  validName() {
    var letters = /^[a-zA-Z\s]*$/;
    var username = document.getElementById("username").value.trim();
    usernameError = "";
    let displayusernameError = document.getElementById("usernameError");



    if (username.length < 3 || username.length > 20) {
        usernameError = "Name must be between 3-20 letters";
    }
    if (username === null || username.trim() === "") {
        usernameError = "this field cannot be empty";
    }
    if (!username.match(letters)) {
        usernameError = "this field should only contain letters";
    }
    if (usernameError.length === 0) {
        $.ajax({
            url: "user-name-validation/" + username,
            async: false
        }).done(function (r) {

            if (r) {
                $("#username").css("border", "3px solid green");

            } else {
                $("#username").css("border", "3px solid red");
                usernameError = "User Name already Exists";
                displayusernameError.innerText = usernameError;
            }
        });
    }

    if (usernameError.length !== 0) {
        $("#username").css("border", "3px solid red");
        $('#nameCheck').attr("hidden", true);

    } else {
        $('#nameCheck').attr("hidden", false);
    }


    displayusernameError.innerText = usernameError;



}

function  validEmail() {
    var email = document.getElementById("email").value.trim();
    emailError = "";
    let displayemailerror = document.getElementById("emailerror");



    if (email === null || email === "") {
        emailError = "this field cannot be empty";
    } else
    if (email.length < 4 || username.length > 20 || email.includes(" ")) {
        emailError = "invalid email";
    } else

    if (!email.includes('@')) {
        emailError = "invalid email";
    }
    if (emailError.length !== 0) {
        $("#email").css("border", "3px solid red");
    }

    if (emailError.length === 0) {

        $.ajax({
            url: "email-validation/" + email,
            async: false
        }).done(function (r) {

            if (r) {
                $("#email").css("border", "3px solid green");
            } else {
                $("#email").css("border", "3px solid red");
                emailError = "Email Already in use";
                displayemailerror.innerText = emailError;
            }
        });
    }
    displayemailerror.innerText = emailError;

    if (emailError.length === 0) {
        $('#mailCheck').attr("hidden", false);
    } else {
        $('#mailCheck').attr("hidden", true);
    }
}

function validPass1() {

    let password = document.getElementById("password").value;
    passwordError = "";
    let displaypassworderror = document.getElementById("passworderror");


    if (password.length < 6) {
        passwordError = "password should be  6 or more characters"
        $("#password").css("border", "3px solid red");

    }

    if (password === null || password === "") {
        passwordError = "this field cannot be empty";
        $("#password").css("border", "3px solid red");
    }

    displaypassworderror.innerText = passwordError;
    if (passwordError.length === 0) {
        $("#password").css("border", "3px solid green");

        validPass2();
    } else {
        $('#passCheck').attr("hidden", true);
        $("#password2").css("border", "3px solid red");
    }
}

function  validPass2() {
    let displaypassworderror2 = document.getElementById("passworderror2");
    let password2 = document.getElementById("password2").value;
    let password = document.getElementById("password").value;
    passwordError2 = "";


    if (password2 !== password) {
        passwordError2 = "no matching password please retype"
    }

    displaypassworderror2.innerText = passwordError2;
    if (passwordError2.length === 0) {
        $("#password2").css("border", "3px solid green");
        $('#passCheck').attr("hidden", false);
    } else {
        $("#password2").css("border", "3px solid red");
        $('#passCheck').attr("hidden", true);
    }

    if (passwordError.length !== 0 || password.length < 6) {
        $("#password2").css("border", "3px solid red");
        $('#passCheck').attr("hidden", true);
    }

}


function validcheck(type) {

    switch (type) {
        case 1:
            validName();

            break;
        case 2:
            validEmail();
            break;
        case 3:
            validPass1();

            break;
        case 4:
            validPass2();

            break;
        default:
            alert("asd");
    }

    if (usernameError.length === 0 && emailError.length === 0 && passwordError.length === 0
            && passwordError2.length === 0) {
        submitbtn.removeAttribute("disabled");
    } else {
        submitbtn.setAttribute("disabled", true);
    }
}
