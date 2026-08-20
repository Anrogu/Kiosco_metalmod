package Development.proyect.Kiosco.Dto;

import lombok.Data;

@Data
public class TransaccionKioscoRequestDto {
    private String numeroNomina;
    private String codigoMaquina;
    private String codigoLote;
    private int cantidad;
    private boolean esMerma;
}