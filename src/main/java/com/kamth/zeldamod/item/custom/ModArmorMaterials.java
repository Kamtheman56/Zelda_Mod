package com.kamth.zeldamod.item.custom;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.item.ModItems;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Supplier;

public enum ModArmorMaterials implements ArmorMaterial {
    ZORA("zora", 0, new int[]{1, 4, 5, 2}, 0, SoundEvents.AXOLOTL_SWIM,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.IRON_INGOT); }),
    DEKU("deku", 0, new int[]{1, 4, 5, 1}, 0, SoundEvents.AZALEA_LEAVES_PLACE,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.IRON_INGOT); }),
    GORON("goron", 0, new int[]{1, 4, 5, 4}, 0, SoundEvents.ANCIENT_DEBRIS_PLACE,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.IRON_INGOT); }),
    STONE("stone", 0, new int[]{1, 4, 5, 0}, 0, SoundEvents.STONE_PLACE,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.IRON_INGOT); }),
    BUNNY("bunny", 0, new int[]{1, 4, 5, 0}, 0, SoundEvents.WOOL_PLACE,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.IRON_INGOT); }),
    FIERCE("fierce", 0, new int[]{1, 4, 5, 6}, 0, SoundEvents.WITHER_SPAWN,
            0.0F, 6.0F, () -> {
        return Ingredient.of(Items.IRON_INGOT); }),
    NIGHT("night", 0, new int[]{1, 4, 5, 0}, 0, SoundEvents.AMBIENT_CAVE.get(),
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.IRON_INGOT); }),
    BLAST("blast", 0, new int[]{1, 4, 5, 0}, 0, SoundEvents.CREEPER_PRIMED,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.IRON_INGOT); }),
    MAJORA("majora", 0, new int[]{1, 4, 5, 6}, 0, SoundEvents.WITHER_SPAWN,
            0.0F, 6.0F, () -> {
        return Ingredient.of(Items.IRON_INGOT); }),
    SCENT("scent", 0, new int[]{1, 4, 5, 0}, 0, SoundEvents.PIGLIN_AMBIENT,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.PORKCHOP); }),
    TRUTH("truth", 0, new int[]{1, 4, 5,0}, 0, SoundEvents.AMETHYST_BLOCK_CHIME,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.AIR); }),
    KEATON("keaton", 0, new int[]{1, 4, 5, 0}, 0, SoundEvents.FOX_AMBIENT,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.AIR); }),
    TROUPE("troupe", 0, new int[]{1, 4, 5, 0}, 0, SoundEvents.VILLAGER_NO,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.AIR); }),
    ROMANI("romani", 0, new int[]{1, 4, 5, 0}, 0, SoundEvents.COW_AMBIENT,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.AIR); }),
    GERO("gero", 0, new int[]{1, 4, 5, 0}, 0, SoundEvents.FROG_AMBIENT,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.AIR); }),
    ZELDAH("heavy", 0, new int[]{3, 4, 5, 2}, 0, SoundEvents.ARMOR_EQUIP_IRON,
            0.0F, 4.0F, () -> {
        return Ingredient.of(Items.IRON_INGOT); }),
    HOVER("hover", 0, new int[]{1, 4, 5, 2}, 0, SoundEvents.ARMOR_EQUIP_ELYTRA,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.IRON_INGOT); }),
    PEGASUS("pegasus", 0, new int[]{1, 4, 5, 2}, 0, SoundEvents.HORSE_GALLOP,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.IRON_INGOT); }),
    Kokiri("kokiri", 0, new int[]{1, 2, 4, 1}, 0, SoundEvents.ARMOR_EQUIP_LEATHER,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Blocks.GREEN_WOOL); }),
    GoronTunic("gorontunic", 0, new int[]{1, 2, 4, 1}, 0, SoundEvents.ARMOR_EQUIP_LEATHER,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.MAGMA_CREAM); }),
    ZoraTunic("zoratunic", 0, new int[]{1, 2, 4, 1}, 0, SoundEvents.ARMOR_EQUIP_LEATHER,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.NAUTILUS_SHELL); }),
    Roc("roc", 0, new int[]{1, 2, 2, 1}, 0, SoundEvents.PARROT_FLY,
            0.0F, 0.0F, () -> {
        return Ingredient.of(ModItems.ROC_FEATHER.get()); }),
    Champions("champions", 0, new int[]{1, 2, 7, 1}, 6, SoundEvents.ARMOR_EQUIP_CHAIN,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.CHAIN); }),
   Champion("champion", 0, new int[]{1, 2, 5, 1}, 4, SoundEvents.ARMOR_EQUIP_LEATHER,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.CHAIN); });


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