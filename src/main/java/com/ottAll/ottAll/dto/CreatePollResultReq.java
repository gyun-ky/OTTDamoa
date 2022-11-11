package com.ottAll.ottAll.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePollResultReq {

    private List<IdEntity> platformList;
    private List<IdEntity> genreList;
    private List<IdEntity> actorList;

    private class IdEntity {
        private Long id;
    }
}
