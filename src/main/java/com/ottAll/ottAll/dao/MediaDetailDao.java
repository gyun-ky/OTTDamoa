package com.ottAll.ottAll.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MediaDetailDao {

    private String name;
    private String imageUrl;
    private List<String> actorList;
    private List<String> genreList;
    private List<String> platformList;
    private String description;
    private boolean likeStatus;

    public MediaDetailDao(String name, String imageUrl, String description, boolean likeStatus){
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.likeStatus = likeStatus;
    }

}
