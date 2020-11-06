package com.opkcloud.user.service.impl;

import java.io.Serializable;
import java.util.List;

import com.opkcloud.user.dao.UserMapper;
import com.opkcloud.user.bean.User;
import com.opkcloud.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 
* Title: UserServiceImpl
* Description: 
* 用户操作实现类 
* Version:1.0.0  
* @author opkcloud
* @date 2018年3月19日
 */
@Service
public class UserServiceImpl implements UserService {

	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Resource
	private RedisTemplate<Serializable, Serializable> redisTemplate;

	@Autowired
    private UserMapper userMapper;
	
	@Override
	public boolean addUser(User user) {
		boolean flag=false;
		try{
			userMapper.addUser(user);
			flag=true;
		}catch(Exception e){
			System.out.println("新增失败!");
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean updateUser(User user) {
		boolean flag=false;
		try{
			userMapper.updateUser(user);
			flag=true;
		}catch(Exception e){
			System.out.println("修改失败!");
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean deleteUser(int id) {
		boolean flag=false;
		try{
			userMapper.deleteUser(id);
			flag=true;
		}catch(Exception e){
			System.out.println("删除失败!");
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	@Async("asyncServiceExecutor")
	public User findUserByName(String userName) {
		logger.info("线程执行");
		return userMapper.findByName(userName);
	}

	@Override
	public List<User> findAll() {
		return userMapper.findAll();
//		RedisDistributionLock lock = new RedisDistributionLock(redisTemplate);
//		Long lockTime = 0L;
//		String name = RedisLockEnum.findAll.getName();
//		try {
//			lockTime = lock.lock(RedisLockEnum.findAll.getName(), name);
//			if (lockTime == null) {
//				logger.info(Thread.currentThread().getName() + "111111");
//				return null;
//			}
//			return userMapper.findAll();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (lockTime != null && lockTime != 0L) {
//				lock.unLock(RedisLockEnum.findAll.getName(), lockTime, name);
//				logger.info("222222");
//			}
//		}
//		return null;
	}
}
