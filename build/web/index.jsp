<html>
    <body>
        <h1>Assignment Portal</h1>
        <hr><!-- comment -->
        <form action="UserAuthentication" method="post">
            <table border="0">
                <tr>
                    <td>userid</td>
                    <td><input type="text" name="userid"></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" name="password"></td>
                </tr>
                 <tr>
                    <td>Usertype</td>
                    <td><select type="usertype" name="usertype"><option>Admin</option><option>Faculty</option><option>Student</option></select></td>
                </tr>
                <tr>
                    <td>save password</td>
                    <td><input type="checkbox" name="save" value="yes"/></td>
                </tr>
         
                <tr>
                    <td><input type="submit" value="Login"/><td><input type="reset" value="Reset"</td>
                </tr>
            </table>
            
        </form>
        <hr>
        <a href="registration.jsp">New-Student-Registration</a>
    </body>
</html>
