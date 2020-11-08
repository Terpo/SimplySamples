package org.terpo.simplysamples.client;

import org.terpo.simplysamples.SimplySamples;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = SimplySamples.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {
 
	public static void init(final FMLClientSetupEvent event) { // NOSONAR
		new ColorInit().init();
	}

	private ClientSetup() {
		// hidden
	}
}
