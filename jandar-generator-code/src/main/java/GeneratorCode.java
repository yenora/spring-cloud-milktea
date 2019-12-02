import org.dom4j.DocumentException;

import com.jandar.generator.ReadFactory;

public class GeneratorCode {

	public static void main(String[] args) throws DocumentException{
		
		String configFilePath = "E:\\yaoswork\\study\\spring-cloud-milktea\\jandar-generator-code\\src\\main\\resources\\generator.xml";

		ReadFactory factory = new ReadFactory(configFilePath);
		factory.generate();

		System.out.println("done!");
	}

}
