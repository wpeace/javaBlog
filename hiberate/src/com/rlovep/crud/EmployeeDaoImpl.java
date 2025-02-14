package com.rlovep.crud;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.From;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.rlovep.Utils.HibernateUtil;
import com.rlovep.hello.Employee;

public class EmployeeDaoImpl implements IEmployeeDao{

	@Override
	public void save(Employee emp) {
		Session session=null;
		Transaction tx=null;
		try {
			session = HibernateUtil.getsession();
			tx = session.beginTransaction();
			session.save(emp);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			tx.commit();
			session.close();
			
		}	
	}

	@Override
	public void update(Employee emp) {
		Session session=null;
		Transaction tx=null;
		try {
			session = HibernateUtil.getsession();
			tx = session.beginTransaction();
			session.saveOrUpdate(emp);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			tx.commit();
			session.close();
			
		}	
		
	}

	@Override
	public Employee findById(Serializable id) {
		Session session=null;
		Transaction tx=null;
		try {
			session = HibernateUtil.getsession();
			tx = session.beginTransaction();
			//主键查询
			return (Employee)session.get(Employee.class, id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			tx.commit();
			session.close();
			
		}	
	}

	@Override
	public List<Employee> getAll() {
		Session session=null;
		Transaction tx=null;
		try {
			session = HibernateUtil.getsession();
			tx = session.beginTransaction();
			Query query = session.createQuery("From Employee");
			return query.list();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			tx.commit();
			session.close();
			
		}	
	}

	@Override
	public List<Employee> getAll(String employeeName) {
		Session session=null;
		Transaction tx=null;
		try {
			session = HibernateUtil.getsession();
			tx = session.beginTransaction();
			Query query = session.createQuery("From Employee where empName=?");
			//参数索引从0开始
			query.setParameter(0, employeeName);
			return query.list();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			tx.commit();
			session.close();
			
		}	
	}

	@Override
	public List<Employee> getAll(int index, int count) {
		Session session=null;
		Transaction tx=null;
		try {
			session = HibernateUtil.getsession();
			tx = session.beginTransaction();
			Query query = session.createQuery("From Employee ");
			//设置分页参数
			query.setFirstResult(index);//设置起始行
			query.setMaxResults(count);//设置行数
			
			return query.list();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			tx.commit();
			session.close();
			
		}	
	}

	@Override
	public void delete(Serializable id) {
		Session session=null;
		Transaction tx=null;
		try {
			session = HibernateUtil.getsession();
			tx = session.beginTransaction();
			Object object = session.get(Employee.class, id);
			if(object!=null){
				session.delete(object);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			tx.commit();
			session.close();
			
		}	
	}

}
