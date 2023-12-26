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
import pvc.caracol.common.reponse.ApiResponse;
import pvc.caracol.mistral.enums.ModeloCaja;
import pvc.caracol.mistral.model.Caja;
import pvc.caracol.mistral.repository.interfaces.ICajaRepository;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
class CajaServiceTest {
    @Mock
    protected ApiResponse response;
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

    @ParameterizedTest(name = "Load medication into drone with {0}: {1}")
    @MethodSource("pvc.caracol.mistral.service.support.CajaServiceTestSuport#getCajasActivasByCentroGestionTestCases")
    void getCajasActivasByCentroGestion(HttpStatus expectedHttpStatus, String testName) throws NotFoundException {
        when(cajaRepository.getCajasActivas(centroGestion)).thenReturn(Arrays.asList(caja));
        assertNotNull(cajaService.getCajasActivasByCentroGestion(centroGestion));
    }
}