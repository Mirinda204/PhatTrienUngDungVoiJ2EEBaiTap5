package com.example.bookmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAllByDeletedFalse();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findByIdAndDeletedFalse(id);
    }

    public boolean existsById(Long id) {
        return productRepository.existsByIdAndDeletedFalse(id);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product productDetails) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setName(productDetails.getName());
        product.setImage(productDetails.getImage());
        product.setPrice(productDetails.getPrice());
        product.setCategoryName(productDetails.getCategoryName());
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setDeleted(true);
        productRepository.save(product);
    }
}
