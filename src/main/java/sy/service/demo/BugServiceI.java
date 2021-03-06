package sy.service.demo;

import sy.pageModel.base.DataGrid;
import sy.pageModel.demo.Bug;

public interface BugServiceI {

	/**
	 * 获得数据表格
	 * 
	 * @param bug
	 * @return
	 */
	public DataGrid datagrid(Bug bug);

	/**
	 * 删除
	 * 
	 * @param ids
	 */
	public void delete(String ids);

	/**
	 * 添加
	 * 
	 * @param bug
	 */
	public void add(Bug bug);

	public String getDescById(String cid);

	public void update(Bug bug);
}
