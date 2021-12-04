package sy.action.demo;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import sy.action.base.BaseAction;
import sy.pageModel.demo.Online;
import sy.service.demo.OnlineServiceI;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 在线列表ACTION
 * 
 * @author 孙宇
 * 
 */
@Namespace("/demo")
@Action(value = "onlineAction")
public class OnlineAction extends BaseAction implements ModelDriven<Online> {

	private Online online = new Online();

	public Online getModel() {
		return online;
	}

	private OnlineServiceI demoOnlineService;

	public OnlineServiceI getDemoOnlineService() {
		return demoOnlineService;
	}

	@Autowired
	public void setDemoOnlineService(OnlineServiceI demoOnlineService) {
		this.demoOnlineService = demoOnlineService;
	}

	public void doNotNeedSession_onlineDatagrid() {
		super.writeJson(demoOnlineService.datagrid(online));
	}

}
