<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>百万富翁竞猜游戏</title>
</head>
<body>
    <%
      //从request中获得属性
      String msg=(String)request.getAttribute("msg");
    if(msg!=null)
    	out.write("<font color='red'>"+msg+"</font>");
    %>
    <%
      //获取竞猜次数
      	Integer times = (Integer)request.getAttribute("times");
  	 	if(times!=null){
  	 		out.write(",你还有"+(5-times)+"次机会！");
  	 	}
    %>
     <form action="/stuJsp/GuessServlet" method="post">
    	请输入30以下的整数：<input type="text" name="lucyNo"/><br/>
    	<%
    		if(times!=null){
    	 %>
    	<input type="hidden" name="times" value="<%=times %>"/>
    	<%
    	}
    	 %>
    	<input type="submit" value="开始竞猜"/>
    	</form>
</body>
</html>