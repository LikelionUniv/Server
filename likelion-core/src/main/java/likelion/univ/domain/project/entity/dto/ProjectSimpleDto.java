package likelion.univ.domain.project.dto;

import likelion.univ.domain.project.entity.Image;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.entity.ProjectMember;
import likelion.univ.domain.project.entity.dto.ImageSimpleDto;
import likelion.univ.domain.project.entity.dto.ProjectMemberDto;
import likelion.univ.domain.project.entity.enums.Output;
import likelion.univ.domain.user.entity.User;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ProjectSimpleDto {

    private String thon;
    private Output outPut;
    private String serviceName;
    private int ordinal;
    private String univ;
    private LocalDate startDate;
    private LocalDate endDate;
    private String tech;
    private String description;
    private String content;
    private String projectUrl;
    private List<ImageSimpleDto> images;
    private List<ProjectMemberDto> members;

    public ProjectSimpleDto() {
    }

//    public ProjectSimpleDto(Project project, List<Image> images, List<ProjectMember> users) {
//        this.thon = project.getThon();
//        this.outPut = project.getOutPut();
//        this.service = project.getService();
//        this.year = project.getYear();
//        this.univ = project.getUniv();
//        this.startDate = project.getStartDate();
//        this.endDate = project.getEndDate();
//        this.tech = project.getTech();
//        this.description = project.getDescription();
//        this.content = project.getContent();
//        this.link = project.getLink();
//        this.images = images.stream()
//                .map(image -> new ImageSimpleDto(image))
//                .collect(Collectors.toList());
//        this.members = users.stream()
//                .map(user -> new ProjectMemberDto(user.getUser()))
//                .collect(Collectors.toList());
//    }

    public ProjectSimpleDto(Project project, List<Image> images, List<User> users) {
        this.thon = project.getThon();
        this.outPut = project.getOutPut();
        this.serviceName = project.getServiceName();
        this.ordinal = project.getOrdinal();
        this.univ = project.getUniv();
        this.startDate = project.getStartDate();
        this.endDate = project.getEndDate();
        this.tech = project.getTech();
        this.description = project.getDescription();
        this.content = project.getContent();
        this.projectUrl = project.getProjectUrl();
        this.images = images.stream()
                .map(image -> new ImageSimpleDto(image))
                .collect(Collectors.toList());
        this.members = users.stream()
                .map(user -> new ProjectMemberDto(user.getId(), user.getProfile().getName()))
                .collect(Collectors.toList());
    }

//    public ProjectSimpleDto(Project project, List<Image> images, List<ProjectMemberDto> users) {
//        this.thon = project.getThon();
//        this.outPut = project.getOutPut();
//        this.service = project.getService();
//        this.year = project.getYear();
//        this.univ = project.getUniv();
//        this.startDate = project.getStartDate();
//        this.endDate = project.getEndDate();
//        this.tech = project.getTech();
//        this.description = project.getDescription();
//        this.content = project.getContent();
//        this.link = project.getLink();
//        this.images = images.stream()
//                .map(image -> new ImageSimpleDto(image))
//                .collect(Collectors.toList());
//        this.members = users;
//    }

//    public ProjectSimpleDto(String thon, Output outPut, String service, int year, String univ, LocalDate startDate, LocalDate endDate, String tech, String description, String content, String link, List<ImageSimpleDto> images, List<ProjectMemberDto> members) {
//        this.thon = thon;
//        this.outPut = outPut;
//        this.service = service;
//        this.year = year;
//        this.univ = univ;
//        this.startDate = startDate;
//        this.endDate = endDate;
//        this.tech = tech;
//        this.description = description;
//        this.content = content;
//        this.link = link;
//        this.images = images;
//        this.members = members;
//    }
}
