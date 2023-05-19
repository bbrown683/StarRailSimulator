package hsr.core.equipment;

import hsr.core.Stat;

public class RelicStat {
    private Stat statType;
    private Number statValue;
    public RelicStat() {}
    public RelicStat(Stat statType, Number statValue) {
        this.statType = statType;
        this.statValue = statValue;
    }
    public Stat getStatType() {
        return statType;
    }
    public void setStatType(Stat statType) {
        this.statType = statType;
    }
    public Number getStatValue() {
        return statValue;
    }
    public void setStatValue(Number statValue) {
        this.statValue = statValue;
    }
    @Override
    public String toString() {
        return "RelicStat [statType=" + statType + ", statValue=" + statValue + "]";
    }
}
