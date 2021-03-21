package nextstep.subway.member.application;

import nextstep.subway.auth.infrastructure.UserDetailService;
import nextstep.subway.exceptions.NotFoundUserException;
import nextstep.subway.member.domain.LoginMember;
import nextstep.subway.member.domain.Member;
import nextstep.subway.member.domain.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailService {
    private MemberRepository memberRepository;

    public CustomUserDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public LoginMember loadUserByUsername(String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(NotFoundUserException::new);
        return LoginMember.of(member);
    }
}
