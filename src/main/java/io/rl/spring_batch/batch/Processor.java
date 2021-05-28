package io.rl.spring_batch.batch;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import io.rl.spring_batch.models.User;

@Component
public class Processor implements ItemProcessor<User, User> {

	private static final Map<String, String> DEPT_NAMES = new HashMap<>();
	
	public Processor() {

		DEPT_NAMES.put("01", "Technology");
		DEPT_NAMES.put("02", "Operations");
		DEPT_NAMES.put("03", "Accounts");

		
	}		
	
	@Override
	public User process(User item) throws Exception {
		System.out.println("Data processing..");
		item.setDept(DEPT_NAMES.get(item.getDept()));
		System.out.println("Data processed..");
		return item;
	}
	
	
}
