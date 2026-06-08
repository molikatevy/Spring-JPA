package co.istad.chaya.itegen3eco.controller;


import co.istad.chaya.itegen3eco.dto.CategoryResponse;
import co.istad.chaya.itegen3eco.dto.CreateCategoryRequest;
import co.istad.chaya.itegen3eco.dto.UpdateCategoryRequest;
import co.istad.chaya.itegen3eco.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CategoryResponse createNew(@Valid @RequestBody CreateCategoryRequest createCategoryRequest){
        return categoryService.creatNew(createCategoryRequest);
    }

    @GetMapping
    public Page<CategoryResponse> getAllCategories(@RequestParam(defaultValue = "0") Integer pageNumber, @RequestParam(defaultValue = "25") Integer pageSize){
        return categoryService.allCategories(pageNumber,pageSize);
    }
    @GetMapping("/{id}")
    public CategoryResponse getCategoryById(@PathVariable Integer id){
        return categoryService.getCategoryById(id);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void hardDeleteCategory(@PathVariable Integer id){
        categoryService.hardDeleteById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void softDeleteCategory(@PathVariable Integer id,@RequestBody CreateCategoryRequest createCategoryRequest){
        categoryService.softDeleteById(id);
    }

    @GetMapping("/{parentCategoryId}/subcategories")
    public List<CategoryResponse> getAllSubCategories(@PathVariable Integer parentCategoryId){
        return categoryService.getSubCategories(parentCategoryId);
    }

    @PatchMapping("/{id}")
    public CategoryResponse updateCategoryById(@PathVariable Integer id, @RequestBody UpdateCategoryRequest updateCategoryRequest){
        return  categoryService.updateCategoryById(id,updateCategoryRequest);
    }


}

