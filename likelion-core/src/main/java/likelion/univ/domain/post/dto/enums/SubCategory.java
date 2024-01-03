package likelion.univ.domain.post.dto.enums;



import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SubCategory {
     // HQ
     NOTICE("공지사항"),
     QNA("질문건의"),
     //INFO("정보 공유"),

     // board
     INFO("정보공유"),
     GET_MEMBER("팀원 모집"),
     GET_PROJECT("프로젝트 모집"),
     SHOWOFF("프로젝트 자랑"),

     // overflow
     FRONTEND("프론트"),
     BACKEND("백엔드"),
     PM("기획"),
     UXUI("디자인"),
     ETC("기타");

     private final String title;

     public static SubCategory findByTitle(String title) {
          if (title.equals(NOTICE.title)) {
               return NOTICE;
          } else if (title.equals(QNA.title)) {
               return QNA;
          } else if (title.equals(INFO.title)) {
               return INFO;
          } else if (title.equals(GET_MEMBER.title)) {
               return GET_MEMBER;
          } else if (title.equals(GET_PROJECT.title)) {
               return GET_PROJECT;
          } else if (title.equals(SHOWOFF.title)) {
               return SHOWOFF;
          } else if (title.equals(FRONTEND.title)) {
               return FRONTEND;
          } else if (title.equals(BACKEND.title)) {
               return BACKEND;
          } else if (title.equals(PM.title)) {
               return PM;
          } else if (title.equals(UXUI.title)) {
               return UXUI;
          } else if (title.equals(ETC.title)) {
               return ETC;
          }
          return null;
     }
     public static boolean isValid(String title) {
          if (SubCategory.findByTitle(title) == null) {
               return false;
          }
          return true;
     }
}

