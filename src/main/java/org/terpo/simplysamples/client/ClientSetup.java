package org.terpo.simplysamples.client;

import org.terpo.simplysamples.RegistryNames;
import org.terpo.simplysamples.SimplySamples;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = SimplySamples.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {
 
	public static void init(final FMLClientSetupEvent event) { // NOSONAR
		// 0xFFFFFF = white
		// 0xFF0000 = red
		// 0x00FF00 = green
		// 0x0000FF = blue
		ItemColors itemColors = Minecraft.getInstance().getItemColors();
		SimplySamples.SAMPLE_ITEMS.getEntries().forEach(item -> itemColors
				.register(new SampleItemColor(RegistryNames.SAMPLES.get(item.getId().getPath())), item.get()));

		BlockColors blockColors = Minecraft.getInstance().getBlockColors();
		SimplySamples.SAMPLE_BLOCKS.getEntries().forEach(block -> blockColors
				.register(new SampleBlockColor(RegistryNames.SAMPLES.get(block.getId().getPath())), block.get()));
	}

	private ClientSetup() {
		// hidden
	}
}
