package com.jandar.generator.template.ant;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import com.jandar.generator.Data;
import com.jandar.generator.template.Ant;

public class GeneratorFeignService implements Ant{

    private Data config;

    public GeneratorFeignService(Data config){
        this.config = config;
    }

    @Override
    public void generator(){
    	try{
    		// interface
            String path = config.getDist() + "/src/main/java/"
                    + config.getPackage().replace(".", "/") + "/"
                    + config.getDOSimpleName() + "FeignService.java";
            File interfaceFile = new File(path);
            if (!interfaceFile.getParentFile().exists()) {
                interfaceFile.getParentFile().mkdirs();
            }

            BufferedWriter bw = new BufferedWriter(new FileWriter(interfaceFile));
            bw.append("package " + config.getPackage() +";");
            bw.newLine();
            bw.newLine();
            bw.append("import javax.validation.Valid;");
            bw.newLine();
            bw.append("import java.util.List;");
            bw.newLine();
            bw.append("import org.springframework.cloud.openfeign.FeignClient;");
            bw.newLine();
            bw.append("import org.springframework.web.bind.annotation.RequestBody;");
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
            bw.newLine();
            bw.append("import " + config.getDOPackageName() + ";");
            bw.newLine();
            bw.append("import com.jandar.common.dto.SearchDTO;");
            bw.newLine();
            bw.append("import com.jandar.common.util.PageResult;");
            bw.newLine();
            bw.append("import "+config.getPackage()+".fallback."+config.getDOSimpleName()+"FeignFallback;");
            bw.newLine();
            bw.newLine();
            bw.append("@FeignClient(value = \""+config.getFeignClient().toUpperCase()+"\", fallback = "+config.getDOSimpleName()+"FeignFallback.class)");
            bw.newLine();
            bw.append("public interface "+config.getDOSimpleName()+"FeignService {");
            bw.newLine();
            bw.newLine();
            bw.append("\t@PostMapping(\"/"+config.getFeignClient().toLowerCase()+"/v1"  + config.getDOUrl() +"/pageResult\")");
            bw.newLine();
            bw.append("\tpublic PageResult<"  + config.getDOName() +"> page(@RequestBody SearchDTO<" + config.getDOName() + "> query);");
            bw.newLine();
            bw.newLine();
            
            bw.append("\t@GetMapping(\"/"+config.getFeignClient().toLowerCase()+"/v1" + config.getDOUrl() + "/{id}\")");
            bw.newLine();
            bw.append("\tpublic " + config.getDOSimpleName() + "DO get(@PathVariable(\"id\") Long id);");
            bw.newLine();
            bw.newLine();
            
            bw.append("\t@PostMapping(\"/"+config.getFeignClient().toLowerCase()+"/v1" + config.getDOUrl() + "/add\")");
            bw.newLine();
            bw.append("\tpublic Long add(@RequestBody @Valid " + config.getDOName() + " entity);");
            bw.newLine();
            bw.newLine();
            bw.append("\t@PutMapping(\"/"+config.getFeignClient().toLowerCase()+"/v1"  + config.getDOUrl() +  "/update\")");
            bw.newLine();
            bw.append("\tpublic void update(@RequestBody @Valid " + config.getDOName() + " entity);");
            bw.newLine();
            bw.newLine();
            bw.append("\t@DeleteMapping(\"/"+config.getFeignClient().toLowerCase()+"/v1" + config.getDOUrl() +"/{id}\")");
            bw.newLine();
            bw.append("\tpublic void delete(@PathVariable(\"id\") Long id);");
            bw.newLine();
            bw.newLine();
            bw.append("\t@PostMapping(\"/"+config.getFeignClient().toLowerCase()+"/v1" + config.getDOUrl() +"/query/list\")");
            bw.newLine();
            bw.append("\tpublic List<"+config.getDOName()+"> listBy(@RequestBody @Valid "+config.getDOName()+" query);");
            bw.newLine();
            bw.newLine();
            bw.append("\t@PostMapping(\"/"+config.getFeignClient().toLowerCase()+"/v1" + config.getDOUrl() +"/query/object\")");
            bw.newLine();
            bw.append("\tpublic "+config.getDOName()+" getBy(@RequestBody @Valid "+config.getDOName()+" query);");
            bw.newLine();
            bw.newLine();
            
            bw.newLine();
            bw.append("}");
            bw.flush();
            bw.close();
            bw = null;
            interfaceFile = null;
    	}catch (Exception e) {
    		e.printStackTrace();
		}
        
    }
}
