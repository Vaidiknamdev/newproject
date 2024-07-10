
<html>
   
    <body>
        <h1>Question upload page</h1>
        <form action="SaveQuestionServlet" method="post">
            <pre>
        userid   <input type="text" name="userid"/>
        question <input type="text" name="question"/>
        subject  <select name="subject">
            <option>java</option>
            <option>python</option>
            <option>c</option>
            <option>C#</option>
            <option>javascript</option>
            <option>html</option>
        </select>
        <input type="submit" value="submit"/>
            </pre>
        </form>
    </body>
</html>
