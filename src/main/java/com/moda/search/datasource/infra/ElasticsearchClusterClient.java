package com.moda.search.datasource.infra;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElasticsearchClusterClient {

  @Resource(name = "elasticsearch")
  private Properties elasticsearch;

  private static Client client = null;
  private static final Logger LOG = LoggerFactory.getLogger(ElasticsearchClusterClient.class);
  private static String searchElsCluster = null;
  private static String searchElsHost = null;

  private static class ElasticsearchClusterClientHolder {
    public static final ElasticsearchClusterClient INSTANCE = new ElasticsearchClusterClient();
  }

  public static ElasticsearchClusterClient instance() {
    return ElasticsearchClusterClientHolder.INSTANCE;
  }

  @PostConstruct
  public void initializeComponent() {
    
    searchElsCluster = elasticsearch.getProperty("search.elasticsearch.cluster");
    searchElsHost = elasticsearch.getProperty("search.elasticsearch.ip");

    LOG.info("searchElsHost : {} - searchElsCluster : {} ", searchElsHost, searchElsCluster);

    Settings settings = Settings.settingsBuilder().put("cluster.name", searchElsCluster).put("client.transport.sniff", true).build();
    TransportClient transportClient;
    try {
      transportClient = TransportClient.builder().settings(settings).build()
              .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(searchElsHost), 9300));
      client = transportClient;
    } catch (UnknownHostException e) {
      LOG.error("Error while initializing els.", e);
    }
    LOG.info("ELS initialized successfully...");
  }

  public Client getElsClient() {
    return client;
  }

  @PreDestroy
  public void finalizeComponent() {
    client.close();
  }
}
