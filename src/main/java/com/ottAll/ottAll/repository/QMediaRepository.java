package com.ottAll.ottAll.repository;

import com.ottAll.ottAll.dao.MediaDetailDao;
import com.ottAll.ottAll.dao.SuggestionMediaDao;
import com.ottAll.ottAll.dao.TrendItemDao;
import com.ottAll.ottAll.entity.Member;

import java.util.List;


public interface QMediaRepository {

    MediaDetailDao findByMediaIdAndMember(Long mediaId, Member member);
    List<String> findActorByMediaId(Long mediaId);
    List<String> findPlatformByMediaId(Long mediaId);
    List<String> findGenreByMediaId(Long mediaId);
    List<SuggestionMediaDao> findSuggestionMedia(Member member);
    List<TrendItemDao> findMediaTrend();

}
