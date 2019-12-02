package com.jandar.generator.template.ant;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import com.jandar.generator.Data;
import com.jandar.generator.template.Ant;

public class GeneratorPortalRest implements Ant{
	
	private Data config;
	
	public GeneratorPortalRest(Data config){
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
			bw.append("import org.springframework.beans.factory.annotation.Autowired;");
			bw.newLine();
			bw.append("import java.util.List;");
			bw.newLine();
			bw.append("import javax.validation.Valid;");
			bw.newLine();
			bw.append("import com.jandar.common.controller.Result;");
			bw.newLine();
			bw.append("import org.springframework.web.bind.annotation.RestController;");
			bw.newLine();
			bw.append("import org.springframework.web.bind.annotation.GetMapping;");
			bw.newLine();
			bw.append("import org.springframework.web.bind.annotation.PathVariable;");
			bw.newLine();
			bw.append("import org.springframework.web.bind.annotation.PostMapping;");
			bw.newLine();
			bw.append("import org.springframework.web.bind.annotation.DeleteMapping;");
			bw.newLine();
			bw.append("import org.springframework.web.bind.annotation.RequestMapping;");
			bw.newLine();
			bw.append("import org.springframework.web.bind.annotation.RequestBody;");
			bw.newLine();
			bw.append("import com.jandar.common.dto.SearchDTO;");
			bw.newLine();
			bw.append("import "+config.getDOPackageName()+";");
			bw.newLine();
			bw.append("import com.jandar.common.util.PageResult;");
			bw.newLine();
			bw.append("import " + 
					config.getPackage().substring(0, config.getPackage().lastIndexOf(".")) + ".service." 
					+config.getDOSimpleName() + "FeignService;");
			bw.newLine();
			bw.newLine();
			bw.newLine();
			bw.append("@RestController");
			bw.newLine();
			bw.append("@RequestMapping(\""+config.getDOUrl()+"\")");
			bw.newLine();
			bw.append("public class "+config.getDOSimpleName()+"Controller {");
			bw.newLine();
			bw.newLine();
			bw.append("\t@Autowired");
			bw.newLine();
			bw.append("\tprivate "+config.getDOSimpleName()+"FeignService "+config.getDOVarible()+"FeignService;");
			bw.newLine();
			bw.newLine();
			bw.append("\t@PostMapping(\"/page\")");
			bw.newLine();
			bw.append("\tpublic Result<PageResult<"+config.getDOName()+">> page(@RequestBody SearchDTO<"+config.getDOName()+"> query) {");
			bw.newLine();
			bw.append("\t\tquery.getEntity().adorn();");
			bw.newLine();
			bw.append("\t\tPageResult<"+config.getDOName()+"> result = "+config.getDOVarible()+"FeignService.page(query);");
			bw.newLine();
			bw.append("\t\treturn Result.ok(result);");
			bw.newLine();
			bw.append("\t}");
			bw.newLine();
			bw.newLine();
			
			bw.append("\t@PostMapping(\"/save\")");
			bw.newLine();
			bw.append("\tpublic Result<String> save(@RequestBody "+config.getDOName()+" record){");
			bw.newLine();
			bw.append("\t\trecord.adorn();");
			bw.newLine();
			bw.append("\t\tif(record.getId() == null){");
			bw.newLine();
			bw.append("\t\t\t"+config.getDOVarible()+"FeignService.add(record);");
			bw.newLine();
			bw.append("\t\t}else{");
			bw.newLine();
			bw.append("\t\t\t"+config.getDOVarible() + "FeignService.update(record);");
			bw.newLine();
			bw.append("\t\t}");
			bw.newLine();
			bw.append("\t\treturn Result.ok(\"操作成功!\");");
			bw.newLine();
			bw.append("\t}");
			bw.newLine();
			bw.newLine();
			bw.append("\t@DeleteMapping(\"/{id}\")");
			bw.newLine();
			bw.append("\tpublic Result<String> delete(@PathVariable(\"id\") Long id){");
			bw.newLine();
			bw.append("\t\t"+config.getDOVarible()+"FeignService.delete(id);");
			bw.newLine();
			bw.append("\t\treturn Result.ok(\"删除成功!\");");
			bw.newLine();
			bw.newLine();
			bw.append("\t}");
			bw.newLine();
			bw.newLine();
			
			bw.append("\t@GetMapping(\"/{id}\")");
			bw.newLine();
			bw.append("\tpublic Result<"+config.getDOName()+"> get(@PathVariable(\"id\") Long id){");
			bw.newLine();
			bw.append("\t\t"+config.getDOName()+" record = "+config.getDOVarible()+"FeignService.get(id);");
			bw.newLine();
			bw.append("\t\treturn Result.ok(record);");
			bw.newLine();
			bw.append("\t}");
			bw.newLine();
			bw.newLine();
			
			bw.append("\t@PostMapping(\"/query/list\")");
			bw.newLine();
			bw.append("\tpublic Result<List<"+config.getDOName()+">> queryList(@RequestBody @Valid "+config.getDOName()+" query) {");
			bw.newLine();
			bw.append("\t\tList<"+config.getDOName()+"> result = "+config.getDOVarible()+"FeignService.listBy(query);");
			bw.newLine();
			bw.append("\t\treturn Result.ok(result);");
			bw.newLine();
			bw.append("\t}");
			bw.newLine();
			bw.newLine();
			
			bw.append("\t@PostMapping(\"/query/object\")");
			bw.newLine();
			bw.append("\tpublic Result<"+config.getDOName()+"> queryObject(@RequestBody @Valid "+config.getDOName()+" query) {");
			bw.newLine();
			bw.append("\t\t"+config.getDOName()+" result = "+config.getDOVarible()+"FeignService.getBy(query);");
			bw.newLine();
			bw.append("\t\treturn Result.ok(result);");
			bw.newLine();
			bw.append("\t}");
			bw.newLine();
			bw.newLine();
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
