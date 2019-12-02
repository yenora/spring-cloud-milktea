package com.jandar.generator.template.ant;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import com.jandar.generator.Data;
import com.jandar.generator.template.Ant;

public class GeneratorFeignFallback implements Ant{

    private Data config;

    public GeneratorFeignFallback(Data config){
        this.config = config;
    }

    @Override
    public void generator(){
    	try{
    		// interface
            String path = config.getDist() + "/src/main/java/"
                    + config.getPackage().replace(".", "/") + "/fallback/"
                    + config.getDOSimpleName() + "FeignFallback.java";
            File interfaceFile = new File(path);
            if (!interfaceFile.getParentFile().exists()) {
                interfaceFile.getParentFile().mkdirs();
            }

            BufferedWriter bw = new BufferedWriter(new FileWriter(interfaceFile));
            bw.append("package " + config.getPackage() +".fallback;");
            bw.newLine();
            bw.newLine();
            bw.append("import org.slf4j.Logger;");
            bw.newLine();
            bw.append("import org.slf4j.LoggerFactory;");
            bw.newLine();
            bw.append("import java.util.List;");
            bw.newLine();
            bw.append("import org.springframework.stereotype.Component;");
            bw.newLine();
            bw.append("import com.google.common.collect.Lists;");
            bw.newLine();
            bw.append("import com.jandar.common.dto.SearchDTO;");
            bw.newLine();
            bw.append("import com.jandar.common.util.PageResult;");
            bw.newLine();
            bw.append("import "+config.getDOPackageName()+";");
            bw.newLine();
            bw.append("import "+config.getPackage()+"."+config.getDOSimpleName()+"FeignService;");
            bw.newLine();
            bw.newLine();
            bw.append("@Component");
            bw.newLine();
            bw.append("public class "+config.getDOSimpleName()+"FeignFallback implements "+config.getDOSimpleName()+"FeignService {");
            bw.newLine();
            bw.newLine();
            
            bw.append("\tprivate static final Logger LOGGER = LoggerFactory.getLogger("+config.getDOSimpleName()+"FeignFallback.class); ");
            bw.newLine();
            bw.newLine();
            
            bw.append("\t@Override");
            bw.newLine();
            bw.append("\tpublic PageResult<"+config.getDOName()+"> page(SearchDTO<"+config.getDOName()+"> search) {");
            bw.newLine();
            bw.append("\t\tLOGGER.error(\"call remote "+config.getDOSimpleName()+"FeignService  failed!\");");
            bw.newLine();
            bw.append("\t\treturn new PageResult<"+config.getDOName()+">();");
            bw.newLine();
            bw.append("\t}");
            bw.newLine(); 
            bw.newLine();
            
            bw.append("\t@Override");
            bw.newLine();
            bw.append("\tpublic "+config.getDOName()+" get(Long id) {");
            bw.newLine();
            bw.append("\t\tLOGGER.error(\"call remote "+config.getDOSimpleName()+"FeignService  failed!\");");
            bw.newLine();
            bw.append("\t\treturn new "+config.getDOName()+"();");
            bw.newLine();
            bw.append("\t}");
            bw.newLine();
            bw.newLine();
            
            bw.append("\t@Override");
            bw.newLine();
            bw.append("\tpublic Long add("+config.getDOName()+" record) {");
            bw.newLine();
            bw.append("\t\tLOGGER.error(\"call remote "+config.getDOSimpleName()+"FeignService  failed!\");");
            bw.newLine();
            bw.append("\t\treturn 0L;");
            bw.newLine();
            bw.append("\t}");
            bw.newLine();
            bw.newLine();
            
        	bw.append("\t@Override"); 
        	bw.newLine();
        	bw.append("\tpublic void delete(Long id) {"); 
        	bw.newLine();
        	bw.append("\t\tLOGGER.error(\"call remote "+config.getDOSimpleName()+"FeignService  failed!\");");
        	bw.newLine();
        	bw.append("\t}");
        	bw.newLine();
        	bw.newLine();

        	bw.append("\t@Override");
        	bw.newLine();
        	bw.append("\tpublic void update("+config.getDOName()+" record) {");
        	bw.newLine();
        	bw.append("\t\tLOGGER.error(\"call remote "+config.getDOSimpleName()+"FeignService  failed!\");");
        	bw.newLine();
        	bw.append("\t}");
        	bw.newLine();
        	bw.newLine();

        	bw.append("\t@Override");
        	bw.newLine();
        	bw.append("\tpublic List<"+config.getDOName()+"> listBy("+config.getDOName()+" query) {");
        	bw.newLine();
        	bw.append("\t\tLOGGER.error(\"call remote "+config.getDOSimpleName()+"FeignService  failed!\");");
        	bw.newLine();
        	bw.append("\t\treturn Lists.newArrayList();");
        	bw.newLine();
        	bw.append("\t}");
        	bw.newLine();
        	bw.newLine();
        	
        	bw.append("\t@Override");
        	bw.newLine();
        	bw.append("\tpublic "+config.getDOName()+" getBy("+config.getDOName()+" query) {");
        	bw.newLine();
        	bw.append("\t\tLOGGER.error(\"call remote "+config.getDOSimpleName()+"FeignService  failed!\");");
        	bw.newLine();
        	bw.append("\t\treturn new "+config.getDOName()+"();");
        	bw.newLine();
        	bw.append("\t}");
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
