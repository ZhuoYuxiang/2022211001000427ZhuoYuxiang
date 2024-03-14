<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>User Registration</title>
  <script>
    $(document).ready(function() {
      $('#registerForm').submit(function(event) {
        var username = $('#username').val();
        var password = $('#password').val();
        var email = $('#email').val();
        var birthdate = $('#birthdate').val();
        var isValid = true;

        // Username validation
        if (username.trim() == '') {
          $('#username').next().remove();
          $('#username').after('<span class="error">Username is required.</span>');
          isValid = false;
        } else {
          $('#username').next().remove();
        }

        // Password validation
        if (password.length < 8) {
          $('#password').next().remove();
          $('#password').after('<span class="error">Password must be at least 8 characters long.</span>');
          isValid = false;
        } else {
          $('#password').next().remove();
        }

        // Email validation
        var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailPattern.test(email)) {
          $('#email').next().remove();
          $('#email').after('<span class="error">Invalid email address.</span>');
          isValid = false;
        } else {
          $('#email').next().remove();
        }

        // Birthdate validation
        var datePattern = /^\d{4}-\d{2}-\d{2}$/;
        if (!datePattern.test(birthdate)) {
          $('#birthdate').next().remove();
          $('#birthdate').after('<span class="error">Invalid date format (yyyy-mm-dd).</span>');
          isValid = false;
        } else {
          $('#birthdate').next().remove();
        }

        if (!isValid) {
          event.preventDefault(); // Prevent form submission if there are errors
        }
      });
    });
  </script>
</head>
<body>
<h1>User Registration</h1>
<!-- register.jsp -->
<form id="registerForm" action="processRegistration.jsp" method="post">
  <!-- register.jsp -->
  Username: <input type="text" id="username" name="username" required><br>
  Password: <input type="password" id="password" name="password" required minlength="8"><br>
  Gender:
  <input type="radio" id="male" name="gender" value="male"> <label for="male">Male</label>
  <input type="radio" id="female" name="gender" value="female"> <label for="female">Female</label><br>
  Email: <input type="email" id="email" name="email" required><br>
  Birthdate: <input type="text" id="birthdate" name="birthdate" placeholder="yyyy-mm-dd" required><br>
  <input type="submit" value="Submit">
</body>
</html>