package com.kamth.zeldamod.custom;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.block.ZeldaBlocks;
import com.kamth.zeldamod.item.ZeldaItems;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum ModArmorMaterials implements ArmorMaterial {


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // MASKS

    // TRANSFORMATION MASKS
    ZORA("zora", 0, new int[]{0, 4, 5, 2}, 0, SoundEvents.AXOLOTL_SWIM,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.NAUTILUS_SHELL); }),

    DEKU("deku", 0, new int[]{0, 4, 5, 1}, 0, SoundEvents.AZALEA_LEAVES_PLACE,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.AZALEA); }),

    GORON("goron", 0, new int[]{4, 4, 5, 4}, 0, SoundEvents.ANCIENT_DEBRIS_PLACE,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.MAGMA_BLOCK); }),

    FIERCE("fierce", 0, new int[]{0, 4, 5, 6}, 0, SoundEvents.WITHER_SPAWN,
            0.0F, 6.0F, () -> {
        return Ingredient.of(ZeldaItems.DEITY_SHARD.get()); }),

    MAJORA("majora", 0, new int[]{0, 4, 5, 6}, 0, SoundEvents.WITHER_SPAWN,
            0.0F, 6.0F, () -> {
        return Ingredient.of(ZeldaItems.DEITY_SHARD.get()); }),







    // NORMAL MASKS


    STONE("stone", 0, new int[]{0, 4, 5, 0}, 0, SoundEvents.STONE_PLACE,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.STONE); }),

    BUNNY("bunny", 0, new int[]{0, 4, 5, 0}, 0, SoundEvents.WOOL_PLACE,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.RABBIT_FOOT); }),

    NIGHT("night", 0, new int[]{0, 4, 5, 0}, 0, SoundEvents.AMBIENT_CAVE.get(),
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.IRON_INGOT); }),

    BLAST("blast", 0, new int[]{0, 4, 5, 0}, 0, SoundEvents.CREEPER_PRIMED,
            0.0F, 0.0F, () -> {
        return Ingredient.of(ZeldaItems.BOMB.get()); }),


    SCENT("scent", 0, new int[]{0, 4, 5, 0}, 0, SoundEvents.PIGLIN_AMBIENT,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.PORKCHOP); }),

    TRUTH("truth", 0, new int[]{0, 4, 5,0}, 0, SoundEvents.AMETHYST_BLOCK_CHIME,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.GOLDEN_APPLE); }),

    KEATON("keaton", 0, new int[]{0, 4, 5, 0}, 0, SoundEvents.FOX_AMBIENT,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.SWEET_BERRIES); }),

    TROUPE("troupe", 0, new int[]{0, 4, 5, 0}, 0, SoundEvents.VILLAGER_NO,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.GHAST_TEAR); }),

    ROMANI("romani", 0, new int[]{0, 4, 5, 0}, 0, SoundEvents.COW_AMBIENT,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.MILK_BUCKET); }),

    GERO("gero", 0, new int[]{0, 4, 5, 0}, 0, SoundEvents.FROG_AMBIENT,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.SLIME_BALL); }),

    HAWK("hawk", 0, new int[]{0, 4, 5, 0}, 0, SoundEvents.SPYGLASS_USE,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.COPPER_INGOT); }),

    GIANT("giant", 0, new int[]{4, 4, 5, 0}, 0, SoundEvents.LODESTONE_COMPASS_LOCK,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.DIAMOND); }),

    COUPLES("couples", 0, new int[]{0, 4, 5, 0}, 0, SoundEvents.ARMOR_EQUIP_LEATHER,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.GHAST_TEAR); }),

    GIBDO("gibdo", 0, new int[]{0, 4, 5, 0}, 0, SoundEvents.HUSK_AMBIENT,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.ROTTEN_FLESH); }),

   FAIRY("fairy", 0, new int[]{0, 4, 5, 0}, 0, SoundEvents.ALLAY_AMBIENT_WITH_ITEM,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.AMETHYST_SHARD); }),

    KAFEI("kafei", 0, new int[]{0, 4, 5, 0}, 0, SoundEvents.ARMOR_EQUIP_LEATHER,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.LEATHER); }),

    BREMEN("bremen", 0, new int[]{0, 4, 5, 0}, 0, SoundEvents.CHICKEN_AMBIENT,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.FEATHER); }),

    CAPTAIN("captain", 0, new int[]{0, 4, 5, 0}, 0, SoundEvents.SKELETON_AMBIENT,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.BONE); }),

    KAMARO("kamaro", 0, new int[]{0, 4, 5, 0}, 0, SoundEvents.ARMOR_EQUIP_LEATHER,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.MUSIC_DISC_CAT); }),

    GARO("garo", 0, new int[]{0, 4, 5, 0}, 0, SoundEvents.ARMOR_EQUIP_LEATHER,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.CHAIN); }),

    POSTMAN("postman", 0, new int[]{0, 4, 5, 0}, 0, SoundEvents.ARMOR_EQUIP_LEATHER,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.PAPER); }),

    SPOOKY("spooky", 0, new int[]{0, 2, 5, 0}, 0, SoundEvents.ARMOR_EQUIP_LEATHER,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.OAK_PLANKS); }),

    SKULL("skull", 0, new int[]{0, 2, 5, 0}, 0, SoundEvents.ARMOR_EQUIP_LEATHER,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.BONE); }),

    GERUDO("gerudo", 0, new int[]{0, 2, 5, 0}, 0, SoundEvents.ARMOR_EQUIP_LEATHER,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.SAND); }),

    KOROK("korok", 0, new int[]{0, 2, 5, 0}, 0, SoundEvents.AZALEA_LEAVES_PLACE,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.OAK_LEAVES); }),


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // ARMOR

    HEAVY("heavy", 0, new int[]{0, 4, 5, 3}, 0, SoundEvents.ARMOR_EQUIP_IRON,
            0.0F, 4.0F, () -> {
        return Ingredient.of(Items.IRON_INGOT); }),
    HOVER("hover", 0, new int[]{1, 4, 5, 1}, 0, SoundEvents.ARMOR_EQUIP_ELYTRA,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.IRON_INGOT); }),
    PEGASUS("pegasus", 0, new int[]{1, 4, 5, 1}, 0, SoundEvents.HORSE_GALLOP,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.SADDLE); }),
    Kokiri("kokiri", 0, new int[]{1, 3, 2, 1}, 15, SoundEvents.ARMOR_EQUIP_LEATHER,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.GREEN_WOOL); }),
    GoronTunic("gorontunic", 0, new int[]{1, 3, 2, 1}, 10, SoundEvents.ARMOR_EQUIP_LEATHER,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.MAGMA_CREAM); }),
    ZoraTunic("zoratunic", 0, new int[]{1, 3, 2, 1}, 10, SoundEvents.ARMOR_EQUIP_LEATHER,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.NAUTILUS_SHELL); }),
    Classic("classic", 0, new int[]{5, 5, 2, 2}, 10, SoundEvents.ARMOR_EQUIP_LEATHER,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.GREEN_WOOL); }),
    Roc("roc", 0, new int[]{1, 2, 2, 1}, 0, SoundEvents.PARROT_FLY,
            0.0F, 0.0F, () -> {
        return Ingredient.of(ZeldaItems.ROC_FEATHER.get()); }),
    LEATHERS("champions", 0, new int[]{1, 7, 7, 1}, 16, SoundEvents.ARMOR_EQUIP_CHAIN,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.CHAIN); }),
   Champion("champion", 0, new int[]{1, 5, 4, 1}, 10, SoundEvents.ARMOR_EQUIP_LEATHER,
            0.0F, 0.0F, () -> {
        return Ingredient.of(ZeldaBlocks.NIGHTSHADE.get()); }),
    Fire("fire", 0, new int[]{2, 2, 5, 2}, 5, SoundEvents.ARMOR_EQUIP_LEATHER,
            0.0F, 0.0F, () -> {
        return Ingredient.of(ZeldaItems.DIN_PEARL.get()); }),
    Dragon("dragon", 0, new int[]{2, 2, 2, 2}, 5, SoundEvents.ARMOR_EQUIP_LEATHER,
            0.0F, 0.0F, () -> {
        return Ingredient.of(ZeldaItems.NAYRU_PEARL.get()); }),
    Archaic("archaic", 0, new int[]{2, 4, 2, 2}, 5, SoundEvents.ARMOR_EQUIP_LEATHER,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.GREEN_WOOL); }),

    DARK_NUT("dark_nut", 0, new int[]{4, 4, 4, 4}, 5, SoundEvents.ARMOR_EQUIP_IRON,
            0.0F, 0.0F, () -> {
        return Ingredient.of(ZeldaItems.MASTER_ORE.get()); }),
    DARK_KNIGHT("dark_knight", 0, new int[]{6, 6, 6, 6}, 5, SoundEvents.ARMOR_EQUIP_NETHERITE,
            0.0F, 0.0F, () -> {
        return Ingredient.of(ZeldaItems.MASTER_ORE.get()); }),

    Dark("dark", 0, new int[]{2, 5, 2, 2}, 6, SoundEvents.ARMOR_EQUIP_LEATHER,
            0.0F, 0.0F, () -> Ingredient.of(ZeldaItems.GLOOM_CLUMP.get())),
    Hylian("hylian", 0, new int[]{3, 4, 2, 2}, 5, SoundEvents.ARMOR_EQUIP_LEATHER,
            0.0F, 0.0F, () -> Ingredient.of(Items.BLACK_WOOL));






    private final String name;
    private final int durabilityMultiplier;
    private final int[] protectionAmounts;
    private final int enchantmentValue;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;

    private static final int[] BASE_DURABILITY = { 11, 16, 16, 13 };

    ModArmorMaterials(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantmentValue, SoundEvent equipSound,
                      float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmounts = protectionAmounts;
        this.enchantmentValue = enchantmentValue;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type pType) {
        return BASE_DURABILITY[pType.ordinal()] * this.durabilityMultiplier;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type pType) {
        return this.protectionAmounts[pType.ordinal()];
    }

    @Override
    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    public String getName() {
        return ZeldaMod.MOD_ID + ":" + this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}