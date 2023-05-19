package hsr.core.simulation;

import java.util.List;

import hsr.core.character.Ability;
import hsr.core.effect.Effect;
import hsr.core.effect.Scaling;

public class Calculator {
    // Calculated based on https://www.prydwen.gg/star-rail/guides/damage-formula/
    public Hit getDamage(Number totalStat, Scaling statScaling, Integer talentLevel, float elementalDamageMultiplier, float allDamageMultiplier, float dotDamageMultiplier, int characterLevel, int enemyLevel,
        float defenseReduction, float defenseIgnore, float resistance, float resistancePenetration, float elementalDamageTaken, float allDamageTaken, boolean broken, float critRate, float critDamage) {
        // Base DMG
        float baseDamage = 0.0f;
        if(talentLevel == null) {
            baseDamage = (statScaling.getFlatModifier().floatValue() / 100.0f) * totalStat.floatValue();
        } else {
            baseDamage = (statScaling.getPerLevelModifier().get(talentLevel - 1).floatValue() / 100.0f) * totalStat.floatValue();
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

        Hit damage = new Hit();
        damage.setNonCrit(estimatedNonCritDamage);        
        damage.setCrit(estimatedCritDamage);
        damage.setWeightedAverage(estimatedAverageDamage);
        return damage;
    }
}
