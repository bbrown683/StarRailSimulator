package hsr.core.effect;

// Secondary Effects are ones that do not directly affect a character or enemy's health, i.e. buffs/debuffs.
public abstract class SecondaryEffect extends Effect {
    protected Scaling statModifier; // If the affect increases/decreases a stat, this will tell by how much.
    protected PrimaryEffect damage; // If applied effect will perform a primary effect of some sort such as damage over time.
    protected Integer duration; // How long said effect lasts, such as bronya's skill lasting for 1 turn. Null if there is no duration.
    protected Integer cooldown; // If effect has a cooldown, such as dan heng's talent. Null if there is no cooldown.
    protected Integer maxStacks; // Number of maximum stacks. Null if it does not stack.
    public Scaling getStatModifier() {
        return statModifier;
    }
    public void setStatModifier(Scaling statModifier) {
        this.statModifier = statModifier;
    }
    public PrimaryEffect getDamage() {
        return damage;
    }
    public void setDamage(PrimaryEffect damage) {
        this.damage = damage;
    }
    public Integer getCooldown() {
        return cooldown;
    }
    public void setCooldown(Integer cooldown) {
        this.cooldown = cooldown;
    }
    public Integer getDuration() {
        return duration;
    }
    public void setDuration(Integer duration) {
        this.duration = duration;
    }
    public Integer getMaxStacks() {
        return maxStacks;
    }
    public void setMaxStacks(Integer maxStacks) {
        this.maxStacks = maxStacks;
    }
    @Override
    public String toString() {
        return "SecondaryEffect [" + super.toString() + ", statModifier=" + statModifier + ", damage=" + damage + ", duration=" + duration
                + ", cooldown=" + cooldown + ", maxStacks=" + maxStacks + "]";
    }

    public static class Buff extends SecondaryEffect {
        public enum Type {
            DISPEL_DEBUFF,
            INCREASE_STAT,
            ADVANCED_FORWARD, 
            SHIELD,
            REGENERATES_ENERGY,
        }
        
        private Type type;
        public Type getType() {
            return type;
        }
        public void setType(Type type) {
            this.type = type;
        }
        @Override
        public String toString() {
            return "Buff [" + super.toString() + ", type=" + type + "]";
        }
    }
    
    public static class Debuff extends SecondaryEffect {
        public enum Type {
            BLEED,
            BURN,
            WIND_SHEAR,
            SHOCK,
            FROZEN,
            ENTANGLEMENT,
            IMPRISONMENT,
            SLOW,
            EXTEND_SHOCK,
            INCREASE_DOT_TAKEN,
            BLINDED,
            REDUCED_DEFENSE
        }

        private Type type;
        private Float baseHitRate; // If effect has a variable chance to succeed and scales with modifiers.
        private Float fixedHitRate; // If effect has a set chance to succeed and does not scale with modifiers.
        public Type getType() {
            return type;
        }
        public void setType(Type type) {
            this.type = type;
        }
        public Float getBaseHitRate() {
            return baseHitRate;
        }
        public void setBaseHitRate(Float baseHitRate) {
            this.baseHitRate = baseHitRate;
        }
        public Float getFixedHitRate() {
            return fixedHitRate;
        }
        public void setFixedHitRate(Float fixedHitRate) {
            this.fixedHitRate = fixedHitRate;
        }
        @Override
        public String toString() {
            return "Debuff [" + super.toString() + ", type=" + type + ", baseHitRate=" + baseHitRate + ", fixedHitRate=" + fixedHitRate + "]";
        } 
    }
}
