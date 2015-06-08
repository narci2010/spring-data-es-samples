package com.xiaohao.elasticsearch.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * Created by Administrator on 15-6-4.
 */
@Document(indexName ="doc-index",  type = "doc", shards = 1, replicas = 0)
public class Doc implements Serializable {

    @Id
    public Long id;
    @Field(type = FieldType.String,searchAnalyzer = "ik", store = true, indexAnalyzer = "ik")
    public String title;
    @Field(type = FieldType.String,searchAnalyzer = "ik", store = true, indexAnalyzer = "ik")
    public String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
