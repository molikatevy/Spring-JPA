package co.istad.chaya.itegen3eco.cofig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class ResourceHandlerConfigs implements WebMvcConfigurer {


    @Value("${file.storage-location}")
    private String storageLocation;

    @Value("${file.client-path}")
    private String clientPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler(clientPath + "/**")
//                system file
                .addResourceLocations("file:" + storageLocation)
//                resource file
                .addResourceLocations("classPath:/static/");
    }
}
