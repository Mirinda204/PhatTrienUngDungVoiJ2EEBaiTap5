package com.example.bookmanagement;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Nạp dữ liệu mẫu khi ứng dụng khởi động (để dễ kiểm thử API).
 */
@Component
public class DataLoader implements CommandLineRunner {

    private final ProductRepository productRepository;

    public DataLoader(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) {
        try {
            if (productRepository.count() == 0) {
                productRepository.save(new Product(
                        "ASUS ROG Zephyrus G14 (2024) - Ryzen 9 / RTX 4070",
                        "https://picsum.photos/seed/rog-g14/200/200",
                        44990000.0,
                        "Laptop"
                ));
                productRepository.save(new Product(
                        "Apple iPhone 15 Pro Max 256GB",
                        "https://picsum.photos/seed/iphone-15-pro-max/200/200",
                        33990000.0,
                        "Điện thoại"
                ));
                productRepository.save(new Product(
                        "Samsung Galaxy S24 Ultra 512GB",
                        "https://picsum.photos/seed/galaxy-s24-ultra/200/200",
                        36490000.0,
                        "Điện thoại"
                ));
                productRepository.save(new Product(
                        "Sony WH-1000XM5 Noise Cancelling Headphones",
                        "https://picsum.photos/seed/sony-xm5/200/200",
                        7990000.0,
                        "Âm thanh"
                ));
                System.out.println("✅ Sample products loaded successfully!");
            }
        } catch (Exception e) {
            System.err.println("⚠️ Error loading sample data: " + e.getMessage());
            e.printStackTrace();
        }
    }
}


