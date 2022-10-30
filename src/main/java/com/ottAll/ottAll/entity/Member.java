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
@Table(name = "Member")
public class Member extends BaseAuditingEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "id")
    private String userId;

    @Column(name = "password", columnDefinition = "TEXT")
    private String password;

    @Column(name = "refresh_token", columnDefinition = "TEXT")
    private String refreshToken;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    private List<MemberActor> memberActorList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    private List<MemberPlatform> memberPlatformList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    private List<MemberGenre> memberGenreList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    private List<LikeMember> likeMemberList = new ArrayList<>();

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private Status status;

    public enum Status{
        INACTIVE, ACTIVE
    }

    public void setRefreshToken(String refreshToken){
        this.refreshToken = refreshToken;
    }
}
