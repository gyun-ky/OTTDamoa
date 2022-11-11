package com.ottAll.ottAll.repository;

import com.ottAll.ottAll.dao.MediaDetailDao;
import com.ottAll.ottAll.dto.SuggestionMediaDao;
import com.ottAll.ottAll.entity.*;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class QMediaRepositoryImpl implements QMediaRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public MediaDetailDao findByMediaIdAndMember(Long mediaId, Member member){
        QMedia qMedia = QMedia.media;
        QLikeMember qLikeMember = QLikeMember.likeMember;

        return jpaQueryFactory.select(
                Projections.bean(MediaDetailDao.class,
                        qMedia.name.as("name"),
                        qMedia.imageUrl.as("imageUrl"),
                        qMedia.description.as("description"),
                        new CaseBuilder()
                                .when(qLikeMember.status.isNull())
                                .then(false)
                                .otherwise(true).as("likeStatus")
                        ))
                .from(qMedia)
                .leftJoin(qMedia.likeMemberList, qLikeMember)
                .on(qLikeMember.member.eq(member))
                .where(qMedia.id.eq(mediaId))
                .fetchOne();
    }

    public List<String> findActorByMediaId(Long mediaId){
        QActor qActor = QActor.actor;
        QMediaActor qMediaActor = QMediaActor.mediaActor;

        return jpaQueryFactory.select(qActor.name
                )
                .from(qActor)
                .innerJoin(qActor.mediaActorList, qMediaActor)
                .on(qMediaActor.media.id.eq(mediaId))
                .fetch();
    }

    public List<String> findPlatformByMediaId(Long mediaId){
        QPlatform qPlatform = QPlatform.platform;
        QMediaPlatform qMediaPlatform = QMediaPlatform.mediaPlatform;

        return jpaQueryFactory.select(qPlatform.name
                )
                .from(qPlatform)
                .innerJoin(qPlatform.mediaPlatformList, qMediaPlatform)
                .on(qMediaPlatform.media.id.eq(mediaId))
                .fetch();
    }

    public List<String> findGenreByMediaId(Long mediaId){
        QGenre qGenre = QGenre.genre;
        QMediaGenre qMediaGenre = QMediaGenre.mediaGenre;

        return jpaQueryFactory.select(qGenre.name
                )
                .from(qGenre)
                .innerJoin(qGenre.mediaGenreList, qMediaGenre)
                .on(qMediaGenre.media.id.eq(mediaId))
                .fetch();
    }

    public List<SuggestionMediaDao> findSuggestionMedia(Member member){
        QMedia qMedia = QMedia.media;
        QMediaGenre qMediaGenre = QMediaGenre.mediaGenre;
        QMediaPlatform qMediaPlatform = QMediaPlatform.mediaPlatform;

        QPlatform qPlatform = QPlatform.platform;
        QGenre qGenre = QGenre.genre;

        QMemberGenre qMemberGenre = QMemberGenre.memberGenre;
        QMemberPlatform qMemberPlatform = QMemberPlatform.memberPlatform;

        return jpaQueryFactory.select(
                Projections.bean(SuggestionMediaDao.class,
                        qMedia.id.as("mediaId"),
                        qMedia.name.as("name"),
                        qMedia.imageUrl.as("imageUrl"),
                        qPlatform.name.as("platform"),
                        qGenre.name.as("genre")
                ))
                .from(qMedia)
                .innerJoin(qMedia.mediaPlatformList, qMediaPlatform)
                .innerJoin(qMediaPlatform.platform, qPlatform)
                .innerJoin(qMedia.mediaGenreList, qMediaGenre)
                .innerJoin(qMediaGenre.genre, qGenre)
                .innerJoin(qGenre.memberGenreList, qMemberGenre)
                .innerJoin(qPlatform.memberPlatformList, qMemberPlatform)
                .where(qMemberPlatform.member.eq(member).and(qMemberGenre.member.eq(member)))
                .orderBy(qMedia.hit.desc())
                .fetch();
    }
}
