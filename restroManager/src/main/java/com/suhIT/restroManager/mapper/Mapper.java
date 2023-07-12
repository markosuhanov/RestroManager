package com.suhIT.restroManager.mapper;

public interface Mapper<Entity,Dto> {

    Entity toEntity(Dto dto);

    Dto toDTO(Entity entity);
}
