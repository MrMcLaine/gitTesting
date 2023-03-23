$('.message a').click(function () {
    debugger;
    $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
});

$("button.register")
    .click(
        function () {
            var firstName = $("form.register-form input.firstName")
                .val();
            var lastName = $("form.register-form input.lastName").val();
            var email = $("form.register-form input.email").val();
            var password = $("form.register-form input.password").val();
            var cpassword = $("form.register-form input.cpassword")
                .val();

            if (firstName == '' || lastName == '' || email == ''
                || password == '' || cpassword == '') {
                alert("Please fill all fields...!!!!!!");
            } else if ((password.length) < 8) {
                alert("Password should atleast 8 character in length...!!!!!!");
            } else if (!(password).match(cpassword)) {
                alert("Your passwords don't match. Try again?");
            } else {
                var userRegistration = {
                    firstName: firstName,
                    lastName: lastName,
                    email: email,
                    password: password
                };

                $.post("registration", userRegistration,
                    function (data) {
                        if (data == 'Success') {
                            $("form")[0].reset();
                            $("form")[1].reset();
                            loginRegisterSwitch();
                            showAlertAfterRegistration();
                        }
                    });
            }
        });