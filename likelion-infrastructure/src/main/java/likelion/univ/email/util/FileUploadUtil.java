package likelion.univ.email.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import likelion.univ.email.exception.CanNotReadAttachmentException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FileUploadUtil {

    public static MultipartFile convertPngToMultipartFile(String filePath) {
        Resource resource = new ClassPathResource(filePath);

        try {
            InputStream inputStream = resource.getInputStream();
            FileItem fileItem = new DiskFileItem(
                    resource.getFilename(),
                    "image/png",
                    false,
                    resource.getFilename(),
                    (int) resource.contentLength(),
                    new File(System.getProperty("java.io.tmpdir"))
            );

            try (OutputStream os = fileItem.getOutputStream()) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
            } finally {
                inputStream.close();
            }

            MultipartFile multipartFile = new CommonsMultipartFile(fileItem);

            return multipartFile;
        } catch (IOException e) {
            throw new CanNotReadAttachmentException();
        }
    }

}
