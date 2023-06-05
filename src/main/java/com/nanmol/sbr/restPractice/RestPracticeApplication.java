package com.nanmol.sbr.restPractice;

import com.nanmol.sbr.restPractice.dao.DaoClass;
import com.nanmol.sbr.restPractice.entities.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:/initValues.properties")
public class RestPracticeApplication {

	public static void main(String[] args) {

		ApplicationContext ctx = SpringApplication.run(RestPracticeApplication.class, args);
		Customer c1 = ctx.getBean("customer", Customer.class);
//		System.out.println(c1);
		DaoClass crud = ctx.getBean(DaoClass.class);
		if(crud.addCustomer(c1)!=null){
			System.out.println(c1);
		}else{
			System.out.println("Customer not added!");
		}
	}

}
