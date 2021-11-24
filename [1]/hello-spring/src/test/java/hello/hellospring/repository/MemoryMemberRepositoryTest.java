package hello.hellospring.repository;

import hello.hellospring.domain.Member;
//import org.assertj.core.api.Assertions;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    /*
    - @Test 장점: 클래스별로 모두 다 돌려볼 수 있다
    - class별로 테스트 될 때, 순서는 무작위이다
    - 그러므로 클래스간 영향이 끼치지 않도록 clear 해줘야 한다
    - TestCode(현 파일)을 먼저 만들고 개발하는 방식을 TDD라고 한다

     */

    // class가 끝나고 자동적 호출
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();
//        System.out.println("result = " + (result == member));
//        Assertions.assertEquals(result, member);
//        Assertions.assertEquals(result, null);

          assertThat(member).isEqualTo(result);
//        assertThat(member).isEqualTo(null);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        //Tip. copy&paste and update object: shift+6
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
//        Member result = repository.findByName("spring2").get();

        assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
//        assertThat(result.size()).isEqualTo(3);

    }


}
