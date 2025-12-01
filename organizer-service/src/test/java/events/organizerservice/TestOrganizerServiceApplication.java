package events.organizerservice;

import org.springframework.boot.SpringApplication;

public class TestOrganizerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(OrganizerServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
