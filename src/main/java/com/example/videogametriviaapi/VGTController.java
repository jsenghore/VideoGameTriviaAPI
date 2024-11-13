package main.java.com.example.videogametriviaapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trivia")
public class VGTController {

    @Autowired
    private VGTDAO vgtDAO;

    // Trivia Facts
    @GetMapping("/facts/random")
    public TriviaFact getRandomFact() {
        return vgtDAO.getRandomFact();
    }

    @GetMapping("/facts/random/{category}")
    public TriviaFact getRandomFactByCategory(@PathVariable String category) {
        return vgtDAO.getRandomFactByCategory(category);
    }

    @PostMapping("/facts")
    public void addFact(@RequestBody TriviaFact fact) {
        vgtDAO.addFact(fact);
    }

    @PutMapping("/facts/{id}")
    public void updateFact(@PathVariable Long id, @RequestBody TriviaFact fact) {
        vgtDAO.updateFact(id, fact);
    }

    @DeleteMapping("/facts/{id}")
    public void deleteFact(@PathVariable Long id) {
        vgtDAO.deleteFact(id);
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
    public void updateQuestion(@PathVariable Long id, @RequestBody TriviaQuestion question) {
        vgtDAO.updateQuestion(id, question);
    }

    @DeleteMapping("/questions/{id}")
    public void deleteQuestion(@PathVariable Long id) {
        vgtDAO.deleteQuestion(id);
    }
}