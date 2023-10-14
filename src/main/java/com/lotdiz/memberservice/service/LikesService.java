package com.lotdiz.memberservice.service;

import com.lotdiz.memberservice.entity.Likes;
import com.lotdiz.memberservice.entity.LikesId;
import com.lotdiz.memberservice.entity.Member;
import com.lotdiz.memberservice.repository.LikesRepository;
import com.lotdiz.memberservice.repository.MemberRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikesService {
  private final LikesRepository likesRepository;
  private final MemberRepository memberRepository;

  public void add(Long memberId, Long projectId) {
    Member member = memberRepository.findByMemberId(memberId).orElseThrow();
    likesRepository.save(Likes.create(member, projectId));
  }

  public void remove(Long memberId, Long projectId) {
    Member member = memberRepository.findByMemberId(memberId).orElseThrow();
    LikesId likesId = Likes.createId(member, projectId);
    Likes likes = likesRepository.findById(likesId).orElseThrow();
    likesRepository.delete(likes);
  }
}