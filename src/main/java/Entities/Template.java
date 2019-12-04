package Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String categories;
    private String catPercent;
    private String assignNum;
    private String assignPercent;

    public Template() {
    }

    public Template(String name, String categories, String catPercent, String assignNum, String assignPercent) {
        this.name = name;
        this.categories = categories;
        this.catPercent = catPercent;
        this.assignNum = assignNum;
        this.assignPercent = assignPercent;
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

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getCatPercent() {
        return catPercent;
    }

    public void setCatPercent(String catPercent) {
        this.catPercent = catPercent;
    }

    public String getAssignNum() {
        return assignNum;
    }

    public void setAssignNum(String assignNum) {
        this.assignNum = assignNum;
    }

    public String getAssignPercent() {
        return assignPercent;
    }

    public void setAssignPercent(String assignPercent) {
        this.assignPercent = assignPercent;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
