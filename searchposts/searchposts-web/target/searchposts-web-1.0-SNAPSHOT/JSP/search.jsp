<%-- 
    Document   : search
    Created on : 28.02.2022, 20:10:28
    Author     : Ruslan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Search posts</h1>

        <input id="subject" type="text" name="subject"/>
        <button type="button" onclick="httpGet()">Search!</button>

        <div id="result">
        </div>
    </body>
</html>
<script>
    function httpGet()
    {
        var param = document.getElementById('subject').value
        var xmlHttp = new XMLHttpRequest();
        var url = 'http://localhost:8080/searchposts-web/api/search?subject=' + param;
        xmlHttp.open("GET", url, false); // false for synchronous request
        xmlHttp.send(null);
        console.log(xmlHttp.responseText);
        document.getElementById("result").innerHTML = xmlHttp.responseText;
    }
</script>
