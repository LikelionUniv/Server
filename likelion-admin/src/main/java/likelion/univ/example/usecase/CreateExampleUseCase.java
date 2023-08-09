package likelion.univ.example.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.example.adaptor.ExampleAdaptor;
import likelion.univ.domain.example.service.ExampleDomainService;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.service.UserDomainService;
import likelion.univ.example.dto.request.CreateExampleRequestDto;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CreateExampleUseCase {

    /* 예시로 적어놓은 유저어댑터 및 도메인서비스입니다. 필요하지않는 싱글톤 인스턴스들은 제거해주세요. */
    private final UserAdaptor userAdaptor;
    private final UserDomainService userDomainService;

    private final ExampleAdaptor exampleAdaptor;
    private final ExampleDomainService exampleDomainService;

    /*
        하나의 usecase엔 하나의 기능만 담기
        여기엔 여러개의 domainservice및 adapter을 호출하여 사용 할 수 있습니다.
        Transaction을 배제한 도메인에 영향을 주지않는 service라고 생각하시면 쉽습니다.
        Transaction의 영역의 로직은 domainservice 혹의 엔티티에 구현해주세요. (but, 엔티티에 구현은 최대한 자제)(예를들어 유저의경우 changeProfile 같은걸 엔티티에 구현)
    */
    public void excute(CreateExampleRequestDto createExampleRequestDto){
        exampleDomainService.createExample(createExampleRequestDto.getBody());
    }
}
