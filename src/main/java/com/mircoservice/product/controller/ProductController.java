package com.mircoservice.product.controller;

import com.mircoservice.product.model.dto.ProductData;
import com.mircoservice.product.model.dto.ResponseData;
import com.mircoservice.product.model.entity.Product;
import com.mircoservice.product.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    // Inject Service
    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    @PostMapping
    public ResponseEntity<ResponseData<Product>> create(@RequestBody ProductData productData, Errors errors) {

        ResponseData<Product> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessage().add(error.getDefaultMessage());
//                 log.debug(" errornya adalah {}", error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Product product = modelMapper.map(productData, Product.class);

        responseData.setStatus(true);
        responseData.setPayload(productService.create(product));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Product>> findById(@PathVariable("id") Long id) {

        ResponseData<Product> responseData = new ResponseData<>();

        var val = productService.findOne(id);

        if (val.isPresent()) {
            responseData.setStatus(true);
            responseData.setPayload(val.get());
            return ResponseEntity.ok(responseData);
        }

        responseData.getMessage().add("Product with id " + id + " not found");
        responseData.setStatus(false);
        responseData.setPayload(null);

        return ResponseEntity.ok(responseData);

    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity handleTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping
    public Iterable<Product> findAll() {
        return productService.findAll();
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id") Long id) {
        productService.removeOne(id);
    }
}
