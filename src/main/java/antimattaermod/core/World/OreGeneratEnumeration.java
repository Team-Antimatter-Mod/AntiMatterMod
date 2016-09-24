package antimattaermod.core.World;

import net.minecraft.block.Block;

import java.util.Enumeration;

/**
 * Created by Raiti on 2016/09/23.
 */
public class OreGeneratEnumeration implements Enumeration{
	
	
	
	
	@Override
	public boolean hasMoreElements() {
		return false;
	}
	
	@Override
	public Object nextElement() {
		return null;
	}
	
	
}

class OreEntry {
	
	private Block generateBlock;
	
	private byte meta;
	
	private int number;
	
	private boolean isGeneratOverworld;
	
	private boolean isGeneratHell;
	
	private boolean isGeneratEnd;
	
	private int mixYOverworld;
	
	private int maxYOverworld;
	
	private int mixYHell;
	
	private int maxYHell;
	
	private int mixYEnd;
	
	private int maxYEnd;
	
	
	public OreEntry(Block generateBlock, byte meta, int number, boolean isGeneratOverworld, boolean isGeneratHell, boolean isGeneratEnd, int ... limiter){
		this.generateBlock = generateBlock;
		this.meta = meta;
		this.number = number;
		this.isGeneratOverworld = isGeneratOverworld;
		this.isGeneratHell = isGeneratHell;
		this.isGeneratEnd = isGeneratEnd;
		
		for (int i = 0; i < limiter.length; i+=2){
			
		}
		
	}
	
	
	
}