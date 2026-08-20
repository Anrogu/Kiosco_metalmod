package Development.proyect.Kiosco.Service;

import Development.proyect.Kiosco.Model.Maquina;
import Development.proyect.Kiosco.Repository.MaquinaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MaquinaService {

    private final MaquinaRepository maquinaRepo;

    /**
     * Valida que la máquina exista y esté en condiciones de operar.
     * @param codigoMaquina El código escaneado en el equipo CNC.
     * @return Entidad Maquina validada.
     */
    public Maquina validarMaquinaOperativa(String codigoMaquina) {
        return maquinaRepo.findByCodigoMaquinaAndEstadoActual(codigoMaquina, "OPERATIVA")
                .orElseThrow(() -> new IllegalArgumentException("Máquina no válida o en mantenimiento: " + codigoMaquina));
    }
}