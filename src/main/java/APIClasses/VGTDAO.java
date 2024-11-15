package APIClasses;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Random;

@Repository
public class VGTDAO {

    @PersistenceContext
    private EntityManager entityManager;

    private final Random random = new Random();

    // Trivia Facts
    public TriviaFact getRandomFact() {
        TypedQuery<TriviaFact> query = entityManager.createQuery("SELECT f FROM TriviaFact f", TriviaFact.class);
        List<TriviaFact> facts = query.getResultList();
        return facts.isEmpty() ? null : facts.get(random.nextInt(facts.size()));
    }

    public TriviaFact getRandomFactByCategory(String category) {
        TypedQuery<TriviaFact> query = entityManager.createQuery("SELECT f FROM TriviaFact f WHERE f.category = :category", TriviaFact.class);
        query.setParameter("category", category);
        List<TriviaFact> facts = query.getResultList();
        return facts.isEmpty() ? null : facts.get(random.nextInt(facts.size()));
    }

    @Transactional
    public void addFact(TriviaFact fact) {
        entityManager.persist(fact);
    }

    @Transactional
    public void updateFact(Long id, TriviaFact fact) {
        TriviaFact existingFact = entityManager.find(TriviaFact.class, id);
        if (existingFact != null) {
            existingFact.setFact(fact.getFact());
            existingFact.setCategory(fact.getCategory());
            entityManager.merge(existingFact);
        }
    }

    @Transactional
    public void deleteFact(Long id) {
        TriviaFact fact = entityManager.find(TriviaFact.class, id);
        if (fact != null) {
            entityManager.remove(fact);
        }
    }

    // Trivia Questions
    public List<TriviaQuestion> getRandomQuestions() {
        TypedQuery<TriviaQuestion> query = entityManager.createQuery("SELECT q FROM TriviaQuestion q", TriviaQuestion.class);
        return query.setMaxResults(5).getResultList();
    }

    public List<TriviaQuestion> getRandomQuestionsByCategory(String category) {
        TypedQuery<TriviaQuestion> query = entityManager.createQuery("SELECT q FROM TriviaQuestion q WHERE q.category = :category", TriviaQuestion.class);
        query.setParameter("category", category);
        return query.setMaxResults(5).getResultList();
    }

    @Transactional
    public void addQuestion(TriviaQuestion question) {
        entityManager.persist(question);
    }

    @Transactional
    public void updateQuestion(Long id, TriviaQuestion question) {
        TriviaQuestion existingQuestion = entityManager.find(TriviaQuestion.class, id);
        if (existingQuestion != null) {
            existingQuestion.setQuestion(question.getQuestion());
            existingQuestion.setOptions(question.getOptions());
            existingQuestion.setCorrectAnswer(question.getCorrectAnswer());
            entityManager.merge(existingQuestion);
        }
    }

    @Transactional
    public void deleteQuestion(Long id) {
        TriviaQuestion question = entityManager.find(TriviaQuestion.class, id);
        if (question != null) {
            entityManager.remove(question);
        }
    }

    // New Method: Get Most Popular Trivia Category
    public String getMostPopularCategory() {
        TypedQuery<Object[]> query = entityManager.createQuery(
                "SELECT f.category, COUNT(f) FROM TriviaFact f GROUP BY f.category ORDER BY COUNT(f) DESC", Object[].class);
        List<Object[]> results = query.getResultList();
        return results.isEmpty() ? null : (String) results.get(0)[0]; // Return the category with the highest count
    }

    // New Method: Cleanup Old Entries
    @Transactional
    public void cleanupOldEntries() {
        // Assuming you have a timestamp field in your entities to check age
        entityManager.createQuery("DELETE FROM TriviaFact f WHERE f.creationDate < CURRENT_DATE - INTERVAL 3 MONTH").executeUpdate();
        entityManager.createQuery("DELETE FROM TriviaQuestion q WHERE q.creationDate < CURRENT_DATE - INTERVAL 3 MONTH").executeUpdate();
    }
}

