package com.uploadImage.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.uploadImage.model.ProductImage;

public interface ProductImageService {

	public String downloadImage(String fileName);

	public String uploadImages(MultipartFile file) throws IOException;
}
