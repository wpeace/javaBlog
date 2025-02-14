package com.rlovep.ognl;

import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

public class OgnlDemo {
     /**
      * 
     * @Title: test1 
     * @Description:测试非根元素的值，必须用#号 
     * @return:void   
     * @throws 
     * @author peace w_peace@163.com
      */
	public static void test1() {
		// 创建一个Ognl上下文对象
		OgnlContext context = new OgnlContext();
		// 放入数据
		User user = new User();
		user.setId(10);
		user.setName("peace");
		// 往非跟元素中放值，取值时需要加上#
		context.put("user", user);
		// 获取刚刚存入的值
		try {
			Object ognl = Ognl.parseExpression("#user.name");
			// 得到获得的值
			System.out.println(Ognl.getValue(ognl, context, context.getRoot()));
		} catch (OgnlException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	* @Title: test2 
	* @Description: 测试根元素的值，不用带#号 
	* @return:void   
	* @throws 
	* @author peace w_peace@163.com
	 */
	public static void test2() {
		// 创建一个Ognl上下文对象
		OgnlContext context = new OgnlContext();
		// 放入数据
		User user = new User();
		user.setId(10);
		user.setName("peace2");
		// 往跟元素中放值，取值时不需要加上#
		context.setRoot(user);
		// 获取刚刚存入的值
		try {
			//直接写属性名即可
			Object ognl = Ognl.parseExpression("name");
			// 得到获得的值
			System.out.println(Ognl.getValue(ognl, context, context.getRoot()));
		} catch (OgnlException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	* @Title: test3 
	* @Description: 根el表达式一样ognl也可以调用静态方法 
	* @return:void   
	* @throws 
	* @author peace w_peace@163.com
	 */
	public static void test3() {
		// 创建一个Ognl上下文对象
		OgnlContext context = new OgnlContext();
		
		try {
			// Ognl表单式语言，调用类的静态方法:格式 @类名@方法调用
			Object ognl = Ognl.parseExpression("@Math@floor(14.9)");
			// 由于Math类在开发中比较常用，所以也可以这样写
			//Object ognl = Ognl.parseExpression("@@floor(10.9)");
			// 得到获得的值
			System.out.println(Ognl.getValue(ognl, context, context.getRoot()));
		} catch (OgnlException e) {
			e.printStackTrace();
		}
	}
    public static void main(String[] args) {
		test1();
		test2();
		test3();
	}
}
