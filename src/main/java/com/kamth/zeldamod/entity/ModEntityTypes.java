package com.kamth.zeldamod.entity;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.entity.custom.projectile.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType.Builder;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ZeldaMod.MOD_ID);



//public static final EntityType<Arrow> ARROW = register("arrow", EntityType.Builder.<Arrow>of(Arrow::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20));
    public static final RegistryObject<EntityType<SeedProjectile>> WHEAT_SEED = ENTITY_TYPES.register("seed", () -> EntityType.Builder.<SeedProjectile>of(SeedProjectile::new, MobCategory.MISC).sized(0.7f,0.7f).clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(ZeldaMod.MOD_ID, "seed").toString()));
    public static final RegistryObject<EntityType<BombProjectile>> BOMB = ENTITY_TYPES.register("bomb", () -> Builder.<BombProjectile>of(BombProjectile::new, MobCategory.MISC).sized(0.5f,0.5f).clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(ZeldaMod.MOD_ID, "bomb").toString()));
    public static final RegistryObject<EntityType<WaterBombProjectile>> WATER_BOMB = ENTITY_TYPES.register("water_bomb", () -> Builder.<WaterBombProjectile>of(WaterBombProjectile::new, MobCategory.MISC).sized(0.5f,0.5f).clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(ZeldaMod.MOD_ID, "water_bomb").toString()));
    public static final RegistryObject<EntityType<FireArrow>> FIRE_ARROW = ENTITY_TYPES.register("fire_arrow", () -> EntityType.Builder.<FireArrow>of(FireArrow::new, MobCategory.MISC).sized(0.5f,0.5f).clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(ZeldaMod.MOD_ID, "fire_arrow").toString()));
    public static final RegistryObject<EntityType<IceArrow>> ICE_ARROW = ENTITY_TYPES.register("ice_arrow", () -> Builder.<IceArrow>of(IceArrow::new, MobCategory.MISC).sized(0.5f,0.5f).clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(ZeldaMod.MOD_ID, "ice_arrow").toString()));
    public static final RegistryObject<EntityType<LightArrow>> LIGHT_ARROW = ENTITY_TYPES.register("light_arrow", () -> Builder.<LightArrow>of(LightArrow::new, MobCategory.MISC).sized(0.5f,0.5f).clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(ZeldaMod.MOD_ID, "light_arrow").toString()));
    public static final RegistryObject<EntityType<BombArrow>> BOMB_ARROW = ENTITY_TYPES.register("bomb_arrow", () -> Builder.<BombArrow>of(BombArrow::new, MobCategory.MISC).sized(0.5f,0.5f).clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(ZeldaMod.MOD_ID, "bomb_arrow").toString()));
    public static final RegistryObject<EntityType<SwordBeam>> SWORD_BEAM = ENTITY_TYPES.register("sword_beam", () -> Builder.<SwordBeam>of(SwordBeam::new, MobCategory.MISC).sized(1.3f,0.5f).clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(ZeldaMod.MOD_ID, "sword_beam").toString()));
    public static final RegistryObject<EntityType<LightningArrow>> LIGHTNING_ARROW = ENTITY_TYPES.register("lightning_arrow", () -> Builder.<LightningArrow>of(LightningArrow::new, MobCategory.MISC).sized(0.5f,0.5f).clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(ZeldaMod.MOD_ID, "lightning_arrow").toString()));
    public static final RegistryObject<EntityType<AncientArrow>> ANCIENT_ARROW = ENTITY_TYPES.register("ancient_arrow", () -> Builder.<AncientArrow>of(AncientArrow::new, MobCategory.MISC).sized(0.5f,0.5f).clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(ZeldaMod.MOD_ID, "ancient_arrow").toString()));
    public static final RegistryObject<EntityType<GustProjectile>> GUST_PROJECTILE = ENTITY_TYPES.register("gust_projectile", () -> Builder.<GustProjectile>of(GustProjectile::new, MobCategory.MISC).sized(1.4f,1.7f).clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(ZeldaMod.MOD_ID, "gust_projectile").toString()));
    public static final RegistryObject<EntityType<Deku_Nut>> DEKU_NUT = ENTITY_TYPES.register("deku_nut", () -> Builder.<Deku_Nut>of(Deku_Nut::new, MobCategory.MISC).sized(0.7f,0.7f).clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(ZeldaMod.MOD_ID, "deku_nut").toString()));
    public static final RegistryObject<EntityType<FireProjectile>> FIRE_PROJECTILE = ENTITY_TYPES.register("fire_projectile", () -> Builder.<FireProjectile>of(FireProjectile::new, MobCategory.MISC).sized(1.7f,.8f).clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(ZeldaMod.MOD_ID, "fire_projectile").toString()));
    public static final RegistryObject<EntityType<IceProjectile>> ICE_PROJECTILE = ENTITY_TYPES.register("ice_projectile", () -> Builder.<IceProjectile>of(IceProjectile::new, MobCategory.MISC).sized(.6f,.7f).clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(ZeldaMod.MOD_ID, "ice_projectile").toString()));
    public static final RegistryObject<EntityType<BoomerangProjectile>> BOOMERANG = ENTITY_TYPES.register("boomerang_projectile", () -> Builder.<BoomerangProjectile>of(BoomerangProjectile::new, MobCategory.MISC).sized(.6f,.7f).clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(ZeldaMod.MOD_ID, "boomerang").toString()));
    public static final RegistryObject<EntityType<MagicBoomerangProjectile>> MAGIC_BOOMERANG = ENTITY_TYPES.register("magic_boomerang_projectile", () -> Builder.<MagicBoomerangProjectile>of(MagicBoomerangProjectile::new, MobCategory.MISC).sized(.6f,.7f).clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(ZeldaMod.MOD_ID, "magic_boomerang").toString()));
    public static void register(IEventBus modEventBus) {
        ENTITY_TYPES.register(modEventBus);

    }
}
