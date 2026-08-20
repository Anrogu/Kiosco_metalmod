package Development.proyect.Kiosco.Model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "sesion_ruteo")
public class SesionRuteo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_operador", nullable = false)
    private Operador operador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_maquina", nullable = false)
    private Maquina maquina;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_lote", nullable = false)
    private LoteViajero loteViajero;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_receta_operacion", nullable = false)
    private RecetaOperacion recetaOperacion;

    @Column(name = "timestamp_inicio")
    private ZonedDateTime timestampInicio = ZonedDateTime.now();

    @Column(name = "timestamp_fin")
    private ZonedDateTime timestampFin;

    @Column(name = "cantidad_buenas")
    private Integer cantidadBuenas = 0;

    @Column(name = "cantidad_scrap")
    private Integer cantidadScrap = 0;

    @Column(name = "cantidad_retrabajo")
    private Integer cantidadRetrabajo = 0;

    @Column(name = "estado_sesion")
    private String estadoSesion = "INICIADA";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aprobado_por_jefe_id")
    private Operador aprobadoPorJefe;

    @Column(name = "motivo_desviacion")
    private String motivoDesviacion;
}