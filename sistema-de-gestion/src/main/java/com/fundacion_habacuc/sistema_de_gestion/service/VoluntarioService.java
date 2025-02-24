import com.fundacion_habacuc.sistema_de_gestion.dto.VoluntarioDTO;
import com.fundacion_habacuc.sistema_de_gestion.entity.Voluntario;
import com.fundacion_habacuc.sistema_de_gestion.repository.VoluntarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VoluntarioService {
    private final VoluntarioRepository voluntarioRepository;

    public List<VoluntarioDTO> obtenerVoluntarios() {
        return voluntarioRepository.findAll().stream()
                .map(voluntario -> new VoluntarioDTO(
                        voluntario.getId(),
                        voluntario.getNombre(),
                        voluntario.getTelefono(),
                        voluntario.getEmail()
                ))
                .collect(Collectors.toList());
    }

    public VoluntarioDTO agregarVoluntario(VoluntarioDTO voluntarioDTO) {
        Voluntario voluntario = new Voluntario();
        voluntario.setNombre(voluntarioDTO.getNombre());
        voluntario.setTelefono(voluntarioDTO.getTelefono());
        voluntario.setEmail(voluntarioDTO.getEmail());

        Voluntario nuevoVoluntario = voluntarioRepository.save(voluntario);
        return new VoluntarioDTO(
                nuevoVoluntario.getId(),
                nuevoVoluntario.getNombre(),
                nuevoVoluntario.getTelefono(),
                nuevoVoluntario.getEmail()
        );
    }

    @Transactional
    public boolean eliminarVoluntario(Long id) {
        if (!voluntarioRepository.existsById(id)) {
            throw new EntityNotFoundException("No se encontró un voluntario con ID: " + id);
        }
        voluntarioRepository.deleteById(id);
        return true;
    }
}
