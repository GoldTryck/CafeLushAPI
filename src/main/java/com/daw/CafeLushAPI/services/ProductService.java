package com.daw.CafeLushAPI.services;

import com.daw.CafeLushAPI.dtos.request.ProductRequest;
import com.daw.CafeLushAPI.dtos.response.ProductResponse;
import java.util.List;

public interface ProductService {
    ProductResponse create(ProductRequest request);

    ProductResponse findById(Long id);

    List<ProductResponse> findAll();

    ProductResponse update(Long id, ProductRequest request);

    void delete(Long id);
}
