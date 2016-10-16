package antimattaermod.core.Item.tool;

import org.jetbrains.annotations.NotNull;

/**
 * Created by sora_suke on 2016/10/16.
 */
public class ScrewDriver extends CraftingTool {

    public ScrewDriver(@NotNull String name, @NotNull String textureName, ToolMaterial material) {
        super(name, textureName, material);
    }

    public ScrewDriver(@NotNull String name, @NotNull String textureName, int maxUses) {
        super(name, textureName, maxUses);
    }

}
