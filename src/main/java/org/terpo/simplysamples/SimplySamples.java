package org.terpo.simplysamples;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.terpo.simplysamples.block.SampleBlock;
import org.terpo.simplysamples.client.ClientSetup;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod(SimplySamples.MODID)
public class SimplySamples {
	public static final String MODID = "simplysamples";

	public static final ItemGroup CREATIVE_TAB = new ItemGroup(MODID) {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(SAMPLE_BLOCKS.getEntries().iterator().next().get());
		}
	};
	public static final Logger LOGGER = LogManager.getLogger(MODID);

	public static final DeferredRegister<Block> SAMPLE_BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
	public static final DeferredRegister<Item> SAMPLE_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

	public SimplySamples() { // NOSONAR
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		SAMPLE_BLOCKS.register(modEventBus);
		SAMPLE_ITEMS.register(modEventBus);
		initBlocks();
		FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientSetup::init);
	}

	private static boolean initBlocks() {
		RegistryNames.SAMPLES.keySet().forEach(indicator -> {
			RegistryObject<Block> registeredIndicator = SAMPLE_BLOCKS.register(indicator, SampleBlock::new);
			SAMPLE_ITEMS.register(indicator, () -> new BlockItem(registeredIndicator.get(), new Item.Properties().group(CREATIVE_TAB)));
		});
		LOGGER.info("Registered Blocks and Items");
		return true;
	}
}
