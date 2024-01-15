package pvc.caracol.tienda.configs;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Base64;

@Configuration
public class MappingConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Configurar para coincidencia estricta de nombres de propiedades
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        // Agregar convertidores personalizados
        modelMapper.addConverter(ctx -> {
            Object source = ctx.getSource();
            return source instanceof String ? LocalDate.parse((String) source) : null;
        }, String.class, LocalDate.class);

        modelMapper.addConverter(ctx -> {
            Object source = ctx.getSource();
            return source instanceof String ? Base64.getDecoder().decode((String) source) : null;
        }, String.class, byte[].class);

        return modelMapper;
    }
}
