package com.legend.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestDataSource {

		@Test
		public void getConnection() throws SQLException{
			ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
			DataSource dataSource = (DataSource)ctx.getBean("dataSource");
			Connection conn = dataSource.getConnection();
			System.out.println(conn);
			Assert.assertNotNull(conn);
		}
		
		@Test
		public void getSessionFactory(){
			ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
			SessionFactory sessionFactory = (SessionFactory) ctx.getBean("sessionFactory");
			Assert.assertNotNull(sessionFactory);
		}
}
