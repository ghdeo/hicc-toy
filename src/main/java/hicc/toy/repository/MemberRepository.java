package hicc.toy.repository;

import hicc.toy.domain.member.Member;

public interface MemberRepository {
    void save(Member member);

    Member findById(Long memberId);

    void clear();
}
