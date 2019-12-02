package com.jandar.generator.template.ant;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import com.jandar.generator.Data;
import com.jandar.generator.template.Ant;

public class GeneratorCommonServiceImpl implements Ant{
	
	private Data config;
	
	public GeneratorCommonServiceImpl(Data config){
		this.config = config;
	}

	@Override
	public void generator(){
		try{
			// interface
			String path = config.getDist() + "/src/main/java/"
					+ config.getPackage().replace(".", "/") + "/impl/"
					+ config.getDOSimpleName() + "ServiceImpl.java";
			File interfaceFile = new File(path);
			if (!interfaceFile.getParentFile().exists())
				interfaceFile.getParentFile().mkdirs();

			BufferedWriter bw = new BufferedWriter(new FileWriter(interfaceFile));
			bw.append("package " + config.getPackage() + ".impl;");
			bw.newLine();
			bw.newLine();
			bw.append("import static com.google.common.base.Preconditions.checkNotNull;");
			bw.newLine();
			bw.append("import static com.google.common.base.Preconditions.checkState;");
			bw.newLine();
			bw.append("import java.util.List;");
			bw.newLine();
			bw.append("import org.apache.commons.lang3.StringUtils;");
			bw.newLine();
			bw.append("import org.springframework.beans.factory.annotation.Autowired;");
			bw.newLine();
			bw.append("import org.springframework.stereotype.Service;");
			bw.newLine();
			bw.append("import org.springframework.transaction.annotation.Transactional;");
			bw.newLine();
			bw.append("import com.github.pagehelper.PageHelper;");
			bw.newLine();
			bw.append("import com.github.pagehelper.PageInfo;");
			bw.newLine();
			bw.append("import "+config.getDOBasePackageName()+".mapper."+config.getDOName()+"Mapper;");
			bw.newLine();
			bw.append("import "+config.getDOBasePackageName()+".pojo."+config.getDOName()+";");
			bw.newLine();
			bw.append("import "+config.getDOBasePackageName()+".pojo."+config.getDOName()+"Example;");
			bw.newLine();
			bw.append("import "+config.getDOBasePackageName()+".pojo."+config.getDOName()+"Example.Criteria;");
			bw.newLine();
			bw.append("import "+config.getPackage()+"."+config.getDOSimpleName()+"Service;");
			bw.newLine();
			bw.append("import com.jandar.common.dto.SearchDTO;");
			bw.newLine();
			bw.newLine();
			bw.append("@Service");
			bw.newLine();
			bw.append("public class " + config.getDOSimpleName() + "ServiceImpl implements "+config.getDOSimpleName()+"Service{");
			bw.newLine();
			bw.newLine();
			bw.append("\t@Autowired");
			bw.newLine();
			bw.append("\tprivate "+config.getDOName()+"Mapper "+config.getDOVarible()+"Mapper;");
			bw.newLine();
			bw.newLine();
			bw.append("\t@Override");
			bw.newLine();
			bw.append("\t@Transactional(readOnly = true)");
			bw.newLine();
			bw.append("\tpublic PageInfo<"+config.getDOName()+"> pageList(SearchDTO<"+config.getDOName()+"> query) {");
			bw.newLine();
			bw.append("\t\t"+config.getDOName()+"Example example = new "+config.getDOName()+"Example();");
			bw.newLine();
			bw.append("\t\tCriteria criteria = example.createCriteria();");
			bw.newLine();
			bw.append("\t\t//TODO edit your query condition");
			bw.newLine();
			
			bw.append("\t\tPageHelper.startPage(query.getPage(), query.getRows());");
			bw.newLine();
			bw.append("\t\tList<"+config.getDOName()+"> list = "+config.getDOVarible()+"Mapper.selectByExample(example);");
			bw.newLine();
			bw.append("\t\tPageInfo<"+config.getDOName()+"> pageInfo = new PageInfo<>(list);");
			bw.newLine();
			bw.append("\t\treturn pageInfo;");
			bw.newLine();
			bw.append("\t}");
			bw.newLine();
			bw.newLine();
			
			
			bw.append("\t@Override");
			bw.newLine();
			bw.append("\t@Transactional(readOnly = true)");
			bw.newLine();
			bw.append("\tpublic "+config.getDOName()+" get(Long id) {");
			bw.newLine();
			bw.append("\t\tcheckNotNull(id, \"param id is null\");");
			bw.newLine();
			bw.append("\t\treturn "+config.getDOVarible()+"Mapper.selectByPrimaryKey(id);");
			bw.newLine();
			bw.append("\t}");
			bw.newLine();
			bw.newLine();
			bw.append("\t@Override");
			bw.newLine();
			bw.append("\t@Transactional");
			bw.newLine();
			bw.append("\tpublic int add("+config.getDOName()+" record) {");
			bw.newLine();
			bw.append("\t\treturn "+config.getDOVarible()+"Mapper.insertSelective(record);");
			bw.newLine();
			bw.append("\t}");
			bw.newLine();
			bw.newLine();
			bw.append("\t@Override");
			bw.newLine();
			bw.append("\t@Transactional");
			bw.newLine();
			bw.append("\tpublic int delete(Long id) {");
			bw.newLine();
			bw.append("\t\tcheckNotNull(id, \"param id is null\");");
			bw.newLine();
			bw.append("\t\treturn "+config.getDOVarible()+"Mapper.deleteByPrimaryKey(id);");
			bw.newLine();
			bw.append("\t}");
			bw.newLine();
			bw.newLine();
			bw.append("\t@Override");
			bw.newLine();
			bw.append("\t@Transactional");
			bw.newLine();
			bw.append("\tpublic int update("+config.getDOName()+" record) {");
			bw.newLine();
			bw.append("\t\tcheckNotNull(record.getId(), \"record's id is null\");");
			bw.newLine();
			bw.append("\t\treturn "+config.getDOVarible()+"Mapper.updateByPrimaryKeySelective(record);");
			bw.newLine();
			bw.append("\t}");
			bw.newLine();
			bw.newLine();
			bw.append("\t@Override");
			bw.newLine();
			bw.append("\t@Transactional");
			bw.newLine();
			bw.append("\tpublic int updateCAS("+config.getDOName()+" record) {");
			bw.newLine();
			bw.append("\t\tcheckNotNull(record.getId(), \"record's id is null\");");
			bw.newLine();
			bw.append("\t\treturn "+config.getDOVarible()+"Mapper.updateByPrimaryKeySelectiveCAS(record);");
			bw.newLine();
			bw.append("\t}");
			
			bw.newLine();
			bw.newLine();
			bw.append("\t@Override");
			bw.newLine();
			bw.append("\t@Transactional(readOnly = true)");
			bw.newLine();
			bw.append("\tpublic List<"+config.getDOName()+"> listBy("+config.getDOName()+" query) {");
			bw.newLine();
			bw.append("\t\t"+config.getDOName()+"Example example = new "+config.getDOName()+"Example();");
			bw.newLine();
			bw.append("\t\tCriteria criteria = example.createCriteria();");
			bw.newLine();
			bw.append("\t\t//TODO edit your query condition");
			bw.newLine();
			
			bw.append("\t\treturn "+config.getDOVarible()+"Mapper.selectByExample(example);");
			bw.newLine();
			bw.append("\t}");
			
			bw.newLine();
			bw.newLine();
			bw.append("\t@Override");
			bw.newLine();
			bw.append("\t@Transactional(readOnly = true)");
			bw.newLine();
			bw.append("\tpublic "+config.getDOName()+" getBy("+config.getDOName()+" query) {");
			bw.newLine();
			bw.append("\t\t"+config.getDOName()+"Example example = new "+config.getDOName()+"Example();");
			bw.newLine();
			bw.append("\t\tCriteria criteria = example.createCriteria();");
			bw.newLine();
			bw.append("\t\t//TODO edit your query condition");
			bw.newLine();
			
			bw.append("\t\tList<"+config.getDOName()+"> result = "+config.getDOVarible()+"Mapper.selectByExample(example);");
			bw.newLine();
			
			bw.append("\t\tcheckState(result.size()<2, \"multy result by query\");");
			bw.newLine();
			bw.append("\t\tif(result.isEmpty()){");
			bw.newLine();
			bw.append("\t\t\treturn null;");
			bw.newLine();
			bw.append("\t\t}");
			bw.newLine();
			bw.append("\t\treturn result.get(0);");
			bw.newLine();
			bw.append("\t}");
			
			
			bw.newLine();
			bw.append("}");
			
			bw.newLine();
			bw.newLine();
			bw.flush();
			bw.close();
			bw = null;
			interfaceFile = null;
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
