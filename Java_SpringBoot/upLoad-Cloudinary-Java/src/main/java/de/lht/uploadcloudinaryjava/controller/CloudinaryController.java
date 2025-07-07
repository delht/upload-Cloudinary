package de.lht.uploadcloudinaryjava.controller;

import de.lht.uploadcloudinaryjava.cloud.GetPubID;
import de.lht.uploadcloudinaryjava.cloud.repo.DeleteFile;
import de.lht.uploadcloudinaryjava.cloud.repo.UploadFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cloud")
@RequiredArgsConstructor
@Slf4j
public class CloudinaryController {

    private final UploadFile uploadFile;
    private final DeleteFile deleteFile;
    private final GetPubID getPubID;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        String folder = "FolderTest/Uploads/"; // File sẽ được upload vào thư mục này trên Cloudinary

        try {
            String url = uploadFile.uploadFile(file, folder);
            return ResponseEntity.ok(url);
        } catch (IOException e) {
            log.error("Upload thất bại", e);
            return ResponseEntity.internalServerError().body("Lỗi khi upload file: " + e.getMessage());
        }
    }

    @PostMapping("/upload-multiple")
    public ResponseEntity<?> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        String folder = "FolderTest/Uploads/";
        try {
            List<String> urls = new ArrayList<>();
            for (MultipartFile file : files) {
                if (file != null && !file.isEmpty()) {
                    String url = uploadFile.uploadFile(file, folder);
                    urls.add(url);
                }
            }
            return ResponseEntity.ok(urls);
        } catch (IOException e) {
            log.error("Lỗi upload nhiều file", e);
            return ResponseEntity.internalServerError().body("Lỗi khi upload nhiều file: " + e.getMessage());
        }
    }

//    =================================================================================================================

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteFileByUrl(@RequestParam("fileUrl") String fileUrl) {
        String folder = "FolderTest/Uploads/"; //Phải trùng với folder đã upload

        try {
            String publicId = getPubID.layPublicIdTuURL(fileUrl, folder);
            log.info("Public ID đã tách: {}", publicId);

            deleteFile.deleteFile(publicId);
            return ResponseEntity.ok("Đã xóa file với public ID: " + publicId);
        } catch (IOException e) {
            log.error("Lỗi khi xóa file theo URL", e);
            return ResponseEntity.internalServerError().body("Lỗi khi xóa file: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("URL không hợp lệ hoặc không tách được public ID.");
        }
    }

}
