package com.ogbc.archive.data.repository;

import com.ogbc.archive.data.entity.TopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<TopicEntity, Long>
{
    public TopicEntity findByName(String name);
}
