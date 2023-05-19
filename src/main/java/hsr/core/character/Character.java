package hsr.core.character;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonSetter;

import hsr.core.Ascension;
import hsr.core.BaseStats;
import hsr.core.Element;
import hsr.core.Entity;
import hsr.core.Path;
import hsr.core.Rarity;
import hsr.core.Stat;
import hsr.core.equipment.LightCone;
import hsr.core.equipment.Relic;
import hsr.core.equipment.RelicSet;
import hsr.core.equipment.RelicSetBonuses;
import hsr.core.equipment.RelicSetBonusesInterface;
import hsr.core.equipment.RelicSlot;
import hsr.core.equipment.relics.RelicSetToSetBonusesConverter;

public class Character extends Entity {
    private Rarity rarity;
    private Path path;
    private Element element;
    private Map<Ascension,BaseStats> baseStats;
    private int baseHealth;
    private int bonusHealth;
    private int baseAttack;
    private int bonusAttack;
    private int baseDefense;
    private int bonusDefense;
    private int baseSpeed;
    private int bonusSpeed;
    private int maxEnergy;
    private float critRate;
    private float critDamage;
    private float breakEffect;
    private float effectHitRate;
    private float effectResist;
    private float energyRegenerationRate;
    private float outgoingHealingBoost;
    private Map<Element,Float> elementalDamageBoost;
    private Map<Element,Float> elementalResistanceBoost;    
    private Ability basicAttack;
    private Ability skill;
    private Ability ultimate;
    private Ability talent;
    private Ability technique;
    private Ascension ascension;
    private LightCone lightCone;
    private Map<RelicSlot,Relic> relics;
    private List<RelicSetBonuses> relicSetBonuses;

    public Character() {
        critRate = 5.0f;
        critDamage = 50.0f;
        elementalDamageBoost = new HashMap<>();
        elementalResistanceBoost = new HashMap<>();
        relics = new HashMap<>();
        relicSetBonuses = new ArrayList<>();
    }

    public Number getStat(Stat stat) {
        switch(stat) {
            case HP: return baseHealth + bonusHealth;
            case ATK: return baseAttack + bonusAttack;
            case DEF: return baseDefense + bonusDefense;
            case SPD: return baseSpeed + bonusSpeed;
            case CRIT_RATE: return critRate;
            case CRIT_DMG: return critDamage;
            case LIGHTNING_DMG: return elementalDamageBoost.getOrDefault(Element.LIGHTNING, 0.0f);
            default:
        }
        return null;
    }

    public void changeAscension(Ascension newAscension) {
        if(ascension != null) {
            BaseStats originalBaseStats = baseStats.get(ascension);
            baseHealth -= originalBaseStats.getHealth();
            baseAttack -= originalBaseStats.getAttack();
            baseDefense -= originalBaseStats.getDefense();
            baseSpeed -= originalBaseStats.getSpeed();
        }

        BaseStats newBaseStats = baseStats.get(newAscension);
        baseHealth += newBaseStats.getHealth();
        baseAttack += newBaseStats.getAttack();
        baseDefense += newBaseStats.getDefense();
        baseSpeed += newBaseStats.getSpeed();
        ascension = newAscension;
    }

    public void equipLightCone(LightCone newLightCone) {
        if(lightCone != null) {
            unequipLightCone(lightCone);
        }

        // Set and add new.
        lightCone = newLightCone;
        baseHealth += newLightCone.getHealth();
        baseAttack += newLightCone.getAttack();
        baseDefense += newLightCone.getDefense();
    }

    public void unequipLightCone(LightCone lightCone) {
        // Subtract old stats.
        baseHealth -= lightCone.getHealth();
        baseAttack -= lightCone.getAttack();
        baseDefense -= lightCone.getDefense();
        lightCone = null;
    }

