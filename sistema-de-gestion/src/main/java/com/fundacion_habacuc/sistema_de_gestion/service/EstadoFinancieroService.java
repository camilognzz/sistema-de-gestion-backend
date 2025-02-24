import com.fundacion_habacuc.sistema_de_gestion.dto.EstadoFinancieroDTO;
import com.fundacion_habacuc.sistema_de_gestion.entity.EstadoFinanciero;
import com.fundacion_habacuc.sistema_de_gestion.repository.EstadoFinancieroRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EstadoFinancieroService {
    private final EstadoFinancieroRepository estadoFinancieroRepository;

    public List<EstadoFinancieroDTO> obtenerEstadosFinancieros() {
        return estadoFinancieroRepository.findAll().stream()
                .map(estado -> new EstadoFinancieroDTO(
                        estado.getId(),
                        estado.getIngresos(),
                        estado.getEgresos(),
                        estado.getIngresos() - estado.getEgresos(), // Calcula el balance
                        estado.getDescripcion(),
                        estado.getFecha()
                ))
                .collect(Collectors.toList());
    }

    public EstadoFinancieroDTO agregarEstadoFinanciero(EstadoFinancieroDTO estadoDTO) {
        EstadoFinanciero estadoFinanciero = new EstadoFinanciero();
        estadoFinanciero.setIngresos(estadoDTO.getIngresos());
        estadoFinanciero.setEgresos(estadoDTO.getEgresos());
        estadoFinanciero.setBalance(estadoDTO.getIngresos() - estadoDTO.getEgresos()); // Cálculo automático
        estadoFinanciero.setDescripcion(estadoDTO.getDescripcion());
        estadoFinanciero.setFecha(estadoDTO.getFecha());

        EstadoFinanciero nuevoEstado = estadoFinancieroRepository.save(estadoFinanciero);
        return new EstadoFinancieroDTO(
                nuevoEstado.getId(),
                nuevoEstado.getIngresos(),
                nuevoEstado.getEgresos(),
                nuevoEstado.getBalance(),
                nuevoEstado.getDescripcion(),
                nuevoEstado.getFecha()
        );
    }

    @Transactional
    public void eliminarEstadoFinanciero(Long id) {
        if (!estadoFinancieroRepository.existsById(id)) {
            throw new EntityNotFoundException("No se encontró un estado financiero con ID: " + id);
        }
        estadoFinancieroRepository.deleteById(id);
    }
}
