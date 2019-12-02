/**
  * Copyright 2019 jandar
  */
package com.jandar.generator;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.jandar.generator.template.aggregate.FeginAggregate;
import com.jandar.generator.template.aggregate.PortalRestAggregate;
import com.jandar.generator.template.aggregate.RestAggregate;
import com.jandar.generator.template.aggregate.ServiceAggregate;

public class ReadFactory {
	
	private String configFilePath;
	
	public ReadFactory(String configFilePath){
		this.configFilePath = configFilePath;
	}
	
	private String readData(Element e, String ... nodes){
		String result = null;
		for(String node : nodes){
			e = e.element(node);
			if(e == null){
				break;
			}
			result = (String) e.getData();
		}
		return result;
	}

	public void generate() throws DocumentException {
		// 1.创建Reader对象
		SAXReader reader = new SAXReader();
		// 2.加载xml
		Document document = reader.read(new File(configFilePath));
		// 3.获取根节点
		Element rootElement = document.getRootElement();
		
		String doname = readData(rootElement, "global", "do");
		String doch = readData(rootElement, "global", "ch");
		
		if(readData(rootElement, "service") != null){
			Data config = Config.build(doname, doch, 
					 readData(rootElement, "service", "package"),
					 readData(rootElement, "service", "dist"));
			new ServiceAggregate().makeFile(config);
		}
		
		if(readData(rootElement, "rest") != null){
			Data config = Config.build(doname, doch, 
					 readData(rootElement, "rest", "package"),
					 readData(rootElement, "rest", "dist"));
			new RestAggregate().makeFile(config);
		}
		
		if(readData(rootElement, "feign") != null){
			Data config = Config.build(doname, doch, 
					 readData(rootElement, "feign", "package"),
					 readData(rootElement, "feign", "dist"),
					 readData(rootElement, "feign", "client"));
			new FeginAggregate().makeFile(config);
		}
		
		if(readData(rootElement, "portal") != null){
			Data config = Config.build(doname, doch, 
					 readData(rootElement, "portal", "package"),
					 readData(rootElement, "portal", "dist"));
			new PortalRestAggregate().makeFile(config);
		}
        
	}

}
