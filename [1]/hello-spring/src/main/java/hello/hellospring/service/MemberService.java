package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    //회원 서비스를 만드려면, 회원 레파지토리가 존재해야 한다
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /**
     * 회원가입
     */
    public Long join(Member member){

//        같은 이름 중복 회원 x
//        Optional<Member> result = memberRepository.findByName(member.getName());
//         과거에는 if null 이렇게 썼다면, 요즈음은 Optional로 감싸서 작성
//         Member member1 = result.get();
//         result.orElseGet() null이 아니면 꺼내!
//
//         refactoring: ctrl+shift+alt+T -> extract method
        validateDuplicateMember(member); // 중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
            try {
                throw new IllegalAccessException("이미 존재하는 회원입니다.");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
