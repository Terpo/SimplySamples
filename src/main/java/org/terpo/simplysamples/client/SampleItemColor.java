package org.terpo.simplysamples.client;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;

public class SampleItemColor implements IItemColor {

	private int overlayColor = -1;

	public SampleItemColor(int overlayColor) {
		this.overlayColor = overlayColor;
	}

	@Override
	public int getColor(ItemStack stack, int color) {
		return color != 1 ? -1 : overlayColor;
	}
}
