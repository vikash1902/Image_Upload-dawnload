package com.uploadImage.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.uploadImage.service.ProductImageService;

@RestController
@RequestMapping("/image")
public class ActionController {

	@Autowired
	private ProductImageService productImageService;

	@PostMapping()
	public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
		System.err.print("hello");
		String test = productImageService.uploadImages(file);
		return ResponseEntity.status(HttpStatus.OK).body(test);
	}

	@GetMapping("/{filename}")
	public ResponseEntity<?> downloadImage(@PathVariable("filename") String fileName) {
		String image = productImageService.downloadImage(fileName);
		if (image != null && !(image.equals(""))) {
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/jpg")).body(image);
		}
		return ResponseEntity.status(HttpStatus.OK).body(image);
	}

}