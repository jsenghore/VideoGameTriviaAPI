package com.example.videogametriviaapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyTasks {

    @Autowired
    private VGTDAO vgtDAO;

    // Daily Trivia Fact Post
    @Scheduled(cron = "0 0 9 * * ?") // Every day at 9 AM
    public void postDailyTriviaFact() {
        TriviaFact fact = vgtDAO.getRandomFact();
        if (fact != null) {
            // Logic to post the fact to X.com (or any other platform)
            System.out.println("Daily Trivia Fact: " + fact.getFact());
        }
    }

    // Weekly Trivia Question Post
    @Scheduled(cron = "0 0 10 * * MON") // Every Monday at 10 AM
    public void postWeeklyTriviaQuestion() {
        List<TriviaQuestion> questions = vgtDAO.getRandomQuestions();
        if (!questions.isEmpty()) {
            // Logic to post the questions to X.com
            questions.forEach(question -> {
                System.out.println("Weekly Trivia Question: " + question.getQuestion());
                // Print options for clarity
                System.out.println("Options: " + question.getOptions());
            });
        }
    }

    // Monthly Popular Trivia
    @Scheduled(cron = "0 0 1 1 * ?") // Every 1st of the month at 1 AM
    public void printPopularTrivia() {
        // Logic to determine the most popular trivia category
        String popularCategory = vgtDAO.getMostPopularCategory();
        System.out.println("Most Popular Trivia Category: " + popularCategory);
    }

    // Database Cleanup
    @Scheduled(cron = "0 0 0 * * ?") // Every day at midnight
    public void cleanupDatabase() {
        vgtDAO.cleanupOldEntries();
        System.out.println("Old trivia facts and questions have been cleaned up.");
    }
}



