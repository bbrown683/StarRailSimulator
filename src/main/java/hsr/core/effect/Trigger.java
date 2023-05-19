package hsr.core.effect;

import hsr.core.effect.SecondaryEffect.Buff;
import hsr.core.effect.SecondaryEffect.Debuff;

public class Trigger {
    private TriggerScope scope;
    private TriggerFrequency frequency;
    private Boolean attacks;
    private Boolean hits;
    private Boolean usesBasicAttack;
    private Boolean usesSkill;
    private Boolean usesUltimate;
    private Boolean usesTalent;
    private Boolean performsFollowupAttack;
    private Boolean defeatsEnemy;
    private Integer reachesHealthThreshold;
    private Buff.Type hasBuff;
    private Debuff.Type hasDebuff;
    private CustomTrigger custom;
    public TriggerScope getScope() {
        return scope;
    }
    public void setScope(TriggerScope scope) {
        this.scope = scope;
    }
    public TriggerFrequency getFrequency() {
        return frequency;
    }
    public void setFrequency(TriggerFrequency frequency) {
        this.frequency = frequency;
    }
    public Boolean getAttacks() {
        return attacks;
    }
    public void setAttacks(Boolean attacks) {
        this.attacks = attacks;
    }
    public Boolean getHits() {
        return hits;
    }
    public void setHits(Boolean hits) {
        this.hits = hits;
    }
    public Boolean getUsesBasicAttack() {
        return usesBasicAttack;
    }
    public void setUsesBasicAttack(Boolean usesBasicAttack) {
        this.usesBasicAttack = usesBasicAttack;
    }
    public Boolean getUsesSkill() {
        return usesSkill;
    }
    public void setUsesSkill(Boolean usesSkill) {
        this.usesSkill = usesSkill;
    }
    public Boolean getUsesUltimate() {
        return usesUltimate;
    }
    public void setUsesUltimate(Boolean usesUltimate) {
        this.usesUltimate = usesUltimate;
    }
    public Boolean getUsesTalent() {
        return usesTalent;
    }
    public void setUsesTalent(Boolean usesTalent) {
        this.usesTalent = usesTalent;
    }
    public Boolean getPerformsFollowupAttack() {
        return performsFollowupAttack;
    }
    public void setPerformsFollowupAttack(Boolean performsFollowupAttack) {
        this.performsFollowupAttack = performsFollowupAttack;
    }
    public Boolean getDefeatsEnemy() {
        return defeatsEnemy;
    }
    public void setDefeatsEnemy(Boolean defeatsEnemy) {
        this.defeatsEnemy = defeatsEnemy;
    }
    public Integer getReachesHealthThreshold() {
        return reachesHealthThreshold;
    }
    public void setReachesHealthThreshold(Integer reachesHealthThreshold) {
        this.reachesHealthThreshold = reachesHealthThreshold;
    }
    public Buff.Type getHasBuff() {
        return hasBuff;
    }
    public void setHasBuff(Buff.Type hasBuff) {
        this.hasBuff = hasBuff;
    }
    public Debuff.Type getHasDebuff() {
        return hasDebuff;
    }
    public void setHasDebuff(Debuff.Type hasDebuff) {
        this.hasDebuff = hasDebuff;
    }
    public CustomTrigger getCustom() {
        return custom;
    }
    public void setCustom(CustomTrigger custom) {
        this.custom = custom;
    }
    @Override
    public String toString() {
        return "Trigger [scope=" + scope + ", frequency=" + frequency + ", attacks=" + attacks + ", hits=" + hits
                + ", usesBasicAttack=" + usesBasicAttack + ", usesSkill=" + usesSkill + ", usesUltimate=" + usesUltimate
                + ", usesTalent=" + usesTalent + ", performsFollowupAttack=" + performsFollowupAttack
                + ", defeatsEnemy=" + defeatsEnemy + ", reachesHealthThreshold=" + reachesHealthThreshold + ", hasBuff="
                + hasBuff + ", hasDebuff=" + hasDebuff + ", custom=" + custom + "]";
    }
}