package com.springboot.practiceimitateshopeebackend.controller;

import com.springboot.practiceimitateshopeebackend.service.ProductImageService;
import com.springboot.practiceimitateshopeebackend.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("/api/product/image")
@RequiredArgsConstructor
public class ProductImageController {

    private final ProductImageService productImageService;

    @PutMapping("/upload")
    public void uploadPhoto(@RequestParam(value = "id") Long id, @RequestParam(value = "file") MultipartFile file) {
        productImageService.uploadPhoto(id, file);
    }

    @GetMapping(path = "/{filename}", produces = {IMAGE_PNG_VALUE, IMAGE_JPEG_VALUE})
    public byte[] getPhoto(@PathVariable("filename") String filename) throws IOException {
        return Files.readAllBytes(Paths.get(StringUtils.PHOTO_DIRECTORY + filename));
    }
}