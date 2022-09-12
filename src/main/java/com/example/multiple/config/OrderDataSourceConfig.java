package com.example.multiple.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		basePackages = {"com.example.multiple.domain.order.repository"},
		entityManagerFactoryRef = "orderEntityManager",
		transactionManagerRef = "orderTransactionManager"
)
public class OrderDataSourceConfig {

	// jpa 설정 정보
	@Value("${spring.jpa.show-sql}")
	private Boolean showSql;
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String ddlAuto;
	@Value("${spring.jpa.properties.hibernate.format_sql}")
	private Boolean formatSql;

	// dbcp2 설정 정보
	@Value("${spring.datasource.dbcp2.initial-size}")
	private Integer initialSize;
	@Value("${spring.datasource.dbcp2.max-idle}")
	private Integer maxIdle;
	@Value("${spring.datasource.dbcp2.min-idle}")
	private Integer minIdle;
	@Value("${spring.datasource.dbcp2.max-total}")
	private Integer maxTotal;
	@Value("${spring.datasource.dbcp2.max-wait-millis}")
	private Integer maxWaitMillis;
	@Value("${spring.datasource.dbcp2.remove-abandoned-timeout}")
	private Integer removeAbandonedTimeout;
	@Value("${spring.datasource.dbcp2.validation-query}")
	private String validationQuery;

	@Bean
//	@ConfigurationProperties("spring.datasource.order")
	public DataSource orderDataSource() {
		// Commons-dbcp2 DataSource 설정
		BasicDataSource dataSource = DataSourceBuilder.create()
				.type(BasicDataSource.class)
				.driverClassName("com.mysql.cj.jdbc.Driver")
				.url("jdbc:mysql://localhost:3307/dborder")
				.username("root")
				.password("1234")
				.build();

		// dbcp2 설정
		dataSource.setInitialSize(initialSize);
		dataSource.setMaxIdle(maxIdle);
		dataSource.setMinIdle(minIdle);
		dataSource.setMaxTotal(maxTotal);
		dataSource.setMaxWaitMillis(maxWaitMillis);
		dataSource.setRemoveAbandonedTimeout(removeAbandonedTimeout);
		dataSource.setValidationQuery(validationQuery);

		return dataSource;
	}

	@Bean
	public PlatformTransactionManager orderTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(orderEntityManager().getObject());

		return transactionManager;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean orderEntityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(orderDataSource());

		// Entity 패키지 경로 지정
		em.setPackagesToScan("com.example.multiple.domain.order.entity");

		// Hibernate 구현체용 JpaVendorAdapter 설정
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(adapter);

		// jpa 설정
		Map<String, Object> properties = new HashMap<>();
		properties.put("hibernate.ddl-auto", ddlAuto);
		properties.put("hibernate.show_sql", showSql);
		properties.put("hibernate.format_sql", formatSql);
		em.setJpaPropertyMap(properties);

		return em;
	}
}
