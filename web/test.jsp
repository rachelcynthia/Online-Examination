<%-- 
    Document   : welcome
    Created on : 9 Feb, 2020, 7:56:19 PM
    Author     : rache
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Test page</title>
        <style>
            p{
                background-color: red;
                color: azure;
            }
            div{
                background-color: darkseagreen;
                color:white;
            }
        </style>
    </head>
    <body>
        <%
        Cookie[] c=request.getCookies();
        int i;
        for(i=0;i<c.length;i++)
        {
            //out.println( c[i].getValue());
            if(c[i].getName().equals("username"))
            {
                out.print("<body><h1> HELLO, "+c[i].getValue()+"!</h1>");
            }
            
            /
                    
        }
                %>
                <form action="server2" method="POST">
                    <h3><p>WELCOME TO THE ONLINE EXAMINATION</p></h3>
                    <div>QUESTION1: What is 12+42?</div>
                    <input type="radio" name="Question1" value="23" />23<br>
                    <input type="radio" name="Question1" value="34" />34<br>
                    <input type="radio" name="Question1" value="54" />54<br>
                   <input type="radio" name="Question1" value="65" />65
                   <hr>
                   <div>QUESTION2: What is 12*2?</div>
                   <input type="radio" name="Question2" value="24" />24<br>
                   <input type="radio" name="Question2" value="6" />6<br>
                   <input type="radio" name="Question2" value="46" />46<br>
                   <input type="radio" name="Question2" value="45" />45
                   <hr>
                   <div>QUESTION3: What is 6^2?</div>
                   <input type="radio" name="Question3" value="12" />12<br>
                   <input type="radio" name="Question3" value="36" />36<br>
                   <input type="radio" name="Question3" value="18" />18<br>
                   <input type="radio" name="Question3" value="62" />62
                   <hr>
                   <div>QUESTION4: What is 36/4?</div>
                   <input type="radio" name="Question4" value="36" />36<br>
                   <input type="radio" name="Question4" value="6" />6<br>
                   <input type="radio" name="Question4" value="0" />0<br>
                   <input type="radio" name="Question4" value="9" />9
                   <hr>
                   <div>QUESTION5: What is 56-6?</div>
                   <input type="radio" name="Question5" value="50" />50<br>
                   <input type="radio" name="Question5" value="56" />56<br>
                   <input type="radio" name="Question5" value="0" />0<br>
                   <input type="radio" name="Question5" value="-1" />-1
                   <hr>
                   <input type="submit" value="Submit Answer" />
                </form>
    </body>
</html>
