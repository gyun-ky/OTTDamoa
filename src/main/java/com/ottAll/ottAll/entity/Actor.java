package com.ottAll.ottAll.entity;

import com.ottAll.ottAll.dto.ActorPollDto;
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
@Table(name = "Actor")
public class Actor extends BaseAuditingEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "profile_image_url", columnDefinition="TEXT")
    private String profileImageUrl;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "actor")
    private List<MediaActor> mediaActorList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "actor")
    private List<MemberActor> memberActorList = new ArrayList<>();

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private Status status;

    public enum Status{
        INACTIVE, ACTIVE
    }

    public static ActorPollDto getActorPollDto(Actor actor){
        return new ActorPollDto(actor);
    }
}
