$("#changePass").validate({
    rules: {
        newPass: {
            required: true,
            passwordFormatCheck: true,
        },
       
    messages: {
        pass: {
            required: "Please provide a password",
        }
    },
    }
});

$.validator.addMethod("passwordFormatCheck", function (value, element) {
    //return this.optional(element) || /^(?=.*\d)(?=.*[A-Z])(?=.*\W).*$/i.test(value);
    return this.optional(element) || /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/i.test(value);
}, 'Password must contain one capital letter,one numerical and one special character');

$.validator.addMethod("lettersonly", function (value, element) {
    return this.optional(element) || /^[a-z\s]+$/i.test(value);
});
