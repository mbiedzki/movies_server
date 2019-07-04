package pl.wasko.movies_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication()
public class MoviesServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviesServerApplication.class, args);
		System.out.println("--------------------------------------------------------------------------------------");
		System.out.println("app works");
	}

}
