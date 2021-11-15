package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    // Optional: 값이 null로 들어올 때 방지하기 위한 것
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    //모든 회원 반환
    List<Member> findAll();

}
