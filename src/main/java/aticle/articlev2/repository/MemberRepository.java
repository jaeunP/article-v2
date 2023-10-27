package aticle.articlev2.repository;

import aticle.articlev2.entity.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface MemberRepository extends CrudRepository<Member, Long> {

    Optional<Member> findByUsername(String username);

    Optional<Member> findAllByUsername(String username);
}
