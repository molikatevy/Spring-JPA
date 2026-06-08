package co.istad.chaya.itegen3eco.service.impl;

import co.istad.chaya.itegen3eco.domain.Category;
import co.istad.chaya.itegen3eco.dto.CreateCategoryRequest;
import co.istad.chaya.itegen3eco.dto.CategoryResponse;
import co.istad.chaya.itegen3eco.dto.UpdateCategoryRequest;
import co.istad.chaya.itegen3eco.mapper.CategoryMapper;
import co.istad.chaya.itegen3eco.repoditory.CategoryRepository;
import co.istad.chaya.itegen3eco.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
   private final CategoryRepository categoryRepository;
   private final CategoryMapper categoryMapper;



    @Override
    public CategoryResponse creatNew(CreateCategoryRequest createCategoryRequest) {
        log.info("createNew {}",createCategoryRequest);

        //validate category name
        boolean isExisting = categoryRepository
                .existsByName(createCategoryRequest.name());
        if (isExisting)
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Category has been already used"
            );

        Category parentCategory = null;
        // Validate parent category
        if (createCategoryRequest.parentCategoryId() != null){
            parentCategory = categoryRepository
                    .findById(createCategoryRequest.parentCategoryId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Parent category has not been found"
                    ));
        }

        Category category = categoryMapper
                .mapCreateCategoryRequestToCategory(createCategoryRequest);
        //System generated data
        category.setIsDelete(false);
        category.setParentCategory(parentCategory);

        //Insert if primary key is null
        //Update if primary key hav value
        category = categoryRepository.save(category);

        return categoryMapper.mapCategoryToCategoryResponse(category);
    }

    @Override
    public Page<CategoryResponse> allCategories(Integer pageNumber, Integer pageSize) {

        Pageable pageable = PageRequest.of(pageNumber,pageSize,Sort.by(Sort.Direction.DESC,"id"));
        Page<Category> categoryPage = categoryRepository.findAllByIsDeleted(false,
                pageable);

        return categoryPage.map(categoryMapper::mapCategoryToCategoryResponse);
    }


    @Override
    public CategoryResponse getCategoryById(Integer id) {
        return categoryRepository.findById(id).map(categoryMapper::mapCategoryToCategoryResponse).
                orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Category not found with this id."));
    }

    @Override
    public void hardDeleteById(Integer id) {
        Category category = categoryRepository.findById(id).
                orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Category not found with this id."));

        categoryRepository.delete(category);
    }

    @Override
    public void softDeleteById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Category not found with this id."
                ));

        softDeleteRecursive(category);
        categoryRepository.save(category);
    }

    private void softDeleteRecursive(Category category) {
        category.setIsDelete(true);

        if (category.getChildCategories() == null ||
                category.getChildCategories().isEmpty()) {
            return;
        }

        for (Category child : category.getChildCategories()) {
            softDeleteRecursive(child);
        }
    }


    @Override
    public List<CategoryResponse> getSubCategories(Integer parentCategoryId) {
        Category category = categoryRepository.findById(parentCategoryId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Category not found with this id."));
        if(category.getIsDelete()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"This category is already deleted.");
        }
        return categoryRepository.findAllByIsDeletedAndParentCategory(false,category).stream().map(categoryMapper::mapCategoryToCategoryResponse).toList();
    }

    @Override
    public CategoryResponse updateCategoryById(Integer id, UpdateCategoryRequest updateCategoryRequest) {
        Category category = categoryRepository.findById(id).
                orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Category not found with this id."));
        if(category.getIsDelete()==true){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"This category is already deleted.");
        }
        if(updateCategoryRequest.name() !=null){
            category.setName(updateCategoryRequest.name());
        }
        if(updateCategoryRequest.description()!=null){
            category.setDescription(updateCategoryRequest.description());
        }
        if(updateCategoryRequest.icon()!=null){
            category.setIcon(updateCategoryRequest.icon());
        }
        category = categoryRepository.save(category);

        return categoryMapper.mapCategoryToCategoryResponse(category);
    }


}