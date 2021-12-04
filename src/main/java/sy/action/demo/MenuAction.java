package sy.action.demo;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import sy.action.base.BaseAction;
import sy.pageModel.base.Json;
import sy.pageModel.demo.Menu;
import sy.service.demo.MenuServiceI;
import sy.util.base.ExceptionUtil;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 菜单ACTION
 * 
 * @author 孙宇
 * 
 */
@Namespace("/demo")
@Action(value = "menuAction", results = { @Result(name = "menu", location = "/demo/admin/menu.jsp"), @Result(name = "menuAdd", location = "/demo/admin/menuAdd.jsp"), @Result(name = "menuEdit", location = "/demo/admin/menuEdit.jsp") })
public class MenuAction extends BaseAction implements ModelDriven<Menu> {

	private static final Logger logger = Logger.getLogger(MenuAction.class);

	private MenuServiceI demoMenuService;

	public MenuServiceI getDemoMenuService() {
		return demoMenuService;
	}

	@Autowired
	public void setDemoMenuService(MenuServiceI demoMenuService) {
		this.demoMenuService = demoMenuService;
	}

	private Menu menu = new Menu();

	public Menu getModel() {
		return menu;
	}

	/**
	 * 首页获得菜单树
	 */
	public void doNotNeedSession_tree() {
		super.writeJson(demoMenuService.tree(menu, false));
	}

	public void doNotNeedSession_treeRecursive() {
		super.writeJson(demoMenuService.tree(menu, true));
	}

	public String menu() {
		return "menu";
	}

	/**
	 * 获得菜单treegrid
	 */
	public void treegrid() {
		super.writeJson(demoMenuService.treegrid(menu));
	}

	/**
	 * 删除一个菜单
	 */
	public void delete() {
		Json j = new Json();
		try {
			demoMenuService.delete(menu);
			j.setSuccess(true);
			j.setMsg("删除成功！");
			j.setObj(menu.getCid());
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("删除失败！");
		}
		super.writeJson(j);
	}

	public String menuAdd() {
		return "menuAdd";
	}

	public void add() {
		Json j = new Json();
		try {
			demoMenuService.add(menu);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("添加失败！");
		}
		super.writeJson(j);
	}

	public String menuEdit() {
		return "menuEdit";
	}

	/**
	 * 编辑菜单
	 */
	public void edit() {
		Json j = new Json();
		try {
			demoMenuService.edit(menu);
			j.setSuccess(true);
			j.setMsg("编辑成功!");
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("编辑失败！");
		}
		writeJson(j);
	}

}
