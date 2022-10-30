package com.ottAll.ottAll.dto;

import com.ottAll.ottAll.entity.Actor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActorPollDto {

    private Long actorId;
    private String name;

    public ActorPollDto(Actor actor){
        this.actorId = actor.getId();
        this.name = actor.getName();
    }

}
