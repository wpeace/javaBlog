<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>动作指令：</title>
</head>
<body>
<%--转发 jsp:foward
    参数 jsp:param

<jsp:forward page="/action2.jsp">
	<jsp:param value="peace" name="name"/>
	<jsp:param value="123456" name="pass"/>
</jsp:forward>
--%>
<%--动态包括 --%>
动态包括：
<jsp:include page="/common/header1.jsp">
     <jsp:param value="lucc" name="name"/>
</jsp:include>
<%--useBean setProperty getProperty --%>
<%--创建Student的实例 
实例名称为student
属性范围为page
--%>
<hr/>
<jsp:useBean id="student" class="com.rlovep.entity.Student" scope="page"/>
<%--设置student的name值 --%>
<jsp:setProperty name="student" property="name" value="peace"/>
<%--输出 student的name值--%>
name:<jsp:getProperty name="student" property="name" />
</body>
</html>