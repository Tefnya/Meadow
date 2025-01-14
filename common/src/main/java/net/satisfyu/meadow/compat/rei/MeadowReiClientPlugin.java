package net.satisfyu.meadow.compat.rei;

import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.satisfyu.meadow.compat.rei.category.CheeseFormCategory;
import net.satisfyu.meadow.compat.rei.category.CookingCauldronCategory;
import net.satisfyu.meadow.compat.rei.category.FondueCategory;
import net.satisfyu.meadow.compat.rei.category.WoodCutterCategory;
import net.satisfyu.meadow.compat.rei.display.CheeseFormDisplay;
import net.satisfyu.meadow.compat.rei.display.CookingCauldronDisplay;
import net.satisfyu.meadow.compat.rei.display.FondueDisplay;
import net.satisfyu.meadow.compat.rei.display.WoodCutterDisplay;
import net.satisfyu.meadow.recipes.CheeseFormRecipe;
import net.satisfyu.meadow.recipes.CookingCauldronRecipe;
import net.satisfyu.meadow.recipes.FondueRecipe;
import net.satisfyu.meadow.recipes.WoodcuttingRecipe;
import net.satisfyu.meadow.registry.ObjectRegistry;

public class MeadowReiClientPlugin {

    public static void registerCategories(CategoryRegistry registry) {
        registry.add(new CookingCauldronCategory());
        registry.add(new CheeseFormCategory());
        registry.add(new WoodCutterCategory());
        registry.add(new FondueCategory());

        registry.addWorkstations(CookingCauldronCategory.COOKING_CAULDRON_DISPLAY, EntryStacks.of(ObjectRegistry.COOKING_CAULDRON.get()));
        registry.addWorkstations(CheeseFormCategory.CHEESE_FORM_DISPLAY, EntryStacks.of(ObjectRegistry.CHEESE_FORM.get()));
        registry.addWorkstations(WoodCutterCategory.WOOD_CUTTER_DISPLAY, EntryStacks.of(ObjectRegistry.WOODCUTTER.get()));
        registry.addWorkstations(FondueCategory.FONDUE_DISPLAY, EntryStacks.of(ObjectRegistry.FONDUE.get()));
    }

    public static void registerDisplays(DisplayRegistry registry) {
        registry.registerFiller(CookingCauldronRecipe.class, CookingCauldronDisplay::new);
        registry.registerFiller(CheeseFormRecipe.class, CheeseFormDisplay::new);
        registry.registerFiller(WoodcuttingRecipe.class, WoodCutterDisplay::new);
        registry.registerFiller(FondueRecipe.class, FondueDisplay::new);
    }
}
