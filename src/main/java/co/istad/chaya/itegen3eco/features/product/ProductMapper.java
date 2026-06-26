package co.istad.chaya.itegen3eco.features.product;

import co.istad.chaya.itegen3eco.features.product.dto.CreateProductRequest;
import co.istad.chaya.itegen3eco.features.product.dto.ProductResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product mapCreateProductRequestToProduct(CreateProductRequest createProductRequest);

    ProductResponse mapProductToProductResponse(Product product);

}
