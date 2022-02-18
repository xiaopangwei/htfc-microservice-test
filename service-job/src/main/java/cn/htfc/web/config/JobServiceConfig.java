package cn.htfc.web.config;

import cn.htfc.web.client.JobClient;
import cn.htfc.web.client.mysql.JobService;
import cn.htfc.web.client.mysql.MysqlClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

@Configuration
public class JobServiceConfig {

	@Bean(name="remoteRestTemplate")
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}


	@ConditionalOnProperty(name = "mysql")
	@PropertySource("classpath:mysql.properties")
	public class MysqlConfig {

		@Bean
		public DataSource dataSource(@Value("${mysql.url}") String url, @Value("${mysql.username}") String username,
									 @Value("${mysql.password}") String password) {
			return DataSourceBuilder.create()
							.username(username)
							.password(password)
							.url(url)
							.driverClassName("com.mysql.jdbc.Driver")
							.build();
		}

		@Bean
		public JobService jobService(JdbcTemplate jdbcTemplate) {
			return new JobService(jdbcTemplate);
		}

		@Bean
		public JobClient MysqlClient(JobService jobService) {
			return new MysqlClient(jobService);
		}
	}

	@Bean
	@ConditionalOnMissingBean
	public DataSource blankDataSource() {
		return DataSourceBuilder.create().build();
	}

}
