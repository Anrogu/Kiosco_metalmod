package Development.proyect.Kiosco.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "maquina")
public class Maquina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_maquina", unique = true, nullable = false)
    private String codigoMaquina;

    private String descripcion;

    @Column(name = "estado_actual")
    private String estadoActual = "OPERATIVA";
}