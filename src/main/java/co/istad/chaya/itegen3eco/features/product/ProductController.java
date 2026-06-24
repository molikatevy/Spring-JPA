package co.istad.chaya.itegen3eco.features.product;

import co.istad.chaya.itegen3eco.features.product.dto.CreateProductRequest;
import co.istad.chaya.itegen3eco.features.product.dto.ProductResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;


    @GetMapping
    public Page<ProductResponse> findAll(@RequestParam(required = false,defaultValue = "0") int pageNumber,
                                         @RequestParam(required = false,defaultValue = "10") int pageSize
                                         ){
        return productService.findAll(pageNumber,pageSize);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ProductResponse createNew(@Valid @RequestBody CreateProductRequest createProductRequest) {
        return productService.createNew(createProductRequest);
    }
}
