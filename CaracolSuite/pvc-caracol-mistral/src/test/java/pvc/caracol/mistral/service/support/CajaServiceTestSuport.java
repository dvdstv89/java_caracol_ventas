package pvc.caracol.mistral.service.support;

import org.springframework.http.HttpStatus;

import java.util.stream.Stream;

public class CajaServiceTestSuport {
    public static Stream<Object[]> getCajasActivasByCentroGestionTestCases() {
        return Stream.of(
                new Object[]{HttpStatus.NOT_FOUND, "not found available drone BUSY"},
                new Object[]{HttpStatus.OK, "Ok"},
                new Object[]{HttpStatus.NOT_FOUND, "not found available drone BATTERY LOW"},
                new Object[]{HttpStatus.NOT_FOUND, "not found medication"},
                new Object[]{HttpStatus.BAD_REQUEST, "weight limit exceeded"},
                new Object[]{HttpStatus.BAD_REQUEST, "empty serial number"},
                new Object[]{HttpStatus.BAD_REQUEST, "empty medications"}
        );
    }
}
