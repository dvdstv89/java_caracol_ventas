package com.system.mistral.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import steved.cinad.client.CintaAuditoraClient;
import steved.cinad.client.ICintaAuditoraClient;

@Configuration
public class ProcesadorCintaAuditoraConfig {
    @Bean
    public ICintaAuditoraClient procesadorCintasAuditoras() {
        return new CintaAuditoraClient();
    }
}
