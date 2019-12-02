package com.jandar.generator.template.ant;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import com.jandar.generator.Data;
import com.jandar.generator.template.Ant;

public class GeneratorCommonService implements Ant{
	
	private Data data;
	
	public GeneratorCommonService(Data data){
		this.data = data;
	}

	@Override
	public void generator(){
		try{
			// interface
			String path = data.getDist() + "/src/main/java/"
					+ data.getPackage().replace(".", "/") + "/"
					+ data.getDOSimpleName() + "Service.java";
			File interfaceFile = new File(path);
			if (!interfaceFile.getParentFile().exists()) {
				interfaceFile.getParentFile().mkdirs();
			}

			BufferedWriter bw = new BufferedWriter(new FileWriter(interfaceFile));
			bw.append("package " + data.getPackage() + ";");
			bw.newLine();
			bw.newLine();
			bw.append("import com.example.common.service.AbstractService;");
			bw.newLine();
			bw.append("import "+ data.getDOPackageName() + ";");
			bw.newLine();
			bw.newLine();
			bw.append("public interface " + data.getDOSimpleName() + "Service extends AbstractService<"+data.getDOName()+">{");
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
