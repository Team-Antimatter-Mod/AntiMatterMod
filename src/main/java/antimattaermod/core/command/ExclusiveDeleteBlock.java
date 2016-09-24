package antimattaermod.core.command;

import antimattaermod.core.AntiMatterModRegistry;
import net.minecraft.block.Block;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

/**
 * Created by Raiti on 2016/09/23.
 */
public class ExclusiveDeleteBlock extends CommandBase{
	@Override
	public String getCommandName() {
		return "excDel";
	}
	
	@Override
	public String getCommandUsage(ICommandSender iCommandSender) {
		return null;
	}
	
	@Override
	public void processCommand(ICommandSender iCommandSender, String[] strings) {
		World world = iCommandSender.getEntityWorld();
		System.out.print("excDel");
		if(strings.length >= 7){
			int x = Integer.parseInt(strings[0]);
			int y = Integer.parseInt(strings[1]);
			int z = Integer.parseInt(strings[2]);
			int posX = Integer.parseInt(strings[3]);
			int posY = Integer.parseInt(strings[4]);
			int posZ = Integer.parseInt(strings[5]);
			Object getobject = Block.blockRegistry.getObject(strings[6]);
			int meta = strings.length >= 8 ? Integer.parseInt(strings[7]) : -1;
			
			System.out.println(x+"."+y+","+z+"~"+posX+","+posY+","+posZ);
			
			for(int ix = x; ix <= posX; ix++){
				for(int iy = y; iy <= posY; iy++){
					for(int iz = z; iz <= posZ; iz++){
						//System.out.println(x+","+y+","+z);
						if(!(world.getBlock(ix,iy,iz) == getobject && (world.getBlockMetadata(ix,iy,iz) == meta || meta == -1)))
							world.setBlock(ix,iy,iz, Blocks.air);
						
					}
					
				}
				
			}
		}
		
	}
}
