<%@ page import = "java.util.*" %>

<html>
    <body>
        <% String name = request.getParameter("name");
           String password = request.getParameter("password");
           out.print("Name = " + name + " and password = " + password + " are missing or incorrect.");
        %>
    </body>
</html>