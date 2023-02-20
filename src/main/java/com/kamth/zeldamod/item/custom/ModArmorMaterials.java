package com.kamth.zeldamod.item.custom;

import com.kamth.zeldamod.ZeldaMod;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Supplier;

public enum ModArmorMaterials implements ArmorMaterial {
    ZORA("zora", 0, new int[]{1, 4, 5, 3}, 0, SoundEvents.AXOLOTL_SWIM,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.IRON_INGOT); }),
    DEKU("deku", 0, new int[]{1, 4, 5, 3}, 0, SoundEvents.AZALEA_LEAVES_PLACE,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.IRON_INGOT); }),
    GORON("goron", 0, new int[]{1, 4, 5, 3}, 0, SoundEvents.ANCIENT_DEBRIS_PLACE,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.IRON_INGOT); }),
    STONE("stone", 0, new int[]{1, 4, 5, 3}, 0, SoundEvents.STONE_PLACE,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.IRON_INGOT); }),
    BUNNY("bunny", 0, new int[]{1, 4, 5, 3}, 0, SoundEvents.WOOL_PLACE,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.IRON_INGOT); }),
    FIERCE("fierce", 0, new int[]{1, 4, 5, 3}, 0, SoundEvents.WITHER_SPAWN,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.IRON_INGOT); }),
    NIGHT("night", 0, new int[]{1, 4, 5, 3}, 0, SoundEvents.AMBIENT_CAVE,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.IRON_INGOT); }),
    BLAST("blast", 0, new int[]{1, 4, 5, 3}, 0, SoundEvents.CREEPER_PRIMED,
            0.0F, 0.0F, () -> {
        return Ingredient.of(Items.IRON_INGOT); }),
    ZELDAH("heavy", 0, new int[]{3, 4, 5, 2}, 0, SoundEvents.ARMOR_EQUIP_CHAIN,
            0.0F, 0.0F, () -> {
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
        return Ingredient.of(Items.NAUTILUS_SHELL); });


    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] slotProtections;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;

    private final LazyLoadedValue<Ingredient> repairIngredient;

    private ModArmorMaterials(String p_40474_, int durability, int[] slotProtections, int enchantmentvalue, SoundEvent sound, float toughness, float knockbackresistance, Supplier<Ingredient> p_40481_) {
        this.name = p_40474_;
        this.durabilityMultiplier = durability;
        this.slotProtections = slotProtections;
        this.enchantmentValue = enchantmentvalue;
        this.sound = sound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackresistance;
        this.repairIngredient = new LazyLoadedValue<>(p_40481_);
    }

    public int getDurabilityForSlot(EquipmentSlot p_40484_) {
        return HEALTH_PER_SLOT[p_40484_.getIndex()] * this.durabilityMultiplier;
    }

    public int getDefenseForSlot(EquipmentSlot p_40487_) {
        return this.slotProtections[p_40487_.getIndex()];
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public SoundEvent getEquipSound() {
        return this.sound;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    public String getName() {
        return ZeldaMod.MOD_ID + ":" + this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public float getKnockbackResistance() {
        return this.knockbackResistance;


    }
}