    public void equipRelic(Relic newRelic) {
        var slot = newRelic.getSlot();
        if(relics.containsKey(slot)) {
            unequipRelicForSlot(slot);
        }

        relics.put(slot, newRelic);     
        var mainstat = newRelic.getMainStat();
        var subStat1 = newRelic.getSubStat1();
        var subStat2 = newRelic.getSubStat2();
        var subStat3 = newRelic.getSubStat3();
        var subStat4 = newRelic.getSubStat4();

        if(mainstat != null) {
            updateStat(mainstat.getStatType(), mainstat.getStatValue());
        }
        if(subStat1 != null) {
            updateStat(subStat1.getStatType(), subStat1.getStatValue());
        }
        if(subStat2 != null) {
            updateStat(subStat2.getStatType(), subStat2.getStatValue());
        }
        if(subStat3 != null) {
            updateStat(subStat3.getStatType(), subStat3.getStatValue());
        }
        if(subStat4 != null) {
            updateStat(subStat4.getStatType(), subStat4.getStatValue());
        }

        updateRelicSetBonuses();
    }

    public void unequipRelicForSlot(RelicSlot slot) {
        var relic = relics.get(slot);
        if(relic == null) {
            return;
        }

        var mainstat = relic.getMainStat();
        var subStat1 = relic.getSubStat1();
        var subStat2 = relic.getSubStat2();
        var subStat3 = relic.getSubStat3();
        var subStat4 = relic.getSubStat4();

        if(mainstat != null) {
            updateStat(mainstat.getStatType(), negateStat(mainstat.getStatValue()));
        }
        if(subStat1 != null) {
            updateStat(subStat1.getStatType(), negateStat(subStat1.getStatValue()));
        }
        if(subStat2 != null) {
            updateStat(subStat2.getStatType(), negateStat(subStat2.getStatValue()));
        }
        if(subStat3 != null) {
            updateStat(subStat3.getStatType(), negateStat(subStat3.getStatValue()));
        }
        if(subStat4 != null) {
            updateStat(subStat4.getStatType(), negateStat(subStat4.getStatValue()));
        }

        updateRelicSetBonuses();
    }

    private Map<RelicSet,Integer> updateRelicSetBonuses() {
        // Get counts.
        var relicSetCounts = new HashMap<RelicSet,Integer>();
        for(var relic : relics.values()) {
            var set = relic.getSet();
            int count = relicSetCounts.getOrDefault(set, 0);
            relicSetCounts.put(set, ++count);
        }

        // Get all set bonuses user is wearing.
        var setBonuses = new ArrayList<RelicSetBonuses>();
        for(var set : relicSetCounts.keySet()) {
            int count = relicSetCounts.get(set);
            RelicSetBonusesInterface setBonusesInterface = RelicSetToSetBonusesConverter.RELIC_SET_TO_SET_BONUSES.get(set);
            RelicSetBonuses setBonus2Piece = setBonusesInterface.get2PieceSetBonuses();
            RelicSetBonuses setBonus4Piece = setBonusesInterface.get4PieceSetBonuses();
            if(count >= 2 && setBonus2Piece != null) {
                setBonuses.add(setBonus2Piece);
            }
            if(count == 4 && setBonus4Piece != null) {
                setBonuses.add(setBonus4Piece);
            } 
        }

        // Now we can update the stats and effects.
        for(var setBonus : setBonuses) {
            if(!relicSetBonuses.contains(setBonus)) {
                Map<Stat,Number> stats = setBonus.getStats();
                for(var stat : stats.keySet()) {
                    var value = stats.get(stat);
                    updateStat(stat, value);
                }
                relicSetBonuses.add(setBonus);
            }
        }
        return relicSetCounts;
    }

    // Used for applying debuffs, unequipping, etc to the character as we can negate the value to get the correct reduction in stat value by calling updateStat.
    private Number negateStat(Number number) {
        if(number instanceof Integer) {
            return number.intValue() * -1;
        } else if(number instanceof Float) {
            return number.floatValue() * -1;
        }
        return null;
    }

