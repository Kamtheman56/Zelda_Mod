package com.kamth.zeldamod.item.custom.util;

import com.kamth.zeldamod.ZeldaMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
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
        private static TagKey<Item> tag(String name){
            return ItemTags.create(new ResourceLocation(ZeldaMod.MOD_ID, name));
        }
        private static TagKey<Item> forgetag(String name){
            return ItemTags.create(new ResourceLocation("forge", name));
        }

    }
}
