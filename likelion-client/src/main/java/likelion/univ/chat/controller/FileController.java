package likelion.univ.chat.controller;

import likelion.univ.chat.usecase.FileUseCase;
import likelion.univ.domain.chat.entity.FileUrl;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@Controller
@RequestMapping("/file")
public class FileController {
    private final FileUseCase fileService;


    @PostMapping("/upload")
    @ResponseBody
    public ResponseEntity<String> handleFileUpload(@RequestBody FileUploadRequest request) {
        String fileUrl = request.getUrl();
        String roomId = request.getRoomId();

        return ResponseEntity.ok("Success");
    }
    @GetMapping("/get")
    @ResponseBody
    public List<FileUrl> fileDetail() { return fileService.findAllFile(); }

    @Data
    static class FileUploadRequest {
        private String url;
        private String roomId;
    }

}
