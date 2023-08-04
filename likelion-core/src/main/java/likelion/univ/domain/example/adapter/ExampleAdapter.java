package likelion.univ.domain.example.adapter;

import likelion.univ.domain.example.entity.Example;
import likelion.univ.domain.example.exception.ExampleNotFoundException;
import likelion.univ.domain.example.repository.ExampleRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExampleAdapter {
    private final ExampleRepository exampleRepository;

    public Example save(Example example) {
        return exampleRepository.save(example);
    }
    public Example findById(Long id){
        return exampleRepository.findById(id)
                .orElseThrow(() -> new ExampleNotFoundException());
    }
}
