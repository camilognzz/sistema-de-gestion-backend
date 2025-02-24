}import com.fundacion_habacuc.sistema_de_gestion.dto.EstadoFinancieroDTO;
import com.fundacion_habacuc.sistema_de_gestion.service.EstadoFinancieroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/finanzas")
@RequiredArgsConstructor
public class EstadoFinancieroController {
    private final EstadoFinancieroService estadoFinancieroService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<EstadoFinancieroDTO>> obtenerFinanzas() {
        List<EstadoFinancieroDTO> estados = estadoFinancieroService.obtenerEstadosFinancieros();
        return ResponseEntity.ok(estados);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EstadoFinancieroDTO> agregarEstadoFinanciero(@Valid @RequestBody EstadoFinancieroDTO estadoFinancieroDTO) {
        EstadoFinancieroDTO nuevoEstado = estadoFinancieroService.agregarEstadoFinanciero(estadoFinancieroDTO);
        return ResponseEntity.status(201).body(nuevoEstado);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminarEstadoFinanciero(@PathVariable Long id) {
        estadoFinancieroService.eliminarEstadoFinanciero(id);
        return ResponseEntity.ok().build();
    }
}
