package pvc.caracol.mistral.service.support;

import org.springframework.http.HttpStatus;

import java.util.stream.Stream;

public class TestSuport {

     public static Stream<Object[]> getCajasActivasByCentroGestionTestCases() {
         return Stream.of(
                 new Object[]{HttpStatus.OK, NameCaseTest.CAJAS_OK},
                 new Object[]{HttpStatus.NOT_FOUND, NameCaseTest.CAJAS_NOT_FOUND}
         );
     }

    public static Stream<Object[]> getCintasAuditorasTestCases() {
        return Stream.of(
                new Object[]{HttpStatus.OK, NameCaseTest.CINTA_AUDITORA_OK},
                new Object[]{HttpStatus.NOT_FOUND, NameCaseTest.CINTA_AUDITORA_NOT_FOUND},
                new Object[]{HttpStatus.NOT_FOUND, NameCaseTest.CINTA_AUDITORAS_OBSOLETA}
        );
    }
}
