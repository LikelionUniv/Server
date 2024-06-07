package likelion.univ.domain.hackathon.entity;

public enum HackathonPart {

    PM("기획"),
    DESIGNER("디자인"),
    FRONTEND("프론트엔드"),
    BACKEND("백엔드"),
    ;

    private final String name;

    HackathonPart(String name) {
        this.name = name;
    }
}
