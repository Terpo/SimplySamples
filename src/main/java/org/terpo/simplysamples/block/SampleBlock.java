package org.terpo.simplysamples.block;

import javax.annotation.Nonnull;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class SampleBlock extends Block {
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	
	public SampleBlock(Properties properties) {
		super(properties);
		this.setDefaultState(getDefaultState().with(WATERLOGGED, Boolean.FALSE).with(HorizontalBlock.HORIZONTAL_FACING, Direction.NORTH));
	}

	public SampleBlock() {
		this(Properties.create(Material.ROCK).hardnessAndResistance(0.125F, 2F).sound(SoundType.GROUND)
				.harvestTool(ToolType.SHOVEL));
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(HorizontalBlock.HORIZONTAL_FACING, context.getPlacementHorizontalFacing()).with(WATERLOGGED,
				Boolean.valueOf(context.getWorld().getBlockState(context.getPos()).getBlock() == Blocks.WATER));
	}

	@Override
	@Nonnull
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) // NOSONAR
	{
		Vector3d offset = state.getOffset(worldIn, pos);
		return VoxelShapes.create(0.125D, 0.0D, 0.125D, 0.875D, 0.1875D, 0.9375D).withOffset(offset.x, offset.y,
				offset.z);
	}

	@Override
	public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
		super.onFallenUpon(worldIn, pos, entityIn, fallDistance);
		if ((((int) fallDistance) > 0) && (RANDOM.nextInt((int) fallDistance) > 5)) {
			worldIn.destroyBlock(pos, true);
		}
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, // NOSONAR
			Hand handIn, BlockRayTraceResult hit) {
		if (!player.isCrouching()) {
			worldIn.destroyBlock(pos, true);
			player.swingArm(handIn);
			return ActionResultType.SUCCESS;
		}
		return ActionResultType.PASS;
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) { // NOSONAR
		return Block.hasEnoughSolidSide(worldIn, pos.down(), Direction.UP);
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(WATERLOGGED);
		builder.add(HorizontalBlock.HORIZONTAL_FACING);
	}

	@Override
	@Nonnull
	@SuppressWarnings("deprecation")
	public FluidState getFluidState(BlockState state) { // NOSONAR
		return state.get(WATERLOGGED).booleanValue() ? Fluids.WATER.getStillFluidState(false)
				: super.getFluidState(state);// NOSONAR
	}

	@Override
	@SuppressWarnings("deprecation")
	public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, // NOSONAR
			boolean isMoving) {
		super.neighborChanged(state, worldIn, pos, blockIn, fromPos, isMoving); // NOSONAR
		if (!this.isValidPosition(state, worldIn, pos)) {
			worldIn.destroyBlock(pos, true);
		}
		// Update the water from flowing to still or vice-versa
		else if (state.get(WATERLOGGED).booleanValue()) {
			worldIn.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
		}
	}
}
