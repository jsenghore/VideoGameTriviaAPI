package com.example.videogametriviaapi;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "trivia_facts")
public class TriviaFact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // Unique identifier for the trivia fact

    private String fact; // The trivia fact text

    private String category; // The category of the trivia fact (e.g., adventure, RPG)

    @Column(name = "creation_date")
    private LocalDateTime creationDate; // Timestamp for when the fact was created

    // Constructors
    public TriviaFact() {
        this.creationDate = LocalDateTime.now(); // Set creation date to now by default
    }

    public TriviaFact(int id, String fact, String category) {
        this.id = id;
        this.fact = fact;
        this.category = category;
        this.creationDate = LocalDateTime.now(); // Set creation date to now
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}