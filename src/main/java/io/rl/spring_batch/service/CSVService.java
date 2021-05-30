package io.rl.spring_batch.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import io.rl.spring_batch.batch.CSVHelper;
import io.rl.spring_batch.models.User;
import io.rl.spring_batch.repositories.UserRepository;

@Service
public class CSVService {
	
	@Autowired
	UserRepository userRepository;
	
	public void save(MultipartFile file) {
		try {
			List<User> users = CSVHelper.csvToTutorials(file.getInputStream());
			this.userRepository.saveAll(users);
		} catch (IOException e) {
			throw new RuntimeException("Fail to store CSV data: "+e.getMessage());// a: handle exception
		}
	}
	
	public List<User> getAllUsers() {
		return this.userRepository.findAll();
	}
}
