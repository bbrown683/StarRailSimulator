package hsr.core;

import java.util.List;

import hsr.core.effect.Effect;

public abstract class Entity {
    protected String name;
    protected int actionValue;
    protected List<Effect> effects;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getActionValue() {
        return actionValue;
    }
    public void setActionValue(int actionValue) {
        this.actionValue = actionValue;
    }
    public List<Effect> getEffects() {
        return effects;
    }
    public void setEffects(List<Effect> effects) {
        this.effects = effects;
    }
}
