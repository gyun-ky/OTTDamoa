package com.ottAll.ottAll.dto;

import com.ottAll.ottAll.entity.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenrePollDto {

    private Long genreId;
    private String name;

    public GenrePollDto(Genre genre){
        this.genreId = genre.getId();
        this.name = genre.getName();
    }
}
