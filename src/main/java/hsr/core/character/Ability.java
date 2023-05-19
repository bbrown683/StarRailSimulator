package hsr.core.character;

import java.util.List;

import hsr.core.effect.Effect;

public class Ability {
    private String name;
    private AbilityType type;
    private AbilityCategory category;
    private Integer cost;
    private List<Effect> effects;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public AbilityType getType() {
        return type;
    }
    public void setType(AbilityType type) {
        this.type = type;
    }
    public AbilityCategory getCategory() {
        return category;
    }
    public void setCategory(AbilityCategory category) {
        this.category = category;
    }
    public Integer getCost() {
        return cost;
    }
    public void setCost(Integer cost) {
        this.cost = cost;
    }
    public List<Effect> getEffects() {
        return effects;
    }
    public void setEffects(List<Effect> effects) {
        this.effects = effects;
    }
    @Override
    public String toString() {
        return "Ability [name=" + name + ", type=" + type + ", category=" + category + ", cost=" + cost + ", effects="
                + effects + "]";
    }
}
