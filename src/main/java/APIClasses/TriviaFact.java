package APIClasses;

public class TriviaFact {
    private Long id; // Unique identifier for the trivia fact
    private String fact; // The trivia fact text
    private String category; // The category of the trivia fact (e.g., adventure, RPG)

    // Constructors
    public TriviaFact() {}

    public TriviaFact(Long id, String fact, String category) {
        this.id = id;
        this.fact = fact;
        this.category = category;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFact() {
        return fact;
    }

    public void setFact(String fact) {
        this.fact = fact;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

