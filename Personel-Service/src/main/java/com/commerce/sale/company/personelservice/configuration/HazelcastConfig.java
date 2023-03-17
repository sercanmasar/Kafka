package com.commerce.sale.company.personelservice.configuration;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientConnectionStrategyConfig;
import com.hazelcast.client.config.ConnectionRetryConfig;
import com.hazelcast.config.*;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastConfig {

    public static final String BEAN_HAZELCAST_INSTANCE = "hazelcastInstance";
    private static final int INITIAL_BACK_OFF_SECONDS = 5;

    @Value("${hz-client.near-cache-eviction-size-threshold:10000}")
    private final int nearCacheEvictionSizeThreshold;

    @Bean
    @ConfigurationProperties(prefix = "hz-client")
    public ClientConfig hzClientConfig() {
        final ClientConfig clientConfig = new ClientConfig();

        ConnectionRetryConfig retryConfig = new ConnectionRetryConfig();
        retryConfig.setInitialBackoffMillis(INITIAL_BACK_OFF_SECONDS * 1000);
        clientConfig.getConnectionStrategyConfig()
                .setReconnectMode(ClientConnectionStrategyConfig.ReconnectMode.ON)
                .setConnectionRetryConfig(retryConfig);

        configureNearCaches(clientConfig);

        return clientConfig;
    }

    private void configureNearCaches(ClientConfig config) {
        HZ_NEAR_CACHES.forEach(cacheName -> {
            NearCacheConfig nearCacheConfig = getNearCacheConfigForMap(cacheName);
            config.addNearCacheConfig(nearCacheConfig);
        });
    }

    private void configureNearCaches(Config config) {
        HZ_NEAR_CACHES.forEach(cacheName -> {
            MapConfig mapConfig = getNearCacheMapConfig(cacheName);
            config.addMapConfig(mapConfig);
        });
    }

    private NearCacheConfig getNearCacheConfigForMap(String mapName) {
        EvictionConfig evictionConfig = new EvictionConfig()
                .setEvictionPolicy(EvictionPolicy.LFU)
                .setMaximumSizePolicy(EvictionConfig.MaxSizePolicy.ENTRY_COUNT)
                .setSize(nearCacheEvictionSizeThreshold);

        return new NearCacheConfig()
                .setName(mapName)
                .setInMemoryFormat(InMemoryFormat.OBJECT)
                .setInvalidateOnChange(true)
                .setEvictionConfig(evictionConfig);
    }

    private MapConfig getNearCacheMapConfig(String mapName) {
        return new MapConfig()
                .setName(mapName)
                .setInMemoryFormat(InMemoryFormat.BINARY)
                .setTimeToLiveSeconds(60 * 10) // 10 minutes
                .setNearCacheConfig(getNearCacheConfigForMap(mapName));
    }

    @Bean(name = BEAN_HAZELCAST_INSTANCE)
    @ConditionalOnProperty(name = "hz-client.start-local-server", havingValue = "false", matchIfMissing = true)
    public HazelcastInstance hazelcastInstance() {
        final ClientConfig config = hzClientConfig();
        configureNearCaches(config);

        LOG.info("Hazelcast client instance starting::GroupName={}::Members={}", config.getInstanceName(), config.getNetworkConfig().getAddresses());
        final HazelcastInstance clientInstance = HazelcastClient.newHazelcastClient(config);
        LOG.info("Hazelcast client instance started");

        return clientInstance;
    }

    @Bean(name = BEAN_HAZELCAST_INSTANCE)
    @ConditionalOnProperty(name = "hz-client.start-local-server", havingValue = "true")
    public HazelcastInstance hazelcastServer() {
        final Config config = new Config();

        // This is for local environment use only!
        final NetworkConfig networkConfig = config.getNetworkConfig();
        networkConfig.setPort(5701);
        networkConfig.setPortAutoIncrement(true);
        networkConfig.getJoin().getMulticastConfig().setEnabled(true);

        configureNearCaches(config);

        return Hazelcast.newHazelcastInstance(config);
    }
}
