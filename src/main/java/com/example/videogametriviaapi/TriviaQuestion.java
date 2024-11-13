package main.java.com.example.videogametriviaapi;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "trivia_questions")
public class TriviaQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique identifier for the trivia question

    private String question; // The trivia question text

    @ElementCollection // To store a list of options
    private List<String> options; // List of answer choices

    private String correctAnswer; // The correct answer

    // Constructors
    public TriviaQuestion() {}

    public TriviaQuestion(Long id, String question, List<String> options, String correctAnswer) {
        this.id = id;
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}

