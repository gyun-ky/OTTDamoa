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
@Table(name = "Genre")
public class Genre extends BaseAuditingEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "genre")
    private List<MediaGenre> mediaGenreList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "genre")
    private List<MemberGenre> memberGenreList = new ArrayList<>();

    @Column(name = "status")
    private Status status;

    public enum Status{
        INACTIVE, ACTIVE
    }

}
