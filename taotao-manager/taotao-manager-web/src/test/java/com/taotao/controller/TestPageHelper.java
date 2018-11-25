package com.taotao.controller;
 
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;

public class TestPageHelper {
	
	private ApplicationContext applicationContext;

	@Test
	public void testPageHelper(){
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		TbItemMapper mapper = applicationContext.getBean(TbItemMapper.class);
		TbItemExample example = new TbItemExample();
		PageHelper.startPage(1, 10);
		List<TbItem> list = mapper.selectByExample(example);
		for(TbItem item: list){
			System.out.println(item.getTitle());
		}
		PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(list);
		long total = pageInfo.getTotal();
		System.out.print(total);
	}
	
	
	@Test
	public void testPageHelper1(){
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		TbItemParamMapper mapper = applicationContext.getBean(TbItemParamMapper.class);
		TbItemParamExample example = new TbItemParamExample();
		PageHelper.startPage(1, 10);
		List<TbItemParam> list = mapper.selectByExample(example);
		for(TbItemParam item: list){
			System.out.println(item.getId());
		}
		PageInfo<TbItemParam> pageInfo = new PageInfo<TbItemParam>(list);
		long total = pageInfo.getTotal();
		System.out.print(total);
	}


}
