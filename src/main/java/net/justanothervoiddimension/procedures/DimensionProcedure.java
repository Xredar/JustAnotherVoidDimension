package net.justanothervoiddimension.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.entity.Entity;

import net.justanothervoiddimension.JustAnotherVoidDimensionModVariables;
import net.justanothervoiddimension.JustAnotherVoidDimensionModElements;

import java.util.Map;
import java.util.HashMap;

@JustAnotherVoidDimensionModElements.ModElement.Tag
public class DimensionProcedure extends JustAnotherVoidDimensionModElements.ModElement {
	public DimensionProcedure(JustAnotherVoidDimensionModElements instance) {
		super(instance, 5);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure Dimension!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		{
			double _setval = (double) (entity.dimension.getId());
			entity.getCapability(JustAnotherVoidDimensionModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.dimensionID = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}

	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			Entity entity = event.player;
			World world = entity.world;
			double i = entity.getPosX();
			double j = entity.getPosY();
			double k = entity.getPosZ();
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("x", i);
			dependencies.put("y", j);
			dependencies.put("z", k);
			dependencies.put("world", world);
			dependencies.put("entity", entity);
			dependencies.put("event", event);
			this.executeProcedure(dependencies);
		}
	}
}
