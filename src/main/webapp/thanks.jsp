<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Thanks</title>
    <link rel="stylesheet" href="style/main.css" type="text/css"/>
</head>
<body>
    <h1>Thanks for joining!</h1>
    <p>Here is the information you entered:</p>
    
    <label>Email:</label>
    <span>${user.email}</span><br>
    <label>First Name:</label>
    <span>${user.firstName}</span><br>
    <label>Last Name:</label>
    <span>${user.lastName}</span><br>
    
    <p>The user has been saved to the database.</p>
    <p>An email has been sent to <b>${user.email}</b>.</p>
    
    <form action="index.jsp" method="get">
        <input type="submit" value="Return">
    </form>
</body>
</html>