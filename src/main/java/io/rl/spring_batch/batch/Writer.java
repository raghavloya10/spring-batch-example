package io.rl.spring_batch.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.rl.spring_batch.models.User;
import io.rl.spring_batch.repositories.UserRepository;

@Component
public class Writer implements ItemWriter<User> {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void write(List<? extends User> users) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Data being saved for users... ");
		System.out.println(users);
		this.userRepository.saveAll(users);
		System.out.println("Data save completed... ");
	}

}
