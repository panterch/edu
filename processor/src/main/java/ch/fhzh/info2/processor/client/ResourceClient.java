package ch.fhzh.info2.processor.client;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;

public class ResourceClient {

    
    public static void main(String[] args) throws IOException {
        ClassPathResource res = new ClassPathResource("res.properties");
        Properties properties = new Properties();
        properties.load(res.getInputStream());
        String mode = properties.getProperty("mode");
        System.out.print("mode is ["+mode+"]\n");
    }

}
