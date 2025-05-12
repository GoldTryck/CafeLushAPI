package com.daw.CafeLushAPI.services.impl;

import com.daw.CafeLushAPI.dtos.request.ProductRequest;
import com.daw.CafeLushAPI.dtos.response.ProductResponse;
import com.daw.CafeLushAPI.services.ProductService;
import com.daw.CafeLushAPI.models.entities.Product;
import com.daw.CafeLushAPI.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public ProductResponse create(ProductRequest request) {
        Product product = modelMapper.map(request, Product.class);
        product = productRepository.save(product);
        return modelMapper.map(product, ProductResponse.class);
    }

    @Override
    public ProductResponse findById(Long id) {
        return null;
    }

    @Override
    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(product -> modelMapper.map(product, ProductResponse.class))
                .toList();
    }

    @Override
    public ProductResponse update(Long id, ProductRequest request) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
