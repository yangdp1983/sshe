package sy.service.demo;

import sy.pageModel.base.DataGrid;
import sy.pageModel.demo.Online;

public interface OnlineServiceI {

	public void saveOrUpdateTonlineByLoginNameAndIp(String loginName, String ip);

	public void deleteTonlineByLoginNameAndIp(String loginName, String ip);

	public DataGrid datagrid(Online online);

}