    private void updateStat(Stat stat, Number value) {
        switch(stat) {
            case HP: bonusHealth += value.intValue(); return;
            case HP_PERCENT: bonusHealth += Math.round(baseHealth * (value.floatValue() / 100)); return;
            case ATK: bonusAttack += value.intValue(); return;
            case ATK_PERCENT: bonusAttack += Math.round(baseAttack * (value.floatValue() / 100)); return;
            case DEF: bonusDefense += value.intValue(); return;
            case DEF_PERCENT: bonusDefense += Math.round(baseDefense * (value.floatValue() / 100)); return;
            case SPD: bonusSpeed += value.intValue(); return;
            case SPD_PERCENT: bonusSpeed += Math.round(baseSpeed * (value.floatValue() / 100)); return;
            case BREAK_EFFECT: breakEffect += value.floatValue(); return;
            case CRIT_DMG: critDamage += value.floatValue(); return;
            case CRIT_RATE: critRate += value.floatValue(); return;
            case EFFECT_HIT_RATE: effectHitRate += value.floatValue(); return;
            case EFFECT_RESIST: effectResist += value.floatValue(); return;
            case ENERGY_REGEN_RATE: energyRegenerationRate += value.floatValue(); return;
            case OUTGOING_HEALING: outgoingHealingBoost += value.floatValue(); return;
            case FIRE_DMG: 
                float fireBonus = elementalDamageBoost.getOrDefault(Element.FIRE, 0.0f); 
                elementalDamageBoost.put(Element.FIRE, fireBonus += value.floatValue()); return;
            case ICE_DMG: 
                float iceBonus = elementalDamageBoost.getOrDefault(Element.ICE, 0.0f); 
                elementalDamageBoost.put(Element.ICE, iceBonus += value.floatValue()); return;
            case IMAGINARY_DMG:
                float imaginaryBonus = elementalDamageBoost.getOrDefault(Element.IMAGINARY, 0.0f); 
                elementalDamageBoost.put(Element.IMAGINARY, imaginaryBonus += value.floatValue()); return;
            case LIGHTNING_DMG:
                float lightningBonus = elementalDamageBoost.getOrDefault(Element.LIGHTNING, 0.0f); 
                elementalDamageBoost.put(Element.LIGHTNING, lightningBonus += value.floatValue()); return;
            case PHYSICAL_DMG:
                float physicalBonus = elementalDamageBoost.getOrDefault(Element.PHYSICAL, 0.0f); 
                elementalDamageBoost.put(Element.PHYSICAL, physicalBonus += value.floatValue()); return;
            case QUANTUM_DMG:
                float quantumBonus = elementalDamageBoost.getOrDefault(Element.QUANTUM, 0.0f); 
                elementalDamageBoost.put(Element.QUANTUM, quantumBonus += value.floatValue()); return;
            case WIND_DMG: 
                float windBonus = elementalDamageBoost.getOrDefault(Element.WIND, 0.0f); 
                elementalDamageBoost.put(Element.WIND, windBonus += value.floatValue()); return;
            case INCREASE_BASIC_ATK_DMG: 
        }
    }

    public Rarity getRarity() {
        return rarity;
    }

    public Path getPath() {
        return path;
    }

    public Element getElement() {
        return element;
    }

    public Ability getBasicAttack() {
        return basicAttack;
    }

    public Ability getSkill() {
        return skill;
    }

    public Ability getUltimate() {
        return ultimate;
    }

    public Ability getTalent() {
        return talent;
    }

    public Ability getTechnique() {
        return technique;
    }

    public Ascension getAscension() {
        return ascension;
    }

    public LightCone getLightCone() {
        return lightCone;
    }

    public Map<RelicSlot, Relic> getRelics() {
        return relics;
    }

    public List<RelicSetBonuses> getRelicSetBonuses() {
        return relicSetBonuses;
    }

