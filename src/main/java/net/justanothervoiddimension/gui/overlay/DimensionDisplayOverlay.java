
package net.justanothervoiddimension.gui.overlay;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.Minecraft;

import net.justanothervoiddimension.JustAnotherVoidDimensionModVariables;
import net.justanothervoiddimension.JustAnotherVoidDimensionModElements;

@JustAnotherVoidDimensionModElements.ModElement.Tag
public class DimensionDisplayOverlay extends JustAnotherVoidDimensionModElements.ModElement {
	public DimensionDisplayOverlay(JustAnotherVoidDimensionModElements instance) {
		super(instance, 6);
	}

	@Override
	public void initElements() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void eventHandler(RenderGameOverlayEvent event) {
		if (!event.isCancelable() && event.getType() == RenderGameOverlayEvent.ElementType.HELMET) {
			int posX = (event.getWindow().getScaledWidth()) / 2;
			int posY = (event.getWindow().getScaledHeight()) / 2;
			PlayerEntity entity = Minecraft.getInstance().player;
			World world = entity.world;
			double x = entity.getPosX();
			double y = entity.getPosY();
			double z = entity.getPosZ();
			if (true) {
				Minecraft.getInstance().fontRenderer
						.drawString(
								"" + ((entity.getCapability(JustAnotherVoidDimensionModVariables.PLAYER_VARIABLES_CAPABILITY, null)
										.orElse(new JustAnotherVoidDimensionModVariables.PlayerVariables())).dimensionID) + "",
								posX + -213, posY + -120, -1);
			}
		}
	}
}
