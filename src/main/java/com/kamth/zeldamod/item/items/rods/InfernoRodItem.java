package com.kamth.zeldamod.item.items.rods;

import com.kamth.zeldamod.block.ModBlocks;
import com.kamth.zeldamod.entity.projectile.magic.FireProjectile;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.List;

public class InfernoRodItem extends Item {
    public InfernoRodItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
    world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.FIRECHARGE_USE, SoundSource.NEUTRAL, 1F, -2F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        ItemStack stack = player.getItemInHand(hand);
        stack.setDamageValue(stack.getDamageValue() + 2);
        if (stack.getDamageValue() >= stack.getMaxDamage()) stack.setCount(0);
        player.startUsingItem(InteractionHand.MAIN_HAND);

    player.awardStat(Stats.ITEM_USED.get(this));

    return super.use(world, player, hand);
    }
    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        BlockPos blockpos = pContext.getClickedPos();
        BlockState blockstate = level.getBlockState(blockpos);

        if (blockstate.is(ModBlocks.POWER_FLAME.get())) {
            if (!(pContext.getItemInHand().getAllEnchantments().containsKey(Enchantments.UNBREAKING))){
                pContext.getItemInHand().enchant(Enchantments.MENDING,1);
                pContext.getLevel().playSound(pContext.getPlayer(),blockpos,SoundEvents.AMETHYST_BLOCK_RESONATE,SoundSource.BLOCKS, 1,1);
            }  return InteractionResult.SUCCESS;}
        else return InteractionResult.PASS;
    }
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BLOCK;
    }
    @Override
    public void onUseTick(Level world, LivingEntity livingEntity, ItemStack stack, int pRemainingUseDuration) {
        Player player = (Player) livingEntity;

        if (!player.getAbilities().instabuild){
    stack.hurtAndBreak(3, livingEntity, (p_43296_) -> {
        p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
    });

}

        FireProjectile projectile = new FireProjectile(world, player);

        projectile.shootFromRotation(player, player.xRotO, player.yRotO, 0.0F, 1.4f, .9f);
        world.addFreshEntity(projectile);

    }
    @Override
    public int getUseDuration(ItemStack itemStack)
    {
        return 72000;
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if(Screen.hasShiftDown()) {
            components.add(Component.translatable("item.inferno_rod.description").withStyle(ChatFormatting.RED).withStyle(ChatFormatting.ITALIC));
        }
        super.appendHoverText(stack, level, components, flag);
    }
    public boolean isFoil(ItemStack pStack) {
        return true;
    }
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pStack.hurtAndBreak(7, pAttacker, (p_43296_) -> {
            p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        });
        return true;
    }
}






