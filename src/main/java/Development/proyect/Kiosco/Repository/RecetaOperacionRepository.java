package Development.proyect.Kiosco.Repository;

import Development.proyect.Kiosco.Model.RecetaOperacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface RecetaOperacionRepository extends JpaRepository<RecetaOperacion, Long> {

    // Para buscar la receta exacta basada en la pieza y la operación actual
    Optional<RecetaOperacion> findByNumeroParteAndOperacionSecuencia(String numeroParte, Integer operacionSecuencia);

    // Para traer toda la ruta de manufactura de una pieza específica
    List<RecetaOperacion> findByNumeroParteOrderByOperacionSecuenciaAsc(String numeroParte);
}