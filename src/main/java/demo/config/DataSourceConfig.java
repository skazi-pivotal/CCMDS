package demo.config;

import java.sql.DriverManager;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import demo.utils.Logger;

@Configuration
@ConfigurationProperties(prefix="spring.datasource")
@Profile("local")
public class DataSourceConfig {
	private String driverClassName;
	private String url;
	private String username;
	private String password;

	public DataSourceConfig() {
		super();
	}

	@Bean
	public DataSource dataSource() {
		SimpleDriverDataSource dataSource = null;
		try {
			Class.forName(this.driverClassName);
			dataSource = new SimpleDriverDataSource();
			dataSource.setDriver(DriverManager.getDriver(this.url));
			dataSource.setUrl(this.url);
			dataSource.setUsername(this.username);
			dataSource.setPassword(this.password);
			Logger.INSTANCE.log("Connected to: " + this.url);
		} catch (Exception e) {
			throw new IllegalStateException("An Exception occurred initialising datasource", e);
		}

		return dataSource;
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
