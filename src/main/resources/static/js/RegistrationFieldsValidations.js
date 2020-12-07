
let usernameError = " ";
let emailError = " ";
let passwordError = " ";
let passwordError2 = " ";
let credentialError = "";

let submitbtn = document.getElementById("submitbtn");

function checkIfValidCredentialNumber() {
    credentialError = "";
    let regex = /^(0|[1-9][0-9]*)$/;
    let credentialNumber = document.getElementById("vet-id-input").value.trim();
    let displayCredentialError = document.getElementById("credentialError");

    if (!credentialNumber.match(regex)) {
        credentialError = "Credential should only contain numbers";
        $("#vet-id-input").addClass("error-border");
        $("#vet-id-input").removeClass("accepted-border");
    } else {
        $("#vet-id-input").removeClass("error-border");
        $("#vet-id-input").addClass("accepted-border");


    }
    displayCredentialError.innerText = credentialError;

    if (credentialError.length === 0) {
        $('#credentialCheck').attr("hidden", false);
    } else {
        $('#credentialCheck').attr("hidden", true);
    }
}


function  checkIfValidName() {
    let letters = /^[a-zA-Z](_(?!(\\.|_))|\\.(?!(_|\\.))|[a-zA-Z0-9]){1,20}[a-zA-Z0-9]$/;
    let username = document.getElementById("username").value.trim();
    usernameError = "";
    let displayUsernameError = document.getElementById("usernameError");



    if (username === null || username.trim() === "") {
        usernameError = "This field is required";
    }
    if (!username.match(letters)) {
        usernameError = "Username  should be between 3-20  characters  and start with a letter";
    }
    if (usernameError.length === 0) {
        $.ajax({
            url: "user-name-validation/" + username,
            async: false
        }).done(function (r) {

            if (!r) {
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
        $("#username").removeClass("accepted-border")
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
    if (email.length < 4 || email.length > 50 || email.includes(" ")) {
        emailError = "Invalid email format";
    } else

    if (!email.includes('@')) {
        emailError = "Invalid email format";
    }
    if (emailError.length !== 0) {
        $("#email").addClass("error-border");
        $("#email").removeClass("accepted-border");

    }

    if (emailError.length === 0) {

        $.ajax({
            url: "email-validation/" + email,
            async: false
        }).done(function (r) {

            if (!r) {
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
credentialCheck

function checkIfValidPassword() {

    let regex = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/

    let password = document.getElementById("password").value;
    passwordError = "";
    let displayPasswordError = document.getElementById("passworderror");


//    if (password.length < 6) {
//        passwordError = "Password should be 6 or more characters"
//        $("#password").addClass("error-border");
//
//    }

    if (password === null || password === "") {
        passwordError = "This field cannot be empty";
        $("#password").addClass("error-border");
    }
    if (!password.match(regex)) {
        passwordError = "Invalid Password format";
        $("#password").addClass("error-border");

    }

    displayPasswordError.innerText = passwordError;
    if (passwordError.length === 0) {
        $("#password").addClass("accepted-border");

        checkIfValidRetypePassword();
    } else {
        $('#passCheck').attr("hidden", true);
        $("#password2").addClass("error-border");
        $("#password").removeClass("accepted-border");
    }
}

function  checkIfValidRetypePassword() {
    let displayPasswordError2 = document.getElementById("passworderror2");
    let password2 = document.getElementById("password2").value;
    let password = document.getElementById("password").value;
    passwordError2 = "";


    if (password2 !== password) {
        passwordError2 = "Password fields don't match";
    }

    displayPasswordError2.innerText = passwordError2;
    if (passwordError2.length === 0) {
        $("#password2").addClass("accepted-border");
        $('#passCheck').attr("hidden", false);
    } else {
        $("#password2").addClass("error-border");
        $('#passCheck').attr("hidden", true);
    }

    if (passwordError2.length !== 0 || password2.length < 6) {
        $("#password2").removeClass("accepted-border");
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
        case 5 :
            checkIfValidCredentialNumber();
            break;
        default:

    }

    if (usernameError.length === 0 && emailError.length === 0 && passwordError.length === 0
            && passwordError2.length === 0 && credentialError.length === 0) {
        submitbtn.removeAttribute("disabled");
    } else {
        submitbtn.setAttribute("disabled", true);
    }
}

