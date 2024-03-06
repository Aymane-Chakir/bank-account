package net.aymane.operationservice;

import net.aymane.operationservice.config.GlobalConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableConfigurationProperties({GlobalConfig.class})
@EnableFeignClients
@EnableDiscoveryClient
public class OperationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OperationServiceApplication.class, args);
	}

}
