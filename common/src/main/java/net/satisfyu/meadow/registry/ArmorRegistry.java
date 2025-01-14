package net.satisfyu.meadow.registry;

import de.cristelknight.doapi.client.render.feature.CustomArmorManager;
import de.cristelknight.doapi.client.render.feature.CustomArmorSet;
import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.satisfyu.meadow.client.model.FurArmorHat;
import net.satisfyu.meadow.client.model.FurArmorInner;
import net.satisfyu.meadow.client.model.FurArmorOuter;
import net.satisfyu.meadow.item.armor.FurBoots;
import net.satisfyu.meadow.item.armor.FurChest;
import net.satisfyu.meadow.item.armor.FurHead;
import net.satisfyu.meadow.item.armor.FurLegs;
import net.satisfyu.meadow.util.MeadowIdentifier;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public class ArmorRegistry {

    public static void registerArmorModelLayers() {
        EntityModelLayerRegistry.register(FurArmorHat.LAYER_LOCATION, FurArmorHat::createBodyLayer);
        EntityModelLayerRegistry.register(FurArmorOuter.LAYER_LOCATION, FurArmorOuter::createBodyLayer);
        EntityModelLayerRegistry.register(FurArmorInner.LAYER_LOCATION, FurArmorInner::createBodyLayer);

    }

    public static  <T extends LivingEntity> void registerHatModels(Map<Item, EntityModel<T>> models, EntityModelSet modelLoader) {
        models.put(ObjectRegistry.FUR_HELMET.get(), new FurArmorHat<>(modelLoader.bakeLayer(FurArmorHat.LAYER_LOCATION)));
    }

    public static <T extends LivingEntity> void registerArmorModels(CustomArmorManager<T> armors, EntityModelSet modelLoader) {
        armors.addArmor(new CustomArmorSet<T>(ObjectRegistry.FUR_HELMET.get(), ObjectRegistry.FUR_CHESTPLATE.get(), ObjectRegistry.FUR_LEGGINGS.get(), ObjectRegistry.FUR_BOOTS.get())
                .setTexture(new MeadowIdentifier("fur"))
                .setOuterModel(new FurArmorOuter<>(modelLoader.bakeLayer(FurArmorOuter.LAYER_LOCATION)))
                .setInnerModel(new FurArmorInner<>(modelLoader.bakeLayer(FurArmorInner.LAYER_LOCATION))));
    }

    @SuppressWarnings("all")
    public static void appendToolTip(@NotNull List<Component> tooltip) {
        Player player = Minecraft.getInstance().player;
        if (player == null) return;
        ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);
        ItemStack chestplate = player.getItemBySlot(EquipmentSlot.CHEST);
        ItemStack leggings = player.getItemBySlot(EquipmentSlot.LEGS);
        ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);
        tooltip.add(Component.nullToEmpty(""));
        tooltip.add(Component.nullToEmpty(ChatFormatting.DARK_GREEN + I18n.get("tooltip.meadow.fur_armor")));
        tooltip.add(Component.nullToEmpty((helmet != null && helmet.getItem() instanceof FurHead ? ChatFormatting.GREEN.toString() : ChatFormatting.GRAY.toString()) + "- [" + ObjectRegistry.FUR_HELMET.get().getDescription().getString() + "]"));
        tooltip.add(Component.nullToEmpty((chestplate != null && chestplate.getItem() instanceof FurChest ? ChatFormatting.GREEN.toString() : ChatFormatting.GRAY.toString()) + "- [" + ObjectRegistry.FUR_CHESTPLATE.get().getDescription().getString() + "]"));
        tooltip.add(Component.nullToEmpty((leggings != null && leggings.getItem() instanceof FurLegs ? ChatFormatting.GREEN.toString() : ChatFormatting.GRAY.toString()) + "- [" + ObjectRegistry.FUR_LEGGINGS.get().getDescription().getString() + "]"));
        tooltip.add(Component.nullToEmpty((boots != null && boots.getItem() instanceof FurBoots ? ChatFormatting.GREEN.toString() : ChatFormatting.GRAY.toString()) + "- [" + ObjectRegistry.FUR_BOOTS.get().getDescription().getString() + "]"));
        tooltip.add(Component.nullToEmpty(""));
        tooltip.add(Component.nullToEmpty(ChatFormatting.GRAY + I18n.get("tooltip.meadow.fur_armor2")));
        tooltip.add(Component.nullToEmpty(((helmet != null && helmet.getItem() instanceof FurHead &&
                chestplate != null && chestplate.getItem() instanceof FurChest &&
                leggings != null && leggings.getItem() instanceof FurLegs &&
                boots != null && boots.getItem() instanceof FurBoots) ? ChatFormatting.DARK_GREEN.toString() : ChatFormatting.GRAY.toString()) + I18n.get("tooltip.meadow.fur_armor3")));
    }
}