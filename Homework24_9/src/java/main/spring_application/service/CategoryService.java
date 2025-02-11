package main.spring_application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import main.spring_application.dto.CategoryDTO;
import main.spring_application.exception.ResourcesNotFoundException;
import main.spring_application.model.Category;
import main.spring_application.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryDTO getByIdCategory(Integer id){
        log.info("Получение новости с id: {}", id);
        return mapToDto(categoryRepository.findById(id).orElseThrow(()
        -> new ResourcesNotFoundException(String.format("Категория с таким id: %d не найдена", id))
                ));
    }

    public List<CategoryDTO> getAllCategory(){
        log.info("Получение списка категорий");
        return categoryRepository.findAll()
                .stream()
                .map(CategoryService::mapToDto)
                .toList();
    }

    public void createCategory(CategoryDTO categoryDTO) {
        log.info("Создание категории");
        categoryRepository.save(mapToEntity(categoryDTO));
    }

    public void updateCategory(CategoryDTO categoryDTO) {
        if(categoryRepository.existsById(Math.toIntExact(categoryDTO.getId()))){
            throw new ResourcesNotFoundException(String.format("Категории с таким id: %d не найдена",categoryDTO.getId()));
        }
        log.info("Обновление категории");
        categoryRepository.save(mapToEntity(categoryDTO));
    }

    public void deleteCategory(Integer id) {
        if(!categoryRepository.existsById(Math.toIntExact(id))){
            throw new ResourcesNotFoundException(String.format("Категории с таким id: %d не найдена",id));
        }
        log.info("Удаление категории с таким id: {}", id);
        categoryRepository.deleteById(id);

    }

    public static Category mapToEntity(CategoryDTO categoryDTO){
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setTitle(categoryDTO.getTitle());
        category.setNewsList(categoryDTO.getNewsDTOList()
                .stream()
                .map(NewsService::mapToEntity)
                .toList());
        return category;
    }

    public static CategoryDTO mapToDto(Category category){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setTitle(category.getTitle());
        categoryDTO.setNewsDTOList(category.getNewsList()
                .stream()
                .map(NewsService::mapToDto)
                .toList());
        return categoryDTO;
    }
}
