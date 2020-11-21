
let usernameError = " ";
let emailError = " ";
let passwordError = " ";
let passwordError2 = " ";

let submitbtn = document.getElementById("submitbtn");


function  checkIfValidName() {
    let letters = /^[a-zA-Z\s]*$/;
    let username = document.getElementById("username").value.trim();
    usernameError = "";
    let displayUsernameError = document.getElementById("usernameError");



    if (username.length < 3 || username.length > 20) {
        usernameError = "Name should be between 3-20 letters";
    }
    if (username === null || username.trim() === "") {
        usernameError = "This field is required";
    }
    if (!username.match(letters)) {
        usernameError = "This field should only contain letters";
    }
    if (usernameError.length === 0) {
        $.ajax({
            url: "user-name-validation/" + username,
            async: false
        }).done(function (r) {

            if (r) {
                $("#username").addClass("accepted-border");

            } else {

                $("#username").removeClass("accepted-border")
                $("#username").addClass("error-border");
                usernameError = "Username already exists";
                displayUsernameError.innerText = usernameError;
            }
        });
    }

    if (usernameError.length !== 0) {
        $("#username").addClass("error-border");
        $('#nameCheck').attr("hidden", true);

    } else {
        $('#nameCheck').attr("hidden", false);
    }


    displayUsernameError.innerText = usernameError;



}

function  checkIfValidEmail() {
    let email = document.getElementById("email").value.trim();
    emailError = "";
    let displayEmailError = document.getElementById("emailerror");



    if (email === null || email === "") {
        emailError = "This field cannot be empty";
    } else
    if (email.length < 4 || email.length > 20 || email.includes(" ")) {
        emailError = "Invalid email format";
    } else

    if (!email.includes('@')) {
        emailError = "Invalid email format";
    }
    if (emailError.length !== 0) {
        $("#email").addClass("error-border");
        $("#email").removeClass("accepted-border")
       
    }

    if (emailError.length === 0) {

        $.ajax({
            url: "email-validation/" + email,
            async: false
        }).done(function (r) {

            if (r) {
                $("#email").addClass("accepted-border");
            } else {
                $("#email").addClass("error-border");
                 $("#email").removeClass("accepted-border");
                emailError = "Email already in use";
                displayEmailError.innerText = emailError;
            }
        });
    }
    displayEmailError.innerText = emailError;

    if (emailError.length === 0) {
        $('#mailCheck').attr("hidden", false);
    } else {
        $('#mailCheck').attr("hidden", true);
    }
}

function checkIfValidPassword() {

    let password = document.getElementById("password").value;
    passwordError = "";
    let displayPasswordError = document.getElementById("passworderror");


    if (password.length < 6) {
        passwordError = "Password should be 6 or more characters"
        $("#password").addClass("error-border");

    }

    if (password === null || password === "") {
        passwordError = "This field cannot be empty";
        $("#password").addClass("error-border");
    }

    displayPasswordError.innerText = passwordError;
    if (passwordError.length === 0) {
        $("#password").addClass("accepted-border");

      checkIfValidRetypePassword();
    } else {
        $('#passCheck').attr("hidden", true);
        $("#password2").addClass("error-border");
    }
}

function  checkIfValidRetypePassword() {
    let displayPasswordError2 = document.getElementById("passworderror2");
    let password2 = document.getElementById("password2").value;
    let password = document.getElementById("password").value;
    passwordError2 = "";


    if (password2 !== password) {
        passwordError2 = "Password fields don't match"
    }

    displayPasswordError2.innerText = passwordError2;
    if (passwordError2.length === 0) {
        $("#password2").addClass("accepted-border");
        $('#passCheck').attr("hidden", false);
    } else {
        $("#password2").addClass("error-border");
        $('#passCheck').attr("hidden", true);
    }

    if (passwordError.length !== 0 || password.length < 6) {
        $("#password2").addClass("error-border");
        $('#passCheck').attr("hidden", true);
    }

}


function checkIfInputsValid(type) {

    switch (type) {
        case 1:
            checkIfValidName();

            break;
        case 2:
            checkIfValidEmail();
            break;
        case 3:
            checkIfValidPassword();

            break;
        case 4:
            checkIfValidRetypePassword();

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

