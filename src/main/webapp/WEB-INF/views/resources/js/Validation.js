$("#register").validate({
    rules: {
        firstName: {
            required: true,
            lettersonly: true
        },
        lastName: {
            required: true,
            lettersonly: true
        },
        pass: {
            required: true,
            passwordFormatCheck: true,
        },
        email: {
            required: true,
            validateEmail: true,
            "remote": {
                url: 'CheckEmailAvailability',
                type: "post",
                data: {
                    email: function () {
                        return $("#email").val();
                    }
                }
            }

        },
        phone: {
            required: true,
            number: true,
        },
        securityAns: {
            required: true
        },
        gender: "required",

    },
    messages: {
        firstName: {
            required: "Please enter your firstname",
            lettersonly: "Please enter valid firstname",
        },
        lastName: {
            required: "Please enter your lastname",
            lettersonly: "Please enter valid lastname",
        },
        pass: {
            required: "Please provide a password",
        },
        email: {
            required: "Please provide an email",
            email: "Please enter a valid email address",
            remote: "The corresponding email already exists"
        },
        securityAns: {
            required: "Please enter security answer",
        }
    },
    errorPlacement: function (error, element) {
        if (element.attr("name") == "gender") {
            error.appendTo("#gender-error");
        } else {
            error.insertAfter(element);
        }
    },

});

$.validator.addMethod("passwordFormatCheck", function (value, element) {
    //return this.optional(element) || /^(?=.*\d)(?=.*[A-Z])(?=.*\W).*$/i.test(value);
    return this.optional(element) || /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/i.test(value);
}, 'Password must contain one capital letter,one numerical and one special character');

$.validator.addMethod("lettersonly", function (value, element) {
    return this.optional(element) || /^[a-z\s]+$/i.test(value);
});

// ^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$


$.validator.addMethod("validateEmail", function (value, element) {
    //return this.optional(element) || /^(?=.*\d)(?=.*[A-Z])(?=.*\W).*$/i.test(value);
    return this.optional(element) || /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/i.test(value);
}, 'Enter Valid Email');