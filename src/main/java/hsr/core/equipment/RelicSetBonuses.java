package hsr.core.equipment;

import java.util.List;
import java.util.Map;

import hsr.core.Stat;
import hsr.core.effect.Effect;

public class RelicSetBonuses {
    private Map<Stat,Number> stats;
    private List<Effect> effects;
    public RelicSetBonuses() {}
    public RelicSetBonuses(Map<Stat,Number> stats, List<Effect> effects) {
        this.stats = stats;
        this.effects = effects;
    }
    public Map<Stat, Number> getStats() {
        return stats;
    }
    public List<Effect> getEffects() {
        return effects;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((stats == null) ? 0 : stats.hashCode());
        result = prime * result + ((effects == null) ? 0 : effects.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RelicSetBonuses other = (RelicSetBonuses) obj;
        if (stats == null) {
            if (other.stats != null)
                return false;
        } else if (!stats.equals(other.stats))
            return false;
        if (effects == null) {
            if (other.effects != null)
                return false;
        } else if (!effects.equals(other.effects))
            return false;
        return true;
    }
}
