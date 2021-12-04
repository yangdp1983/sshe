package sy.action.demo;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import sy.action.base.BaseAction;
import sy.pageModel.base.Json;
import sy.pageModel.demo.Role;
import sy.service.demo.RoleServiceI;
import sy.util.base.ExceptionUtil;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 角色ACTION
 * 
 * @author 孙宇
 * 
 */
@Namespace("/demo")
@Action(value = "roleAction", results = { @Result(name = "role", location = "/demo/admin/role.jsp"), @Result(name = "roleAdd", location = "/demo/admin/roleAdd.jsp"), @Result(name = "roleEdit", location = "/demo/admin/roleEdit.jsp") })
public class RoleAction extends BaseAction implements ModelDriven<Role> {

	private static final Logger logger = Logger.getLogger(RoleAction.class);

	private Role role = new Role();
	private RoleServiceI demoRoleService;

	public RoleServiceI getDemoRoleService() {
		return demoRoleService;
	}

	@Autowired
	public void setDemoRoleService(RoleServiceI demoRoleService) {
		this.demoRoleService = demoRoleService;
	}

	public Role getModel() {
		return role;
	}

	/**
	 * 跳转到角色管理页面
	 * 
	 * @return
	 */
	public String role() {
		return "role";
	}

	public String roleAdd() {
		return "roleAdd";
	}

	public String roleEdit() {
		return "roleEdit";
	}

	/**
	 * 获得角色datagrid
	 */
	public void datagrid() {
		super.writeJson(demoRoleService.datagrid(role));
	}

	/**
	 * 添加一个角色
	 */
	public void add() {
		Json j = new Json();
		try {
			demoRoleService.add(role);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		} catch (Exception e) {
			j.setMsg("添加失败！");
			logger.error(ExceptionUtil.getExceptionMessage(e));
		}
		super.writeJson(j);
	}

	/**
	 * 编辑一个角色
	 */
	public void edit() {
		Json j = new Json();
		try {
			demoRoleService.edit(role);
			j.setSuccess(true);
			j.setMsg("编辑成功！");
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("编辑失败！");
		}
		super.writeJson(j);
	}

	/**
	 * 删除一个或多个角色
	 */
	public void delete() {
		Json j = new Json();
		demoRoleService.delete(role.getIds());
		j.setSuccess(true);
		j.setMsg("删除成功！");
		super.writeJson(j);
	}

	/**
	 * 获得角色下拉列表
	 */
	public void doNotNeedSession_combobox() {
		super.writeJson(demoRoleService.combobox());
	}

}
