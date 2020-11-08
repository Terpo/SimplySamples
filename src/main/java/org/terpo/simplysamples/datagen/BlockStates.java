package org.terpo.simplysamples.datagen;

import org.terpo.simplysamples.SimplySamples;

import net.minecraft.data.DataGenerator;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockStates extends BlockStateProvider {

	public BlockStates(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, SimplySamples.MODID, existingFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
		SimplySamples.SAMPLE_BLOCKS.getEntries().forEach(entry -> {
			BlockModelBuilder withExistingParent = models().withExistingParent(entry.get().getRegistryName().getPath(),
					new ResourceLocation(SimplySamples.MODID, "block/sample"));
			getVariantBuilder(entry.get()).forAllStates(mapper -> {
				Direction dir = mapper.get(BlockStateProperties.HORIZONTAL_FACING);
				return ConfiguredModel.builder().modelFile(withExistingParent)
						.rotationY(((dir.getHorizontalIndex() + 2) % 4) * 90).build();
			});

		});
	}
}