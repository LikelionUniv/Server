package likelion.univ.common.processor;

import java.util.List;
import likelion.univ.annotation.Processor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

@Processor
public class ConvertSliceProcessor<T> {

    public Slice<T> execute(List<T> contents, Pageable pageable) {
        if (hasNext(contents, pageable)) {
            return new SliceImpl<>(subContentOne(contents, pageable), pageable, true);
        } else {
            return new SliceImpl<>(contents, pageable, false);
        }
    }

    private Boolean hasNext(List<T> content, Pageable pageable) {
        return pageable.isPaged() && content.size() > pageable.getPageSize();
    }

    private List<T> subContentOne(List<T> content, Pageable pageable) {
        return content.subList(0, pageable.getPageSize());
    }
}
