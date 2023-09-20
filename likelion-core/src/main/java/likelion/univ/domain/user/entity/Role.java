package likelion.univ.domain.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {

    GUEST("GUEST"),
    USER("USER"),
    MANAGER("MANAGER"),
    ADMIN("ADMIN"),
    CODEIT_ADMIN("CODEIT_ADMIN"),
    SUPER_ADMIN("SUPER_ADMIN");

    private String value;
}
