package com.narpath.batchmail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.Flow.Processor;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootApplication
@EnableBatchProcessing
public class BatchMailApplication {
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	public DataSource dataSource;
	
	@Autowired
	public JavaMailSender mailSender;
	
	/*
	 * private ItemWriter<S> itemWriter() { return new SendMail; }
	 */
	@Bean
	public PagingQueryProvider queryProvider() throws Exception {
		SqlPagingQueryProviderFactoryBean factory = new SqlPagingQueryProviderFactoryBean();
		factory.setSelectClause("select id,name,designation,city,companyname,email ");
		factory.setFromClause("from employee");
		factory.setSortKey("id");
		factory.setDataSource(dataSource);
		return factory.getObject();
	}
	
	@Bean
	public ItemReader<Employee> itemReader() throws Exception {
		
		return new JdbcPagingItemReaderBuilder<Employee>()
				.dataSource(dataSource)
				.name("jdbcCursorItemReader")
				.queryProvider(queryProvider())
				.rowMapper(new EmployeeRowMapper())
				.build();
	}

	@Bean
	public Step chunkBasedStep() throws Exception {
		return this.stepBuilderFactory.get("chunkBasedStep")
				.<Employee,SimpleMailMessage>chunk(5)
				.reader(itemReader())
				.processor(new MailProcessor())
				.writer(new SendMail(mailSender))
				.build();
	}
	


	@Bean
	public Job job() throws Exception {
		return this.jobBuilderFactory.get("job").start(chunkBasedStep()).build();
		
	}


	public static void main(String[] args) {
		SpringApplication.run(BatchMailApplication.class, args);
	}

}
