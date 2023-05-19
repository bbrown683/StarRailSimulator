package hsr.core.equipment.relics;

import java.util.Collections;

import hsr.core.Stat;
import hsr.core.equipment.RelicSetBonuses;
import hsr.core.equipment.RelicSetBonusesInterface;

public class FleetOfTheAgeless implements RelicSetBonusesInterface {

    @Override
    public RelicSetBonuses get2PieceSetBonuses() {
        return new RelicSetBonuses(Collections.singletonMap(Stat.HP_PERCENT, 12.0f), null);
    }

    @Override
    public RelicSetBonuses get4PieceSetBonuses() {
        return null;
    }
    
}
