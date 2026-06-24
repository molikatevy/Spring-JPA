package co.istad.chaya.itegen3eco.features.category;

import co.istad.chaya.itegen3eco.features.category.dto.CreateCategoryRequest;
import co.istad.chaya.itegen3eco.features.category.dto.CategoryResponse;
import co.istad.chaya.itegen3eco.features.category.dto.UpdateCategoryRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {

    CategoryResponse creatNew(CreateCategoryRequest createCategoryRequest);
    Page<CategoryResponse> allCategories(Integer pageNumber, Integer pageSize);
    CategoryResponse getCategoryById(Integer id);

    void hardDeleteById(Integer id);
    void softDeleteById(Integer id);

    List<CategoryResponse> getSubCategories(Integer parentCategoryId);

    CategoryResponse updateCategoryById(Integer id, UpdateCategoryRequest updateCategoryRequest);
}