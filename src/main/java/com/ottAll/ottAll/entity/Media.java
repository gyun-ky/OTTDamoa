package com.ottAll.ottAll.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Media")
public class Media extends BaseAuditingEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "media_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "image_url", columnDefinition="TEXT")
    private String imageUrl;

    @Column(name = "description", columnDefinition="TEXT")
    private String description;

//    @Column(name = "director")
//    private String director;

    @Column(name = "hit")
    private Long hit;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "media")
    private List<MediaActor> mediaActorList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "media")
    private List<MediaGenre> mediaGenreList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "media")
    private List<MediaPlatform> mediaPlatformList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "media")
    private List<LikeMember> likeMemberList = new ArrayList<>();

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private Status status;

    public enum Status{
        INACTIVE, ACTIVE
    }

    public void addHit(Long hitCount){
        this.hit += hitCount;
    }

}
