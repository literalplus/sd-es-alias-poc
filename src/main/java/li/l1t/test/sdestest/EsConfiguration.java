package li.l1t.test.sdestest;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;

@Configuration
public class EsConfiguration {
    @Bean
    public Client client() {
        Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();
        PreBuiltTransportClient client = new PreBuiltTransportClient(settings);
        client.addTransportAddress(new TransportAddress(InetAddress.getLoopbackAddress(), 9300));
        return client;
    }
}
