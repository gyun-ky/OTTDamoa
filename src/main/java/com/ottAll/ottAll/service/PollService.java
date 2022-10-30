package com.ottAll.ottAll.service;

import com.ottAll.ottAll.dto.*;
import com.ottAll.ottAll.entity.*;
import com.ottAll.ottAll.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PollService {

    private final MemberRepository memberRepository;

    private final PlatformRepository platformRepository;
    private final GenreRepository genreRepository;
    private final ActorRepository actorRepository;

    private final MemberPlatformRepository memberPlatformRepository;
    private final MemberGenreRepository memberGenreRepository;
    private final MemberActorRepository memberActorRepository;

    /**
     * 설문조사 선택 항목 조회
     * @return
     */
    public RetrievePollDto retrievePollInfo(){

        // 설문조사 플랫폼 리스트
        List<PlatformPollDto> platformPollDtos = platformRepository.findAllByStatus(Platform.Status.ACTIVE)
                .stream()
                .map(Platform::getPlatformPollDto)
                .collect(Collectors.toList());

        // 설문조사 장르 리스트
        List<GenrePollDto> genrePollDtos = genreRepository.findAllByStatus(Genre.Status.ACTIVE)
                .stream()
                .map(Genre::getGenrePollDto)
                .collect(Collectors.toList());

        // 설문조사 배우 리스트
        List<ActorPollDto> actorPollDtos = actorRepository.findAllByStatus(Actor.Status.ACTIVE)
                .stream()
                .map(Actor::getActorPollDto)
                .collect(Collectors.toList());

        RetrievePollDto result = RetrievePollDto.builder()
                .platformList(platformPollDtos)
                .genreList(genrePollDtos)
                .actorList(actorPollDtos)
                .build();

        return result;

    }

//    @Transactional(rollbackFor = Exception.class)
//    public void createPollResult(String userId, CreatePollResultReq createPollResultReq){
//
//        Member member = memberRepository.findByUserIdAndStatus(userId, Member.Status.ACTIVE)
//                .orElseThrow(() -> new NoSuchElementException("해당 유져가 존재하지 않습니다"));
//
//        createPollResultReq.getActorList()
//                .stream()
//                .map(a->)
//    }

//    public void create
}
