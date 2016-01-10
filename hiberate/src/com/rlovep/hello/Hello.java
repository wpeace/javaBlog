package com.rlovep.hello;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class Hello {
	/*
	 * drop table employee;
       create table employee(
           empId int auto_increment not null primary key,
           empName varchar(20),
           workDate datetime
        );
	 */
	public static void main(String[] args) {
      //创建对象
	  Employee emp=new Employee();
	  emp.setEmpId(10);
      emp.setEmpName("peace6");
      emp.setWorkDate(new Date());
   // 获取加载配置文件的管理类对象
   		Configuration config = new Configuration();
   		config.configure();  // 默认加载src/hibenrate.cfg.xml文件
   		ServiceRegistry serviceRegistry =new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
   		// 创建session的工厂对象
   		SessionFactory sf = config.buildSessionFactory(serviceRegistry);
   		// 创建session (代表一个会话，与数据库连接的会话)
   		Session session = sf.openSession();
   		// 开启事务
   		Transaction tx = session.beginTransaction();
   		//保存-数据库
   		session.save(emp);
   	
   		// 提交事务
   		tx.commit();
   		// 关闭
   		session.close();
   		sf.close();
	}
}
