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

    private List<platformIdInst> platformList;
    private List<genreIdInst> genreList;
    private List<actorIdInst> actorList;

    @Getter
    @Setter
    public static class platformIdInst {
        private Long platformId;
    }

    @Getter
    @Setter
    public static class genreIdInst {
        private Long genreId;
    }

    @Getter
    @Setter
    public static class actorIdInst {
        private Long actorId;
    }
}
