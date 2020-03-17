package com.kosmx.somecoolextraitems.tools;

import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

/**
 * Tool item tags provided by Fabric.
 */
public class toolTags {
	public static final Tag<Item> AXES = register("axes");
	public static final Tag<Item> HOES = register("hoes");
	public static final Tag<Item> PICKAXES = register("pickaxes");
	public static final Tag<Item> SHOVELS = register("shovels");
	public static final Tag<Item> SWORDS = register("swords");

	//private ToolTags() { }

	private static Tag<Item> register(String id) {
		return TagRegistry.item(new Identifier("somecoolextraitems", id));
	}
}