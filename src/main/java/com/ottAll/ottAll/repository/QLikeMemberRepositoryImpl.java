package com.ottAll.ottAll.repository;

import com.ottAll.ottAll.dao.LikeMediaDao;
import com.ottAll.ottAll.entity.Member;
import com.ottAll.ottAll.entity.QLikeMember;
import com.ottAll.ottAll.entity.QMedia;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class QLikeMemberRepositoryImpl implements QLikeMemberRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public List<LikeMediaDao> findLikeMediaList(Member member){
        QLikeMember qLikeMember = QLikeMember.likeMember;
        QMedia qMedia = QMedia.media;

        return jpaQueryFactory.select(
                Projections.bean(LikeMediaDao.class,
                    qMedia.id.as("mediaId"),
                            qMedia.name.as("name"),
                            qMedia.imageUrl.as("imageUrl")
                ))
                .from(qLikeMember)
                .innerJoin(qLikeMember.media, qMedia)
                .on(qLikeMember.member.eq(member))
                .fetch();
    }
}
