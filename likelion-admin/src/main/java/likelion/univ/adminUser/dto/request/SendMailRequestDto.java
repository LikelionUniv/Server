package likelion.univ.adminUser.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SendMailRequestDto {
    private String[] toAddress;
    private String title;
    private String body;
    private List<MultipartFile> files;
}
