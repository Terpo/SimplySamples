package org.terpo.simplysamples.datagen;

import org.terpo.simplysamples.SimplySamples;

import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class Items extends ItemModelProvider {

	public Items(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, SimplySamples.MODID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		SimplySamples.SAMPLE_ITEMS.getEntries().forEach(entry -> withExistingParent(entry.getId().getPath(), new ResourceLocation(SimplySamples.MODID, "item/sample")));
	}
}