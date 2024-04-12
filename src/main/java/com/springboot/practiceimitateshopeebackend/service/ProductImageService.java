package com.springboot.practiceimitateshopeebackend.service;

import org.springframework.web.multipart.MultipartFile;

public interface ProductImageService {

    void uploadPhoto(Long id, MultipartFile file);
}
