package com.example.jdbc.connection;

import static com.example.jdbc.connection.ConnectionConst.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.zaxxer.hikari.HikariDataSource;

class ConnectionTest {
	@Test
	void driverManager() throws SQLException {
		Connection con1 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		Connection con2 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}

	@Test
	void dataSourceDriverManager() throws SQLException {
		//DriverManagerDataSource - 항상 새로운 커넥션 획득
		DriverManagerDataSource dataSource = new DriverManagerDataSource(URL,
			USERNAME, PASSWORD);
		useDataSource(dataSource);
	}

	@Test
	void dataSourceConnectionPool() throws SQLException {
		HikariDataSource hikariDataSource = new HikariDataSource();
		hikariDataSource.setJdbcUrl(URL);
		hikariDataSource.setUsername(USERNAME);
		hikariDataSource.setPassword(PASSWORD);
		hikariDataSource.setMaximumPoolSize(10);
		hikariDataSource.setPoolName("MyPool");

		useDataSource(hikariDataSource);
	}

	private void useDataSource(DataSource dataSource) throws SQLException {
		Connection con1 = dataSource.getConnection();
		Connection con2 = dataSource.getConnection();
	}
}
