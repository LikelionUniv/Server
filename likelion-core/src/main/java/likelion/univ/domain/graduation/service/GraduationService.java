package likelion.univ.domain.graduation.service;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import likelion.univ.domain.graduation.entity.Graduation;
import likelion.univ.domain.graduation.exception.GraduationNotFoundException;
import likelion.univ.domain.graduation.repository.GraduationRepository;
import likelion.univ.domain.graduation.service.command.GraduationsCreateCommand;
import likelion.univ.domain.user.entity.User;
import likelion.univ.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class GraduationService {
    private final GraduationRepository graduationRepository;
    private final UserRepository userRepository;

    public void createGraduation(GraduationsCreateCommand command) {
        List<User> users = userRepository.findAllByIdIn(command.userId());
        List<Graduation> graduations = users.stream().map(command::toGraduations).toList();
        graduationRepository.saveAll(graduations);
    }

    @Transactional(readOnly = true)
    public Graduation find(Long userId, Long ordinal) {
        return graduationRepository.findTopByUserIdAndOrdinal(userId, ordinal)
                .orElseThrow(GraduationNotFoundException::new);
    }

    public void updateFileUrl(Graduation graduation, String fileUrl) {
        graduation.fileUpload(fileUrl);
        graduationRepository.save(graduation);
    }

    // pdf 생성
    public File generatePdf(Graduation graduation, String fileName) {
        String htmlName = "index.html";
        try {
            String baseUri = "templates/" + htmlName;
            String htmlContent = readHtml(baseUri);

            // 변수 치환 - 타임리프 사용 x
            htmlContent = htmlContent.replace("{{userName}}", graduation.getUser().getProfile().getName());
            htmlContent = htmlContent.replace("{{graduationId}}", String.format("%06d", graduation.getId()));
            htmlContent = htmlContent.replace(" {{ordinal}}", graduation.getOrdinal().toString());
            htmlContent = htmlContent.replace(" {{ordinal}}", graduation.getOrdinal().toString());
            htmlContent = htmlContent.replace("{{universityName}}", graduation.getUser().getUniversityInfo().getUniversity().getName());
            htmlContent = htmlContent.replace("{{part}}", graduation.getUser().getProfile().getPart().getValue());


            File pdfFile = File.createTempFile(fileName, ".pdf");

            OutputStream os = new FileOutputStream(pdfFile);
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFont(() -> getClass().getResourceAsStream("/static/font/Pretendard-Medium.ttf"), "Pretendard-Medium");
            builder.useFont(() -> getClass().getResourceAsStream("/static/font/Pretendard-SemiBold.ttf"), "Pretendard-SemiBold");
            builder.useFont(() -> getClass().getResourceAsStream("/static/font/Pretendard-Light.ttf"), "Pretendard-Light");
            builder.useFastMode();
            builder.withHtmlContent(htmlContent, baseUri); // Pass base URI for relative paths
            builder.toStream(os);
            builder.run();
            return pdfFile;
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert HTML to PDF", e);
        }
    }

    // jar 로 변환 했을때 인식 못하는 에러 해결용
    private String readHtml(String htmlPath) throws Exception {
        ClassPathResource resource = new ClassPathResource(htmlPath);

        try (InputStreamReader inputStreamReader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

            // HTML 내용을 String으로 변환
            return bufferedReader.lines().collect(Collectors.joining(System.lineSeparator()));
        }
    }
}
