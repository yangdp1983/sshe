package sy.service.demo.impl;

import java.util.Date;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sy.dao.base.BaseDaoI;
import sy.model.demo.Tloguserlogin;
import sy.model.demo.Tloguserreg;
import sy.pageModel.demo.User;
import sy.service.demo.LogUserServiceI;

@Service("demoLogUserService")
public class LogUserServiceImpl implements LogUserServiceI {

	private static final Logger logger = Logger.getLogger(LogUserServiceImpl.class);

	private BaseDaoI<Tloguserlogin> logUserLoginDao;
	private BaseDaoI<Tloguserreg> logUserRegDao;

	public BaseDaoI<Tloguserlogin> getLogUserLoginDao() {
		return logUserLoginDao;
	}

	@Autowired
	public void setLogUserLoginDao(BaseDaoI<Tloguserlogin> logUserLoginDao) {
		this.logUserLoginDao = logUserLoginDao;
	}

	public BaseDaoI<Tloguserreg> getLogUserRegDao() {
		return logUserRegDao;
	}

	@Autowired
	public void setLogUserRegDao(BaseDaoI<Tloguserreg> logUserRegDao) {
		this.logUserRegDao = logUserRegDao;
	}

	public void afterLog(JoinPoint point) {
		logger.info(new Date() + "进入after");
		logger.info(point.getSignature().getName());
		Object[] args = point.getArgs();
		if (args != null && args.length > 0) {
			for (Object obj : args) {
				logger.info(obj);
			}
		}
	}

	public void beforeLog(JoinPoint point) {
		logger.info(new Date() + "进入before");
		logger.info(point.getSignature().getName());
		Object[] args = point.getArgs();
		if (args != null && args.length > 0) {
			for (Object obj : args) {
				logger.info(obj);
			}
		}
	}

	public Object aroundLog(ProceedingJoinPoint point) {

		Object o = null;

		String methodName = point.getSignature().getName();// 获得方法名称

		User user = null;

		Object[] args = point.getArgs();
		if (args != null && args.length > 0) {
			for (Object obj : args) {
				if (obj instanceof User) {
					user = (User) obj;// 获得参数

				}
			}
		}

		if ("login".equals(methodName)) {
			Tloguserlogin t = new Tloguserlogin();
			t.setCdatetime(new Date());
			t.setCid(UUID.randomUUID().toString());
			t.setCname(user.getCname());
			try {
				o = point.proceed();// 继续
				t.setCmsg("登录成功！");
			} catch (Throwable e) {
				t.setCmsg("登录失败！");
			}
			logUserLoginDao.save(t);
		}

		if ("save".equals(methodName)) {
			Tloguserreg t = new Tloguserreg();
			t.setCdatetime(new Date());
			t.setCid(UUID.randomUUID().toString());
			t.setCname(user.getCname());
			try {
				o = point.proceed();// 继续
				t.setCmsg("注册成功！");
			} catch (Throwable e) {
				t.setCmsg("注册失败！");
			}
			logUserRegDao.save(t);
		}

		return o;

	}
}
