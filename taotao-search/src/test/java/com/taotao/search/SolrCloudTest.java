package com.taotao.search;

import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class SolrCloudTest {
	
	@Test
	public void testAddDocument() throws Exception{
		String zkHost = "172.16.16.100:2181,172.16.16.100:2182,172.16.16.100:2183";
		CloudSolrServer solrServer = new CloudSolrServer(zkHost);
		solrServer.setDefaultCollection("collection2");
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", "test001");
		document.addField("item_title", "测试商品");
		solrServer.add(document);
		solrServer.commit();
	}
	
	@Test
	public void testDeleteDocument() throws Exception{
		String zkHost = "172.16.16.100:2181,172.16.16.100:2182,172.16.16.100:2183";
		CloudSolrServer solrServer = new CloudSolrServer(zkHost);
		solrServer.setDefaultCollection("collection2");
		solrServer.deleteByQuery("*:*");
		solrServer.commit();
	}

}
