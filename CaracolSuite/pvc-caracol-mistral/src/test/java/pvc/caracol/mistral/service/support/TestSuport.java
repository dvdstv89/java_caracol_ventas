package pvc.caracol.mistral.service.support;

import org.springframework.http.HttpStatus;
import pvc.caracol.mistral.messages.NameCaseTest;

import java.util.stream.Stream;

public class TestSuport {

    public static Stream<Object[]> getCajasActivasByCentroGestionTestCases() {
        return Stream.of(
                new Object[]{HttpStatus.OK, NameCaseTest.CAJAS_OK_200},
                new Object[]{HttpStatus.NOT_FOUND, NameCaseTest.CAJAS_NOT_FOUND_404},
                new Object[]{HttpStatus.SERVICE_UNAVAILABLE, NameCaseTest.GENERIC_INSTANCIA_NOT_FOUND_503}
        );
    }

    public static Stream<Object[]> getCintasAuditorasTestCases() {
        return Stream.of(
                new Object[]{HttpStatus.OK, NameCaseTest.CINTA_AUDITORA_OK_200},
                new Object[]{HttpStatus.NOT_FOUND, NameCaseTest.CINTA_AUDITORA_NOT_FOUND_404},
                new Object[]{HttpStatus.PRECONDITION_FAILED, NameCaseTest.CINTA_AUDITORAS_OBSOLETA_412},
                new Object[]{HttpStatus.SERVICE_UNAVAILABLE, NameCaseTest.GENERIC_INSTANCIA_NOT_FOUND_503}
        );
    }
}
