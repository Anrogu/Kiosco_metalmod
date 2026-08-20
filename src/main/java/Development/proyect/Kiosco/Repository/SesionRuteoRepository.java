package Development.proyect.Kiosco.Repository;

import Development.proyect.Kiosco.Model.SesionRuteo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface SesionRuteoRepository extends JpaRepository<SesionRuteo, Long> {

    // Crucial: Busca el último registro de un lote para saber en qué operación se quedó
    Optional<SesionRuteo> findTopByLoteViajeroIdOrderByTimestampInicioDesc(Long loteViajeroId);

    // Para buscar si una máquina tiene una sesión activa (evita empalmar lotes)
    Optional<SesionRuteo> findTopByMaquinaIdAndEstadoSesionOrderByTimestampInicioDesc(Long maquinaId, String estadoSesion);

    // Para que el jefe de piso vea las sesiones bloqueadas que requieren su autorización
    List<SesionRuteo> findByEstadoSesion(String estadoSesion);
}