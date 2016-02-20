<%--
  Created by IntelliJ IDEA.
  User: condor
  Date: 25/04/15
  Time: 09:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>



<%


    String s = (String)session.getAttribute("cucu");
    out.print(s);
    if(s!=null)
    {
        out.print("</br><b>cucu bau</b>");
        out.println(System.currentTimeMillis());
    }
    else
    {
        out.print("</br><b>nu esti logate nene</b>");

    }
%>

</body>
</html>
