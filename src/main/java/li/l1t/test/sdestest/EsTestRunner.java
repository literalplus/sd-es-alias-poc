package li.l1t.test.sdestest;

import org.elasticsearch.cluster.metadata.AliasMetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.AliasQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EsTestRunner implements CommandLineRunner {
    private final ElasticsearchTemplate template;

    @Autowired
    public EsTestRunner(ElasticsearchTemplate template) {
        this.template = template;
    }


    @Override
    public void run(String... args) throws Exception {
        template.addAlias(createAlias(ensureIndexExists("backing-1"), "aliased"));
        template.addAlias(createAlias(ensureIndexExists("backing-2"), "aliased"));
        List<AliasMetaData> data = template.queryForAlias("aliased");
        System.out.println(data);
    }

    private String ensureIndexExists(String indexName) {
        if(!template.indexExists(indexName)) {
            template.createIndex(indexName);
        }
        return indexName;
    }

    private AliasQuery createAlias(String indexName, String aliasName) {
        AliasQuery query = new AliasQuery();
        query.setIndexName(indexName);
        query.setAliasName(aliasName);
        return query;
    }
}
