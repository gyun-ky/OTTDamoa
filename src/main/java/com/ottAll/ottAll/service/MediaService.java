package com.ottAll.ottAll.service;

import com.ottAll.ottAll.dao.MediaDetailDao;
import com.ottAll.ottAll.dao.SuggestionMediaDao;
import com.ottAll.ottAll.dao.TrendItemDao;
import com.ottAll.ottAll.dto.SuggestionMediaDto;
import com.ottAll.ottAll.dto.TrendItemDto;
import com.ottAll.ottAll.entity.Media;
import com.ottAll.ottAll.entity.Member;
import com.ottAll.ottAll.repository.MediaRepository;
import com.ottAll.ottAll.repository.MemberRepository;
import com.ottAll.ottAll.repository.QMediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MediaService {

    private final MemberRepository memberRepository;
    private final QMediaRepository qMediaRepository;
    private final MediaRepository mediaRepository;

    /**
     * 미디어 상세 조회
     * @param mediaId
     * @param userId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public MediaDetailDao retrieveMediaDetail(Long mediaId, String userId){

        Member member = memberRepository.findByUserIdAndStatus(userId, Member.Status.ACTIVE).orElseThrow(() -> new NoSuchElementException("해당 유져 존재하지 않습니다"));
        Media media = mediaRepository.findByIdAndStatus(mediaId, Media.Status.ACTIVE).orElseThrow(() -> new NoSuchElementException("해당 미디어가 존재하지 않습니다."));

        MediaDetailDao mediaDetailDao = qMediaRepository.findByMediaIdAndMember(mediaId, member);

        mediaDetailDao.setActorList(qMediaRepository.findActorByMediaId(mediaId));
        mediaDetailDao.setPlatformList(qMediaRepository.findPlatformByMediaId(mediaId));
        mediaDetailDao.setGenreList(qMediaRepository.findGenreByMediaId(mediaId));

        media.addHit(10L);

        return mediaDetailDao;
    }

    /**
     * 추천 미디어 리스트 조회
     * @param userId
     * @return
     */
    public List<SuggestionMediaDto> retrieveSuggestionMediaList(String userId){

        Member member = memberRepository.findByUserIdAndStatus(userId, Member.Status.ACTIVE)
                .orElseThrow(() -> new NoSuchElementException("해당 유져가 존재하지 않습니다."));

        List<SuggestionMediaDao> suggestionMediaDaoList = qMediaRepository.findSuggestionMedia(member);

        HashMap<Long, SuggestionMediaDto> map = new HashMap<>();

        for(SuggestionMediaDao smd : suggestionMediaDaoList){
            SuggestionMediaDto existDto = map.get(smd.getMediaId());
            if(existDto == null){
                map.put(smd.getMediaId(), new SuggestionMediaDto(smd));
            }else{
                existDto.addDuplicateMedia(smd);
            }
        }

        return map.values().stream().collect(Collectors.toList());

    }

    public List<TrendItemDto> retrieveMediaTrend(){
        List<TrendItemDao> trendItemDaoList = qMediaRepository.findMediaTrend();
        List<TrendItemDto> trendItemDtoList = new ArrayList<>();
        HashMap<Long, Long> hitMap = new HashMap<>();

        int total = 0;

        for(TrendItemDao tiDao : trendItemDaoList){
            trendItemDtoList.add(new TrendItemDto(tiDao));
            hitMap.put(tiDao.getMediaId(), tiDao.getHit());
            total += tiDao.getHit();
        }

        System.out.println("total : " + total + " ");

        for(TrendItemDto tiDto : trendItemDtoList){
            int percent = (hitMap.get(tiDto.getMediaId()).intValue() * 100 / total);
            tiDto.setPercent(percent);
        }

        return trendItemDtoList;

    }



}
