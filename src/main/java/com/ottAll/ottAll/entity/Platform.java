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
@Table(name = "Platform")
public class Platform extends BaseAuditingEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "platform_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "platform")
    private List<MediaPlatform> mediaPlatformList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "platform")
    private List<MemberPlatform> memberPlatformList = new ArrayList<>();

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private Status status;

    public enum Status{
        INACTIVE, ACTIVE
    }
}
