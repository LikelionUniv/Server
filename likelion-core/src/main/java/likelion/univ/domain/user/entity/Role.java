package likelion.univ.domain.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {

    GUEST("GUEST"),
    USER("USER"), /* 아기사자 */
    MANAGER("MANAGER"), /* 운영진 */
    UNIVERSITY_ADMIN("UNIVERSITY_ADMIN"), /* 대학대표 (대학관리자) */
    SUPER_ADMIN("SUPER_ADMIN"); /* 총관리자 */

    private String value;
}
