package com.kamth.zeldamod.item.armors;

import com.kamth.zeldamod.ZeldaMod;
import com.kamth.zeldamod.effect.ModEffects;
import com.kamth.zeldamod.item.ModItems;
import com.kamth.zeldamod.item.armors.render.ModModelLayers;
import com.kamth.zeldamod.item.armors.render.PegasusBootsModel;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Consumer;

//Todo set a timer for hover effect!


public class HoverBoots extends ArmorItem {
    private static final String PEGASUS = new ResourceLocation(ZeldaMod.MOD_ID, "textures/models/armor/hover.png").toString();

    public HoverBoots(ArmorMaterial p_40386_, Type type, Properties p_40388_) {
        super(p_40386_, type, p_40388_);


    }
    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {

        if (player.isSprinting() && player.getDeltaMovement().y > 0  && !player.onGround() && player.hasEffect(ModEffects.HOVER.get())) {
            player.fallDistance = -3;
            player.setNoGravity(true);
            Vec3 vec3 = player.getDeltaMovement();
            player.setDeltaMovement(vec3.x, 0.0, vec3.z);
            player.canSpawnSprintParticle();
            world.addParticle(ParticleTypes.CLOUD,player.getX(), player.getY() + 0.1D, player.getZ(), vec3.x, 0, vec3.z * -4.0D);

            world.playSound(null,player.getX(),player.getY(),player.getZ(), SoundEvents.WOOL_STEP, SoundSource.NEUTRAL, 1F, 0.2F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        }
        if (!player.hasEffect(ModEffects.HOVER.get())){
            player.setNoGravity(false);
            player.resetFallDistance();
        }

        if (!player.onGround() && !player.isSprinting() && player.getItemBySlot(EquipmentSlot.FEET).getItem() == ModItems.HOVER_BOOTS.get()) {
            player.setNoGravity(false);
            player.fallDistance = -3;
        }
        if (player.onGround() && player.getItemBySlot(EquipmentSlot.FEET).getItem() == ModItems.HOVER_BOOTS.get()) {
            player.addEffect(((new MobEffectInstance(ModEffects.HOVER.get(), 90, 0, true, false))));
        }
    }
   @Override
   public boolean canWalkOnPowderedSnow(ItemStack stack, LivingEntity wearer)
    {
        return stack.getItem() == ModItems.HOVER_BOOTS.get();
    }



    @Override
    public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return PEGASUS;
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private PegasusBootsModel model;

            @Nullable
            @Override
            public HumanoidModel<?> getHumanoidArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> _default) {
                if (null == model) {
                    model = new PegasusBootsModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModModelLayers.PEGASUS));
                }
                return model;
            }
        });
    }


    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if(Screen.hasShiftDown()) {
            components.add(Component.translatable("armor.hover_boots.description_advanced").withStyle(ChatFormatting.YELLOW).withStyle(ChatFormatting.ITALIC));
        }
        else {
            components.add(Component.translatable("armor.hover_boots.description_basic").withStyle(ChatFormatting.GRAY));
        }
        super.appendHoverText(stack, level, components, flag);
    }
}





