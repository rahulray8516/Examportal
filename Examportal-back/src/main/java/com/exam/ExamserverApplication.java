package com.exam;
import com.exam.helper.UserFoundException;
import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ExamserverApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
//	public QuizRepository quizRepository;

	public static void main(String[] args) {


		SpringApplication.run(ExamserverApplication.class, args);


	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("starting code");
		try {


			System.out.println("starting code");

			User user = new User();

			user.setFirstName("Rahul");
			user.setLastName("Ray");
			user.setUsername("login12");
			user.setPassword(this.bCryptPasswordEncoder.encode("abc"));
			user.setEmail("rahul@gmail.com");
			user.setProfile("default.png");

			Role role1 = new Role();
			role1.setRoleId(82L);
			role1.setRoleName("NORMAL");

			Set<UserRole> userRoleSet = new HashSet<>();
			UserRole userRole = new UserRole();

			userRole.setRole(role1);

			userRole.setUser(user);

			userRoleSet.add(userRole);

			User user1 = this.userService.createUser(user, userRoleSet);
			System.out.println(user1.getUsername());


		} catch (UserFoundException e) {
			e.printStackTrace();


		}

	}

}
