package likelion.univ.domain.user.repository.searchcondition;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserSearchCondition {
    private String name;
    private String university;
    private String part;
}
