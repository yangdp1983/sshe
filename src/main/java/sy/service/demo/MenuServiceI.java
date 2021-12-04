package sy.service.demo;

import java.util.List;

import sy.pageModel.base.TreeNode;
import sy.pageModel.demo.Menu;

public interface MenuServiceI {

	/**
	 * 获取菜单树
	 * 
	 * @param auth
	 * @param b
	 *            是否递归子节点
	 * @return
	 */
	public List<TreeNode> tree(Menu menu, Boolean b);

	/**
	 * 获得菜单treegrid
	 * 
	 * @param menu
	 * @return
	 */
	public List<Menu> treegrid(Menu menu);

	/**
	 * 删除菜单
	 * 
	 * @param menu
	 */
	public void delete(Menu menu);

	/**
	 * 添加菜单
	 * 
	 * @param menu
	 */
	public void add(Menu menu);

	public void edit(Menu menu);

}
