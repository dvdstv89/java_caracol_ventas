package pvc.caracol.cinad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.sql.Date;

@EnableDiscoveryClient
@SpringBootApplication
public class Application {
    public static final Integer MIN_COUNT_DIGIT_PRODUCT_CODE = 10;
    public static final Integer COUNT_CHARACTERS_REPORT_CODE = 12;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
