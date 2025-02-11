package main.spring_application.controller;

import main.spring_application.dto.CategoryDTO;
import main.spring_application.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public CategoryDTO getIdCategory(@PathVariable Long id) {
        return categoryService.getByIdCategory(Math.toIntExact(id));
    }

    @GetMapping
    public List<CategoryDTO> getAllCategory() {
        return categoryService.getAllCategory();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDTO createCategory(@RequestBody CategoryDTO categoryDTO) {
        categoryService.createCategory(categoryDTO);
        return categoryService.getByIdCategory(Math.toIntExact(categoryDTO.getId()));
    }

    @PutMapping
    public CategoryDTO updateCategory(@RequestBody CategoryDTO categoryDTO) {
        categoryService.updateCategory(categoryDTO);
        return categoryService.getByIdCategory(Math.toIntExact(categoryDTO.getId()));
    }


    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(Math.toIntExact(id));
    }
}
