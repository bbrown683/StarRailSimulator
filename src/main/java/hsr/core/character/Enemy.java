package hsr.core.character;

import java.util.List;

import hsr.core.Element;
import hsr.core.Entity;

public class Enemy extends Entity {
    private int level;
    private boolean elite;
    private int toughness;
    private List<Element> weaknesses;
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public boolean isElite() {
        return elite;
    }
    public void setElite(boolean elite) {
        this.elite = elite;
    }
    public int getToughness() {
        return toughness;
    }
    public void setToughness(int toughness) {
        this.toughness = toughness;
    }
    public List<Element> getWeaknesses() {
        return weaknesses;
    }
    public void setWeaknesses(List<Element> weaknesses) {
        this.weaknesses = weaknesses;
    }
}
