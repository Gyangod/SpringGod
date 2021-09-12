package com.gyangod;


import com.gyangod.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GyangodApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(GyangodApplication.class, args);
	}

	@Autowired
	private CustomerRepository  customerRepository;

	@Override
	public void run(String... args) throws Exception {
		customerRepository.deleteAll();
		/*customerRepository.save(new CustomerEntity("andy","Anindya",null,"abc@xyz.com","1234567890", "hello", UserStatusState.ACTIVE.name()));
		customerRepository.save(new CustomerEntity("ashoke","Ashoke","Chakraborty","abcd@xyz.com","1234567890", "baba", UserStatusState.ACTIVE.name()));*/
	}
}
