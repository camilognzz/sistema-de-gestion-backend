import com.fundacion_habacuc.sistema_de_gestion.dto.ContactoEstrategicoDTO;
import com.fundacion_habacuc.sistema_de_gestion.service.ContactoEstrategicoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contactos")
@RequiredArgsConstructor
public class ContactoEstrategicoController {
    private final ContactoEstrategicoService contactoService;

    @GetMapping
    public ResponseEntity<List<ContactoEstrategicoDTO>> obtenerContactos() {
        List<ContactoEstrategicoDTO> contactos = contactoService.obtenerContactos();
        return ResponseEntity.ok(contactos);
    }

    @PostMapping
    public ResponseEntity<ContactoEstrategicoDTO> agregarContacto(@Valid @RequestBody ContactoEstrategicoDTO contactoDTO) {
        ContactoEstrategicoDTO nuevoContacto = contactoService.agregarContacto(contactoDTO);
        return ResponseEntity.status(201).body(nuevoContacto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarContacto(@PathVariable Long id) {
        contactoService.eliminarContacto(id);
        return ResponseEntity.noContent().build();
    }

    // Manejo de excepciones personalizadas
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleNotFound(EntityNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }
}
