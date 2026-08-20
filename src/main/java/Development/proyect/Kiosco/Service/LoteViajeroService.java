package Development.proyect.Kiosco.Service;

import Development.proyect.Kiosco.Model.LoteViajero;
import Development.proyect.Kiosco.Repository.LoteViajeroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoteViajeroService {

    private final LoteViajeroRepository loteRepo;

    public LoteViajero validarLoteParaOperacion(String codigoLote) {
        LoteViajero lote = loteRepo.findByCodigoLote(codigoLote)
                .orElseThrow(() -> new IllegalArgumentException("Lote no reconocido: " + codigoLote));

        if ("RETENIDO".equals(lote.getEstadoLote())) {
            throw new IllegalStateException("Lote retenido. Requiere liberación de jefatura.");
        }
        return lote;
    }

    public void retenerLotePorMerma(LoteViajero lote) {
        lote.setEstadoLote("RETENIDO");
        loteRepo.save(lote);
    }
}