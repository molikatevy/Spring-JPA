package co.istad.chaya.itegen3eco.features.category;

import co.istad.chaya.itegen3eco.features.category.dto.CategoryResponse;
import co.istad.chaya.itegen3eco.features.category.dto.CreateCategoryRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    //Return type = Target
    //Parameter = Source
    Category mapCreateCategoryRequestToCategory(CreateCategoryRequest createCategoryRequest);



    @Mapping(source = "parentCategory.id", target = "parentId")
    CategoryResponse mapCategoryToCategoryResponse(Category category);
}

