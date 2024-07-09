package com.kamth.zeldamod.custom;

import com.kamth.zeldamod.ZeldaMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.StructureType;

public class ModTags {


    public static class Structures
    {
        private static TagKey<StructureType<?>> forgetag(String name){
        return TagKey.create(Registries.STRUCTURE_TYPE,(new ResourceLocation("forge", name)));
    }
    }


    public static class Blocks{

public static final TagKey<Block> BOMB
        = tag("bomb_blocks");
        public static final TagKey<Block> HAMMER
                = tag("hammer_blocks");
        public static final TagKey<Block> MITTS
                = tag("mitts_blocks");
        public static final TagKey<Block> SCENT
                = tag("scent_blocks");
        public static final TagKey<Block> DEMON
                = tag("demon_blocks");
        public static final TagKey<Block> HOOKSHOT
                = tag("hookshot_blocks");
        public static final TagKey<Block> CLAWSHOT
                = tag("clawshot_blocks");
        public static final TagKey<Block> FLAME
                = tag("flame_blocks");
        public static final TagKey<Block> BURN
                = tag("burnable_blocks");
        public static final TagKey<Block> DEKU
                = tag("deku_blocks");
        public static final TagKey<Block> DEKU_GOLD
                = tag("deku_blocks_gold");
        public static final TagKey<Block> HEAVY
                = tag("heavy_boots_blocks");
        public static final TagKey<Block> HEAVY2
                = tag("heavy_boots_fragile_blocks");
        public static final TagKey<Block> SWORD_BEAM
                = tag("sword_beam_blocks");
        public static final TagKey<Block> SACRED_FLAMES
                = tag("sacred_flame_blocks");
        public static final TagKey<Block> STAMINA_FRUIT
                = tag("stamina_fruit_blocks");
        private static TagKey<Block> tag(String name){
            return BlockTags.create(new ResourceLocation(ZeldaMod.MOD_ID, name));
        }
        private static TagKey<Block> forgetag(String name){
return BlockTags.create(new ResourceLocation("forge", name));
        }
    }

    public static class Items{
        public static final TagKey<Item> SLING_AMMO
                = tag("sling_ammo");
        public static final TagKey<Item> BOW_AMMO
                = tag("bow");
        public static final TagKey<Item> MASKS
                = tag("masks");
        public static final TagKey<Item> SHOVEL_ITEMS
                = tag("shovel_items");
        public static final TagKey<Item> HAMMERS
                = tag("hammers");
        public static final TagKey<Item> LEGENDARY_ITEMS
                = tag("items");
        public static final TagKey<Item> SWORDS
                = tag("swords");
        public static final TagKey<Item> SHIELDS
                = tag("shields");
        private static TagKey<Item> tag(String name){
            return ItemTags.create(new ResourceLocation(ZeldaMod.MOD_ID, name));
        }
        private static TagKey<Item> forgetag(String name){
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }
    public static class Entities{
        public static final TagKey<EntityType<?>>MIRROR
                = tag("cannot_reflect");
        public static final TagKey<EntityType<?>>UNDEAD
                = tag("undead");
        public static final TagKey<EntityType<?>>EVIL
                = tag("evil");
        public static final TagKey<EntityType<?>>BUGS
                = tag("bugs");
        public static final TagKey<EntityType<?>>SEED_PROJECTILE
                = tag("wood_reflect");

        private static TagKey<EntityType<?>> tag(String name){
            return TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation(ZeldaMod.MOD_ID, name));
        }
        private static TagKey<EntityType<?>> forgetag(String name){
            return TagKey.create(Registries.ENTITY_TYPE,(new ResourceLocation("forge", name)));
        }
    }}


