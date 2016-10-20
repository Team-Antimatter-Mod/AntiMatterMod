package antimattermod.core.Item.tool;


import org.jetbrains.annotations.NotNull;

/**
 * Created by Raiti on 2016/10/14.
 */
public class Hammer extends CraftingTool {
	
	
	public Hammer(@NotNull String name, @NotNull String textureName, ToolMaterial material) {
		super(name, textureName, material);
	}
	
	public Hammer(@NotNull String name, @NotNull String textureName, int maxUses) {
		super(name, textureName, maxUses);
	}
}
