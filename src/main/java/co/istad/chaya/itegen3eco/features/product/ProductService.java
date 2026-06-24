package co.istad.chaya.itegen3eco.features.product;

import co.istad.chaya.itegen3eco.features.product.dto.CreateProductRequest;
import co.istad.chaya.itegen3eco.features.product.dto.ProductResponse;
import org.springframework.data.domain.Page;

public interface ProductService {


    Page<ProductResponse> findAll(int pageNumber, int pageSize);

/**
 * Create a new product
 * @param createProductRequest  is requesting data for creating product
 * @return f@link ProductResponse
 *
 * */
ProductResponse createNew (CreateProductRequest createProductRequest);
}
