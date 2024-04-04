package likelion.univ.common.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Slice;

@Getter
@Builder
public class SliceResponse<T> {

    private int currentPage;
    private int size;
    private Boolean hasNext;
    private List<T> data;

    public static SliceResponse of(Slice slice) {
        return SliceResponse.builder()
                .currentPage(slice.getNumber() + 1)
                .size(slice.getNumberOfElements())
                .hasNext(slice.hasNext())
                .data(slice.getContent())
                .build();
    }
}
