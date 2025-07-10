package de.lht.testuploadcloudinary;

import com.cloudinary.Cloudinary;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class TestUploadCloudinaryApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestUploadCloudinaryApplication.class, args);
    }

    @Bean
    public Cloudinary cloudinaryConfig() {
        Cloudinary cloudinary = null;
        Map config = new HashMap();
        config.put("cloud_name", "");
        config.put("api_key", "");
        config.put("api_secret", "");
        cloudinary = new Cloudinary(config);
        return cloudinary;
    }

}
