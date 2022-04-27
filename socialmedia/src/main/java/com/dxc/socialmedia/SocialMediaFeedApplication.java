package com.dxc.socialmedia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import javax.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dxc.socialmedia.entity.SecurityAuditorAware;
import com.dxc.socialmedia.services.FileService;

@EnableJpaAuditing(auditorAwareRef="auditorAware")
@SpringBootApplication
public class SocialMediaFeedApplication implements CommandLineRunner {
	
	@Bean
	public AuditorAware<String> auditorAware() {
		return new SecurityAuditorAware();
	}
	
	@Resource
	 FileService storageService;

	public static void main(String[] args) {
		SpringApplication.run(SocialMediaFeedApplication.class, args);
		System.out.println("running on spring boot");
	}
	
	@Override
	  public void run(String... arg) throws Exception {
//	    storageService.deleteAll();
//	    storageService.init();
	  }

}
