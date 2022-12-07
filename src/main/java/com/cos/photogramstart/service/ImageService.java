package com.cos.photogramstart.service;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.web.dto.image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ImageService {

    private final ImageRepository imageRepository;

    @Transactional
    public Page<Image> 이미지스토리(int principalId, Pageable pageable){
        Page<Image> images= imageRepository.mStory(principalId, pageable);
        return images;
    }


    @Value("${file.path}")
    private String uploadFolder;
    @Transactional
    public void 사진업로드(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails){
        UUID uuid = UUID.randomUUID();
        String imageFileName= uuid+"_"+imageUploadDto.getFile().getOriginalFilename();

        Path imageFilePath = Paths.get(uploadFolder+imageFileName);

        try{
            Files.write(imageFilePath, imageUploadDto.getFile().getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }

        Image image= imageUploadDto.toEntity(imageFileName, principalDetails.getUser());
        Image imageEntity= imageRepository.save(image);


    }

}
