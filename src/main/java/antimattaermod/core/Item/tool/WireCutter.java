package antimattaermod.core.Item.tool;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Raiti on 2016/10/15.
 */
public class WireCutter extends CraftingTool {
	
	public WireCutter(@NotNull String name, @NotNull String textureName, ToolMaterial material) {
		super(name, textureName, material);
	}
	
	public WireCutter(@NotNull String name, @NotNull String textureName, int maxUses) {
		super(name, textureName, maxUses);
	}
}