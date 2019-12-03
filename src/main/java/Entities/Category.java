package Entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private List<CategoryPercent> categoryPercents;

    public Category() {
    }

    public Category(String name, List<CategoryPercent> categoryPercents) {
        this.name = name;
        this.categoryPercents = categoryPercents;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CategoryPercent> getCategoryPercents() {
        return categoryPercents;
    }

    public void setCategoryPercents(List<CategoryPercent> categoryPercents) {
        this.categoryPercents = categoryPercents;
    }
}
