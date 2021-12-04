<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>SSHE(Struts2.3.x+Spring3.1.x+Hibernate4.1.x+Easyui1.3.x+Maven)v20121015</title>
<jsp:include page="../inc.jsp"></jsp:include>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',href:'${pageContext.request.contextPath}/demo/layout/north.jsp'" style="height: 60px;overflow: hidden;" class="logo"></div>
	<div data-options="region:'west',title:'功能导航',href:'${pageContext.request.contextPath}/demo/layout/west.jsp'" style="width: 200px;overflow: hidden;"></div>
	<div data-options="region:'east',title:'日历',split:true,href:'${pageContext.request.contextPath}/demo/layout/east.jsp'" style="width: 200px;overflow: hidden;"></div>
	<div data-options="region:'center',title:'欢迎使用SSHE示例系统',href:'${pageContext.request.contextPath}/demo/layout/center.jsp'" style="overflow: hidden;"></div>
	<div data-options="region:'south',href:'${pageContext.request.contextPath}/demo/layout/south.jsp'" style="height: 20px;overflow: hidden;"></div>

	<jsp:include page="user/login.jsp"></jsp:include>
	<jsp:include page="user/reg.jsp"></jsp:include>

	<jsp:include page="isIe.jsp"></jsp:include>
</body>
</html>