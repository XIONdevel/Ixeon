package com.noix;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.*;
import net.minecraft.client.render.item.model.ItemModel;
import net.minecraft.client.render.item.model.special.TridentModelRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class IxeonModelProvider extends FabricModelProvider {

    private static final Item trident = Items.TRIDENT;

    public IxeonModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.output.accept(
                Items.TRIDENT,
                ItemModels.condition(
                        new RiptideBooleanProperty(),
                        createModel(createCustomTridentModel(itemModelGenerator)),
                        createModel(createDefaultTridentModel(itemModelGenerator))
                )
        );
    }

    public static ItemModel.Unbaked createDefaultTridentModel(ItemModelGenerator generator) {
        return ItemModels.basic(Models.GENERATED.upload( //todo: try to replace with default
                ModelIds.getItemModelId(trident),
                TextureMap.layer0(trident),
                generator.modelCollector
        ));
    }

    public static ItemModel.Unbaked createCustomTridentModel(ItemModelGenerator generator) {
        return ItemModels.basic(Identifier.of(Ixeon.MOD_ID, "item/trident"));
    }

    private static ItemModel.Unbaked createModel(ItemModel.Unbaked icon) {
        ItemModel.Unbaked unbaked2 = ItemModels.special(ModelIds.getItemSubModelId(trident, "_in_hand"), new TridentModelRenderer.Unbaked());
        ItemModel.Unbaked unbaked3 = ItemModels.special(ModelIds.getItemSubModelId(trident, "_throwing"), new TridentModelRenderer.Unbaked());
        ItemModel.Unbaked unbaked4 = ItemModels.condition(ItemModels.usingItemProperty(), unbaked3, unbaked2);
        return ItemModelGenerator.createModelWithInHandVariant(icon, unbaked4);
    }


    /*
    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        ItemModel.Unbaked defaultTrident = ItemModels.basic(Models.GENERATED.upload(
                ModelIds.getItemModelId(Items.TRIDENT),
                TextureMap.layer0(Items.TRIDENT),
                itemModelGenerator.modelCollector
        ));

        itemModelGenerator.output.accept(
                Items.TRIDENT,
                ItemModels.condition(
                        new RiptideBooleanProperty(),
                        IxeonModelHolder.TRIDENT_RIPTIDE,
                        defaultTrident
                )
        );
    }
     */

}
