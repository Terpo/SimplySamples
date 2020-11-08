package org.terpo.simplysamples.item;


import org.terpo.simplysamples.SimplySamples;

import net.minecraft.item.Item;

public class SampleItem extends Item {
	public SampleItem() {
		super((new Item.Properties()).group(SimplySamples.CREATIVE_TAB));
	}
}
