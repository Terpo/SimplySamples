package org.terpo.simplysamples.datagen;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import org.terpo.simplysamples.SimplySamples;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.data.LootTableProvider;
import net.minecraft.loot.ConstantRange;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTableManager;
import net.minecraft.util.ResourceLocation;

public class LootTables extends LootTableProvider {

	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

	private final DataGenerator generator;

	public LootTables(DataGenerator dataGeneratorIn) {
		super(dataGeneratorIn);
		this.generator = dataGeneratorIn;
	}

	@Override
	public void act(DirectoryCache cache) {
		final Map<Block, LootTable.Builder> lootTables = new HashMap<>();
		SimplySamples.SAMPLE_BLOCKS.getEntries()
				.forEach(indicator -> lootTables.put(indicator.get(), createIndicator(indicator.get())));

		Map<ResourceLocation, LootTable> tables = new HashMap<>();
		for (Map.Entry<Block, LootTable.Builder> entry : lootTables.entrySet()) {
			tables.put(entry.getKey().getLootTable(),
					entry.getValue().setParameterSet(LootParameterSets.BLOCK).build());
		}
		writeTables(cache, tables);
	}

	private LootTable.Builder createIndicator(Block indicator) {
		LootPool.Builder builder = LootPool.builder().name(indicator.getRegistryName().getPath())
				.rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(indicator));
		return LootTable.builder().addLootPool(builder);
	}

	private void writeTables(DirectoryCache cache, Map<ResourceLocation, LootTable> tables) {
		Path outputFolder = this.generator.getOutputFolder();
		tables.forEach((key, lootTable) -> {
			Path path = outputFolder.resolve("data/" + key.getNamespace() + "/loot_tables/" + key.getPath() + ".json");
			try {
				IDataProvider.save(GSON, cache, LootTableManager.toJson(lootTable), path);
			} catch (IOException e) {
				SimplySamples.LOGGER.error("Couldn't write loot table {}", path, e);
			}
		});
	}

}