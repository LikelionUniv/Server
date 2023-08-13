package likelion.univ.domain.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public enum Part {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드"),
    PM("기획"),
    DESIGNER("디자인"),
    PM_DESIGNER("기획/디자인");

    private String value;


    private static final Map<String, Part> BY_VALUE =
            Stream.of(values()).collect(Collectors.toMap(Part::getValue, Function.identity()));


    public static Part converter(String value){
        return BY_VALUE.get(value);
    }
}
