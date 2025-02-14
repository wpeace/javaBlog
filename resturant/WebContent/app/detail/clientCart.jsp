﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="style/css/index.css" />
	<script type="text/javascript">
	 // 删除菜品项
	function removeSorder(node) {
		var gid = node.lang;
		window.location.href = "${pageContext.request.contextPath }/order?method=removeOrder&gid="+gid;
	}
	
	// 修改菜品项数量
	function alterSorder(node) {
		var snumber = node.value;
		var gid = node.lang;
		window.location.href = "${pageContext.request.contextPath }/order?method=alterSorder&gid="+gid+"&snumber="+snumber;
	}
	
	// 下单
	function genernateOrder() {
		window.location.href = "${pageContext.request.contextPath }/order?method=takeOrder";
	}
	</script>
</head>

<body style="text-align: center">
	<div id="all">
		<div id="menu">
			<!-- 餐车div -->
			<div id="count">
				<table align="center" width="100%">
					<tr height="40">
				 		<td align="center" width="20%">菜名</td>
				 		<td align="center" width="20%">单价</td>
				 		<td align="center" width="20%">数量</td>
				 		<td align="center" width="20%">小计</td>
				 		<td align="center" width="20%">操作</td>
				 	</tr>
				 		<c:forEach items="${sessionScope.foods }" var="entry">
					   <tr height="60">
					      <td align="center" width="20%"> ${entry.key.foodName}</td>   
					      <td align="center" width="20%"> ${entry.key.price}</td>   
					       <td align="center" width="20%">
					 			<input type="text" value="${entry.value}" size="3" lang="${entry.key.id}" onblur="alterSorder(this)"/>
					 		</td>
					 		<td align="center" width="20%">${entry.key.price*entry.value}</td>
					 		<td align="center" width="20%">
					 			<input type="button" value="删除" class="btn_next" lang="${entry.key.id}" onclick="removeSorder(this)" />
					 		</td>
					 		<c:set var="sum" value="${sum+entry.key.price*entry.value}"></c:set>
				 	    </tr>
                       </c:forEach>
					<tr>
						<td colspan="6" align="right">总计:
							<span style="font-size:36px;">&yen;&nbsp;<c:out value="${sum}"></c:out></span>
							<label
								id="counter" style="font-size:36px"></label>
						</td>
					</tr>
					<tr>
						<td colspan="6" style="margin-left: 100px; text-align: center;"align="right">
							<input type="hidden" name="bId" value="">
									<input type="button" value="下单" class="btn_next" onclick="genernateOrder()" />
						</td>
					</tr>
				</table>
			</div>
		</div>

		<!-- 右边菜系列表，菜品搜索框  -->
		<div id="dish_class">
			<div id="dish_top">
						<ul>
				<li class="dish_num"></li>
					<li>
						<a href="${pageContext.request.contextPath }/app/detail/clientCart.jsp">
							<img src="${pageContext.request.contextPath }/app/detail/style/images/call2.gif" />
						</a>
					</li>
				</ul>
			</div>

			<div id="dish_2">
				<ul>
					
						<c:forEach items="${applicationScope.foodtype}" var="type">
						<li>
							<a href="${pageContext.request.contextPath }/index?method=getMenu">${type.typeName}</a>
						</li>
					</c:forEach>
					
				</ul>
			</div>
			<div id="dish_3">
				<!-- 搜索菜品表单  -->
				<form action="${pageContext.request.contextPath }/index" method="post">
					<table width="166px">
						<tr>
							<td>
								<input type="text" id="dish_name" name="keyword" class="select_value" /> 
								<input type="hidden" value="searchFood" name="method">
							</td>
						</tr>
						<tr>
							<td><input type="submit" id="sub" value="" /></td>
						</tr>
						<tr>
							<td>
								<a href="${pageContext.request.contextPath }/index?method=getMenu">
									<img src="${pageContext.request.contextPath }/app/detail/style/images/look.gif" />
								</a>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
