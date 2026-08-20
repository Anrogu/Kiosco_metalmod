package Development.proyect.Kiosco.Repository;

import Development.proyect.Kiosco.Model.Maquina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MaquinaRepository extends JpaRepository<Maquina, Long> {

    // Validar ubicación en el kiosco
    Optional<Maquina> findByCodigoMaquina(String codigoMaquina);

    // Para validar que la máquina esté operativa antes de permitir un registro
    Optional<Maquina> findByCodigoMaquinaAndEstadoActual(String codigoMaquina, String estadoActual);
}