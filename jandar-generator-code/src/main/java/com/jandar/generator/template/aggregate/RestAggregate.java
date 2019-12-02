/**
  * Copyright 2019 jandar
  */
package com.jandar.generator.template.aggregate;

import java.util.List;

import com.google.common.collect.Lists;
import com.jandar.generator.Data;
import com.jandar.generator.template.Ant;
import com.jandar.generator.template.Build;
import com.jandar.generator.template.ant.GeneratorRest;

public class RestAggregate implements Build {
	
	@Override
	public void makeFile(Data data) {
		System.out.println("生成rest文件");
		List<Ant> ants = Lists.newArrayList(
				new GeneratorRest(data)
				);
		ants.forEach(Ant::generator);
	}

}
