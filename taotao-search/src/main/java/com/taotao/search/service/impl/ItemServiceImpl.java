package com.taotao.search.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.TaotaoResult;
import com.taotao.search.mapper.ItemMapper;
import com.taotao.search.pojo.Item;
import com.taotao.search.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private SolrServer solrServer;

	@Override
	public TaotaoResult importAllItems() {
		try{
			List<Item> list = itemMapper.getItemList();
			for(Item item : list){
				SolrInputDocument document = new SolrInputDocument();
				document.setField("id", item.getId());
				document.setField("item_title", item.getTitle());
				document.setField("item_price", item.getPrice());
				document.setField("item_sell_point", item.getSellPoint());
				document.setField("item_image", item.getImage());
				document.setField("item_category_name", item.getCatelogName());
				document.setField("item_desc", item.getItemDesc());
				solrServer.add(document);
			}
			solrServer.commit();
		}catch(Exception e){
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtils.getStackTrace(e));
		}
		return TaotaoResult.ok();
	}

}
