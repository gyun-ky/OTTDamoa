package com.ottAll.ottAll.dto;

import com.ottAll.ottAll.entity.Platform;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlatformPollDto {

    private Long platformId;
    private String name;

    public PlatformPollDto(Platform platform){
        this.platformId = platform.getId();
        this.name = platform.getName();
    }

}
