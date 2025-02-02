package com.noix;

import net.minecraft.client.data.ItemModels;
import net.minecraft.client.render.item.model.ConditionItemModel;
import net.minecraft.client.render.item.model.ItemModel;
import net.minecraft.util.Identifier;

public class IxeonModelHolder {

    private static IxeonModelHolder instance = null;

    public static final ItemModel.Unbaked TRIDENT_RIPTIDE = ItemModels.basic(Identifier.of(Ixeon.MOD_ID, "item/trident"));


    //useless for now
    public IxeonModelHolder getInstance() {
        if (instance == null) {
            instance = new IxeonModelHolder();
        }
        return instance;
    }

    private IxeonModelHolder() {
    }






}
