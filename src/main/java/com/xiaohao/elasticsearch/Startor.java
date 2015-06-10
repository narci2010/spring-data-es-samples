package com.xiaohao.elasticsearch;

import com.xiaohao.elasticsearch.entity.Doc;
import com.xiaohao.elasticsearch.repository.DocRepository;
import com.xiaohao.elasticsearch.util.ApplicationUtil;
import org.apache.lucene.util.QueryBuilder;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.highlight.HighlightBuilder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

import static org.elasticsearch.index.query.FilterBuilders.boolFilter;
import static org.elasticsearch.index.query.FilterBuilders.termFilter;

/**
 * Created by Administrator on 15-6-4.
 */
public class Startor implements InitializingBean {

    private ElasticsearchTemplate elasticsearchTemplate;

    private DocRepository docRepository;

    public void init(){
        System.out.println("init test");
        Doc doc = new Doc();
        doc.setId(19999L);
        doc.setTitle("哈哈王二小刘胡兰");
        doc.setContent("提示信息中多次提到SLF4J，google了一下，才发现这是现在的Hibernate使用SLF4J API记录日志，所以在Hibernate的lib中，不再提供Log4J的包，而大部分框架依然使用Log4J记录日志，这样导致了兼容性问题");
        elasticsearchTemplate.createIndex(Doc.class);
        for(Long i=0L;i<99;i++){
            doc.setId(i);
            docRepository.save(doc);
        }

    }

    public void query(){
        BoolQueryBuilder subBuilder = QueryBuilders.boolQuery();
        subBuilder.should(QueryBuilders.matchPhraseQuery("tilte", "哈"))
                .should(QueryBuilders.matchPhraseQuery("content","发现"));
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withTypes("doc")
                .withQuery(subBuilder)
                .withFilter(boolFilter().must(termFilter("id", 23)))
                .withPageable(new PageRequest(0,1000)).build();

        Page<Doc> docPage =docRepository.search(searchQuery);
        Iterator<Doc> docIterator =docPage.iterator();
        while (docIterator.hasNext()){
            Doc d =docIterator.next();
            System.out.println(d.getId());
        }
    }


    public ElasticsearchTemplate getElasticsearchTemplate() {
        return elasticsearchTemplate;
    }

    public void setElasticsearchTemplate(ElasticsearchTemplate elasticsearchTemplate) {
        this.elasticsearchTemplate = elasticsearchTemplate;
    }

    public DocRepository getDocRepository() {
        return docRepository;
    }

    public void setDocRepository(DocRepository docRepository) {
        this.docRepository = docRepository;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if(elasticsearchTemplate==null){
            System.out.println("elasticsearchTemplate is null");
            return;
        }
        docRepository =ApplicationUtil.getBean(DocRepository.class);
        query();
    }
}
