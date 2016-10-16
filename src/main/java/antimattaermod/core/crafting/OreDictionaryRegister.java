package antimattaermod.core.crafting;

import antimattaermod.core.AntiMatterModRegistry;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Created by kojin15 on 2016/10/15.
 * Changed by Raiti-chan on 2016/10/16.
 * 鉱石辞書に関すするEnum定数
 * @author kojin15 Raiti-chan
 */
public enum OreDictionaryRegister {
    /*
     * ・鉱石辞書の追加方法
     * 追加名(追加するItemStack),
     * 複数登録は 追加名(ItemStack,ItemStack,ItemStack), とする。もちろんいくつでも可能
     * ,←これわすれないように
     * あと、例えばironIngotとかみたいにこのmodでは追加しないけど、使うよ(主にバニラの鉱石辞書)
     * って物は
     * 追加名,
     * って登録してね。(一応 "ingotIron"は入れておいた)
     */
    stickWood,
    ingotIron,
	plateIron(new ItemStack(AntiMatterModRegistry.industrialplate_01,0,0)),
	craftingToolHardHammer(new ItemStack(AntiMatterModRegistry.hammer_01,1,OreDictionary.WILDCARD_VALUE)),
	craftingToolWrench(new ItemStack(AntiMatterModRegistry.wrench_01,1,OreDictionary.WILDCARD_VALUE)),
	craftingToolWireCutter(new ItemStack(AntiMatterModRegistry.wirecutter_01,1,OreDictionary.WILDCARD_VALUE)),
    craftingToolFile(new ItemStack(AntiMatterModRegistry.file_01,1,OreDictionary.WILDCARD_VALUE)),
	
	//追加はこのコメントより上に
	;
	
	/*
	 * レシピでの使用の際は、
	 * 登録名を入れるところに
	 * OreDictionaryRegister.plateIron や
	 * OreDictionaryRegister.craftingTookWrench;
	 * で、RecipeRegisterクラスのメソッド shapedOreRecipe や shapelessOreRecipe
	 * を通すこと
	 * IDEAなので候補が出てきます。
	 * その方が効率いいでしょ
	 */
	
	
	
	private ItemStack[] stacks;
	
	/**
	 * enumの登録
	 * @param itemStack 追加するItemStack配列
	 */
	OreDictionaryRegister(ItemStack... itemStack) {
		this.stacks = itemStack;
	}
	
	OreDictionaryRegister(){
		this.stacks = null;
	}
	
	/**
	 * ItemStack配列を取得
	 * @return ItemStack配列
	 */
	public ItemStack[] getStacks(){
		return stacks;
	}
	
	
	
	/**
     * 鉱石辞書登録関数
     * @param event FMLイベント
     */
    public static void OreDictionaryRegisterPreInit(FMLPreInitializationEvent event){

        //鉱石辞書登録
	    a:for (OreDictionaryRegister element:OreDictionaryRegister.values()){
		    if(element.getStacks() == null) continue a;
		    b:for (ItemStack stack:element.getStacks()){
			    if (stack == null) continue b;
			    OreDictionary.registerOre(element.toString(),stack);
		    }
		    
	    }


    }
}
