package com.ottAll.ottAll.dto;

import com.ottAll.ottAll.dao.TrendItemDao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrendItemDto {
    private Long mediaId;
    private String name;
    private int percent;

    public TrendItemDto(TrendItemDao trendItemDao){
        this.mediaId = trendItemDao.getMediaId();
        this.name = trendItemDao.getName();
    }
}
