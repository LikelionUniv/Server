package likelion.univ.domain.example.adaptor;

import likelion.univ.annotation.Adaptor;
import likelion.univ.domain.example.entity.Example;
import likelion.univ.domain.example.exception.ExampleNotFoundException;
import likelion.univ.domain.example.repository.ExampleRepository;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class ExampleAdaptor {
    private final ExampleRepository exampleRepository;

    public void save(Example example) {
        exampleRepository.save(example);
    }
    public Example findById(Long id){
        return exampleRepository.findById(id)
                .orElseThrow(() -> new ExampleNotFoundException());
    }
    public void delete(Example example) {exampleRepository.delete(example);
    }
}
