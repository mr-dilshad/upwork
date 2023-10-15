package com.example.Python1;

import com.example.Python1.entity.User;
import com.example.Python1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import java.util.UUID;

@SpringBootApplication
public class Python1Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Python1Application.class, args);
	}

	@Autowired
	UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		userRepository.save(User.builder().userId(UUID.randomUUID().toString()).userEmail("hello@gmail.com").userName("hell").build());
	}
}
