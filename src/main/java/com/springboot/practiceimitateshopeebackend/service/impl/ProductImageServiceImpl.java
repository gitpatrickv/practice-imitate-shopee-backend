package com.springboot.practiceimitateshopeebackend.service.impl;

import com.springboot.practiceimitateshopeebackend.entity.Inventory;
import com.springboot.practiceimitateshopeebackend.entity.ProductImage;
import com.springboot.practiceimitateshopeebackend.repository.InventoryRepository;
import com.springboot.practiceimitateshopeebackend.repository.ProductImageRepository;
import com.springboot.practiceimitateshopeebackend.service.ProductImageService;
import com.springboot.practiceimitateshopeebackend.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductImageServiceImpl implements ProductImageService {

    private final ProductImageRepository productImageRepository;
    private final InventoryRepository inventoryRepository;

    @Override
    public void uploadPhoto(Long id, MultipartFile file) {
        log.info("Saving picture for user ID: {}", id);
        Optional<Inventory> inventory = inventoryRepository.findById(id);

        ProductImage productImage = new ProductImage();
        productImage.setInventory(inventory.get());
        productImage.setPhotoUrl(processImage(id,file));
        productImageRepository.save(productImage);
        log.info(productImage.getPhotoUrl());
    }

    private String getFileExtension(String filename){
        return Optional.of(filename)
                .filter(name -> name.contains("."))
                .map(name -> "." + name.substring(filename.lastIndexOf(".") + 1))
                .orElse(".png");
    }

    private String processImage(Long id, MultipartFile image){
        String filename = id + getFileExtension(image.getOriginalFilename());
        try {
            Path fileStorageLocation = Paths.get(StringUtils.PHOTO_DIRECTORY).toAbsolutePath().normalize();

            if(!Files.exists(fileStorageLocation)) {
                Files.createDirectories(fileStorageLocation);
            }

            Files.copy(image.getInputStream(), fileStorageLocation.resolve(filename), REPLACE_EXISTING);

            return ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/product/image/" + filename).toUriString();
        }
        catch (Exception exception) {
            throw new RuntimeException("Unable to save image");
        }
    }

}
