package com.example.videogametriviaapi;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Random;

@Repository
public class VGTDAO {

    @PersistenceContext
    private EntityManager entityManager;

    private final Random random = new Random();

    // Trivia Facts
    public TriviaFact getRandomFact() {
        Query query = entityManager.createNativeQuery("SELECT * FROM trivia_facts ORDER BY RAND() LIMIT 1", TriviaFact.class);
        return (TriviaFact) query.getSingleResult();
    }

    public TriviaFact getRandomFactByCategory(String category) {
        Query query = entityManager.createNativeQuery("SELECT * FROM trivia_facts WHERE category = :category ORDER BY RAND() LIMIT 1", TriviaFact.class);
        query.setParameter("category", category);
        return (TriviaFact) query.getSingleResult();
    }

    @Transactional
    public void addFact(TriviaFact fact) {
        entityManager.persist(fact);
    }

    @Transactional
    public void updateFact(int id, TriviaFact fact) {
        TriviaFact existingFact = entityManager.find(TriviaFact.class, id);
        if (existingFact != null) {
            existingFact.setFact(fact.getFact());
            existingFact.setCategory(fact.getCategory());
            entityManager.merge(existingFact);
        }
    }

    @Transactional
    public void deleteFact(int id) {
        TriviaFact fact = entityManager.find(TriviaFact.class, id);
        if (fact != null) {
            entityManager.remove(fact);
        }
    }

    // Trivia Questions
    public List<TriviaQuestion> getRandomQuestions() {
        Query query = entityManager.createNativeQuery("SELECT * FROM trivia_questions ORDER BY RAND() LIMIT 5", TriviaQuestion.class);
        return query.getResultList();
    }

    public List<TriviaQuestion> getRandomQuestionsByCategory(String category) {
        Query query = entityManager.createNativeQuery("SELECT * FROM trivia_questions WHERE category = :category ORDER BY RAND() LIMIT 5", TriviaQuestion.class);
        query.setParameter("category", category);
        return query.getResultList();
    }

    @Transactional
    public void addQuestion(TriviaQuestion question) {
        entityManager.persist(question);
    }

    @Transactional
    public void updateQuestion(int id, TriviaQuestion question) {
        TriviaQuestion existingQuestion = entityManager.find(TriviaQuestion.class, id);
        if (existingQuestion != null) {
            existingQuestion.setQuestion(question.getQuestion());
            existingQuestion.setOptions(question.getOptions());
            existingQuestion.setCorrectAnswer(question.getCorrectAnswer());
            entityManager.merge(existingQuestion);
        }
    }

    @Transactional
    public void deleteQuestion(int id) {
        TriviaQuestion question = entityManager.find(TriviaQuestion.class, id);
        if (question != null) {
            entityManager.remove(question);
        }
    }

    // New Method: Get Most Popular Trivia Category
    public String getMostPopularCategory() {
        Query query = entityManager.createNativeQuery(
                "SELECT category, COUNT(*) FROM trivia_facts GROUP BY category ORDER BY COUNT(*) DESC");
        List<Object[]> results = query.getResultList();
        return results.isEmpty() ? null : (String) results.get(0)[0]; // Return the category with the highest count
    }

    // New Method: Cleanup Old Entries
    @Transactional
    public void cleanupOldEntries() {
        // Assuming you have a timestamp field in your entities to check age
        entityManager.createNativeQuery("DELETE FROM trivia_facts WHERE creation_date < NOW() - INTERVAL 3 MONTH").executeUpdate();
        entityManager.createNativeQuery("DELETE FROM trivia_questions WHERE creation_date < NOW() - INTERVAL 3 MONTH").executeUpdate();
    }
}