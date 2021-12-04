<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="padding: 5px;overflow: hidden;">
	<form id="userForm" method="post">
		<table class="tableForm">
			<tr>
				<th style="width: 55px;">登录名</th>
				<td><input name="cname" class="easyui-validatebox" data-options="required:'true',missingMessage:'请填写登录名称'" />
				</td>
				<th style="width: 55px;">所属角色</th>
				<td><input name="roleIds" style="width: 145px;" />
				</td>
			</tr>
			<tr>
				<th>密码</th>
				<td><input name="cpwd" type="password" class="easyui-validatebox" data-options="required:'true',missingMessage:'请填写登录密码'" />
				</td>
				<th>重复密码</th>
				<td><input type="password" class="easyui-validatebox" data-options="required:'true',missingMessage:'请再次填写密码',validType:'eqPassword[\'#userForm input[name=cpwd]\']'" />
				</td>
			</tr>
		</table>
	</form>
</div>