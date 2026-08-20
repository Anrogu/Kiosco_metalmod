package Development.proyect.Kiosco.Controller;

import Development.proyect.Kiosco.Dto.TransaccionKioscoRequestDto;
import Development.proyect.Kiosco.Service.TransaccionKioscoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/kiosco")
@RequiredArgsConstructor
public class KioscoController {

    private final TransaccionKioscoService transaccionService;

    @PostMapping("/transaccion")
    public ResponseEntity<Map<String, String>> registrarTransaccion(@RequestBody TransaccionKioscoRequestDto request) {
        Map<String, String> response = new HashMap<>();

        try {
            // Mandamos llamar al orquestador
            String resultado = transaccionService.procesarTransaccion(
                    request.getNumeroNomina(),
                    request.getCodigoMaquina(),
                    request.getCodigoLote(),
                    request.getCantidad(),
                    request.isEsMerma()
            );

            // Si llegamos aquí, la transacción fue exitosa (Verde)
            response.put("status", "SUCCESS");
            response.put("mensaje", resultado);
            return ResponseEntity.ok(response); // HTTP 200

        } catch (IllegalArgumentException | IllegalStateException e) {
            // Si el lote está retenido, el operador no existe, o hay algún bloqueo (Rojo)
            response.put("status", "ERROR");
            response.put("mensaje", e.getMessage());
            return ResponseEntity.badRequest().body(response); // HTTP 400
        } catch (Exception e) {
            // Error general del servidor
            response.put("status", "FATAL_ERROR");
            response.put("mensaje", "Error interno del servidor. Contactar a TI.");
            return ResponseEntity.internalServerError().body(response); // HTTP 500
        }
    }
}