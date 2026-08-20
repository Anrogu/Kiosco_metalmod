package Development.proyect.Kiosco.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "operador")
public class Operador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_nomina", unique = true, nullable = false)
    private String numeroNomina;

    @Column(nullable = false)
    private String nombre;

    @Column(name = "es_jefe_piso")
    private Boolean esJefePiso = false;

    private Boolean activo = true;
}