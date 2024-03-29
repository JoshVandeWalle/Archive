package com.ogbc.archive.service;

import com.ogbc.archive.api.model.ContentModel;
import com.ogbc.archive.api.model.PassageModel;
import com.ogbc.archive.api.model.TopicModel;

import java.util.List;

public interface ContentBusinessInterface
{
    ContentActionOutcome store(ContentModel content);
    List<ContentModel> retrieveByTopic(TopicModel topic);
    List<ContentModel> retrieveByPassage(PassageModel passage);
    List<ContentModel> retrieveRecent();
}
