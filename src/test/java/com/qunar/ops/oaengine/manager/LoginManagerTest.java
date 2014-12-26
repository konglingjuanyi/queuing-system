package com.qunar.ops.oaengine.manager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class LoginManagerTest {
	@Autowired
	private LoginManager loginManager;
	
	@Test
	public void loginTest(){
		String ret = loginManager.login("nuby.zhang", "4rfv3edC");
		System.out.println(ret);
	}

}
