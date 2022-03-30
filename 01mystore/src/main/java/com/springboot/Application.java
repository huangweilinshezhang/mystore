package com.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//@MapperScan("com.springboot.mapper")
@SpringBootApplication

@MapperScan(basePackages="com.springboot.mapper")//开启扫描Mapper接口的包以及子目录
//@ComponentScan(basePackages = "com.springboot.mapper")


public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
