package antimattermod.core.command;

import net.minecraft.block.Block;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
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
		if(strings.length >= 6){
			int x = pointGet(strings[0],iCommandSender,0);
			int y = pointGet(strings[1],iCommandSender,1);
			int z = pointGet(strings[2],iCommandSender,2);
			int posX = pointGet(strings[3],iCommandSender,0);
			int posY = pointGet(strings[4],iCommandSender,1);
			int posZ = pointGet(strings[5],iCommandSender,2);
			Object getobject = strings.length >= 7 ? Block.blockRegistry.getObject(strings[6]):null;
			int meta = strings.length >= 8 ? Integer.parseInt(strings[7]) : -1;
			
			System.out.println(x+"."+y+","+z+"~"+posX+","+posY+","+posZ);
			
			for(int ix = x; ix <= posX; ix++){
				for(int iy = y; iy <= posY; iy++){
					for(int iz = z; iz <= posZ; iz++){
						//System.out.println(x+","+y+","+z);
						if(!(world.getBlock(ix,iy,iz) == getobject && (world.getBlockMetadata(ix,iy,iz) == meta || meta == -1)))
							world.setBlock(ix,iy,iz, Blocks.air,0,2);
						
					}
					
				}
				
			}
		}
		
	}
	
	private int pointGet(String text,ICommandSender sender,int pos){
		
		int point = 0;
		
		switch (pos){
			case 0:
				point = (int)sender.getEntityWorld().getPlayerEntityByName(sender.getCommandSenderName()).posX;
				break;
			case 1:
				point = (int)sender.getEntityWorld().getPlayerEntityByName(sender.getCommandSenderName()).posY;
				break;
			case 2:
				point = (int)sender.getEntityWorld().getPlayerEntityByName(sender.getCommandSenderName()).posZ;
				break;
		}
		
		if (text.equals("~")){
			return point;
		}else if(text.indexOf("~")!=-1){
			return point + Integer.parseInt(text.substring(1));
		}else {
			return Integer.parseInt(text);
		}
		
		
	}
}
