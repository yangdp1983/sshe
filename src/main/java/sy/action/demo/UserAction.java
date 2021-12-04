package sy.action.demo;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import sy.action.base.BaseAction;
import sy.pageModel.base.Json;
import sy.pageModel.base.SessionInfo;
import sy.pageModel.demo.User;
import sy.service.demo.UserServiceI;
import sy.util.base.ExceptionUtil;
import sy.util.base.IpUtil;
import sy.util.base.ResourceUtil;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 用户ACTION
 * 
 * @author 孙宇
 * 
 */
@Namespace("/demo")
@Action(value = "userAction", results = { @Result(name = "user", location = "/demo/admin/user.jsp"), @Result(name = "userAdd", location = "/demo/admin/userAdd.jsp"), @Result(name = "userEdit", location = "/demo/admin/userEdit.jsp"), @Result(name = "userRoleEdit", location = "/demo/admin/userRoleEdit.jsp"), @Result(name = "doNotNeedAuth_userInfo", location = "/demo/user/userInfo.jsp") })
public class UserAction extends BaseAction implements ModelDriven<User> {

	private static final Logger logger = Logger.getLogger(UserAction.class);

	private UserServiceI demoUserService;

	public UserServiceI getDemoUserService() {
		return demoUserService;
	}

	@Autowired
	public void setDemoUserService(UserServiceI demoUserService) {
		this.demoUserService = demoUserService;
	}

	private User user = new User();

	public User getModel() {
		return user;
	}

	public String user() {
		return "user";
	}

	public String userAdd() {
		return "userAdd";
	}

	public String userEdit() {
		return "userEdit";
	}

	public String userRoleEdit() {
		return "userRoleEdit";
	}

	public String doNotNeedAuth_userInfo() {
		return "doNotNeedAuth_userInfo";
	}

	public void doNotNeedAuth_editUserInfo() {
		Json j = new Json();
		try {
			demoUserService.editUserInfo(user);
			j.setSuccess(true);
			j.setMsg("修改成功！");
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("修改失败！");
		}
		super.writeJson(j);
	}

	public void doNotNeedSession_login() {
		Json j = new Json();
		User u = demoUserService.login(user);
		if (u != null) {
			SessionInfo sessionInfo = new SessionInfo();
			sessionInfo.setUserId(u.getCid());
			sessionInfo.setLoginName(user.getCname());
			sessionInfo.setLoginPassword(user.getCpwd());
			sessionInfo.setIp(IpUtil.getIpAddr(ServletActionContext.getRequest()));
			ServletActionContext.getRequest().getSession().setAttribute(ResourceUtil.getSessionInfoName(), sessionInfo);
			sessionInfo.setAuthIds(u.getAuthIds());
			sessionInfo.setAuthNames(u.getAuthNames());
			sessionInfo.setRoleIds(u.getRoleIds());
			sessionInfo.setRoleNames(u.getRoleNames());
			sessionInfo.setAuthUrls(u.getAuthUrls());

			j.setObj(sessionInfo);
			j.setMsg("登录成功！");
			j.setSuccess(true);
		} else {
			j.setMsg("登录失败！用户名或密码错误！");
		}
		super.writeJson(j);
	}

	public void doNotNeedSession_logout() {
		ServletActionContext.getRequest().getSession().invalidate();
		Json j = new Json();
		j.setSuccess(true);
		super.writeJson(j);
	}

	public void doNotNeedSession_reg() {
		Json j = new Json();
		try {
			demoUserService.save(user);
			j.setMsg("注册成功！");
			j.setSuccess(true);
		} catch (Exception e) {
			j.setMsg("注册失败，用户名已存在！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(j);
	}

	public void add() {
		Json j = new Json();
		try {
			demoUserService.save(user);
			j.setMsg("添加成功！");
			j.setSuccess(true);
		} catch (Exception e) {
			j.setMsg("添加失败，用户名已存在！");
			ExceptionUtil.getExceptionMessage(e);
		}
		super.writeJson(j);
	}

	public void edit() {
		Json j = new Json();
		try {
			demoUserService.update(user);
			j.setSuccess(true);
			j.setMsg("编辑成功！");
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("编辑失败，用户名已存在！");
		}
		super.writeJson(j);
	}

	public void roleEdit() {
		Json j = new Json();
		try {
			demoUserService.roleEdit(user);
			j.setSuccess(true);
			j.setMsg("编辑成功！");
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("编辑失败！");
		}
		super.writeJson(j);
	}

	public void delete() {
		Json j = new Json();
		demoUserService.delete(user.getIds());
		j.setSuccess(true);
		j.setMsg("删除成功!");
		super.writeJson(j);
	}

	public void datagrid() {
		super.writeJson(demoUserService.datagrid(user));
	}

	public void doNotNeedSession_combobox() {
		super.writeJson(demoUserService.combobox(user));
	}

	public void doNotNeedSession_datagrid() {
		if (user.getQ() != null && !user.getQ().trim().equals("")) {
			user.setCname(user.getQ());
		}
		super.writeJson(demoUserService.datagrid(user));
	}

}
