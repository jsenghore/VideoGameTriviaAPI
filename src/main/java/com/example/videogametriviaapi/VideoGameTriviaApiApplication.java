package main.java.com.example.videogametriviaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // Enable scheduling for the application
public class VideoGameTriviaApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(VideoGameTriviaApiApplication.class, args);
    }
}
