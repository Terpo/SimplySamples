package org.terpo.simplysamples.datagen;

import org.terpo.simplysamples.RegistryNames;
import org.terpo.simplysamples.SimplySamples;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.fml.RegistryObject;

public class Language extends LanguageProvider {

	public Language(DataGenerator generator) {
		super(generator,SimplySamples.MODID, "en_us");
	}

	@Override
	protected void addTranslations() {
		
		add("itemGroup."+SimplySamples.MODID, "Simply Samples");
		
		SimplySamples.SAMPLE_BLOCKS.getEntries().stream().map(RegistryObject::get).sorted((b1,b2) -> b1.getRegistryName().compareTo(b2.getRegistryName())).forEach(block -> {
			add(block, createLocale(block));
		});

	}

	private String createLocale(Block block) {
		String replaceSample = block.getRegistryName().getPath().replace(RegistryNames.SAMPLE_SUFFIX, " Sample");
		return replaceSample.substring(0, 1).toUpperCase() + replaceSample.substring(1); //first letter to upper case
	}

}
