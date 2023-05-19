package hsr.core.equipment.relics;

import java.util.Collections;
import java.util.Map;

import hsr.core.Stat;
import hsr.core.equipment.RelicSetBonuses;
import hsr.core.equipment.RelicSetBonusesInterface;

public class GeniusOfBrilliantStars implements RelicSetBonusesInterface {
    @Override
    public RelicSetBonuses get2PieceSetBonuses() {
        return new RelicSetBonuses(Collections.singletonMap(Stat.QUANTUM_DMG, 10.0f), null);
    }
    @Override
    public RelicSetBonuses get4PieceSetBonuses() {
        return new RelicSetBonuses(Map.ofEntries(Map.entry(Stat.SPD_PERCENT, 6.0f), Map.entry(Stat.INCREASE_BASIC_ATK_DMG, 10.0f)), null);
    }
}
