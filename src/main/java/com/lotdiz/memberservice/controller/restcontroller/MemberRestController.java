package com.lotdiz.memberservice.controller.restcontroller;

import com.lotdiz.memberservice.dto.request.MemberInfoForChangeRequestDto;
import com.lotdiz.memberservice.dto.request.MemberInfoForSignUpRequestDto;
import com.lotdiz.memberservice.dto.response.MemberInfoForQueryResponseDto;
import com.lotdiz.memberservice.dto.response.ResultDataResponse;
import com.lotdiz.memberservice.entity.Member;
import com.lotdiz.memberservice.service.MemberService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberRestController {

  private final MemberService memberService;

  @PostMapping("/sign-up")
  public ResponseEntity<ResultDataResponse<Object>> signup(
      @Valid @RequestBody MemberInfoForSignUpRequestDto memberInfoForSignUpRequestDto) {
    memberService.signup(memberInfoForSignUpRequestDto);
    return ResponseEntity.ok()
        .body(
            new ResultDataResponse<>(
                String.valueOf(HttpStatus.OK.value()), HttpStatus.OK.name(), "회원 가입 성공", null));
  }

  @GetMapping("/members")
  public ResponseEntity<ResultDataResponse<MemberInfoForQueryResponseDto>> showMember(
      @RequestHeader Long memberId) {
    return ResponseEntity.ok()
        .body(
            new ResultDataResponse<>(
                String.valueOf(HttpStatus.OK.value()),
                HttpStatus.OK.name(),
                "회원 정보 조회 성공",
                memberService.showMember(memberId)));
  }

  @PutMapping("/members")
  public ResponseEntity<ResultDataResponse<Object>> renewMember(
      @RequestHeader Long memberId,
      @Valid @RequestBody MemberInfoForChangeRequestDto memberInfoForChangeRequestDto) {
    Member member = memberService.findMemberByMemberId(memberId);
    memberService.renewMemberInfo(member.getMemberEmail(), memberInfoForChangeRequestDto);
    return ResponseEntity.ok().body(
        new ResultDataResponse<>(
            String.valueOf(HttpStatus.OK.value()), HttpStatus.OK.toString(), "회원 정보 수정 성공", null)
    );
  }

  @PostMapping("/members/isDuplicated")
  public ResponseEntity<ResultDataResponse<Boolean>> isDuplicatedUsername(@Valid @RequestBody String username) {
    username = username.replaceAll("\"", "");
    return ResponseEntity.ok().body(
        new ResultDataResponse<>(
            String.valueOf(HttpStatus.OK.value()),
            HttpStatus.OK.name(),
            "이메일 중복 여부 조회 성공",
            memberService.checkMemberByMemberEmail(username)
        )
    );
  }
}