    @Override
    public String toString() {
        return "Base Stats\n" + 
        "HP\t\t\t" + baseHealth + "+" + bonusHealth + "\n" +
        "ATK\t\t\t" + baseAttack + "+" + bonusAttack + "\n" +
        "DEF\t\t\t" + baseDefense + "+" + bonusDefense + "\n" +
        "SPD\t\t\t" + baseSpeed + "+" + bonusSpeed + "\n" +
        "\nAdvanced Stats\n" +
        "CRIT Rate\t\t\t" + critRate + "\n" +
        "CRIT DMG\t\t\t" + critDamage + "\n" +
        "Break Effect\t\t\t" + breakEffect + "\n" +
        "Outgoing Healing Boost\t\t\t" + outgoingHealingBoost + "\n" +
        "Max Energy\t\t\t" + maxEnergy + "\n" +
        "Energy Regeneration Rate\t\t\t" + energyRegenerationRate + "\n" +
        "Effect Hit Rate\t\t\t" + effectHitRate + "\n" +
        "Effect RES\t\t\t" + effectResist + "\n" +
        "\nDMG Type\n" +
        "Physical DMG Boost\t\t\t" + elementalDamageBoost.getOrDefault(Element.PHYSICAL, 0.0f) + "%\n" +
        "Fire DMG Boost\t\t\t" + elementalDamageBoost.getOrDefault(Element.FIRE, 0.0f) + "%\n" +
        "Ice DMG Boost\t\t\t" + elementalDamageBoost.getOrDefault(Element.ICE, 0.0f) + "%\n" +
        "Lightning DMG Boost\t\t\t" + elementalDamageBoost.getOrDefault(Element.LIGHTNING, 0.0f) + "%\n" +
        "Wind DMG Boost\t\t\t" + elementalDamageBoost.getOrDefault(Element.WIND, 0.0f) + "%\n" +
        "Quantum DMG Boost\t\t\t" + elementalDamageBoost.getOrDefault(Element.QUANTUM, 0.0f) + "%\n" +
        "Imaginary DMG Boost\t\t\t" + elementalDamageBoost.getOrDefault(Element.IMAGINARY, 0.0f) + "%\n" +
        "Physical RES Boost\t\t\t" + elementalResistanceBoost.getOrDefault(Element.PHYSICAL, 0.0f) + "%\n" +
        "Fire RES Boost\t\t\t" + elementalResistanceBoost.getOrDefault(Element.FIRE, 0.0f) + "%\n" +
        "Ice RES Boost\t\t\t" + elementalResistanceBoost.getOrDefault(Element.ICE, 0.0f) + "%\n" +
        "Lightning RES Boost\t\t\t" + elementalResistanceBoost.getOrDefault(Element.LIGHTNING, 0.0f) + "%\n" +
        "Wind RES Boost\t\t\t" + elementalResistanceBoost.getOrDefault(Element.WIND, 0.0f) + "%\n" +
        "Quantum RES Boost\t\t\t" + elementalResistanceBoost.getOrDefault(Element.QUANTUM, 0.0f) + "%\n" +
        "Imaginary RES Boost\t\t\t" + elementalResistanceBoost.getOrDefault(Element.IMAGINARY, 0.0f) + "%\n" +
        "\n";
    } 

    // Builds a character from a JSON structure.
    public static class CharacterBuilder {
        private String name;
        private Rarity rarity;
        private Path path;
        private Element element;
        private Map<Ascension,BaseStats> baseStats;
        private Ability basicAttack;
        private Ability skill;
        private Ability ultimate;
        private Ability talent;
        private Ability technique;
        
        @JsonSetter(value = "name")
        public CharacterBuilder setName(String name) {
            this.name = name;
            return this;
        }

        @JsonSetter(value = "rarity")
        public CharacterBuilder setRarity(Rarity rarity) {
            this.rarity = rarity;
            return this;
        }

        @JsonSetter(value = "path")
        public CharacterBuilder setPath(Path path) {
            this.path = path;
            return this;
        }

        @JsonSetter(value = "element")
        public CharacterBuilder setElement(Element element) {
            this.element = element;
            return this;
        }

        @JsonSetter(value = "baseStats")
        public CharacterBuilder setBaseStats(Map<Ascension, BaseStats> baseStats) {
            this.baseStats = baseStats;
            return this;
        }

        @JsonSetter(value = "basicAttack")
        public CharacterBuilder setBasicAttack(Ability basicAttack) {
            this.basicAttack = basicAttack;
            return this;
        }

        @JsonSetter(value = "skill")
        public CharacterBuilder setSkill(Ability skill) {
            this.skill = skill;
            return this;
        }

        @JsonSetter(value = "ultimate")
        public CharacterBuilder setUltimate(Ability ultimate) {
            this.ultimate = ultimate;
            return this;
        }

        @JsonSetter(value = "talent")
        public CharacterBuilder setTalent(Ability talent) {
            this.talent = talent;
            return this;
        }

        @JsonSetter(value = "technique")
        public CharacterBuilder setTechnique(Ability technique) {
            this.technique = technique;
            return this;
        }

        public Character build(Ascension ascension) {
            Character character = new Character();
            character.name = name;
            character.element = element;
            character.path = path;
            character.rarity = rarity;
            character.baseStats = baseStats;
            character.basicAttack = basicAttack;
            character.skill = skill;
            character.ultimate = ultimate;
            character.maxEnergy = ultimate.getCost();
            character.talent = talent;
            character.technique = technique;
            character.changeAscension(ascension);
            return character;
        }
    }
}