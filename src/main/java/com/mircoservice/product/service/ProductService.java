package com.mircoservice.product.service;

import com.mircoservice.product.model.entity.Product;
import com.mircoservice.product.model.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ProductService {

    final private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product create(Product product) {

        return productRepository.save(product);
    }

    public Optional<Product> findOne(Long id) {

        return productRepository.findById(id);
    }

    public Iterable<Product> findAll() {

        return productRepository.findAll();
    }

    public void removeOne(Long id) {

        productRepository.deleteById(id);
    }
}
