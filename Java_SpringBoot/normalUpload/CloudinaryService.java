package de.lht.testuploadcloudinary;

import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {
    private final Cloudinary cloudinary;

    public CloudinaryService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public Map upload(MultipartFile file) {
        try {
            Map uploadOptions = Map.of("resource_type", "auto");
            return this.cloudinary.uploader().upload(file.getBytes(), uploadOptions);
//            return this.cloudinary.uploader().upload(file.getBytes(), Map.of()); /chi up duoc moi hinh anh
        } catch (IOException io) {
            throw new RuntimeException("Image upload fail");
        }
    }
}


