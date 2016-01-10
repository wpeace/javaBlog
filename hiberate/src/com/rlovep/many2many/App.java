package com.rlovep.many2many;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.Test;

public class App {
	private static SessionFactory sf=null;
	static{
		Configuration config=new Configuration().configure().addClass(Project.class).addClass(Developer.class);
		//创建serviceRegistry  代替buildSessionFactory()方法
   		ServiceRegistry serviceRegistry =new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
   		// 创建session的工厂对象
   		sf = config.buildSessionFactory(serviceRegistry);
	}
	   // 多对多
		//1. 设置inverse属性，对保存数据影响?
		// 有影响。
		// inverse=false ，有控制权，可以维护关联关系； 保存数据的时候会把对象关系插入中间表；
		// inverse=true,  没有控制权， 不会往中间表插入数据。
	@Test
	public void testSave(){
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		//创建项目
		Project project1=new Project();
		Project project2=new Project();
		project1.setName("ptool1");
		project2.setName("ptool2");
		//创建员工对象
		Developer developer1=new Developer();
		Developer developer2=new Developer();
		Developer developer3=new Developer();
		developer1.setName("peace1");
		developer2.setName("peace2");
		developer3.setName("peace3");
		//创建关系
		/*
		 * 工程1：peace1，peace2
		 * 工程2：peace2，peace3
		 * peace1：工程1
		 * peace2：工程1和2
		 * peace3：工程2
		 * 
		 */
		/*project1.getDevelopers().add(developer1);
		project1.getDevelopers().add(developer2);
		project2.getDevelopers().add(developer3);
		project2.getDevelopers().add(developer2);*/
		developer1.getProjects().add(project1);
		developer2.getProjects().add(project1);
		developer2.getProjects().add(project2);
		developer3.getProjects().add(project2);
		session.save(developer3);
		session.save(developer2);
		session.save(developer1);
		
		tx.commit();
		session.close();
	}
	//2 .设置inverse属性， 对获取数据影响？  无
	@Test
	public void testGet(){
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		Project project=(Project)session.get(Project.class, 7);
		System.out.println(project.getName());
		System.out.println(project.getDevelopers());
		tx.commit();
		session.close();
	}
	//3. 设置inverse属性， 对解除关系影响？
		// 有影响。
		// inverse=false ,有控制权， 解除关系就是删除中间表的数据。
		// inverse=true, 不能解除
	@Test
	public void removeRelation() {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		Project project=(Project)session.get(Project.class, 7);
		project.getDevelopers().clear();
		tx.commit();
		session.close();
	}
	//3. 设置inverse属性，对删除数据的影响?
		// inverse=false, 有控制权。 先删除中间表数据，再删除自身。
		// inverse=true, 没有控制权。 如果删除的数据有被引用，会报错！ 否则，才可以删除
		@Test
		public void deleteData() {
			Session session = sf.openSession();
			Transaction tx = session.beginTransaction();
			Project project=(Project)session.get(Project.class, 7);
			session.delete(project);
			tx.commit();
			session.close();
		}
}
