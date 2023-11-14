package likelion.univ.common.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Slice;

import java.util.List;

@Getter
@Builder
public class SliceResponse<T> {

    private int currentPage;
    private int size;
    private Boolean hasNext;
    private List<T> data;

    public static SliceResponse of(Slice slice) {
        return SliceResponse.builder()
                .currentPage(slice.getNumber()+1)
                .size(slice.getNumberOfElements())
                .hasNext(slice.hasNext())
                .data(slice.getContent())
                .build();
    }
}
