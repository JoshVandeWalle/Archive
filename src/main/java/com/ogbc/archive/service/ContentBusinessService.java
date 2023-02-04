package com.ogbc.archive.service;

import com.ogbc.archive.data.entity.ContentEntity;
import com.ogbc.archive.data.entity.TopicEntity;
import com.ogbc.archive.data.repository.ContentRepository;
import com.ogbc.archive.data.repository.TopicRepository;
import com.ogbc.archive.model.ContentModel;
import com.ogbc.archive.model.PassageModel;
import com.ogbc.archive.model.TopicModel;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Setter
public class ContentBusinessService implements ContentBusinessInterface
{
    @Autowired
    ContentRepository contentRepository;

    @Autowired
    TopicRepository topicRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ContentActionOutcome store(ContentModel content)
    {
        List<TopicEntity> topicEntities = new ArrayList<>();

        for (TopicModel topicModel : content.getTopics())
        {
            TopicEntity topicEntity = topicRepository.findByName(topicModel.getName());

            if (topicEntity == null)
            {
                topicEntity =  new TopicEntity(topicModel);
            }

            topicRepository.save(topicEntity);
            topicEntities.add(topicEntity);
        }

        ContentEntity entity = new ContentEntity(content);
        entity.setTopics(topicEntities);
        contentRepository.save(entity);

        return ContentActionOutcome.STORED;
    }

    @Override
    public List<ContentModel> retrieveByTopic(TopicModel topic)
    {
        return convertEntitiesToModels(contentRepository.findByTopic(topic.getName()));
    }

    @Override
    public List<ContentModel> retrieveByPassage(PassageModel passage)
    {

        return convertEntitiesToModels(passage.getVerse() == null ?
                contentRepository.findByChapter(passage.getBook(), passage.getChapter().toString())
                : contentRepository.findByVerse(passage.getBook(), passage.getChapter().toString(), passage.getVerse().toString()));

    }

    @Override
    public List<ContentModel> retrieveRecent()
    {
        return convertEntitiesToModels(contentRepository.findRecent());
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
