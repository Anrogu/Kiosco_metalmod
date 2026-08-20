package Development.proyect.Kiosco.Repository;

import Development.proyect.Kiosco.Model.LoteViajero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface LoteViajeroRepository extends JpaRepository<LoteViajero, Long> {

    // Validar el escaneo de la tarjeta viajera
    Optional<LoteViajero> findByCodigoLote(String codigoLote);

    // Útil para el dashboard de jefes de piso (ver qué lotes están bloqueados por SCRAP)
    List<LoteViajero> findByEstadoLote(String estadoLote);
}