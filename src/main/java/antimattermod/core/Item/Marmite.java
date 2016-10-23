package antimattermod.core.Item;


import antimattermod.core.AntiMatterModCore;
import antimattermod.core.AntiMatterModRegistry;
import net.minecraft.item.ItemFood;


/**
 * Created by worldofthetakumi on 2016/10/18.
 */
public class Marmite extends ItemFood{

    private static int foodID_01=29000;

    public Marmite(String texture){
        //第一引数にID,第二引数に回復量,第三引数はオオカミが食べれるかどうか
        super(1000,200,false);
        setCreativeTab(AntiMatterModRegistry.tabMaterials);
        setTextureName(AntiMatterModCore.MOD_ID+":"+"/food/"+texture);
        setUnlocalizedName("marmite");
        setAlwaysEdible();
    }

}
