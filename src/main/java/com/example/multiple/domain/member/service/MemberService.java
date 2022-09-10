package com.example.multiple.domain.member.service;

import com.example.multiple.domain.member.dto.MemberDto;
import com.example.multiple.domain.member.entity.Member;
import com.example.multiple.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {

	private final MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public MemberDto saveMember(MemberDto memberDto) {
		Member savedMember = memberRepository.save(new Member(memberDto.getId()));
		return MemberDto.builder()
				.seq(savedMember.getSeq())
				.id(savedMember.getId())
				.build();
	}

	public MemberDto getMember(Integer seq) {
		MemberDto memberDto = null;
		Optional<Member> findMember = memberRepository.findById(seq);
		if (findMember.isPresent()) {
			Member member = findMember.get();
			memberDto = MemberDto.builder()
					.seq(member.getSeq())
					.id(member.getId())
					.build();
		}
		return memberDto;
	}
}
