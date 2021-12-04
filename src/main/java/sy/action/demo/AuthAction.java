package sy.action.demo;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import sy.action.base.BaseAction;
import sy.pageModel.base.Json;
import sy.pageModel.demo.Auth;
import sy.service.demo.AuthServiceI;
import sy.util.base.ExceptionUtil;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 权限ACTION
 * 
 * @author 孙宇
 * 
 */
@Namespace("/demo")
@Action(value = "authAction", results = { @Result(name = "auth", location = "/demo/admin/auth.jsp"), @Result(name = "authAdd", location = "/demo/admin/authAdd.jsp"), @Result(name = "authEdit", location = "/demo/admin/authEdit.jsp") })
public class AuthAction extends BaseAction implements ModelDriven<Auth> {

	private static final Logger logger = Logger.getLogger(AuthAction.class);

	private Auth auth = new Auth();
	private AuthServiceI demoAuthService;

	public AuthServiceI getDemoAuthService() {
		return demoAuthService;
	}

	@Autowired
	public void setDemoAuthService(AuthServiceI demoAuthService) {
		this.demoAuthService = demoAuthService;
	}

	public Auth getModel() {
		return auth;
	}

	public String auth() {
		return "auth";
	}

	public String authAdd() {
		return "authAdd";
	}

	public String authEdit() {
		return "authEdit";
	}

	/**
	 * 获得权限树
	 */
	public void doNotNeedSession_tree() {
		super.writeJson(demoAuthService.tree(auth, false));
	}

	public void doNotNeedSession_treeRecursive() {
		super.writeJson(demoAuthService.tree(auth, true));
	}

	/**
	 * 获得权限treegrid
	 */
	public void treegrid() {
		super.writeJson(demoAuthService.treegrid(auth));
	}

	/**
	 * 删除一个权限
	 */
	public void delete() {
		Json j = new Json();
		try {
			demoAuthService.delete(auth);
			j.setSuccess(true);
			j.setMsg("删除成功！");
			j.setObj(auth.getCid());
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("删除失败！");
		}
		super.writeJson(j);
	}

	/**
	 * 编辑权限
	 */
	public void edit() {
		Json j = new Json();
		try {
			demoAuthService.edit(auth);
			j.setSuccess(true);
			j.setMsg("编辑成功!");
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("编辑失败！");
		}
		super.writeJson(j);
	}

	/**
	 * 添加权限
	 */
	public void add() {
		Json j = new Json();
		try {
			demoAuthService.add(auth);
			j.setSuccess(true);
			j.setMsg("添加成功!");
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("添加失败！");
		}
		super.writeJson(j);
	}

}
