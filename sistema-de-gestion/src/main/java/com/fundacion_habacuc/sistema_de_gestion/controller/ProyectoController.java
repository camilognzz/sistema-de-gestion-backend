import com.fundacion_habacuc.sistema_de_gestion.dto.ProyectoDTO;
import com.fundacion_habacuc.sistema_de_gestion.service.ProyectoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proyectos")
@RequiredArgsConstructor
public class ProyectoController {
    private final ProyectoService proyectoService;

    @GetMapping
    public ResponseEntity<List<ProyectoDTO>> obtenerProyectos() {
        List<ProyectoDTO> proyectos = proyectoService.obtenerProyectos();
        return ResponseEntity.ok(proyectos);
    }

    @PostMapping
    public ResponseEntity<ProyectoDTO> crearProyecto(@Valid @RequestBody ProyectoDTO proyectoDTO) {
        ProyectoDTO nuevoProyecto = proyectoService.crearProyecto(proyectoDTO);
        return ResponseEntity.status(201).body(nuevoProyecto);
    }

    // Manejo de errores si no se encuentra un usuario o proyecto
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleNotFound(EntityNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }
}
