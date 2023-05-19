package hsr.core.equipment;

public class Relic {
    private RelicSet set;
    private RelicSlot slot;
    private int level;
    private RelicStat mainStat;
    private RelicStat subStat1;
    private RelicStat subStat2;
    private RelicStat subStat3;
    private RelicStat subStat4;

    public Relic(RelicSet set, RelicSlot slot) {
        this.set = set;
        this.slot = slot;
    }

    public Relic(RelicSet set, RelicSlot slot, RelicStat mainStat) {
        this.set = set;
        this.slot = slot;
        this.mainStat = mainStat;
    }

    public Relic(RelicSet set, RelicSlot slot, RelicStat mainStat, RelicStat subStat1) {
        this.set = set;
        this.slot = slot;
        this.subStat1 = subStat1;
    }

    public Relic(RelicSet set, RelicSlot slot, RelicStat mainStat, RelicStat subStat1, RelicStat subStat2) {
        this.set = set;
        this.slot = slot;
        this.mainStat = mainStat;
        this.subStat1 = subStat1;
        this.subStat2 = subStat2;
    }

    public Relic(RelicSet set, RelicSlot slot, RelicStat mainStat, RelicStat subStat1, RelicStat subStat2, RelicStat subStat3) {
        this.set = set;
        this.slot = slot;
        this.subStat1 = subStat1;
        this.subStat2 = subStat2;
        this.subStat3 = subStat3;
    }

    public Relic(RelicSet set, RelicSlot slot, RelicStat mainStat, RelicStat subStat1, RelicStat subStat2, RelicStat subStat3, RelicStat subStat4) {
        this.set = set;
        this.slot = slot;
        this.mainStat = mainStat;
        this.subStat1 = subStat1;
        this.subStat2 = subStat2;
        this.subStat3 = subStat3;
        this.subStat4 = subStat4;
    }

    public RelicSet getSet() {
        return set;
    }
    public void setSet(RelicSet set) {
        this.set = set;
    }
    public RelicSlot getSlot() {
        return slot;
    }
    public void setSlot(RelicSlot slot) {
        this.slot = slot;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public RelicStat getMainStat() {
        return mainStat;
    }
    public void setMainStat(RelicStat mainStat) {
        this.mainStat = mainStat;
    }
    public RelicStat getSubStat1() {
        return subStat1;
    }
    public void setSubStat1(RelicStat subStat1) {
        this.subStat1 = subStat1;
    }
    public RelicStat getSubStat2() {
        return subStat2;
    }
    public void setSubStat2(RelicStat subStat2) {
        this.subStat2 = subStat2;
    }
    public RelicStat getSubStat3() {
        return subStat3;
    }
    public void setSubStat3(RelicStat subStat3) {
        this.subStat3 = subStat3;
    }
    public RelicStat getSubStat4() {
        return subStat4;
    }
    public void setSubStat4(RelicStat subStat4) {
        this.subStat4 = subStat4;
    }
    @Override
    public String toString() {
        return "Relic [set=" + set + ", slot=" + slot + ", level=" + level + ", mainStat=" + mainStat + ", subStat1="
                + subStat1 + ", subStat2=" + subStat2 + ", subStat3=" + subStat3 + ", subStat4=" + subStat4 + "]";
    }
}