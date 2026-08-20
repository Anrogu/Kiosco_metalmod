package Development.proyect.Kiosco.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "lote_viajero")
public class LoteViajero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_lote", unique = true, nullable = false)
    private String codigoLote;

    @Column(name = "numero_parte", nullable = false)
    private String numeroParte;

    @Column(name = "cantidad_original", nullable = false)
    private Integer cantidadOriginal;

    @Column(name = "cantidad_actual", nullable = false)
    private Integer cantidadActual;

    @Column(name = "estado_lote")
    private String estadoLote = "EN_PROCESO";
}