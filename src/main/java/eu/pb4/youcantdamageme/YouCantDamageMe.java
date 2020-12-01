package eu.pb4.youcantdamageme;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;

public class YouCantDamageMe implements ModInitializer {
    public static final GameRules.Key<GameRules.BooleanRule> DAMAGE_GAMERULE = GameRuleRegistry.register("playerDamage", GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(true));

    public void onInitialize() { }
}
