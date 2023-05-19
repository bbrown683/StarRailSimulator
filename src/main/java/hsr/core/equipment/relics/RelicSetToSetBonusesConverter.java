package hsr.core.equipment.relics;

import java.util.Map;

import hsr.core.equipment.RelicSet;
import hsr.core.equipment.RelicSetBonusesInterface;

public class RelicSetToSetBonusesConverter {
    public static final Map<RelicSet,RelicSetBonusesInterface> RELIC_SET_TO_SET_BONUSES = Map.ofEntries(
        Map.entry(RelicSet.MUSKETEER_OF_WILD_WHEAT, new MusketeerOfWildWheat()),
        Map.entry(RelicSet.FLEET_OF_THE_AGELESS, new FleetOfTheAgeless())
        );
}
