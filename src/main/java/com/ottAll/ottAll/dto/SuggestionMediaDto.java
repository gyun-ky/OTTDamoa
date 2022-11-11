package com.ottAll.ottAll.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SuggestionMediaDto {

    private Long mediaId;
    private String name;
    private String imageUrl;
    private List<String> genreList = new ArrayList<>();
    private List<String> platformList = new ArrayList<>();

    public SuggestionMediaDto(SuggestionMediaDao suggestionMediaDao){
        this.mediaId = suggestionMediaDao.getMediaId();
        this.name = suggestionMediaDao.getName();
        this.imageUrl = suggestionMediaDao.getImageUrl();
        this.genreList.add(suggestionMediaDao.getGenre());
        this.platformList.add(suggestionMediaDao.getPlatform());
    }

    public void addDuplicateMedia(SuggestionMediaDao suggestionMediaDao){
        this.genreList.add(suggestionMediaDao.getGenre());
        this.genreList = this.genreList
                .stream().distinct().collect(Collectors.toList());

        this.platformList.add(suggestionMediaDao.getPlatform());
        this.platformList = this.platformList
                .stream().distinct().collect(Collectors.toList());
    }

}
