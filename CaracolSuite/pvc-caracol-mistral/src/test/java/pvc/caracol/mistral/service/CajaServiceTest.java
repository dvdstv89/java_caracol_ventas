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
import pvc.caracol.common.exceptions.NotFoundException;
import pvc.caracol.common.reponse.WebResponse;
import pvc.caracol.mistral.enums.ModeloCaja;
import pvc.caracol.mistral.model.Caja;
import pvc.caracol.mistral.repository.interfaces.ICajaRepository;
import pvc.caracol.mistral.service.support.NameCaseTest;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
class CajaServiceTest {
    @Mock
    protected WebResponse response;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private ICajaRepository cajaRepository;

    @InjectMocks
    private CajaService cajaService;

    private final String centroGestion = "1";
    private Caja caja;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        caja = Caja.builder()
                .id("1")
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
    void getCajasActivasByCentroGestion(HttpStatus expectedHttpStatus, String testName) throws NotFoundException {
        // Arrange
        if (testName.equals(NameCaseTest.CAJAS_OK)) {
            when(cajaRepository.getCajasActivas(centroGestion)).thenReturn(Collections.singletonList(caja));
        }
        if (testName.equals(NameCaseTest.CAJAS_NOT_FOUND)) {
            when(cajaRepository.getCajasActivas(centroGestion)).thenReturn(Collections.emptyList());
            assertThrows(NotFoundException.class, () -> cajaService.getCajasActivasByCentroGestion(centroGestion));
            return;
        }

        //Act
        WebResponse result = cajaService.getCajasActivasByCentroGestion(centroGestion);
        //Assert
        assertEquals(expectedHttpStatus, result.getStatusCode());
    }
}