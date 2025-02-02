package com.noix;

import com.mojang.serialization.MapCodec;
import net.minecraft.client.render.item.property.bool.BooleanProperty;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ModelTransformationMode;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class RiptideBooleanProperty implements BooleanProperty {

    public static final MapCodec<RiptideBooleanProperty> CODEC = MapCodec.unit(new RiptideBooleanProperty());
    private RegistryEntry<Enchantment> entry;


    @Override
    public boolean getValue(ItemStack stack, @Nullable ClientWorld world, @Nullable LivingEntity user, int seed, ModelTransformationMode modelTransformationMode) {
        if (entry == null) {
            Optional<Registry<Enchantment>> optional = world.getRegistryManager().getOptional(RegistryKeys.ENCHANTMENT);
            if (optional.isEmpty()) {
                Ixeon.LOGGER.warn("Enchantments registry not found");
                return false;
            }

            Registry<Enchantment> enchantmentRegistry = optional.get();
            entry = enchantmentRegistry.getEntry(enchantmentRegistry.get(Enchantments.RIPTIDE));
        }

        return stack != null &&
                stack.isOf(Items.TRIDENT) &&
                stack.hasEnchantments() &&
                stack.getEnchantments().getEnchantments().contains(entry);
    }

    @Override
    public MapCodec<? extends BooleanProperty> getCodec() {
        return CODEC;
    }
}
