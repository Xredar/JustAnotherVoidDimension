package net.justanothervoiddimension.procedures;

import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.network.play.server.SPlayerAbilitiesPacket;
import net.minecraft.network.play.server.SPlaySoundEventPacket;
import net.minecraft.network.play.server.SPlayEntityEffectPacket;
import net.minecraft.network.play.server.SChangeGameStatePacket;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

import net.justanothervoiddimension.world.dimension.VoidDimensionDimension;
import net.justanothervoiddimension.block.VoidPortalBlock;
import net.justanothervoiddimension.JustAnotherVoidDimensionModElements;

import java.util.Map;

@JustAnotherVoidDimensionModElements.ModElement.Tag
public class VoidPortalOnBlockRightClickedProcedure extends JustAnotherVoidDimensionModElements.ModElement {
	public VoidPortalOnBlockRightClickedProcedure(JustAnotherVoidDimensionModElements instance) {
		super(instance, 2);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure VoidPortalOnBlockRightClicked!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure VoidPortalOnBlockRightClicked!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure VoidPortalOnBlockRightClicked!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure VoidPortalOnBlockRightClicked!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure VoidPortalOnBlockRightClicked!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((entity.dimension.getId()) == 0)) {
			{
				Entity _ent = entity;
				if (!_ent.world.isRemote && _ent instanceof ServerPlayerEntity) {
					DimensionType destinationType = VoidDimensionDimension.type;
					ObfuscationReflectionHelper.setPrivateValue(ServerPlayerEntity.class, (ServerPlayerEntity) _ent, true, "field_184851_cj");
					ServerWorld nextWorld = _ent.getServer().getWorld(destinationType);
					((ServerPlayerEntity) _ent).connection.sendPacket(new SChangeGameStatePacket(4, 0));
					((ServerPlayerEntity) _ent).teleport(nextWorld, nextWorld.getSpawnPoint().getX(), nextWorld.getSpawnPoint().getY() + 1,
							nextWorld.getSpawnPoint().getZ(), _ent.rotationYaw, _ent.rotationPitch);
					((ServerPlayerEntity) _ent).connection.sendPacket(new SPlayerAbilitiesPacket(((ServerPlayerEntity) _ent).abilities));
					for (EffectInstance effectinstance : ((ServerPlayerEntity) _ent).getActivePotionEffects()) {
						((ServerPlayerEntity) _ent).connection.sendPacket(new SPlayEntityEffectPacket(_ent.getEntityId(), effectinstance));
					}
					((ServerPlayerEntity) _ent).connection.sendPacket(new SPlaySoundEventPacket(1032, BlockPos.ZERO, 0, false));
				}
			}
		} else if (((entity.dimension.getId()) == 2)) {
			{
				Entity _ent = entity;
				if (!_ent.world.isRemote && _ent instanceof ServerPlayerEntity) {
					DimensionType destinationType = DimensionType.OVERWORLD;
					ObfuscationReflectionHelper.setPrivateValue(ServerPlayerEntity.class, (ServerPlayerEntity) _ent, true, "field_184851_cj");
					ServerWorld nextWorld = _ent.getServer().getWorld(destinationType);
					((ServerPlayerEntity) _ent).connection.sendPacket(new SChangeGameStatePacket(4, 0));
					((ServerPlayerEntity) _ent).teleport(nextWorld, nextWorld.getSpawnPoint().getX(), nextWorld.getSpawnPoint().getY() + 1,
							nextWorld.getSpawnPoint().getZ(), _ent.rotationYaw, _ent.rotationPitch);
					((ServerPlayerEntity) _ent).connection.sendPacket(new SPlayerAbilitiesPacket(((ServerPlayerEntity) _ent).abilities));
					for (EffectInstance effectinstance : ((ServerPlayerEntity) _ent).getActivePotionEffects()) {
						((ServerPlayerEntity) _ent).connection.sendPacket(new SPlayEntityEffectPacket(_ent.getEntityId(), effectinstance));
					}
					((ServerPlayerEntity) _ent).connection.sendPacket(new SPlaySoundEventPacket(1032, BlockPos.ZERO, 0, false));
				}
			}
		}
		if (((new Object() {
			public boolean getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getBoolean(tag);
				return false;
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "FirstTime")) == (true))) {
			if (!world.getWorld().isRemote) {
				BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
				TileEntity _tileEntity = world.getTileEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_tileEntity != null)
					_tileEntity.getTileData().putBoolean("FirstTime", (false));
				world.getWorld().notifyBlockUpdate(_bp, _bs, _bs, 3);
			}
			if (world instanceof ServerWorld) {
				IWorld _worldorig = world;
				world = ((ServerWorld) world).getServer().getWorld(VoidDimensionDimension.type);
				world.setBlockState(new BlockPos((int) x, (int) (y - 1), (int) z), VoidPortalBlock.block.getDefaultState(), 3);
				world.setBlockState(new BlockPos((int) (x + 1), (int) (y - 1), (int) (z + 1)), Blocks.STONE.getDefaultState(), 3);
				world.setBlockState(new BlockPos((int) (x + 1), (int) (y - 1), (int) (z + 0)), Blocks.STONE.getDefaultState(), 3);
				world.setBlockState(new BlockPos((int) (x + 1), (int) (y - 1), (int) (z - 1)), Blocks.STONE.getDefaultState(), 3);
				world.setBlockState(new BlockPos((int) (x - 1), (int) (y - 1), (int) (z + 1)), Blocks.STONE.getDefaultState(), 3);
				world.setBlockState(new BlockPos((int) (x - 1), (int) (y - 1), (int) (z + 0)), Blocks.STONE.getDefaultState(), 3);
				world.setBlockState(new BlockPos((int) (x - 1), (int) (y - 1), (int) (z - 1)), Blocks.STONE.getDefaultState(), 3);
				world.setBlockState(new BlockPos((int) (x + 0), (int) (y - 1), (int) (z + 1)), Blocks.STONE.getDefaultState(), 3);
				world.setBlockState(new BlockPos((int) (x + 0), (int) (y - 1), (int) (z - 1)), Blocks.STONE.getDefaultState(), 3);
				world = _worldorig;
			}
		}
	}
}
