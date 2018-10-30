package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.config.RibbonConfiguration;

@SpringBootApplication
@RestController
@RibbonClient(name = "hello-service", configuration = RibbonConfiguration.class)
public class SpringRibbonClientApplication {

	@LoadBalanced
    @Bean
    RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    public String serverLocation() {
        String servLoc = this.restTemplate.getForObject("http://hello-service", String.class);
        return servLoc;
    }
    
	public static void main(String[] args) {
		SpringApplication.run(SpringRibbonClientApplication.class, args);
	}
}
