<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" charset="utf-8">
	var loginDialog;
	var loginTabs;
	var loginInputForm;
	var loginDatagridForm;
	var loginComboboxForm;
	var loginDatagridName;
	var loginComboboxName;
	var sessionInfo;
	$(function() {

		var formParam = {
			url : '${pageContext.request.contextPath}/demo/userAction!doNotNeedSession_login.action',
			success : function(data) {
				var d = $.parseJSON(data);
				if (d.success) {
					loginDialog.dialog('close');

					$('#sessionInfoDiv').html(sy.fs('[<strong>{0}</strong>]，欢迎你！您使用[<strong>{1}</strong>]IP登录！', d.obj.loginName, d.obj.ip));

					flashOnlineDatagrid();
				}
				$.messager.show({
					msg : d.msg,
					title : '提示'
				});
			}
		};

		loginInputForm = $('#loginInputForm').form(formParam);
		loginDatagridForm = $('#loginDatagridForm').form(formParam);
		loginComboboxForm = $('#loginComboboxForm').form(formParam);

		loginDialog = $('#loginDialog').show().dialog({
			closable : false,
			title : '登录',
			modal : true,
			buttons : [ {
				text : '注册',
				handler : function() {
					regDialog.dialog('open');
				}
			}, {
				text : '登录',
				handler : function() {
					if ($('#onlineDatagrid').length > 0) {
						var f = loginTabs.tabs('getSelected').find('form');
						f.submit();
					}
				}
			} ],
			onOpen : function() {
				var t = $(this);
				window.setTimeout(function() {
					t.find('input[name=cname]').focus();
				}, 0);
			}
		});

		loginTabs = $('#loginTabs').tabs({
			onSelect : function(title) {
				if ('输入方式' == title) {
					window.setTimeout(function() {
						$('div.validatebox-tip').remove();
						loginInputForm.find('input[name=cname]').focus();
					}, 0);
				} else if ('表格方式' == title) {
					window.setTimeout(function() {
						if (loginDatagridName.combogrid('options').url == '') {
							loginDatagridName.combogrid({
								url : '${pageContext.request.contextPath}/demo/userAction!doNotNeedSession_datagrid.action'
							});
						}
						$('div.validatebox-tip').remove();
						loginDatagridName.combogrid('textbox').focus();
					}, 0);
				} else if ('补全方式' == title) {
					window.setTimeout(function() {
						if (loginComboboxName.combobox('options').url == '') {
							loginComboboxName.combobox({
								url : '${pageContext.request.contextPath}/demo/userAction!doNotNeedSession_combobox.action'
							});
						}
						$('div.validatebox-tip').remove();
						loginComboboxName.combobox('textbox').focus();
					}, 0);
				}
			}
		});

		loginDatagridName = $('#loginDatagridName').combogrid({
			required : true,
			loadMsg : '数据加载中....',
			panelWidth : 440,
			panelHeight : 180,
			required : true,
			fitColumns : true,
			value : '',
			idField : 'cname',
			textField : 'cname',
			mode : 'remote',
			url : '',
			pagination : true,
			pageSize : 5,
			pageList : [ 5, 10 ],
			columns : [ [ {
				field : 'cid',
				title : '编号',
				width : 60,
				hidden : true
			}, {
				field : 'cname',
				title : '登录名',
				width : 100,
				sortable : true
			}, {
				field : 'ccreatedatetime',
				title : '创建时间',
				width : 150,
				sortable : true
			}, {
				field : 'cmodifydatetime',
				title : '最后修改时间',
				width : 150,
				sortable : true
			} ] ],
			delay : 500
		});

		loginComboboxName = $('#loginComboboxName').combobox({
			required : true,
			url : '',
			textField : 'cname',
			valueField : 'cname',
			mode : 'remote',
			panelHeight : 'auto',
			delay : 500,
			value : ''
		});

		$('form input').bind('keyup', function(event) {/* 增加回车提交功能 */
			if (event.keyCode == '13') {
				$(this).submit();
			}
		});

		sessionInfo = {
			userId : '${sessionInfo.userId}',
			loginName : '${sessionInfo.loginName}',
			ip : '${sessionInfo.ip}'
		};
		if (sessionInfo.userId) {/*如果有userId说明已经登陆过了*/
			window.setTimeout(function() {
				$('div.validatebox-tip').remove();
			}, 500);
			loginDialog.dialog('close');
			flashOnlineDatagrid();
		}

	});

	function flashOnlineDatagrid() {/*刷新在线列表*/
		window.setTimeout(function() {
			if ($('#onlineDatagrid').length > 0) {
				onlineDatagrid.datagrid({
					pagination : true,
					url : '${pageContext.request.contextPath}/demo/onlineAction!doNotNeedSession_onlineDatagrid.action'
				});
				var p = onlineDatagrid.datagrid('getPager');
				p.pagination({
					showPageList : false,
					showRefresh : false,
					beforePageText : '',
					afterPageText : '/{pages}',
					displayMsg : ''
				});
			}
		}, 3000);
	}
</script>
<div id="loginDialog" style="width:250px;height:210px;display: none;overflow: hidden;">
	<div id="loginTabs" data-options="fit:true,border:false">
		<div title="输入方式" style="overflow: hidden;">
			<div class="info">
				<div class="tip icon-tip"></div>
				<div>用户名和密码都是admin</div>
			</div>
			<div align="center">
				<form id="loginInputForm" method="post">
					<table>
						<tr>
							<th style="text-align: right;">登录名</th>
							<td><input name="cname" class="easyui-validatebox" data-options="required:true,missingMessage:'请填写登录名称'" value="admin" /></td>
						</tr>
						<tr>
							<th style="text-align: right;">密码</th>
							<td><input name="cpwd" type="password" class="easyui-validatebox" data-options="required:true,missingMessage:'请填写登录密码'" value="admin" style="width: 150px;" /></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div title="表格方式" style="overflow: hidden;">
			<div class="info">
				<div class="tip icon-tip"></div>
				<div>可模糊检索用户名</div>
			</div>
			<div align="center">
				<form id="loginDatagridForm" method="post">
					<table>
						<tr>
							<th style="text-align: right;">登录名</th>
							<td><select id="loginDatagridName" name="cname" style="display: none;width: 155px;"></select></td>
						</tr>
						<tr>
							<th style="text-align: right;">密码</th>
							<td><input name="cpwd" type="password" class="easyui-validatebox" data-options="required:true,missingMessage:'请填写登录密码'" style="width: 150px;" /></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div title="补全方式" style="overflow: hidden;">
			<div class="info">
				<div class="tip icon-tip"></div>
				<div>可自动补全用户名</div>
			</div>
			<div align="center">
				<form id="loginComboboxForm" method="post">
					<table>
						<tr>
							<th style="text-align: right;">登录名</th>
							<td><select id="loginComboboxName" name="cname" style="display: none;width: 155px;"></select>
							</td>
						</tr>
						<tr>
							<th style="text-align: right;">密码</th>
							<td><input name="cpwd" type="password" class="easyui-validatebox" data-options="required:true,missingMessage:'请填写登录密码'" style="width: 150px;" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</div>