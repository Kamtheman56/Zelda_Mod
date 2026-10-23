package com.kamth.zeldamod.tags;

import com.kamth.zeldamod.ZeldaMod;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks{

        public static final TagKey<Block> TEST = createTag("test");


        public static final TagKey<Block> NEEDS_MASTER_TOOL = createTag("needs_master_tool");
        public static final TagKey<Block> INCORRECT_FOR_MASTER_TOOL = createTag("incorrect_for_master_tool");





        public static TagKey<Block> createTag(String name){
            return BlockTags.create(Identifier.fromNamespaceAndPath(ZeldaMod.MOD_ID, name));
        }
    }



    public static class Items{






        public static final TagKey<Item> MASTER_REPAIRABLE = createTag("master_repairable");


        public static TagKey<Item> createTag(String name){
            return ItemTags.create(Identifier.fromNamespaceAndPath(ZeldaMod.MOD_ID, name));
        }


    }






}
