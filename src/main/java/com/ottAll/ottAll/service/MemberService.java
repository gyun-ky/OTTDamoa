package com.ottAll.ottAll.service;

import com.ottAll.ottAll.dao.LikeMediaDao;
import com.ottAll.ottAll.dto.CreatePollResultReq;
import com.ottAll.ottAll.entity.*;
import com.ottAll.ottAll.repository.*;
import com.ottAll.ottAll.util.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MediaRepository mediaRepository;
    private final LikeMemberRepository likeMemberRepository;
    private final QLikeMemberRepository qLikeMemberRepository;


    @Transactional(rollbackFor = Exception.class)
    public void createLike(String userId, Long mediaPk){

        Member member = memberRepository.findByUserIdAndStatus(userId, Member.Status.ACTIVE).orElseThrow(() -> new NoSuchElementException("존재하지 않는 유져입니다."));
        Media media = mediaRepository.findByIdAndStatus(mediaPk, Media.Status.ACTIVE).orElseThrow(() -> new NoSuchElementException("존재하지 않는 미디어입니다."));

        if(likeMemberRepository.existsByMemberAndMediaAndStatus(member, media, LikeMember.Status.ACTIVE)){
            throw new NoSuchElementException("이미 좋아요한 미디어 입니다.");
        }

        media.addHit(20L);

        likeMemberRepository.save(LikeMember.builder()
                .member(member)
                .media(media)
                .status(LikeMember.Status.ACTIVE)
                .build());
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteLike(String userId, Long mediaPk){

        Member member = memberRepository.findByUserIdAndStatus(userId, Member.Status.ACTIVE)
                .orElseThrow(() -> new NoSuchElementException("유효하지 않은 유져입니다."));
        Media media = mediaRepository
                .findByIdAndStatus(mediaPk, Media.Status.ACTIVE).orElseThrow(() -> new NoSuchElementException("존재하지 않는 미디어입니다."));

        LikeMember likeMember = likeMemberRepository.findByMemberAndMediaAndStatus(member, media, LikeMember.Status.ACTIVE)
                .orElseThrow(() -> new NoSuchElementException("좋아요한 미디어가 아닙니다."));

        media.addHit(-10L);

        likeMemberRepository.delete(likeMember);

    }

    public List<LikeMediaDao> retrieveLikeList(String userId){

        Member member = memberRepository.findByUserIdAndStatus(userId, Member.Status.ACTIVE)
                .orElseThrow(() -> new NoSuchElementException("유효하지 않은 유져입니다."));

        List<LikeMediaDao> likeMediaList = qLikeMemberRepository.findLikeMediaList(member);

        return likeMediaList;

    }


}
