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
import pvc.caracol.common.exceptions.NotFoundCausedException;
import pvc.caracol.common.exceptions.NotFoundException;
import pvc.caracol.common.reponse.ApiWebResponse;
import pvc.caracol.mistral.http.input.CajaRegistradoraDto;
import pvc.caracol.mistral.messages.NameCaseTest;
import pvc.caracol.mistral.model.CintaAuditora;
import pvc.caracol.mistral.repository.interfaces.ICintaAuditoraRepository;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
class CintaAuditoraServiceTest {
    @Mock
    protected ApiWebResponse response;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private ICintaAuditoraRepository cintaAuditoraRepository;

    @InjectMocks
    private CintaAuditoraService cintaAuditoraService;
    private CajaRegistradoraDto cajaRegistradoraDto;
    private CintaAuditora cintaAuditora;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cajaRegistradoraDto = CajaRegistradoraDto.builder()
                .idCaja("13")
                .codigoRed("R01")
                .idCentroGestion(1)
                .fechaInicio(LocalDate.of(2022, 1, 1))
                .fechaFin(LocalDate.of(2022, 1, 3))
                .build();
    }

    @ParameterizedTest(name = "Buscar Cintas Auditoras {0}: {1}")
    @MethodSource("pvc.caracol.mistral.service.support.TestSuport#getCintasAuditorasTestCases")
    void getCintaAuditora(HttpStatus expectedHttpStatus, String testName) throws NotFoundException, FeignClientException, NotFoundCausedException {
        // Arrange
        if (testName.equals(NameCaseTest.CINTA_AUDITORA_OK_200)) {
            when(cintaAuditoraRepository.getCintaAuditora(cajaRegistradoraDto)).thenReturn(Collections.singletonList(cintaAuditora));
        } else if (testName.equals(NameCaseTest.CINTA_AUDITORA_NOT_FOUND_404)) {
            when(cintaAuditoraRepository.getCintaAuditora(cajaRegistradoraDto)).thenReturn(Collections.emptyList());
            assertThrows(NotFoundException.class, () -> cintaAuditoraService.getCintaAuditora(cajaRegistradoraDto));
            return;
        } else if (testName.equals(NameCaseTest.CINTA_AUDITORAS_OBSOLETA_412)) {
            cajaRegistradoraDto.setFechaInicio(LocalDate.of(2020, 1, 1));
            assertThrows(NotFoundCausedException.class, () -> cintaAuditoraService.getCintaAuditora(cajaRegistradoraDto));
            return;
        } else if (testName.equals(NameCaseTest.GENERIC_INSTANCIA_NOT_FOUND_503)) {
            when(cintaAuditoraRepository.getCintaAuditora(cajaRegistradoraDto)).thenThrow(FeignClientException.class);
            assertThrows(FeignClientException.class, () -> cintaAuditoraService.getCintaAuditora(cajaRegistradoraDto));
            return;
        }

        //Act
        ApiWebResponse result = cintaAuditoraService.getCintaAuditora(cajaRegistradoraDto);
        //Assert
        assertEquals(expectedHttpStatus, result.getStatusCode());
    }
}