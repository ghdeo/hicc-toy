package hicc.toy.repository;

import hicc.toy.domain.member.Member;
import hicc.toy.domain.member.MemberRole;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @AfterEach
    void afterEach() {
        memberRepository.deleteAll();
    }

    @Test
    void save() throws Exception {
        //given
        Member member = Member.builder()
                .memberRole(MemberRole.NON_MEMBER)
                .build();

        //when
        Member save = memberRepository.save(member);

        //then
        Member foundMember = memberRepository.findById(save.getId()).get();
        assertThat(foundMember.getId()).isEqualTo(member.getId());
        assertThat(foundMember.getMemberRole()).isEqualTo(member.getMemberRole());
    }

}