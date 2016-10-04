package com.moda.search.datasource.infra;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class CassandraClient {

  private static final Logger LOG = LoggerFactory.getLogger(CassandraClient.class);

  @Resource(name = "cassandra")
  private Properties cassandra;

  public static CassandraClient instance() {
    return CassandraClientHolder.INSTANCE;
  }

  private static final class CassandraClientHolder {
    public static final CassandraClient INSTANCE = new CassandraClient();
  }

  @PostConstruct
  public void initializeComponent() {

    LOG.info("Loading properties");

    String hosts = cassandra.getProperty("analytics.cassandra.seeds");

    String clusterName = cassandra.getProperty("analytics.cassandra.cluster");

    LOG.info("hosts:" + hosts);
    LOG.info("clusterName:" + clusterName);
  }

  @PreDestroy
  public void finalizeComponent() {
    LOG.info("Closing connections....");
  }
}
