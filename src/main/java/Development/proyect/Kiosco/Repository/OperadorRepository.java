package Development.proyect.Kiosco.Repository;

import Development.proyect.Kiosco.Model.Operador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OperadorRepository extends JpaRepository<Operador, Long> {

    // Validar identidad en el kiosco
    Optional<Operador> findByNumeroNominaAndActivoTrue(String numeroNomina);

    // Útil para cuando necesites listar a los jefes de piso en algún dashboard
    Iterable<Operador> findByEsJefePisoTrueAndActivoTrue();
}