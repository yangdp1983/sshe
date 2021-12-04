package sy.service.demo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sy.dao.base.BaseDaoI;
import sy.model.demo.Tbug;
import sy.pageModel.base.DataGrid;
import sy.pageModel.demo.Bug;
import sy.service.demo.BugServiceI;
import sy.util.base.ClobUtil;

@Service("demoBugService")
public class BugServiceImpl implements BugServiceI {

	private BaseDaoI<Tbug> bugDao;

	public BaseDaoI<Tbug> getBugDao() {
		return bugDao;
	}

	@Autowired
	public void setBugDao(BaseDaoI<Tbug> bugDao) {
		this.bugDao = bugDao;
	}

	public DataGrid datagrid(Bug bug) {
		DataGrid j = new DataGrid();
		j.setRows(changeModel(find(bug)));
		j.setTotal(total(bug));
		return j;
	}

	private List<Bug> changeModel(List<Tbug> tbugs) {
		List<Bug> bugs = new ArrayList<Bug>();
		if (tbugs != null && tbugs.size() > 0) {
			for (Tbug tb : tbugs) {
				Bug b = new Bug();
				BeanUtils.copyProperties(tb, b);
				bugs.add(b);
			}
		}
		return bugs;
	}

	private List<Tbug> find(Bug bug) {
		String hql = "select new Tbug(t.cid,t.cname,t.ccreatedatetime) from Tbug t where 1=1 ";

		List<Object> values = new ArrayList<Object>();
		hql = addWhere(bug, hql, values);

		if (bug.getSort() != null && bug.getOrder() != null) {
			hql += " order by " + bug.getSort() + " " + bug.getOrder();
		}
		return bugDao.find(hql, values, bug.getPage(), bug.getRows());
	}

	private Long total(Bug bug) {
		String hql = "select count(*) from Tbug t where 1=1 ";
		List<Object> values = new ArrayList<Object>();
		hql = addWhere(bug, hql, values);
		return bugDao.count(hql, values);
	}

	private String addWhere(Bug bug, String hql, List<Object> values) {
		return hql;
	}

	public void delete(String ids) {
		if (ids != null) {
			for (String id : ids.split(",")) {
				Tbug t = bugDao.get(Tbug.class, id);
				if (t != null) {
					bugDao.delete(t);
				}
			}
		}
	}

	public void add(Bug bug) {
		if (bug.getCcreatedatetime() == null) {
			bug.setCcreatedatetime(new Date());
		}
		Tbug t = new Tbug();
		BeanUtils.copyProperties(bug, t);
		t.setCid(UUID.randomUUID().toString());
		bugDao.save(t);
	}

	public String getDescById(String cid) {
		Tbug t = bugDao.get(Tbug.class, cid);
		return ClobUtil.getString(t.getCdesc());
	}

	public void update(Bug bug) {
		Tbug t = bugDao.get(Tbug.class, bug.getCid());
		if (t != null) {
			t.setCdesc(bug.getCdesc());
			t.setCname(bug.getCname());
			t.setCcreatedatetime(new Date());
		}
	}

}
