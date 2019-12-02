/**
  * Copyright 2019 jandar
  */
package com.jandar.generator.template.aggregate;

import java.util.List;

import com.google.common.collect.Lists;
import com.jandar.generator.Data;
import com.jandar.generator.template.Ant;
import com.jandar.generator.template.Build;
import com.jandar.generator.template.ant.GeneratorFeignFallback;
import com.jandar.generator.template.ant.GeneratorFeignService;

public class FeginAggregate implements Build {
	
	@Override
	public void makeFile(Data data) {
		System.out.println("生成feign文件");
		List<Ant> ants = Lists.newArrayList(
				new GeneratorFeignService(data),
				new GeneratorFeignFallback(data)
				);
		ants.forEach(Ant::generator);
	}

}
