package com.example.videogametriviaapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/trivia")
public class VGTController {

    @Autowired
    private VGTDAO vgtDAO;

    // Trivia Facts
    @GetMapping("/getRandomFact")
    public TriviaFact getRandomFact() {
        return vgtDAO.getRandomFact();
    }

    @GetMapping("/getRandomFactByCategory/{category}")
    public TriviaFact getRandomFactByCategory(@PathVariable String category) {
        return vgtDAO.getRandomFactByCategory(category);
    }

    @PostMapping("/addFact")
    public void addFact(@RequestBody TriviaFact fact) throws SQLException {
        vgtDAO.addFact(fact);
    }

    @PutMapping("/updateFact/{id}")
    public void updateFact(@PathVariable int id, @RequestBody TriviaFact fact) {
        vgtDAO.updateFact(id, fact);
    }

    @RequestMapping("/deleteFact/{id}")
    public TriviaFact deleteFact(@PathVariable int id) {
        return vgtDAO.deleteFact(id);
    }

    // Trivia Questions
    @GetMapping("/questions/quiz")
    public List<TriviaQuestion> getRandomQuestions() {
        return vgtDAO.getRandomQuestions();
    }

    @GetMapping("/questions/quiz/{category}")
    public List<TriviaQuestion> getRandomQuestionsByCategory(@PathVariable String category) {
        return vgtDAO.getRandomQuestionsByCategory(category);
    }

    @PostMapping("/questions")
    public void addQuestion(@RequestBody TriviaQuestion question) {
        vgtDAO.addQuestion(question);
    }

    @PutMapping("/questions/{id}")
    public void updateQuestion(@PathVariable int id, @RequestBody TriviaQuestion question) {
        vgtDAO.updateQuestion(id, question);
    }

    @DeleteMapping("/questions/{id}")
    public void deleteQuestion(@PathVariable int id) {
        vgtDAO.deleteQuestion(id);
    }
}