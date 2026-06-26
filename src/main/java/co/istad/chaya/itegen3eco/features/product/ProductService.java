package co.istad.chaya.itegen3eco.features.product;

import co.istad.chaya.itegen3eco.features.product.dto.CreateProductRequest;
import co.istad.chaya.itegen3eco.features.product.dto.ProductResponse;
import org.springframework.data.domain.Page;

public interface ProductService {


    /**
     * Find products by pagination
     * @param pageNumber
     * @param pageSize
     * @return
     */
    Page<ProductResponse> findAll(int pageNumber, int pageSize);


    /**
     * Create a new product
     * @param createProductRequest is requesting data for creating product
     * @return {@link ProductResponse}
     * @author tang_sengkim
     * @since 23-June-2026
     */
    ProductResponse createNew(CreateProductRequest createProductRequest);



}
