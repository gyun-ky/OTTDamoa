package com.ottAll.ottAll.service;

import com.ottAll.ottAll.dao.MediaDetailDao;
import com.ottAll.ottAll.entity.LikeMember;
import com.ottAll.ottAll.entity.Media;
import com.ottAll.ottAll.entity.Member;
import com.ottAll.ottAll.repository.LikeMemberRepository;
import com.ottAll.ottAll.repository.MediaRepository;
import com.ottAll.ottAll.repository.MemberRepository;
import com.ottAll.ottAll.repository.QMediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MediaService {

    private final MemberRepository memberRepository;
    private final QMediaRepository qMediaRepository;


    public MediaDetailDao retrieveMediaDetail(Long mediaId, String userId){

        Member member = memberRepository.findByUserIdAndStatus(userId, Member.Status.ACTIVE).orElseThrow(() -> new NoSuchElementException("해당 유져 존재하지 않습니다"));

        MediaDetailDao mediaDetailDao = qMediaRepository.findByMediaIdAndMember(mediaId, member);

        mediaDetailDao.setActorList(qMediaRepository.findActorByMediaId(mediaId));
        mediaDetailDao.setPlatformList(qMediaRepository.findPlatformByMediaId(mediaId));
        mediaDetailDao.setGenreList(qMediaRepository.findGenreByMediaId(mediaId));

        return mediaDetailDao;
    }
}
