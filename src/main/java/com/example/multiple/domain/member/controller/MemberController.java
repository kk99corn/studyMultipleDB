package com.example.multiple.domain.member.controller;

import com.example.multiple.domain.member.dto.MemberDto;
import com.example.multiple.domain.member.service.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

	private final MemberService memberService;

	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@GetMapping("/member/{seq}")
	public MemberDto getMember(@PathVariable(name = "seq") Integer seq) {
		return memberService.getMember(seq);
	}

	@PostMapping("/member/{id}")
	public MemberDto saveMember(@PathVariable(name = "id") String id) {
		return memberService.saveMember(MemberDto.builder().id(id).build());
	}
}
