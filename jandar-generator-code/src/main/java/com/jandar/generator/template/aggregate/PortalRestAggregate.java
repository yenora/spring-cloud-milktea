package com.jandar.generator.template.aggregate;

import java.util.List;

import com.google.common.collect.Lists;
import com.jandar.generator.Data;
import com.jandar.generator.template.Ant;
import com.jandar.generator.template.Build;
import com.jandar.generator.template.ant.GeneratorPortalRest;

public class PortalRestAggregate implements Build {
	
	@Override
	public void makeFile(Data data) {
		System.out.println("生成portal rest文件");
		List<Ant> ants = Lists.newArrayList(
				new GeneratorPortalRest(data)
				);
		ants.forEach(Ant::generator);
	}

}
