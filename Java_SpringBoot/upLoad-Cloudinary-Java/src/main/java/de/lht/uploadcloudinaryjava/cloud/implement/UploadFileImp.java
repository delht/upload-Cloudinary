package de.lht.uploadcloudinaryjava.cloud.implement;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import de.lht.uploadcloudinaryjava.cloud.repo.UploadFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UploadFileImp implements UploadFile {
    private final Cloudinary cloudinary;

    @Override
    public String uploadFile(MultipartFile file, String foldername) throws IOException {

        assert file.getOriginalFilename() != null;

        String publicValue = generatePublicValue(file.getOriginalFilename()); //tao public id (dung UUID)
        log.info("publicValue : {}", publicValue);

        String extension = getFileName(file.getOriginalFilename())[1]; //lay phan duoi sau file (jpg, mp3,...)
        log.info("extension : {}", extension);

        String filePublicId =  publicValue;

        //file tam luu trong service trc khi up len cloud
        File fileUpload = convert(file);
        log.info("fileupload is: {}", fileUpload);


//        cloudinary.uploader().upload(fileUpload, ObjectUtils.asMap("public_id", filePublicId, "resource_type", "raw"));
        cloudinary.uploader().upload(fileUpload, ObjectUtils.asMap("public_id", filePublicId, "folder", foldername , "resource_type", "raw"));
        cleanDisk(fileUpload);

        //tao url thong qua lib cloud (url + folder + pub id)
        String filePath = cloudinary.url().resourceType("raw").generate(StringUtils.join( foldername,filePublicId, ".", extension));
        log.info("Uploaded file URL: {}", filePath);

        return filePath;
    }

    //Chuyen doi file tu pc thanh file de cloud xu ly
    private File convert(MultipartFile file) throws IOException {
        assert file.getOriginalFilename() != null;
        File convFile = new File(StringUtils.join(generatePublicValue(file.getOriginalFilename()), ".", getFileName(file.getOriginalFilename())[1]));

        try(InputStream is = file.getInputStream()) {
            Files.copy(is, convFile.toPath());
        }
        return convFile;
    }


    private void cleanDisk(File file) {
        try {
            log.info("file.toPath(): {}", file.toPath());
            Path filePath = file.toPath();
            Files.delete(filePath);
        } catch (IOException e) {
            log.error("Loi khi xoa file tam");
        }
    }

    //Tao pub id
    public String generatePublicValue(String originalName){
        String fileName = getFileName(originalName)[0];
        return StringUtils.join(UUID.randomUUID().toString(), "_", fileName);
    }

    //tach ten file va phan duoi sau dau cham (.)
    public String[] getFileName(String originalName) {
        return originalName.split("\\.");
    }

}
