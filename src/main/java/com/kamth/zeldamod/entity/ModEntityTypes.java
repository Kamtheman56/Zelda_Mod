package com.kamth.zeldamod.entity;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.entity.mobs.*;
import com.kamth.zeldamod.entity.mobs.hostile.chus.ChuchuEntity;
import com.kamth.zeldamod.entity.mobs.hostile.chus.ElectricChuchuEntity;
import com.kamth.zeldamod.entity.mobs.hostile.chus.FireChuchuEntity;
import com.kamth.zeldamod.entity.mobs.hostile.chus.IceChuchuEntity;
import com.kamth.zeldamod.entity.mobs.hostile.darknuts.DarkKnightEntity;
import com.kamth.zeldamod.entity.mobs.hostile.darknuts.DarknutEntity;
import com.kamth.zeldamod.entity.mobs.hostile.darknuts.IronKnuckleEntity;
import com.kamth.zeldamod.entity.mobs.hostile.deku.DekuMadScrubEntity;
import com.kamth.zeldamod.entity.mobs.hostile.deku.DekuScrubEntity;
import com.kamth.zeldamod.entity.mobs.hostile.keese.ElectricKeeseEntity;
import com.kamth.zeldamod.entity.mobs.hostile.keese.FireKeeseEntity;
import com.kamth.zeldamod.entity.mobs.hostile.keese.IceKeeseEntity;
import com.kamth.zeldamod.entity.mobs.hostile.keese.KeeseEntity;
import com.kamth.zeldamod.entity.projectile.seeds.BombSeedProjectile;
import com.kamth.zeldamod.entity.projectile.seeds.DekuNutProjectile;
import com.kamth.zeldamod.entity.projectile.seeds.KorokSeedProjectile;
import com.kamth.zeldamod.entity.projectile.seeds.SeedProjectile;
import com.kamth.zeldamod.entity.projectile.arrows.*;
import com.kamth.zeldamod.entity.projectile.bombs.BombFlowerEntity;
import com.kamth.zeldamod.entity.projectile.bombs.BombEntity;
import com.kamth.zeldamod.entity.projectile.bombs.bombchu.BombchuEntity;
import com.kamth.zeldamod.entity.projectile.bombs.WaterBombEntity;
import com.kamth.zeldamod.entity.projectile.boomerangs.BoomerangProjectile;
import com.kamth.zeldamod.entity.projectile.boomerangs.GaleBoomerangProjectile;
import com.kamth.zeldamod.entity.projectile.boomerangs.MagicBoomerangProjectile;
import com.kamth.zeldamod.entity.projectile.grapples.Clawshot;
import com.kamth.zeldamod.entity.projectile.grapples.Hookshot;
import com.kamth.zeldamod.entity.projectile.magic.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityType.Builder;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ZeldaMod.MOD_ID);


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // PROJECTILES

    // ARROWS

    public static final RegistryObject<EntityType<FireArrow>> FIRE_ARROW = ENTITY_TYPES.register("fire_arrow", () -> EntityType.Builder.<FireArrow>of(FireArrow::new, MobCategory.MISC).sized(0.5f,0.5f).clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(ZeldaMod.MOD_ID, "fire_arrow").toString()));
    public static final RegistryObject<EntityType<AncientArrow>> ANCIENT_ARROW = ENTITY_TYPES.register("ancient_arrow", () -> Builder.<AncientArrow>of(AncientArrow::new, MobCategory.MISC).sized(0.5f,0.5f).noSummon().clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(ZeldaMod.MOD_ID, "ancient_arrow").toString()));
    public static final RegistryObject<EntityType<LightArrow>> LIGHT_ARROW = ENTITY_TYPES.register("light_arrow", () -> Builder.<LightArrow>of(LightArrow::new, MobCategory.MISC).sized(0.5f,0.5f).clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(ZeldaMod.MOD_ID, "light_arrow").toString()));
    public static final RegistryObject<EntityType<IceArrow>> ICE_ARROW = ENTITY_TYPES.register("ice_arrow", () -> Builder.<IceArrow>of(IceArrow::new, MobCategory.MISC).sized(0.5f,0.5f).clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(ZeldaMod.MOD_ID, "ice_arrow").toString()));
    public static final RegistryObject<EntityType<BombArrow>> BOMB_ARROW = ENTITY_TYPES.register("bomb_arrow", () -> Builder.<BombArrow>of(BombArrow::new, MobCategory.MISC).sized(0.5f,0.5f).clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(ZeldaMod.MOD_ID, "bomb_arrow").toString()));
    public static final RegistryObject<EntityType<ShockArrow>> SHOCK_ARROW = ENTITY_TYPES.register("lightning_arrow", () -> Builder.<ShockArrow>of(ShockArrow::new, MobCategory.MISC).sized(0.5f,0.5f).clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(ZeldaMod.MOD_ID, "lightning_arrow").toString()));

    // BOMBS

    public static final RegistryObject<EntityType<BombEntity>> BOMB = ENTITY_TYPES.register("bomb", () -> Builder.<BombEntity>of(BombEntity::new, MobCategory.MISC).sized(0.5f,0.6f).noSummon().clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(ZeldaMod.MOD_ID, "bomb").toString()));
    public static final RegistryObject<EntityType<WaterBombEntity>> WATER_BOMB = ENTITY_TYPES.register("water_bomb", () -> Builder.<WaterBombEntity>of(WaterBombEntity::new, MobCategory.MISC).noSummon().sized(0.5f,0.5f).clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(ZeldaMod.MOD_ID, "water_bomb").toString()));
    public static final RegistryObject<EntityType<BombFlowerEntity>> BOMB_FLOWER = ENTITY_TYPES.register("bomb_flower", () -> Builder.<BombFlowerEntity>of(BombFlowerEntity::new, MobCategory.MISC).sized(.6f,.7f).noSummon().clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(ZeldaMod.MOD_ID, "bomb_flower").toString()));
    public static final RegistryObject<EntityType<BombchuEntity>> BOMBCHU = ENTITY_TYPES.register("bombchu", () -> Builder.<BombchuEntity>of(BombchuEntity::new, MobCategory.MISC).noSummon().sized(0.4f,0.4f).clientTrackingRange(6).updateInterval(4).build(new ResourceLocation(ZeldaMod.MOD_ID, "bombchu").toString()));


    // BOOMERANGS

    public static final RegistryObject<EntityType<BoomerangProjectile>> BOOMERANG = ENTITY_TYPES.register("boomerang_projectile", () -> Builder.<BoomerangProjectile>of(BoomerangProjectile::new, MobCategory.MISC).noSummon().sized(.6f,.7f).clientTrackingRange(6).updateInterval(20).build(new ResourceLocation(ZeldaMod.MOD_ID, "boomerang").toString()));
    public static final RegistryObject<EntityType<MagicBoomerangProjectile>> MAGIC_BOOMERANG = ENTITY_TYPES.register("magic_boomerang_projectile", () -> Builder.<MagicBoomerangProjectile>of(MagicBoomerangProjectile::new, MobCategory.MISC).noSummon().sized(.6f,.7f).clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(ZeldaMod.MOD_ID, "magic_boomerang").toString()));
    public static final RegistryObject<EntityType<GaleBoomerangProjectile>> GALE_BOOMERANG = ENTITY_TYPES.register("gale_boomerang_projectile", () -> Builder.<GaleBoomerangProjectile>of(GaleBoomerangProjectile::new, MobCategory.MISC).noSummon().sized(.6f,.7f).clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(ZeldaMod.MOD_ID, "gale_boomerang").toString()));


    // MAGIC PROJECTILES

    public static final RegistryObject<EntityType<GustProjectile>> GUST_PROJECTILE = ENTITY_TYPES.register("gust_projectile", () -> Builder.<GustProjectile>of(GustProjectile::new, MobCategory.MISC).sized(1.4f,1.7f).noSummon().clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(ZeldaMod.MOD_ID, "gust_projectile").toString()));
    public static final RegistryObject<EntityType<FireProjectile>> FIRE_PROJECTILE = ENTITY_TYPES.register("fire_projectile", () -> Builder.<FireProjectile>of(FireProjectile::new, MobCategory.MISC).sized(1.7f,.8f).noSummon().clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(ZeldaMod.MOD_ID, "fire_projectile").toString()));
    public static final RegistryObject<EntityType<IceProjectile>> ICE_PROJECTILE = ENTITY_TYPES.register("ice_projectile", () -> Builder.<IceProjectile>of(IceProjectile::new, MobCategory.MISC).sized(.6f,.7f).noSummon().clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(ZeldaMod.MOD_ID, "ice_projectile").toString()));
    public static final RegistryObject<EntityType<SandProjectile>> SAND_PROJECTILE = ENTITY_TYPES.register("sand_projectile", () -> Builder.<SandProjectile>of(SandProjectile::new, MobCategory.MISC).sized(.6f,.7f).clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(ZeldaMod.MOD_ID, "sand_projectile").toString()));
    public static final RegistryObject<EntityType<SwordBeam>> SWORD_BEAM = ENTITY_TYPES.register("sword_beam", () -> Builder.<SwordBeam>of(SwordBeam::new, MobCategory.MISC).sized(1.3f,0.5f).noSummon().clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(ZeldaMod.MOD_ID, "sword_beam").toString()));
    public static final RegistryObject<EntityType<SwordBeam_Evil>> SWORD_BEAM2 = ENTITY_TYPES.register("sword_beam2", () -> Builder.<SwordBeam_Evil>of(SwordBeam_Evil::new, MobCategory.MISC).sized(1.5f,0.6f).noSummon().clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(ZeldaMod.MOD_ID, "sword_beam2").toString()));


    // HOOKS

    public static final RegistryObject<EntityType<Hookshot>> HOOKSHOT = ENTITY_TYPES.register("hookshot_projectile", () -> Builder.<Hookshot>of(Hookshot::new, MobCategory.MISC).sized(.6f,.7f).noSummon().clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(ZeldaMod.MOD_ID, "hookshot_projectile").toString()));
    public static final RegistryObject<EntityType<Clawshot>> CLAWSHOT = ENTITY_TYPES.register("clawshot_projectile", () -> Builder.<Clawshot>of(Clawshot::new, MobCategory.MISC).sized(.6f,.7f).noSummon().clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(ZeldaMod.MOD_ID, "clawshot_projectile").toString()));



    // SEEDS

    public static final RegistryObject<EntityType<SeedProjectile>> SEED_PROJECTILE = ENTITY_TYPES.register("seed", () -> EntityType.Builder.<SeedProjectile>of(SeedProjectile::new, MobCategory.MISC).sized(0.7f,0.7f).clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(ZeldaMod.MOD_ID, "seed").toString()));
    public static final RegistryObject<EntityType<DekuNutProjectile>> DEKU_NUT = ENTITY_TYPES.register("deku_nut", () -> Builder.<DekuNutProjectile>of(DekuNutProjectile::new, MobCategory.MISC).sized(0.7f,0.7f).clientTrackingRange(4).noSummon().updateInterval(20).build(new ResourceLocation(ZeldaMod.MOD_ID, "deku_nut").toString()));
    public static final RegistryObject<EntityType<BombSeedProjectile>> BOMB_SEED = ENTITY_TYPES.register("bomb_seed_projectile", () -> Builder.<BombSeedProjectile>of(BombSeedProjectile::new, MobCategory.MISC).noSummon().sized(.6f,.7f).clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(ZeldaMod.MOD_ID, "bomb_seed").toString()));
    public static final RegistryObject<EntityType<KorokSeedProjectile>> KOROK_SEED = ENTITY_TYPES.register("korok_seed", () -> EntityType.Builder.<KorokSeedProjectile>of(KorokSeedProjectile::new, MobCategory.MISC).sized(0.7f,0.7f).noSummon().clientTrackingRange(4).updateInterval(20).build(new ResourceLocation(ZeldaMod.MOD_ID, "korok_seed").toString()));



    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // MOBS

    // HOSTILE MOBS

    public static final RegistryObject<EntityType<DekuScrubEntity>> DEKU = ENTITY_TYPES.register("deku", () -> EntityType.Builder.of(DekuScrubEntity::new, MobCategory.CREATURE)
            .sized(.8f,.8f).build("deku"));

    public static final RegistryObject<EntityType<DekuMadScrubEntity>> DEKU_MAD = ENTITY_TYPES.register("deku_mad", () -> EntityType.Builder.of(DekuMadScrubEntity::new, MobCategory.MONSTER)
            .sized(.8f,.8f).build("deku_mad"));

    public static final RegistryObject<EntityType<DarknutEntity>> DARK_NUT = ENTITY_TYPES.register("dark_nut", () -> EntityType.Builder.of(DarknutEntity::new, MobCategory.MONSTER)
            .sized(.8f,1.8f).build("dark_nut"));

    public static final RegistryObject<EntityType<DarkKnightEntity>> DARK_KNIGHT = ENTITY_TYPES.register("dark_knight", () -> EntityType.Builder.of(DarkKnightEntity::new, MobCategory.MONSTER)
            .sized(.8f,1.8f).build("dark_knight"));

    public static final RegistryObject<EntityType<SkulltulaEntity>> SKULLTULA = ENTITY_TYPES.register("skulltula", () -> Builder.of(SkulltulaEntity::new, MobCategory.MONSTER)
            .sized(1.7F, .75F).build("skulltula"));

    public static final RegistryObject<EntityType<IronKnuckleEntity>> IRON_KNUCKLE = ENTITY_TYPES.register("iron_knuckle", () -> EntityType.Builder.of(IronKnuckleEntity::new, MobCategory.MONSTER)
            .sized(.8f,1.8f).build("iron_knuckle"));

    // KEESE
    public static final RegistryObject<EntityType<KeeseEntity>> KEESE = ENTITY_TYPES.register("keese", () -> EntityType.Builder.of(KeeseEntity::new, MobCategory.MONSTER)
            .sized(.6f,.6f).build("keese"));

    public static final RegistryObject<EntityType<FireKeeseEntity>> FIRE_KEESE = ENTITY_TYPES.register("fire_keese", () -> EntityType.Builder.of(FireKeeseEntity::new, MobCategory.MONSTER).fireImmune()
            .sized(.6f,.6f).build("fire_keese"));

    public static final RegistryObject<EntityType<IceKeeseEntity>> ICE_KEESE = ENTITY_TYPES.register("ice_keese", () -> EntityType.Builder.of(IceKeeseEntity::new, MobCategory.MONSTER)
            .sized(.6f,.6f).build("ice_keese"));

    public static final RegistryObject<EntityType<ElectricKeeseEntity>> ELECTRIC_KEESE = ENTITY_TYPES.register("electric_keese", () -> EntityType.Builder.of(ElectricKeeseEntity::new, MobCategory.MONSTER)
            .sized(.6f,.6f).build("electric_keese"));

    // CHUS
    public static final RegistryObject<EntityType<ChuchuEntity>> CHUCHU = ENTITY_TYPES.register("chuchu", () -> EntityType.Builder.of(ChuchuEntity::new, MobCategory.MONSTER)
            .sized(2.04F, 2.04F).build("chuchu"));

    public static final RegistryObject<EntityType<FireChuchuEntity>> CHUCHU_FIRE = ENTITY_TYPES.register("chuchu_fire", () -> Builder.of(FireChuchuEntity::new, MobCategory.MONSTER).fireImmune()
            .sized(2.04F, 2.04F).build("chuchu_fire"));

    public static final RegistryObject<EntityType<IceChuchuEntity>> CHUCHU_ICE = ENTITY_TYPES.register("chuchu_ice", () -> Builder.of(IceChuchuEntity::new, MobCategory.MONSTER)
            .sized(2.04F, 2.04F).build("chuchu_ice"));

    public static final RegistryObject<EntityType<ElectricChuchuEntity>> CHUCHU_ELECTRIC = ENTITY_TYPES.register("chuchu_electric", () -> Builder.of(ElectricChuchuEntity::new, MobCategory.MONSTER)
            .sized(2.04F, 2.04F).build("chuchu_electric"));

    // FRIENDLY MOBS

    public static final RegistryObject<EntityType<KorokEntity>> KOROK = ENTITY_TYPES.register("korok", () -> Builder.of(KorokEntity::new, MobCategory.CREATURE)
            .sized(.6F, .8F).build("korok"));



    public static void register(IEventBus modEventBus) {
        ENTITY_TYPES.register(modEventBus);

    }

}
