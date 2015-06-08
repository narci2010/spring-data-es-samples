package com.xiaohao.elasticsearch;

import com.xiaohao.elasticsearch.entity.Doc;
import com.xiaohao.elasticsearch.repository.DocRepository;
import com.xiaohao.elasticsearch.util.ApplicationUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import javax.annotation.Resource;

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
        docRepository =ApplicationUtil.getBean(DocRepository.class);
        for(Long i=0L;i<99;i++){
            doc.setId(i);
            docRepository.save(doc);
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

        init();
    }
}
