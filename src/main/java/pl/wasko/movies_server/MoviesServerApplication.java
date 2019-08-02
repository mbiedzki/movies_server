package pl.wasko.movies_server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.wasko.movies_server.model.User;
import pl.wasko.movies_server.service.UserService;

@SpringBootApplication()
public class MoviesServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviesServerApplication.class, args);
		System.out.println("--------------------------------------------------------------------------------------");
		System.out.println("app works");
	}

}
