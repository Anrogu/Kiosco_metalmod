package Development.proyect.Kiosco.Service;

import Development.proyect.Kiosco.Model.Operador;
import Development.proyect.Kiosco.Repository.OperadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OperadorService {

    private final OperadorRepository operadorRepo;

    /**
     * Valida que el operador exista y esté activo en la planta.
     * @param numeroNomina El código escaneado del gafete.
     * @return Entidad Operador validada.
     */
    public Operador validarOperadorActivo(String numeroNomina) {
        return operadorRepo.findByNumeroNominaAndActivoTrue(numeroNomina)
                .orElseThrow(() -> new IllegalArgumentException("Operador inválido o inactivo: " + numeroNomina));
    }
}