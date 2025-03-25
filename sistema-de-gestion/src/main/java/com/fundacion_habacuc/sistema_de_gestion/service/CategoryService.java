package com.fundacion_habacuc.sistema_de_gestion.service;

import com.fundacion_habacuc.sistema_de_gestion.dto.CategoryDTO;
import com.fundacion_habacuc.sistema_de_gestion.entity.TransactionCategory;
import com.fundacion_habacuc.sistema_de_gestion.exception.CategoryInUseException;
import com.fundacion_habacuc.sistema_de_gestion.exception.DuplicateCategoryException;
import com.fundacion_habacuc.sistema_de_gestion.exception.ResourceNotFoundException;
import com.fundacion_habacuc.sistema_de_gestion.repository.CategoryRepository;
import com.fundacion_habacuc.sistema_de_gestion.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final TransactionRepository transactionRepository;
    private final ModelMapper modelMapper;

    // Crear categoría
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        // Validar nombre único
        if (categoryRepository.existsByName(categoryDTO.getName())) {
            throw new DuplicateCategoryException("Category name already exists: " + categoryDTO.getName());
        }

        TransactionCategory category = modelMapper.map(categoryDTO, TransactionCategory.class);
        return modelMapper.map(categoryRepository.save(category), CategoryDTO.class);
    }

    // Obtener todas las categorías
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .toList();
    }

    // Obtener categoría por ID
    public CategoryDTO getCategoryById(Long id) {
        return modelMapper.map(findCategoryOrThrow(id), CategoryDTO.class);
    }

    // Actualizar categoría
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        TransactionCategory existingCategory = findCategoryOrThrow(id);

        // Validar si el nuevo nombre ya existe (excepto para sí misma)
        if (!existingCategory.getName().equals(categoryDTO.getName())
                && categoryRepository.existsByName(categoryDTO.getName())) {
            throw new DuplicateCategoryException("Category name already exists: " + categoryDTO.getName());
        }

        existingCategory.setName(categoryDTO.getName());
        existingCategory.setDescription(categoryDTO.getDescription());

        return modelMapper.map(categoryRepository.save(existingCategory), CategoryDTO.class);
    }

    // Eliminar categoría
    public void deleteCategory(Long id) {
        TransactionCategory category = findCategoryOrThrow(id);

        // Validar si la categoría está en uso
        if (transactionRepository.existsByCategory(category)) {
            throw new CategoryInUseException("Cannot delete category: It has associated transactions");
        }

        categoryRepository.delete(category);
    }

    // Método auxiliar para buscar categoría
    private TransactionCategory findCategoryOrThrow(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
    }
}
