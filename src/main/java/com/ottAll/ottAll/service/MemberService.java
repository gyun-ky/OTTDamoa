package com.ottAll.ottAll.service;

import com.ottAll.ottAll.entity.LikeMember;
import com.ottAll.ottAll.entity.Media;
import com.ottAll.ottAll.entity.Member;
import com.ottAll.ottAll.repository.LikeMemberRepository;
import com.ottAll.ottAll.repository.MediaRepository;
import com.ottAll.ottAll.repository.MemberRepository;
import com.ottAll.ottAll.util.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MediaRepository mediaRepository;
    private final LikeMemberRepository likeMemberRepository;

    @Transactional(rollbackFor = Exception.class)
    public void createLike(String userId, Long mediaPk){

        Member member = memberRepository.findByUserIdAndStatus(userId, Member.Status.ACTIVE).orElseThrow(() -> new NoSuchElementException("존재하지 않는 유져입니다."));
        Media media = mediaRepository.findByIdAndStatus(mediaPk, Media.Status.ACTIVE).orElseThrow(() -> new NoSuchElementException("존재하지 않는 미디어입니다."));

        if(likeMemberRepository.existsByMemberAndMediaAndStatus(member, media, LikeMember.Status.ACTIVE)){
            throw new NoSuchElementException("이미 좋아요한 미디어 입니다.");
        }

        likeMemberRepository.save(LikeMember.builder()
                .member(member)
                .media(media)
                .status(LikeMember.Status.ACTIVE)
                .build());
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteLike(String userId, Long mediaPk){

        Member member = memberRepository.findByUserIdAndStatus(userId, Member.Status.ACTIVE).orElseThrow(() -> new NoSuchElementException("존재하지 않는 유져입니다."));
        Media media = mediaRepository.findByIdAndStatus(mediaPk, Media.Status.ACTIVE).orElseThrow(() -> new NoSuchElementException("존재하지 않는 미디어입니다."));

        LikeMember likeMember = likeMemberRepository.findByMemberAndMediaAndStatus(member, media, LikeMember.Status.ACTIVE)
                .orElseThrow(() -> new NoSuchElementException("좋아요한 미디어가 아닙니다."));

        likeMemberRepository.delete(likeMember);

    }

}
