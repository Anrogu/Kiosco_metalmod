package Development.proyect.Kiosco.Service;

import Development.proyect.Kiosco.Model.*;
import Development.proyect.Kiosco.Repository.SesionRuteoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.ZonedDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransaccionKioscoService {

    private final OperadorService operadorService;
    private final MaquinaService maquinaService;
    private final LoteViajeroService loteService;
    private final SesionRuteoRepository sesionRepo;

    @Transactional
    public String procesarTransaccion(String numeroNomina, String codigoMaquina, String codigoLote, int cantidad, boolean esMerma) {

        // 1. Delegar validaciones limpias
        Operador operador = operadorService.validarOperadorActivo(numeroNomina);
        Maquina maquina = maquinaService.validarMaquinaOperativa(codigoMaquina);
        LoteViajero lote = loteService.validarLoteParaOperacion(codigoLote);

        // 2. Lógica de negocio orquestada
        Optional<SesionRuteo> sesionActiva = sesionRepo.findTopByLoteViajeroIdOrderByTimestampInicioDesc(lote.getId());

        if (sesionActiva.isEmpty() || !"INICIADA".equals(sesionActiva.get().getEstadoSesion())) {
            return registrarInicio(operador, maquina, lote);
        } else {
            return registrarFin(sesionActiva.get(), cantidad, esMerma, lote);
        }
    }

    private String registrarInicio(Operador op, Maquina maq, LoteViajero lote) {
        SesionRuteo sesion = new SesionRuteo();
        sesion.setOperador(op);
        sesion.setMaquina(maq);
        sesion.setLoteViajero(lote);
        sesion.setEstadoSesion("INICIADA");
        sesionRepo.save(sesion);
        return "OPERACION_INICIADA";
    }

    private String registrarFin(SesionRuteo sesion, int cant, boolean merma, LoteViajero lote) {
        sesion.setTimestampFin(ZonedDateTime.now());
        sesion.setCantidadBuenas(cant);

        if (merma || cant < lote.getCantidadActual()) {
            sesion.setEstadoSesion("BLOQUEADA");
            loteService.retenerLotePorMerma(lote);
            sesionRepo.save(sesion);
            return "LOTE_BLOQUEADO_POR_MERMA";
        }

        sesion.setEstadoSesion("COMPLETADA");
        sesionRepo.save(sesion);
        return "OPERACION_COMPLETADA";
    }
}