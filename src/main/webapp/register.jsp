<%--
  Created by IntelliJ IDEA.
  User: xiang
  Date: 2024/3/13
  Time: 22:00
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
    <title>User Registration</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
        $(document).ready(function(){
            $("#registrationForm").submit(function(event){
                var isValid = true;
                $(".error").remove();

                // Username validation1
                var username = $("#username").val();
                if(username == ""){
                    $("#username").after("<span class='error'>Username is required</span>");
                    isValid = false;
                }

                // Password validation
                var password = $("#password").val();
                if(password == ""){
                    $("#password").after("<span class='error'>Password is required</span>");
                    isValid = false;
                } else if(password.length < 8){
                    $("#password").after("<span class='error'>Password must be at least 8 characters long</span>");
                    isValid = false;
                }

                // Email validation
                var email = $("#email").val();
                var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
                if(email == ""){
                    $("#email").after("<span class='error'>Email is required</span>");
                    isValid = false;
                } else if(!emailRegex.test(email)){
                    $("#email").after("<span class='error'>Invalid email address</span>");
                    isValid = false;
                }

                // Birthdate validation
                var birthdate = $("#birthdate").val();
                var birthdateRegex = /^\d{4}-\d{2}-\d{2}$/;
                if(birthdate == ""){
                    $("#birthdate").after("<span class='error'>Birthdate is required</span>");
                    isValid = false;
                } else if(!birthdateRegex.test(birthdate)){
                    $("#birthdate").after("<span class='error'>Invalid date format (yyyy-dd-mm)</span>");
                    isValid = false;
                }

                if(!isValid){
                    event.preventDefault(); // Prevent form submission if there are errors1
                }
            });
        });
    </script>
</head>
<body>
<h2>User Registration</h2>
<form id="registrationForm" action="submit_registration.jsp" method="post">
    <div>
        <label for="username">Username:</label>
        <input type="text" id="username" name="username">
    </div>
    <div>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password">
    </div>
    <div>
        <label for="email">Email:</label>
        <input type="text" id="email" name="email">
    </div>
    <div>
        <label for="birthdate">Birthdate:</label>
        <input type="text" id="birthdate" name="birthdate">
    </div>
    <div>
        <input type="submit" value="Register">
    </div>
</form>
</body>
</html>