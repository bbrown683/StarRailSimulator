package hsr.core.effect;

import java.util.List;

import hsr.core.Stat;

public class Scaling {
    private Stat stat; // If effect has other scaling type such as defense.
    private Number modifierPercent; // What the modifier is for said scaling, without increasing based on a constant value as a percentage.
    private Number modifierFlat; // What the modifier is for said scaling, without increasing based on a constant value as a flat value.
    private List<Number> levelModifierPercent; // What the modifier is for said scaling, without increasing based on a changing level value.
    private List<Number> levelModifierFlat;
    public Stat getStat() {
        return stat;
    }
    public void setStat(Stat stat) {
        this.stat = stat;
    }
    public Number getModifierPercent() {
        return modifierPercent;
    }
    public void setModifierPercent(Number modifierPercent) {
        this.modifierPercent = modifierPercent;
    }
    public Number getModifierFlat() {
        return modifierFlat;
    }
    public void setModifierFlat(Number modifierFlat) {
        this.modifierFlat = modifierFlat;
    }
    public List<Number> getLevelModifierPercent() {
        return levelModifierPercent;
    }
    public void setLevelModifierPercent(List<Number> levelModifierPercent) {
        this.levelModifierPercent = levelModifierPercent;
    }
    public List<Number> getLevelModifierFlat() {
        return levelModifierFlat;
    }
    public void setLevelModifierFlat(List<Number> levelModifierFlat) {
        this.levelModifierFlat = levelModifierFlat;
    }
}
