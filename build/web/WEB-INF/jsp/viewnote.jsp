<%-- 
    Document   : viewnote.jsp
    Created on : 28-Sep-2022, 5:18:13 PM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>note keeper form</title>
    </head>
    <body>
        <h1>Simple Note Keeper</h1>
        <h3>
            View Note
        </h3>
        <h5>Title: ${title}</h5>
        
        <h5>Contents: ${content}</h5>
        
        <!--href points back to note url but includes edit into param-->
        <a href="note?edit" name="edit">Edit</a>
        
    </body>
</html>
