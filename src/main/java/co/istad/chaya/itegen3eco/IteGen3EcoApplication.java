package co.istad.chaya.itegen3eco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
@SpringBootApplication
public class IteGen3EcoApplication {

	public static void main(String[] args) {
		SpringApplication.run(IteGen3EcoApplication.class, args);
	}

}
