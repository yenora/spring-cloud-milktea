package com.jandar.generator.template.ant;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import com.jandar.generator.Data;
import com.jandar.generator.template.Ant;

public class GeneratorRest implements Ant{
	
	private Data config;
	
	public GeneratorRest(Data config){
		this.config = config;
	}

	@Override
	public void generator(){
		try{
			// interface
			String path = config.getDist() + "/src/main/java/"
					+ config.getPackage().replace(".", "/") + "/"
					+ config.getDOSimpleName() + "Controller.java";
			File interfaceFile = new File(path);
			if (!interfaceFile.getParentFile().exists())
				interfaceFile.getParentFile().mkdirs();

			BufferedWriter bw = new BufferedWriter(new FileWriter(interfaceFile));
			bw.append("package "+config.getPackage()+";");
			bw.newLine();
			bw.newLine();
			bw.append("import java.util.List;");
			bw.newLine();
			bw.append("import javax.validation.Valid;");
			bw.newLine();
			bw.append("import org.slf4j.Logger;");
			bw.newLine();
			bw.append("import org.slf4j.LoggerFactory;");
			bw.newLine();
			bw.append("import org.springframework.beans.factory.annotation.Autowired;");
			bw.newLine();
			bw.append("import org.springframework.http.ResponseEntity;");
			bw.newLine();
			bw.append("import org.springframework.stereotype.Controller;");
			bw.newLine();
			bw.append("import org.springframework.web.bind.annotation.DeleteMapping;");
			bw.newLine();
			bw.append("import org.springframework.web.bind.annotation.GetMapping;");
			bw.newLine();
			bw.append("import org.springframework.web.bind.annotation.PathVariable;");
			bw.newLine();
			bw.append("import org.springframework.web.bind.annotation.PostMapping;");
			bw.newLine();
			bw.append("import org.springframework.web.bind.annotation.PutMapping;");
			bw.newLine();
			bw.append("import org.springframework.web.bind.annotation.RequestBody;");
			bw.newLine();
			bw.append("import org.springframework.web.bind.annotation.RequestMapping;");
			bw.newLine();
			bw.append("import com.jandar.common.log.AutoLog;");
			bw.newLine();
			bw.append("import com.jandar.common.util.PageResult;");
			bw.newLine();
			bw.append("import "+config.getDOBasePackageName()+".pojo."+config.getDOName()+";");
			bw.newLine();
			bw.append("import "+config.getDOBasePackageName()+".service."+config.getDOSimpleName()+"Service;");
			bw.newLine();
			bw.append("import com.jandar.common.dto.SearchDTO;");
			bw.newLine();
			bw.newLine();
			bw.append("@Controller");
			bw.newLine();
			bw.append("@RequestMapping(\"/v1"+config.getDOUrl()+"\")");
			bw.newLine();
			bw.append("public class "+config.getDOSimpleName()+"Controller {");
			bw.newLine();
			bw.newLine();
			bw.append("private static final Logger LOGGER = LoggerFactory.getLogger("+config.getDOSimpleName()+"Controller.class);");
			bw.newLine();
			bw.append("\t@Autowired");
			bw.newLine();
			bw.append("\tprivate "+config.getDOSimpleName()+"Service "+config.getDOVarible()+"Service;");
			bw.newLine();
			bw.newLine();
			bw.append("\t@AutoLog(value=\"调用"+config.getDOCH()+"分页列表查询接口\")");
			bw.newLine();
			bw.append("\t@PostMapping(\"/pageResult\")");
			bw.newLine();
			bw.append("\tpublic ResponseEntity<PageResult<"+config.getDOName()+">> pageResult(@RequestBody SearchDTO<"+config.getDOName()+"> query) throws Exception {");
			bw.newLine();
			bw.append("\t\tif (LOGGER.isInfoEnabled()) {");
			bw.newLine();
			bw.append("\t\t\tLOGGER.info(\"调用"+config.getDOCH()+"分页列表查询接口\");");
			bw.newLine();
			bw.append("\t\t}");
			bw.newLine();
			bw.append("\t\tPageResult<"+config.getDOName()+"> pageResult = PageResult.build("+config.getDOVarible()+"Service.pageList(query));");
			bw.newLine();
			bw.append("\t\treturn ResponseEntity.ok(pageResult);");
			bw.newLine();
			bw.append("\t}");
			bw.newLine();
			bw.newLine();
			
			bw.append("\t@AutoLog(value=\"调用"+config.getDOCH()+"详情查询接口\")");
			bw.newLine();
			bw.append("\t@GetMapping(\"/{id}\")");
			bw.newLine();
			bw.append("\tpublic ResponseEntity<"+config.getDOName()+"> object(@PathVariable(\"id\") Long id) throws Exception {");
			bw.newLine();
			bw.append("\t\tif (LOGGER.isInfoEnabled()) {");
			bw.newLine();
			bw.append("\t\t\tLOGGER.info(\"调用"+config.getDOCH()+"详情查询接口\");");
			bw.newLine();
			bw.append("\t\t}");
			bw.newLine();
			bw.append("\t\t"+config.getDOName()+" result = "+config.getDOVarible()+"Service.get(id);");
			bw.newLine();
			bw.append("\t\treturn ResponseEntity.ok(result);");
			bw.newLine();
			bw.append("\t}");
			bw.newLine();
			bw.newLine();
			
			bw.append("\t@AutoLog(value=\"调用"+config.getDOCH()+"添加接口\")");
			bw.newLine();
			bw.append("\t@PostMapping(\"/add\")");
			bw.newLine();
			bw.append("\tpublic ResponseEntity<Void> add(@RequestBody @Valid "+config.getDOName()+" "+config.getDOVarible()+") throws Exception {");
			bw.newLine();
			bw.append("\t\tif (LOGGER.isInfoEnabled()) {");
			bw.newLine();
			bw.append("\t\t\tLOGGER.info(\"调用"+config.getDOCH()+"添加接口\");");
			bw.newLine();
			bw.append("\t\t}");
			bw.newLine();
			bw.append("\t\t"+config.getDOVarible()+"Service.add("+config.getDOVarible()+");");
			bw.newLine();
			bw.append("\t\treturn ResponseEntity.ok().build();");
			bw.newLine();
			bw.append("\t}");
			bw.newLine();
			bw.newLine();
			bw.append("\t@AutoLog(value=\"调用"+config.getDOCH()+"删除接口\")");
			bw.newLine();
			bw.append("\t@DeleteMapping(\"/{id}\")");
			bw.newLine();
			bw.append("\tpublic ResponseEntity<Void> delete(@PathVariable(\"id\") Long id) throws Exception {");
			bw.newLine();
			bw.append("\t\tif (LOGGER.isInfoEnabled()) {");
			bw.newLine();
			bw.append("\t\t\tLOGGER.info(\"调用"+config.getDOCH()+"删除接口\");");
			bw.newLine();
			bw.append("\t\t}");
			bw.newLine();
			bw.append("\t\t"+config.getDOVarible()+"Service.delete(id);");
			bw.newLine();
			bw.append("\t\treturn ResponseEntity.ok().build();");
			bw.newLine();
			bw.append("\t}");
			bw.newLine();
			bw.newLine();
			bw.append("\t@AutoLog(value=\"调用"+config.getDOCH()+"修改接口\")");
			bw.newLine();
			bw.append("\t@PutMapping(\"/update\")");
			bw.newLine();
			bw.append("\tpublic ResponseEntity<Void> update(@RequestBody @Valid "+config.getDOName()+" "+config.getDOVarible()+") throws Exception {");
			bw.newLine();
			bw.append("\t\tif (LOGGER.isInfoEnabled()) {");
			bw.newLine();
			bw.append("\t\t\tLOGGER.info(\"调用"+config.getDOCH()+"修改接口\");");
			bw.newLine();
			bw.append("\t\t}");
			bw.newLine();
			bw.append("\t\t//you can call updateCAS method while DO object has version field!");
			bw.newLine();
			bw.append("\t\t"+config.getDOVarible()+"Service.update("+config.getDOVarible()+");");
			bw.newLine();
			bw.append("\t\treturn ResponseEntity.ok().build();");
			bw.newLine();
			bw.newLine();
			bw.append("\t}");
			
			bw.newLine();
			bw.newLine();
			bw.append("\t@AutoLog(value=\"调用"+config.getDOCH()+"列表查询接口\")");
			bw.newLine();
			bw.append("\t@PostMapping(\"/query/list\")");
			bw.newLine();
			bw.append("\tpublic ResponseEntity<List<"+config.getDOName()+">> queryList(@RequestBody @Valid "+config.getDOName()+" query) throws Exception {");
			bw.newLine();
			bw.append("\t\tif (LOGGER.isInfoEnabled()) {");
			bw.newLine();
			bw.append("\t\t\tLOGGER.info(\"调用"+config.getDOCH()+"列表查询接口\");");
			bw.newLine();
			bw.append("\t\t}");
			bw.newLine();
			bw.append("\t\tList<"+config.getDOName()+"> result = "+config.getDOVarible()+"Service.listBy(query);");
			bw.newLine();
			bw.append("\t\treturn ResponseEntity.ok(result);");
			bw.newLine();
			bw.newLine();
			bw.append("\t}");
			
			bw.newLine();
			bw.newLine();
			bw.append("\t@AutoLog(value=\"调用"+config.getDOCH()+"列表查询接口\")");
			bw.newLine();
			bw.append("\t@PostMapping(\"/query/object\")");
			bw.newLine();
			bw.append("\tpublic ResponseEntity<"+config.getDOName()+"> queryObject(@RequestBody @Valid "+config.getDOName()+" query) throws Exception {");
			bw.newLine();
			bw.append("\t\tif (LOGGER.isInfoEnabled()) {");
			bw.newLine();
			bw.append("\t\t\tLOGGER.info(\"调用"+config.getDOCH()+"列表查询接口\");");
			bw.newLine();
			bw.append("\t\t}");
			bw.newLine();
			bw.append("\t\t"+config.getDOName()+" result = "+config.getDOVarible()+"Service.getBy(query);");
			bw.newLine();
			bw.append("\t\treturn ResponseEntity.ok(result);");
			bw.newLine();
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
