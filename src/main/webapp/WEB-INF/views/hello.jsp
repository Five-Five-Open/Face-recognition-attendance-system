<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.test.form.Test" %>
<%@ page import="java.util.Iterator" %>
<html>
<body>

<%
    List list=new ArrayList();
    list=(List)request.getAttribute("now");

    if(list!=null){
        Iterator<Test>  it = list.iterator();

        while (it.hasNext()) {
            Test student = (Test) it.next();
%>

<%=student.getName()%>
<%=student.getAge()%>
<%
    }
    }
%>

</body>
</html>