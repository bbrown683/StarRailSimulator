package hsr.core.effect;

import java.util.List;

import hsr.core.Stat;

public class Scaling {
    private Stat stat; // If effect has other scaling type such as defense.
    private Number flatModifier; // What the modifier is for said scaling, without increasing based on a constant value.
    private List<Number> perLevelModifier; // What the modifier is for said scaling, without increasing based on a changing level value.
    public Stat getStat() {
        return stat;
    }
    public void setStat(Stat stat) {
        this.stat = stat;
    }
    public Number getFlatModifier() {
        return flatModifier;
    }
    public void setFlatModifier(Number flatModifier) {
        this.flatModifier = flatModifier;
    }
    public List<Number> getPerLevelModifier() {
        return perLevelModifier;
    }
    public void setPerLevelModifier(List<Number> perLevelModifier) {
        this.perLevelModifier = perLevelModifier;
    }
    @Override
    public String toString() {
        return "Scaling [stat=" + stat + ", flatModifier=" + flatModifier + ", perLevelModifier=" + perLevelModifier + "]";
    }
}
