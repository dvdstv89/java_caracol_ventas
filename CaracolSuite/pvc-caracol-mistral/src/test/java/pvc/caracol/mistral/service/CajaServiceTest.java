package pvc.caracol.mistral.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import pvc.caracol.common.exceptions.FeignClientException;
import pvc.caracol.common.exceptions.NotFoundException;
import pvc.caracol.common.reponse.ApiWebResponse;
import pvc.caracol.mistral.enums.ModeloCaja;
import pvc.caracol.mistral.messages.NameCaseTest;
import pvc.caracol.mistral.model.Caja;
import pvc.caracol.mistral.repository.interfaces.ICajaRepository;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
class CajaServiceTest {
    @Mock
    protected ApiWebResponse response;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private ICajaRepository cajaRepository;

    @InjectMocks
    private CajaService cajaService;

    private final Integer idCentroGestion = 1;
    private Caja caja;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        caja = Caja.builder()
                .idCaja("1")
                .codigoAlmacen("1")
                .codigoRed("1")
                .centroCosto("1")
                .numeroCaja("1")
                .descripcion("descripcion")
                .codigoComercial("1")
                .mlc(true)
                .modelo(ModeloCaja.Serie2000)
                .build();
    }

    @ParameterizedTest(name = "Buscar Cajas registradoras activas {0}: {1}")
    @MethodSource("pvc.caracol.mistral.service.support.TestSuport#getCajasActivasByCentroGestionTestCases")
    void getCajasActivasByCentroGestion(HttpStatus expectedHttpStatus, String testName) throws NotFoundException, FeignClientException {
        // Arrange
        if (testName.equals(NameCaseTest.CAJAS_OK_200)) {
            when(cajaRepository.getCajasActivasByCentroGestion(idCentroGestion)).thenReturn(Collections.singletonList(caja));
        } else if (testName.equals(NameCaseTest.CAJAS_NOT_FOUND_404)) {
            when(cajaRepository.getCajasActivasByCentroGestion(idCentroGestion)).thenReturn(Collections.emptyList());
            assertThrows(NotFoundException.class, () -> cajaService.getCajasActivasByCentroGestion(idCentroGestion));
            return;
        } else if (testName.equals(NameCaseTest.GENERIC_INSTANCIA_NOT_FOUND_503)) {
            when(cajaRepository.getCajasActivasByCentroGestion(idCentroGestion)).thenThrow(FeignClientException.class);
            assertThrows(FeignClientException.class, () -> cajaRepository.getCajasActivasByCentroGestion(idCentroGestion));
            return;
        }

        //Act
        ApiWebResponse result = cajaService.getCajasActivasByCentroGestion(idCentroGestion);
        //Assert
        assertEquals(expectedHttpStatus, result.getStatusCode());
    }
}