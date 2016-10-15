package antimattaermod.core.Item.tool;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Raiti on 2016/10/15.
 */
public class File extends CraftingTool {
	
	public File(@NotNull String name, @NotNull String textureName, ToolMaterial material) {
		super(name, textureName, material);
	}
	
	public File(@NotNull String name, @NotNull String textureName, int maxUses) {
		super(name, textureName, maxUses);
	}
}
