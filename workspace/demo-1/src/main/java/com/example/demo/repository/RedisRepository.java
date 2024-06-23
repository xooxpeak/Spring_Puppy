package com.example.demo.repository;

import com.example.demo.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface RedisRepository extends CrudRepository<UserEntity, String>  {

}
