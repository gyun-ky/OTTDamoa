package com.ottAll.ottAll.service;

import com.ottAll.ottAll.dto.ActorPollDto;
import com.ottAll.ottAll.dto.GenrePollDto;
import com.ottAll.ottAll.dto.PlatformPollDto;
import com.ottAll.ottAll.dto.RetrievePollDto;
import com.ottAll.ottAll.entity.Actor;
import com.ottAll.ottAll.entity.Genre;
import com.ottAll.ottAll.entity.Platform;
import com.ottAll.ottAll.repository.ActorRepository;
import com.ottAll.ottAll.repository.GenreRepository;
import com.ottAll.ottAll.repository.PlatformRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PollService {

    private final PlatformRepository platformRepository;
    private final GenreRepository genreRepository;
    private final ActorRepository actorRepository;

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
}
