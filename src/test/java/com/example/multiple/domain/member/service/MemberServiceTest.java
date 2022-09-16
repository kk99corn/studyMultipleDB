package com.example.multiple.domain.member.service;

import com.example.multiple.domain.member.dto.MemberDto;
import com.example.multiple.domain.member.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional("memberTransactionManager")
class MemberServiceTest {

	@Autowired
	MemberService memberService;

	@Test
	void test() {
		// Given
		String id = "memberservicetest";
		MemberDto memberDto = MemberDto.builder()
				.id(id)
				.build();

		// When
		memberDto = memberService.saveMember(memberDto);

		// Then
		assertThat(memberDto.getSeq()).isGreaterThan(0);
		assertThat(memberDto.getId()).isEqualTo(id);
	}
}