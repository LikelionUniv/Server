package likelion.univ.user.service;

import likelion.univ.domain.graduation.entity.Graduation;
import likelion.univ.domain.graduation.service.GraduationService;
import likelion.univ.domain.user.entity.Profile;
import likelion.univ.domain.user.entity.User;
import likelion.univ.domain.user.service.UserService;
import likelion.univ.s3.S3FileUploadProcess;
import likelion.univ.user.dto.request.ProfileEditRequestDto;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

@RequiredArgsConstructor
@Transactional
@Service
public class ClientUserService {

    private final AuthenticatedUserUtils authenticatedUserUtils;
    private final UserService userService;
    private final GraduationService graduationService;
    private final S3FileUploadProcess s3FileUploadProcess;

    public void editProfile(Long userId, ProfileEditRequestDto profileEditRequestDto) {
        authenticatedUserUtils.checkIdentification(userId);
        Profile profile = profileEditRequestDto.toProfile();
        userService.editProfile(userId, profile);
    }

    public String generateGraduationPdf(Long userId, Long ordinal) {
        Graduation graduation = graduationService.find(userId, ordinal);

        if (graduation.getFileUrl() != null) {
            return graduation.getFileUrl();
        }

        User user = graduation.getUser();
        String fileName = "멋쟁이사자처럼-" + graduation.getOrdinal() + "기-수료증";
        String fileUri = "수료증/" + user.getId() + "/멋쟁이사자처럼-12기-수료증.pdf";

        File file = graduationService.generatePdf(graduation, fileName);
        String fileS3Url = s3FileUploadProcess.execute(fileUri, file);

        graduationService.updateFileUrl(graduation, fileS3Url);
        return fileS3Url;
    }
}
