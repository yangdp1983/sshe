package sy.service.demo;

import java.util.List;

import sy.pageModel.base.DataGrid;
import sy.pageModel.demo.Role;

public interface RoleServiceI {

	public DataGrid datagrid(Role role);

	public void add(Role role);

	public void edit(Role role);

	public void delete(String ids);

	public List<Role> combobox();

}
