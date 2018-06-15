/**
 * 
 */
package com.chenyf.drools.controller;

import org.kie.api.definition.KiePackage;
import org.kie.api.definition.rule.Rule;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chenyf.drools.fact.Product;

@RestController
@RequestMapping("drools")
public class DroolsController {

	@Autowired
	private KieSession kieSession;

	@GetMapping("discount")
	public Product getDiscount(String type) {
		Product product = new Product();
		product.setType(type);
		kieSession.insert(product);
		kieSession.fireAllRules();
		//kieSession.dispose();
		return product;
	}
	
	@GetMapping("reload_rule")
	public String reload() {
		//可从数据空读取规则
		String ruleStr = "package com.product;\r\n" + 
				"import com.chenyf.drools.fact.Product;\r\n" + 
				"rule \"rule3\"  \r\n" + 
				"    when  \r\n" + 
				"        p : Product(type == \"diamond\");  \r\n" + 
				"    then  \r\n" + 
				"        p.setDiscount(5);\r\n" + 
				"end  \r\n" + 
				"rule \"rule4\"  \r\n" + 
				"    when  \r\n" + 
				"        p : Product(type == \"gold\");\r\n" + 
				"    then  \r\n" + 
				"        p.setDiscount(5);\r\n" + 
				"end";
		KieHelper kieHelper = new KieHelper();
		kieHelper.addContent(ruleStr, ResourceType.DRL);
		this.kieSession = kieHelper.build().newKieSession();
		
		StringBuilder info = new StringBuilder();
		for(KiePackage kiePackage : kieHelper.getKieContainer().getKieBase().getKiePackages()) {
			for (Rule rule : kiePackage.getRules()) {
				info.append("onload rule ").append(rule.getName()).append("\n"); 
			}
		}
		info.append("reload success"); 
		return info.toString();
	}

}
