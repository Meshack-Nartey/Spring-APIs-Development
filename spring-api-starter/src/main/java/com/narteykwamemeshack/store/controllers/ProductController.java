package com.narteykwamemeshack.store.controllers;

import com.narteykwamemeshack.store.dtos.ProductDto;
import com.narteykwamemeshack.store.entities.Product;
import com.narteykwamemeshack.store.mappers.ProductMapper;
import com.narteykwamemeshack.store.repositories.ProductRepository;
import com.narteykwamemeshack.store.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

@GetMapping
    List<ProductDto> getAllProducts(@RequestParam(name = "categoryId", required = false) Byte categoryId)
{    List<Product> products;
    if(categoryId != null) {
        products = productRepository.findAllByCategoryId(categoryId);
    }
    else
        products = productRepository.findAll();

    return  products.stream().map(productMapper::toDto).toList();

    };
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(product -> ResponseEntity.ok(productMapper.toDto(product)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(
            @RequestBody ProductDto productDto,
            UriComponentsBuilder uriBuilder) {
        var category = categoryRepository.findById(productDto.getCategoryId()).orElse(null);
        if (category == null) {
            return ResponseEntity.badRequest().build();
        }

        var product = productMapper.toEntity(productDto);
        product.setCategory(category);
        productRepository.save(product);
        productDto.setId(product.getId());

        var uri = uriBuilder.path("/products/{id}").buildAndExpand(productDto.getId()).toUri();

        return ResponseEntity.created(uri).body(productDto);
    }

    //UPDATING A PRODUCT
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(
            @PathVariable(name = "id") long id,
            @RequestBody ProductDto productDto) {
       var category = categoryRepository.findById(productDto.getCategoryId()).orElse(null);
        if (category == null) {
            return ResponseEntity.notFound().build();
        }
       var product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
         productMapper.update(productDto, product);
         product.setCategory(category);
         productRepository.save(product);
         productDto.setId(product.getId());

         return ResponseEntity.ok(productDto);
    }

    //DELETING A PRODUCT
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable(name = "id") long id) {
        var product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        productRepository.delete(product);

        return ResponseEntity.noContent().build();
    }






}
