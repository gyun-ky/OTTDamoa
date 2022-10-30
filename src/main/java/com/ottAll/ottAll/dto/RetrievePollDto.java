package com.ottAll.ottAll.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RetrievePollDto {

    private List<PlatformPollDto> platformList;
    private List<GenrePollDto> genreList;
    private List<ActorPollDto> actorList;

}
