package com.ottAll.ottAll.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrendItemDao {
    private Long mediaId;
    private String name;
    private Long hit;
}
