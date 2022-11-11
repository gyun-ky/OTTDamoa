package com.ottAll.ottAll.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SuggestionMediaDao {

    private Long mediaId;
    private String name;
    private String imageUrl;
    private String platform;
    private String genre;
}
