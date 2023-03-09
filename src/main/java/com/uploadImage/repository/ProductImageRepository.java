package com.uploadImage.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uploadImage.model.ProductImage;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
	Optional<ProductImage> findByName(String fileName);
}
