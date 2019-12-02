/**
  * Copyright 2019 jandar
  */
package com.jandar.generator.template.aggregate;

import java.util.List;

import com.google.common.collect.Lists;
import com.jandar.generator.Data;
import com.jandar.generator.template.Ant;
import com.jandar.generator.template.Build;
import com.jandar.generator.template.ant.GeneratorCommonService;
import com.jandar.generator.template.ant.GeneratorCommonServiceImpl;

public class ServiceAggregate implements Build {
	
	@Override
	public void makeFile(Data data) {
		System.out.println("生成service文件");
		List<Ant> ants = Lists.newArrayList(
				new GeneratorCommonService(data),
				new GeneratorCommonServiceImpl(data)
				);
		ants.forEach(Ant::generator);
	}

}
