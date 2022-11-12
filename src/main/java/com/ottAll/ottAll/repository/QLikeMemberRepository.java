package com.ottAll.ottAll.repository;

import com.ottAll.ottAll.dao.LikeMediaDao;
import com.ottAll.ottAll.entity.Member;

import java.util.List;

public interface QLikeMemberRepository {

    List<LikeMediaDao> findLikeMediaList(Member member);
}
