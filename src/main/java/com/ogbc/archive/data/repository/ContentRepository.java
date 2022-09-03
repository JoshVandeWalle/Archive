package com.ogbc.archive.data.repository;

import com.ogbc.archive.data.entity.ContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContentRepository extends JpaRepository<ContentEntity, Long>
{
    @Query(value="SELECT DISTINCT CONTENT.* FROM CONTENT JOIN CONTENT_TOPIC ON CONTENT.id = CONTENT_TOPIC.content_id JOIN TOPIC ON CONTENT_TOPIC.topic_id = TOPIC.id where TOPIC.topic LIKE %:name% ORDER BY CONTENT.`date`;", nativeQuery = true)
    List<ContentEntity> findByTopic(String name);
    @Query(value= """
            SELECT * from CONTENT WHERE passage LIKE CONCAT('%', :book, '%') AND
           ((CAST(SUBSTRING_INDEX(SUBSTRING_INDEX(passage, ':', 1), ' ', -1) AS UNSIGNED) = :chapter) OR
           (passage NOT LIKE '%:%' AND passage LIKE CONCAT('%', :chapter, '%')) OR
           (LENGTH(passage) - LENGTH(REPLACE(passage, ':', '')) > 1 AND
           CAST(SUBSTRING_INDEX(SUBSTRING_INDEX(passage, ':', 1), ' ', -1) AS UNSIGNED) <= :chapter AND
           CAST(SUBSTRING_INDEX(SUBSTRING_INDEX(passage, ':', 2), '-', -1) AS UNSIGNED) >= :chapter));""", nativeQuery = true)
    List<ContentEntity> findByChapter(@Param("book") String book, @Param("chapter") String chapter);
    @Query(value= """
            SELECT * from CONTENT WHERE passage LIKE CONCAT('%', :book, '%') AND
            ((passage NOT LIKE '%:%' AND SUBSTRING_INDEX(passage, ' ', -1) = :chapter) OR
            (LENGTH(passage) - LENGTH(REPLACE(passage, ':', '')) = 1 AND passage LIKE '%:%' AND passage NOT LIKE '%-%' AND SUBSTRING_INDEX(SUBSTRING_INDEX(passage, ':', 1), ' ', -1) = :chapter\s
            \tAND SUBSTRING_INDEX(passage, ':', -1) = :verse) OR
            LENGTH(passage) - LENGTH(REPLACE(passage, ':', '')) = 1 AND passage LIKE '%-%' AND SUBSTRING_INDEX(SUBSTRING_INDEX(passage, ':', 1), ' ', -1) = :chapter AND
            \tSUBSTRING_INDEX(SUBSTRING_INDEX(passage, ':', -1), '-', 1) <= :verse AND
            \tSUBSTRING_INDEX(passage, '-', -1) >= :verse OR
            LENGTH(passage) - LENGTH(REPLACE(passage, ':', '')) = 2 AND SUBSTRING_INDEX(SUBSTRING_INDEX(passage, ':', 1), ' ', -1) < :chapter AND
            \tSUBSTRING_INDEX(SUBSTRING_INDEX(passage, ':', 2), '-', -1) > :chapter OR
            LENGTH(passage) - LENGTH(REPLACE(passage, ':', '')) = 2 AND SUBSTRING_INDEX(SUBSTRING_INDEX(passage, ':', 1), ' ', -1) = :chapter AND
            \tSUBSTRING_INDEX(SUBSTRING_INDEX(passage, ':', -2), '-', 1) <= :verse OR
            LENGTH(passage) - LENGTH(REPLACE(passage, ':', '')) = 2 AND SUBSTRING_INDEX(SUBSTRING_INDEX(passage, ':', 2), '-', -1) = :chapter AND
            \tSUBSTRING_INDEX(passage, ':', -1) >= :verse)""", nativeQuery = true)
    List<ContentEntity> findByVerse(@Param("book") String book, @Param("chapter") String chapter,  @Param("verse") String verse);
    @Query(value = "SELECT * FROM CONTENT ORDER BY date LIMIT 10", nativeQuery = true)
    List<ContentEntity> findRecent();
}
