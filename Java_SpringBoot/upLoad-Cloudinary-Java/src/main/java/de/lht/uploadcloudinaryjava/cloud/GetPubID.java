package de.lht.uploadcloudinaryjava.cloud;

import org.springframework.stereotype.Component;

@Component
public class GetPubID {
    public String layPublicIdTuURL(String fileUrl, String folder) {
        int start = fileUrl.indexOf(folder); //tim vi tri dau tien cua path folder
        return fileUrl.substring(start); //cat chuoi tu vi tri start
    }
}
