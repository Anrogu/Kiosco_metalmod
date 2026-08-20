package Development.proyect.Kiosco.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "receta_operacion", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"numero_parte", "operacion_secuencia"})
})
public class RecetaOperacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_parte", nullable = false)
    private String numeroParte;

    @Column(name = "operacion_secuencia", nullable = false)
    private Integer operacionSecuencia;

    @Column(name = "tiempo_ciclo_teorico_segundos", nullable = false)
    private Integer tiempoCicloTeoricoSegundos;
}