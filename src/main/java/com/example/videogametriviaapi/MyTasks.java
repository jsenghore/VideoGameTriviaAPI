package com.example.videogametriviaapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;

import java.util.List;

@Component
public class MyTasks {

    @Autowired
    private VGTDAO vgtDAO;

    private final String BEARER_TOKEN = "AAAAAAAAAAAAAAAAAAAAADk1xAEAAAAAYu8eIENJFKaV64foF8HQXZR0h6c%3DDJ4U3HdrkeXPIV8NXghSG65bfpd6a6vV6DRPZQWnO7x9T91XUu";

    @Scheduled(cron = "0 0 9 * * ?") // Every day at 9 AM
    public void postDailyTriviaFact() {
        TriviaFact fact = vgtDAO.getRandomFact();
        if (fact != null) {
            String tweetText = "Daily Trivia Fact: " + fact.getFact();
            postTweet(tweetText);
        }
    }

    @Scheduled(cron = "0 0 10 * * MON") // Every Monday at 10 AM
    public void postWeeklyTriviaQuestion() {
        List<TriviaQuestion> questions = vgtDAO.getRandomQuestions();
        if (!questions.isEmpty()) {
            for (TriviaQuestion question : questions) {
                String tweetText = "Weekly Trivia Question: " + question.toString();
                postTweet(tweetText);
            }
        }
    }

    @Scheduled(cron = "0 0 1 1 * ?") // Every 1st of the month at 1 AM
    public void printPopularTrivia() {
        String popularCategory = vgtDAO.getMostPopularCategory();
        System.out.println("Most Popular Trivia Category: " + popularCategory);
    }

    @Scheduled(cron = "0 0 0 * * ?") // Every day at midnight
    public void cleanupDatabase() {
        vgtDAO.cleanupOldEntries();
        System.out.println("Old trivia facts and questions have been cleaned up.");
    }

    private void postTweet(String text) {
        String url = "https://api.x.com/2/tweets";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(BEARER_TOKEN);

        String requestBody = "{\"text\": \"" + text + "\"}";

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
            System.out.println("Tweet posted successfully: " + response.getBody());
        } catch (Exception e) {
            System.err.println("Error posting tweet: " + e.getMessage());
        }
    }
}



