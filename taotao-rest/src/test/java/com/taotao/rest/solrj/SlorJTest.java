package com.taotao.rest.solrj;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class SlorJTest {
	
	@Test
	public void addDocument() throws SolrServerException, IOException{
		SolrServer solrServer = new HttpSolrServer("http://172.16.16.100:8080/solr");
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", "test001");
		document.addField("item_title", "测试商品1");
		document.addField("item_price", 1000);
		solrServer.add(document);
		solrServer.commit();
	}
	
	@Test
	public void deleteDocument() throws SolrServerException, IOException{
		SolrServer solrServer = new HttpSolrServer("http://172.16.16.100:8080/solr");
//		solrServer.deleteById("test001");
		solrServer.deleteByQuery("*:*");
		solrServer.commit();
	}
	
	@Test
	public void queryDocument() throws SolrServerException{
		SolrServer solrServer = new HttpSolrServer("http://172.16.16.100:8080/solr");
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("*:*");
		solrQuery.setStart(20);
		solrQuery.setRows(50);
		QueryResponse response = solrServer.query(solrQuery);
		SolrDocumentList result = response.getResults();
		System.out.println("共查询到：" + result.getNumFound());
		for(SolrDocument doc : result){
			System.out.println(doc.get("id"));
			System.out.println(doc.get("item_title"));
			System.out.println(doc.get("item_price"));
			System.out.println(doc.get("item_sell_point"));
			System.out.println(doc.get("item_image"));
		}
		
	}

}
