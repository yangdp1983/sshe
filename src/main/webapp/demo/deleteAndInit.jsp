<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>SSHE初始化数据库</title>
</head>
<body>
	init database....
	<script type="text/javascript">
		window.setTimeout(function() {
			window.location.replace('${pageContext.request.contextPath}/demo/repairAction!doNotNeedSession_deleteAndRepair.action');
		}, 1000);
	</script>
</body>
</html>
