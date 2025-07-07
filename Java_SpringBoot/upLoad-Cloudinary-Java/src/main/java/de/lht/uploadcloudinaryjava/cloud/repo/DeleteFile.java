package de.lht.uploadcloudinaryjava.cloud.repo;

import java.io.IOException;

public interface DeleteFile {
    void deleteFile(String publicId) throws IOException;
}
