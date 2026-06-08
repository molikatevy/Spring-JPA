package co.istad.chaya.itegen3eco.mapper;

import co.istad.chaya.itegen3eco.domain.Category;
import co.istad.chaya.itegen3eco.dto.CategoryResponse;
import co.istad.chaya.itegen3eco.dto.CreateCategoryRequest;
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

