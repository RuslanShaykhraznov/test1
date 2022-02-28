# Установка
* Установить [Tomcat server](https://tomcat.apache.org/download-80.cgi)
* Положить searchposts-web.war в tomcatdir\webapps
* Запустить startup.bat из tomcatdir\bin (либо разархивировать .war в папку \webapps\searchposts-web)
* Запустить tomcat, проверить доступность http://localhost:8080/searchposts-web/JSP/search.jsp
* При необходимости изменить адрес ссылки в tomcatdir\webapps\searchposts\JSP\search.jsp и перезапустить tomcat

# Searcher 
getPostsBySubject - обращается к api stackoverflow для получения потов и возвращает html
convertToHtml - преобразует строку в HTML table

# JerseyResource
getHTML - принимает строку и возвращает html table
