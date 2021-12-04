package sy.service.demo;

import java.util.List;

import sy.pageModel.base.DataGrid;
import sy.pageModel.demo.User;

public interface UserServiceI {

	public User login(User user);

	public void save(User user);

	public DataGrid datagrid(User user);

	public void delete(String ids);

	public void update(User user);

	public void roleEdit(User user);

	public void editUserInfo(User user);

	public List<User> combobox(User user);

}
