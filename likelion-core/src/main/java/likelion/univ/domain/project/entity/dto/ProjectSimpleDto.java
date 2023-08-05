package likelion.univ.domain.project.dto;

import likelion.univ.domain.project.entity.Image;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.entity.dto.ImageSimpleDto;
import likelion.univ.domain.project.entity.enums.Output;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ProjectSimpleDto {

    private long projectId;
    private String thon;
    private Output outPut;
    private String service;
    private int year;
    private String univ;
    private LocalDate startDate;
    private LocalDate endDate;
    private String member;
    private String tech;
    private String description;
    private String content;
    private String link;
    private List<ImageSimpleDto> images;

    public ProjectSimpleDto(Project project, List<Image> images) {
        this.projectId = project.getId();
        this.thon = project.getThon();
        this.outPut = project.getOutPut();
        this.service = project.getService();
        this.year = project.getYear();
        this.univ = project.getUniv();
        this.startDate = project.getStartDate();
        this.endDate = project.getEndDate();
        this.member = project.getMember();
        this.tech = project.getTech();
        this.description = project.getDescription();
        this.content = project.getContent();
        this.link = project.getLink();
        this.images = images.stream()
                .map(image -> new ImageSimpleDto(image))
                .collect(Collectors.toList());
    }
}
