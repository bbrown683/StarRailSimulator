package hsr.core.equipment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonSetter;

import hsr.core.Ascension;
import hsr.core.BaseStats;
import hsr.core.Path;
import hsr.core.Rarity;
import hsr.core.effect.Effect;

public class LightCone {
    private String name;
    private Rarity rarity;
    private Path path;
    private Map<Ascension,BaseStats> baseStats;
    private int health;
    private int attack;
    private int defense;
    private List<Effect> effects;
    private Ascension ascension;

    public LightCone() {
        effects = new ArrayList<>();
    }
    
    public void changeAscension(Ascension ascension) {
        BaseStats newBaseStats = baseStats.get(ascension);
        health = newBaseStats.getHealth();
        attack = newBaseStats.getAttack();
        defense = newBaseStats.getDefense();
        this.ascension = ascension;
    }

    public String getName() {
        return name;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public Path getPath() {
        return path;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public List<Effect> getEffects() {
        return effects;
    }

    public Ascension getAscension() {
        return ascension;
    }

    public static class LightConeBuilder {
        private String name;
        private Rarity rarity;
        private Path path;
        private Map<Ascension,BaseStats> baseStats;
        private List<Effect> effects;

        @JsonSetter(value = "name")
        public LightConeBuilder setName(String name) {
            this.name = name;
            return this;
        }

        @JsonSetter(value = "rarity")
        public LightConeBuilder setRarity(Rarity rarity) {
            this.rarity = rarity;
            return this;
        }

        @JsonSetter(value = "path")
        public LightConeBuilder setPath(Path path) {
            this.path = path;
            return this;
        }

        @JsonSetter(value = "baseStats")
        public LightConeBuilder setBaseStats(Map<Ascension, BaseStats> baseStats) {
            this.baseStats = baseStats;
            return this;
        }

        @JsonSetter(value = "effects")
        public LightConeBuilder setEffects(List<Effect> effects) {
            this.effects = effects;
            return this;
        }

        public LightCone build(Ascension ascension) {
            LightCone lightCone = new LightCone();
            lightCone.name = name;
            lightCone.rarity = rarity;
            lightCone.path = path;
            lightCone.baseStats = baseStats;
            lightCone.changeAscension(ascension);
            lightCone.effects = effects;
            return lightCone;
        }
    }
}
