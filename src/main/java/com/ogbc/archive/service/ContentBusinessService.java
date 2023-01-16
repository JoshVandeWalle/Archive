package com.ogbc.archive.service;

import com.ogbc.archive.data.entity.ContentEntity;
import com.ogbc.archive.data.repository.ContentRepository;
import com.ogbc.archive.model.ContentModel;
import com.ogbc.archive.model.PassageModel;
import com.ogbc.archive.model.TopicModel;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Setter
public class ContentBusinessService implements ContentBusinessInterface
{
    @Autowired
    ContentRepository repository;

    @Override
    public List<ContentModel> retrieveByTopic(TopicModel topic)
    {
        return convertEntitiesToModels(repository.findByTopic(topic.getName()));
    }

    @Override
    public List<ContentModel> retrieveByPassage(PassageModel passage)
    {

        return convertEntitiesToModels(passage.getVerse() == null ?
                repository.findByChapter(passage.getBook(), passage.getChapter().toString())
                : repository.findByVerse(passage.getBook(), passage.getChapter().toString(), passage.getVerse().toString()));

    }

    @Override
    public List<ContentModel> retrieveRecent()
    {
        return convertEntitiesToModels(repository.findRecent());
    }

    private List<ContentModel> convertEntitiesToModels(List<ContentEntity> contentEntities)
    {
        List<ContentModel> contentModels = new ArrayList<>();

        for (ContentEntity contentEntity : contentEntities)
        {
            contentModels.add(new ContentModel(contentEntity));
        }

        return contentModels;
    }
}
