package org.terpo.simplysamples.client;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockDisplayReader;

public class SampleBlockColor implements IBlockColor {

	private int overlayColor = -1;

	public SampleBlockColor(int overlayColor) {
		this.overlayColor = overlayColor;
	}

	@Override
	public int getColor(BlockState state, IBlockDisplayReader reader, BlockPos pos, int color) {
		return overlayColor;
	}
}
