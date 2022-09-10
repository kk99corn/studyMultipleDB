package com.example.multiple.domain.member.repository;

import com.example.multiple.domain.member.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MemberRepositoryTest {

	@Autowired
	MemberRepository memberRepository;

	@Test
	void test() {
		List<Member> all = memberRepository.findAll();
		System.out.println(all);
	}
}