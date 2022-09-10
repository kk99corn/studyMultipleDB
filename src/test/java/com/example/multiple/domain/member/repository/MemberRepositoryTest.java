package com.example.multiple.domain.member.repository;

import com.example.multiple.domain.member.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

	@Autowired
	MemberRepository memberRepository;

	@Test
	void test() {
		// Given
		String id = "test2";
		Member saveMember = memberRepository.save(new Member(id));

		// When
		Optional<Member> findMember = memberRepository.findById(saveMember.getSeq());

		// Then
		if (findMember.isPresent()) {
			Member member = findMember.get();

			assertThat(member.getSeq()).isEqualTo(saveMember.getSeq());
			assertThat(member.getId()).isEqualTo(id);
		}
	}
}