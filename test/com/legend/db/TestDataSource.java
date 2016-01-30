package com.legend.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:beans.xml" })
public class TestDataSource {

	@Resource(name = "dataSource")
	private DataSource dataSource;

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	@Test
	public void getConnection() throws SQLException {
		Connection conn = dataSource.getConnection();
		System.out.println(conn);
		Assert.assertNotNull(conn);
	}

	@Test
	public void getSessionFactory() {
		Assert.assertNotNull(sessionFactory);
	}
}
