<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" charset="utf-8">
	$.messager.progress({
		text : '数据加载中....',
		interval : 100
	});
</script>
<div style="padding: 5px;">
	<form id="userForm" method="post">
		<input name="cid" type="hidden" />
		<table class="tableForm">
			<tr>
				<th style="width: 55px;">登录名</th>
				<td><input name="cname" class="easyui-validatebox" data-options="required:'true',missingMessage:'请填写登录名称'" style="width: 150px;" />
				</td>
				<th style="width: 55px;">所属角色</th>
				<td><input name="roleIds" style="width: 150px;" /></td>
			</tr>
			<tr>
				<th>密码</th>
				<td><input name="cpwd" type="password" />
				</td>
				<td colspan="2" style="text-align: left;">如果不修改请留空</td>
			</tr>
			<tr>
				<th>创建时间</th>
				<td><input name="ccreatedatetime" class="easyui-datetimebox" data-options="required:'true',editable:false" style="width: 155px;" />
				</td>
				<th>修改时间</th>
				<td><input name="cmodifydatetime" class="easyui-datetimebox" data-options="required:'true',editable:false" style="width: 150px;" />
				</td>
			</tr>
		</table>
	</form>
</div>