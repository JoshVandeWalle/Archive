package com.ogbc.archive.data.repository;

import com.ogbc.archive.data.entity.ContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContentRepository extends JpaRepository<ContentEntity, Long>
{
    @Query(value="SELECT DISTINCT CONTENT.* FROM CONTENT JOIN CONTENT_TOPIC ON CONTENT.id = CONTENT_TOPIC.content_id JOIN TOPIC ON CONTENT_TOPIC.topic_id = TOPIC.id where TOPIC.topic LIKE %:name% ORDER BY CONTENT.`date`;", nativeQuery = true)
    List<ContentEntity> findByTopic(@Param("name") String name);
    @Query(value= """
            SELECT * from CONTENT WHERE passage LIKE CONCAT('%', :book, '%') AND\s
            (passage LIKE CONCAT('%', :chapter, ':%') OR (LENGTH(passage) - LENGTH(REPLACE(passage, ':', '')) > 1 AND\s
            CAST(SUBSTRING_INDEX(SUBSTRING_INDEX(passage, ':', 1), ' ', -1) AS UNSIGNED) <= :chapter AND\s
            CAST(SUBSTRING_INDEX(SUBSTRING_INDEX(passage, ':', 2), '-', -1) AS UNSIGNED) >= :chapter));""", nativeQuery = true)
    List<ContentEntity> findByChapter(@Param("book") String book, String chapter);
    @Query(value="SELECT * FROM CONTENT WHERE passage LIKE %:book% AND passage LIKE CONCAT('%', :chapter, ':%') AND passage LIKE CONCAT ('%:%', :verse, '%')", nativeQuery = true)
    List<ContentEntity> findByVerse(String book, String chapter, String verse);
    @Query(value = "SELECT * FROM CONTENT ORDER BY date LIMIT 10", nativeQuery = true)
    List<ContentEntity> findRecent();
}
