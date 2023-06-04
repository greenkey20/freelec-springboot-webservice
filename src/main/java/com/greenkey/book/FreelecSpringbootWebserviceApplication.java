package com.greenkey.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// 2023.5.7(일) 19h30
//@EnableJpaAuditing // JPA Auditing annotations 활성화
@SpringBootApplication // 스프링부트의 자동설정, 스프링 Bean 읽기 및 생성을 모두 자동으로 설정 + 이 애너테이션 위치부터 설정을 읽어가는 바, 이 클래스는 프로젝트의 최상단에 위치
public class FreelecSpringbootWebserviceApplication {
	public static void main(String[] args) {
		SpringApplication.run(FreelecSpringbootWebserviceApplication.class, args);
	}
}
