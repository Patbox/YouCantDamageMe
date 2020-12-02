package eu.pb4.youcantdamageme;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;

public class YouCantDamageMe implements ModInitializer {
    public static final GameRules.Key<GameRules.BooleanRule> DAMAGE_GAMERULE = GameRuleRegistry.register("playerDamage", GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(true));
    public static final GameRules.Key<GameRules.IntRule> DAMAGE_SCALE_GAMERULE = GameRuleRegistry.register("playerDamageScale", GameRules.Category.PLAYER, GameRuleFactory.createIntRule(100, 1));

    public static final GameRules.Key<GameRules.BooleanRule> MAGIC_DAMAGE_GAMERULE = GameRuleRegistry.register("magicDamage", GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(true));
    public static final GameRules.Key<GameRules.BooleanRule> ENTITY_ATTACK_DAMAGE_GAMERULE = GameRuleRegistry.register("entityAttackDamage", GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(true));
    public static final GameRules.Key<GameRules.BooleanRule> SELF_DAMAGE_GAMERULE = GameRuleRegistry.register("selfDamage", GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(true));

    public void onInitialize() { }
}
