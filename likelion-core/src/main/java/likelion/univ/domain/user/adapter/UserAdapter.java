package likelion.univ.domain.user.adapter;

import likelion.univ.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserAdapter {
    private final UserRepository userRepository;
}
