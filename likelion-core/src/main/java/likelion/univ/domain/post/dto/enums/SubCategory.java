package likelion.univ.domain.post.dto.enums;



import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SubCategory {
     // HQ
     NOTICE("공지사항"),
     QNA("질문건의"),
     HQ_INFO("중앙정보공유"),

     // board
     FREE_INFO("멋대정보공유"),
     GET_MEMBER("팀원 모집"),
     GET_PROJECT("프로젝트 모집"),
     SHOWOFF("프로젝트 자랑"),

     // overflow
     FRONTEND("프론트"),
     BACKEND("백엔드"),
     PM("기획"),
     UXUI("디자인"),
     ETC("기타");

     private final String name;

}

