package com.chenyf.drools;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDroolsApplicationTests {

	@Autowired
	private KieSession kieSession;

	@Test
	public void contextLoads() {

		System.out.println();
	}

}
