package com.uploadImage.serviceImpl;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.uploadImage.model.ProductImage;
import com.uploadImage.repository.ProductImageRepository;
import com.uploadImage.service.ProductImageService;
import com.uploadImage.util.ImageUtil;

@Service
public class ProductImageServiceImpl implements ProductImageService {
	@Autowired
	private ProductImageRepository imageRepo;

	@Transactional
	@Override
	public String downloadImage(String fileName) {
		try {
			Optional<ProductImage> imageData = imageRepo.findByName(fileName);
			byte[] image = ImageUtil.decompressImage(imageData.get().getImageData());
			String base64EncodedImageBytes = Base64.getEncoder().encodeToString(image);
			return base64EncodedImageBytes;
		} catch (Exception e) {
			e.printStackTrace();
			return "No image Available!!!!!!!";
		}
	}

	@Override
	public String uploadImages(MultipartFile file) throws IOException {
		ProductImage productImage = imageRepo.save(ProductImage.builder().name(file.getOriginalFilename())
				.type(file.getContentType()).imageData(ImageUtil.compressImage(file.getBytes())).build());
		if (productImage != null) {
			return "file uploaded Sucessfully :" + file.getOriginalFilename();
		}
		return null;
	}

}
