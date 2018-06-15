package com.chenyf.drools;

import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class DroolsAutoConfig {

	// 通过加载drl文件启动

	// private static final String drlFile = "rules/product_rule.drl";
	//
	// @Bean
	// public KieContainer kieContainer() {
	// KieServices kieServices = KieServices.Factory.get();
	// KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
	// kieFileSystem.write(ResourceFactory.newClassPathResource(drlFile));
	// KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
	// kieBuilder.buildAll();
	// KieModule kieModule = kieBuilder.getKieModule();
	// KieContainer kieContainer =
	// kieServices.newKieContainer(kieModule.getReleaseId());
	// return kieContainer;
	// }
	//
	// @Bean
	// public KieSession kieSession() throws IOException {
	// return kieContainer().newKieSession();
	// }

	@Bean
	public KieSession kieSession() throws IOException {
		String ruleStr = "package com.product;\r\n" + 
				"import com.chenyf.drools.fact.Product;\r\n" + 
				"rule \"rule1\" \r\n" + 
				" when \r\n" + 
				" p : Product(type == \"diamond\"); \r\n" + 
				" then \r\n" + 
				" p.setDiscount(10);\r\n" + 
				"end \r\n" + 
				"rule \"rule2\" \r\n" + 
				" when \r\n" + 
				" p : Product(type == \"gold\");\r\n" + 
				" then \r\n" + 
				" p.setDiscount(15);\r\n" + 
				"end";
		KieHelper kieHelper = new KieHelper();
		kieHelper.addContent(ruleStr, ResourceType.DRL);
		return kieHelper.build().newKieSession();
	}

}