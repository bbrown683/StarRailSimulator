package hsr.core.simulation;

import java.util.List;

import hsr.core.effect.Scaling;

public class Calculator {
    public int getHealing(Number totalStat, Scaling statScaling, Integer talentLevel, float outgoingHealingBonus) {
        float baseHealing = 0.0f;
        float percentScalingFloat = 0.0f;
        float flatScalingFloat = 0.0f;
        if(talentLevel == null) {
            float percentScaling = statScaling.getModifierPercent().floatValue();
            float flatScaling = statScaling.getModifierFlat().floatValue();
            baseHealing = ((percentScaling + flatScaling) / 100.0f) * totalStat.floatValue();
        } else {
            List<Number> percentScalingList = statScaling.getLevelModifierPercent();
            List<Number> flatScalingList = statScaling.getLevelModifierFlat();
            if(percentScalingList != null &&  talentLevel <= percentScalingList.size()) {
                percentScalingFloat = percentScalingList.get(talentLevel - 1).floatValue();
            }
            if(flatScalingList != null && talentLevel <= flatScalingList.size()) {
                flatScalingFloat = flatScalingList.get(talentLevel - 1).floatValue();
            }
            baseHealing = (percentScalingFloat  / 100.0f) * totalStat.floatValue() + flatScalingFloat;
        }
        return Math.round(baseHealing * (1.0f + outgoingHealingBonus));
    }

    // Calculated based on https://www.prydwen.gg/star-rail/guides/damage-formula/
    public Damage getDamage(Number totalStat, Scaling statScaling, Integer talentLevel, float elementalDamageMultiplier, float allDamageMultiplier, float dotDamageMultiplier, int characterLevel, int enemyLevel,
        float defenseReduction, float defenseIgnore, float resistance, float resistancePenetration, float elementalDamageTaken, float allDamageTaken, boolean broken, float critRate, float critDamage) {
        
        // Base DMG
        float baseDamage = 0.0f;
        float percentScalingFloat = 0.0f;
        float flatScalingFloat = 0.0f;
        if(talentLevel == null) {
            Number percentScaling = statScaling.getModifierPercent();
            Number flatScaling = statScaling.getModifierFlat();
            if(percentScaling != null) {
                percentScalingFloat = percentScaling.floatValue();
            }
            if(flatScaling != null) {
                flatScalingFloat = flatScaling.floatValue();
            }
            baseDamage = (percentScalingFloat / 100.0f) * totalStat.floatValue() + flatScalingFloat;
        } else {
            List<Number> percentScalingList = statScaling.getLevelModifierPercent();
            List<Number> flatScalingList = statScaling.getLevelModifierFlat();
            if(percentScalingList != null &&  talentLevel <= percentScalingList.size()) {
                percentScalingFloat = percentScalingList.get(talentLevel - 1).floatValue();
            }
            if(flatScalingList != null && talentLevel <= flatScalingList.size()) {
                flatScalingFloat = flatScalingList.get(talentLevel - 1).floatValue();
            }
            baseDamage = (percentScalingFloat  / 100.0f) * totalStat.floatValue() + flatScalingFloat;
        }

        // DMG% Multiplier.
        float damageMultiplier = (100.0f + elementalDamageMultiplier + allDamageMultiplier + dotDamageMultiplier) / 100.0f;

        // DEF Multiplier
        float enemyLevelDefenseMultiplier = (200 + 10 * enemyLevel) * ((100.0f - (defenseReduction + defenseIgnore)) / 100.0f);
        float characterLevelDefenseMultiplier = 200 + 10 * characterLevel;
        float defenseMultiplier = 1.0f - (enemyLevelDefenseMultiplier / (enemyLevelDefenseMultiplier + characterLevelDefenseMultiplier));

        // RES Multiplier
        float resistanceMultiplier = (100.0f - (resistance - resistancePenetration)) / 100.0f;

        // DMG Taken Multiplier
        float damageTakenMultiplier = (1.0f + elementalDamageTaken + allDamageTaken);

        // Toughness Multiplier
        float toughnessMultiplier = broken ? 1.0f : 0.9f; // Broken 100%, Unbroken 90%

        // Multiply together.
        float totalDamage = baseDamage * damageMultiplier * defenseMultiplier * resistanceMultiplier * damageTakenMultiplier * toughnessMultiplier;

        int estimatedNonCritDamage = Math.round(totalDamage);
        int estimatedCritDamage = Math.round((1.0f + (critDamage / 100.0f)) * estimatedNonCritDamage);
        int estimatedAverageDamage = Math.round((((100.0f - critRate) / 100.0f) * estimatedNonCritDamage) + (((critRate / 100.0f) * estimatedCritDamage)));

        Damage damage = new Damage();
        damage.setNonCrit(estimatedNonCritDamage);        
        damage.setCrit(estimatedCritDamage);
        damage.setWeightedAverage(estimatedAverageDamage);
        return damage;
    }
}
