package com.example.videogametriviaapi;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "trivia_questions")
public class TriviaQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // Unique identifier for the trivia question

    private String question; // The trivia question text

    @Column(name = "correct_answer")
    private String correctAnswer; // The correct answer

    @Column(name = "creation_date")
    private LocalDateTime creationDate; // Timestamp for when the question was created

    // Constructors
    public TriviaQuestion() {
        this.creationDate = LocalDateTime.now(); // Set creation date to now by default
    }

    public TriviaQuestion(int id, String question, String correctAnswer) {
        this.id = id;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.creationDate = LocalDateTime.now(); // Set creation date to now
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}