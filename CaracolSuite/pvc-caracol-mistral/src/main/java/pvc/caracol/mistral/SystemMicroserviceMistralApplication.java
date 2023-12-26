package pvc.caracol.mistral;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.time.LocalDate;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SystemMicroserviceMistralApplication {

    public static final LocalDate fechaMinimaBuscarCintas = LocalDate.of(2022, 1, 1);

    public static void main(String[] args) {
        SpringApplication.run(SystemMicroserviceMistralApplication.class, args);
    }

}
