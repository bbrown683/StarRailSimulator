package hsr;

import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;

import hsr.core.Ascension;
import hsr.core.Stat;
import hsr.core.character.Character;
import hsr.core.effect.PrimaryEffect;
import hsr.core.equipment.LightCone;
import hsr.core.equipment.Relic;
import hsr.core.equipment.RelicSet;
import hsr.core.equipment.RelicSlot;
import hsr.core.equipment.RelicStat;
import hsr.core.simulation.Calculator;
import hsr.core.simulation.Hit;

public class Main {
    public static void main(String[] args) throws Exception {
        InputStream servalTemplateJson = Main.class.getResourceAsStream("/character/Serval.json");
        InputStream makeTheWorldClamorTemplateJson = Main.class.getResourceAsStream("/lightcone/MakeTheWorldClamor.json");

        ObjectMapper mapper = new ObjectMapper();
        Character.CharacterBuilder servalBuilder = mapper.readValue(servalTemplateJson, Character.CharacterBuilder.class);
        LightCone.LightConeBuilder makeTheWorldClamorBuilder = mapper.readValue(makeTheWorldClamorTemplateJson, LightCone.LightConeBuilder.class);

        Character serval = servalBuilder.build(Ascension.LEVEL_50);

        LightCone makeTheWorldClamor = makeTheWorldClamorBuilder.build(Ascension.LEVEL_50);
        serval.equipLightCone(makeTheWorldClamor);

        serval.equipRelic(new Relic(RelicSet.MUSKETEER_OF_WILD_WHEAT, RelicSlot.HEAD, new RelicStat(Stat.HP, 90), new RelicStat(Stat.CRIT_DMG, 4.6f), new RelicStat(Stat.BREAK_EFFECT, 4.6f)));
        serval.equipRelic(new Relic(RelicSet.MUSKETEER_OF_WILD_WHEAT, RelicSlot.HANDS, new RelicStat(Stat.ATK, 45), new RelicStat(Stat.ATK_PERCENT, 3.4f), new RelicStat(Stat.CRIT_RATE, 2.5f)));
        serval.equipRelic(new Relic(RelicSet.MUSKETEER_OF_WILD_WHEAT, RelicSlot.BODY, new RelicStat(Stat.CRIT_DMG, 34.4f), new RelicStat(Stat.ATK_PERCENT, 3.4f), new RelicStat(Stat.DEF_PERCENT, 3.8f), new RelicStat(Stat.SPEED, 3), new RelicStat(Stat.BREAK_EFFECT, 5.1f)));
        serval.equipRelic(new Relic(RelicSet.MUSKETEER_OF_WILD_WHEAT, RelicSlot.FEET, new RelicStat(Stat.ATK_PERCENT, 5.5f), new RelicStat(Stat.HP_PERCENT, 2.7f), new RelicStat(Stat.CRIT_DMG, 4.6f)));
        serval.equipRelic(new Relic(RelicSet.FLEET_OF_THE_AGELESS, RelicSlot.PLANAR_SPHERE, new RelicStat(Stat.LIGHTNING_DMG, 4.9f), new RelicStat(Stat.CRIT_RATE, 2.5f), new RelicStat(Stat.BREAK_EFFECT, 5.1f)));
        serval.equipRelic(new Relic(RelicSet.FLEET_OF_THE_AGELESS, RelicSlot.LINK_ROPE, new RelicStat(Stat.ATK_PERCENT, 5.5f), new RelicStat(Stat.HP, 30), new RelicStat(Stat.EFFECT_RESIST, 2.7f)));
        
        int totalAttack = serval.getStat(Stat.ATK).intValue();
        float lightningDamageBoost = serval.getStat(Stat.LIGHTNING_DMG).floatValue();
        float critRate = serval.getStat(Stat.CRIT_RATE).floatValue();
        float critDamage = serval.getStat(Stat.CRIT_DMG).floatValue();
        float wheatDamageBonus = 10.0f;

        Calculator calculator = new Calculator();
        Hit damage = calculator.getDamage(totalAttack, ((PrimaryEffect)serval.getBasicAttack().getEffects().get(0)).getScaling(), 3, lightningDamageBoost, 
        wheatDamageBonus, 0.0f, 50, 49, 0.0f, 0.0f, 20.0f, 0.0f, 
        0.0f, 0.0f, false, critRate, critDamage);

        System.out.println(serval);   
        System.out.println(damage);   
    }
}