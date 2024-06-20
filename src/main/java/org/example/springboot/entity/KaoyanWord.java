package org.example.springboot.entity;

import javax.persistence.*;

@Table(name = "kaoyan")
public class KaoyanWord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String word;
    private String definition;
    private Integer difficulty_level_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public Integer getDifficulty_level_id() {
        return difficulty_level_id;
    }

    public void setDifficulty_level_id(Integer difficulty_level_id) {
        this.difficulty_level_id = difficulty_level_id;
    }



}
