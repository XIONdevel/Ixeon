package com.noix;

import net.fabricmc.api.ModInitializer;

import net.minecraft.client.render.item.property.bool.BooleanProperties;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Ixeon implements ModInitializer {
	public static final String MOD_ID = "ixeon";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		BooleanProperties.ID_MAPPER.put(Identifier.of("riptide_property"), RiptideBooleanProperty.CODEC);
	}
}