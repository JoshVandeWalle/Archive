package com.ogbc.archive.service;

import com.ogbc.archive.model.ContentModel;
import com.ogbc.archive.model.PassageModel;
import com.ogbc.archive.model.TopicModel;

import java.util.List;

public interface ContentBusinessInterface
{
    List<ContentModel> retrieveByTopic(TopicModel topic);
    List<ContentModel> retrieveByPassage(PassageModel passage);
    List<ContentModel> retrieveRecent();
}
