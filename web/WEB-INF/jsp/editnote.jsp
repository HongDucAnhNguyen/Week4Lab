<%-- 
    Document   : editnote.jsp
    Created on : 28-Sep-2022, 5:18:30 PM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Page</title>
    </head>
    <body>
        <form method="POST" action="note">
            <h1>Simple Note Keeper</h1>
        <h2>Edit Note</h2>
        
        <h5>Title:</h5>
        <input type="text" name="title" value= "${note.title}" >
        <h5>Content:</h5>
        <textarea name="content">${note.content}</textarea>
        <br>
        <input type="submit" value="Save">
        </form>
        <p>${message}</p>
    </body>
</html>
