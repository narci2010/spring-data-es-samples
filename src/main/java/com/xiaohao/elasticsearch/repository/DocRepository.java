package com.xiaohao.elasticsearch.repository;

import com.xiaohao.elasticsearch.entity.Doc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by Administrator on 15-6-4.
 */
public interface DocRepository extends ElasticsearchRepository<Doc, Long> {

}
