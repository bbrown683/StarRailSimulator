package hsr.core.simulation;

public class Hit {
    private int nonCrit;
    private int crit;
    private int weightedAverage;
    public int getNonCrit() {
        return nonCrit;
    }
    public void setNonCrit(int nonCrit) {
        this.nonCrit = nonCrit;
    }
    public int getCrit() {
        return crit;
    }
    public void setCrit(int crit) {
        this.crit = crit;
    }
    public int getWeightedAverage() {
        return weightedAverage;
    }
    public void setWeightedAverage(int weightedAverage) {
        this.weightedAverage = weightedAverage;
    }
    @Override
    public String toString() {
        return "Hit [nonCrit=" + nonCrit + ", crit=" + crit + ", weightedAverage=" + weightedAverage + "]";
    }
}
