package antimattaermod.core.command;

import net.minecraft.block.Block;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 * Created by Raiti on 2016/10/09.
 */
public class Createsphere extends CommandBase {
	
	@Override
	public String getCommandName() {
		return "sphere";
	}
	
	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) {
		return null;
	}
	
	@Override
	public void processCommand(ICommandSender iCommandSender, String[] parm) {
		World world = iCommandSender.getEntityWorld();
		if(parm.length >= 4){
			int x = pointGet(parm[0],iCommandSender,0);
			int y = pointGet(parm[1],iCommandSender,1);
			int z = pointGet(parm[2],iCommandSender,2);
			int r = Integer.parseInt(parm[3]);
			
			System.out.println(x+"."+y+","+z+","+r);
			
			for (int w = 0; w <= r; w++){
				int h = (int)Math.round(Math.sqrt(r*r - w*w));
				for (int hf = 0; hf <= h; hf++){
					world.setBlock(x+w,y,z+hf,Blocks.stone);
					world.setBlock(x+w,y,z-hf,Blocks.stone);
					world.setBlock(x-w,y,z+hf,Blocks.stone);
					world.setBlock(x-w,y,z-hf,Blocks.stone);
					
					
				}
			}
			
			/*for(float l = 0; l < 90 ; l+=0.1){
				int xx = Math.round(MathHelper.cos((float) Math.toRadians(l))*posX);
				int yy = Math.round(MathHelper.sin((float) Math.toRadians(l))*posX);
				for(int h = 0; h < xx ; h++){
					Temp[h][yy] = 1;
					//world.setBlock(x+h,y,z+yy,Blocks.stone);
				}
			}*/
			
			/*
			for(int h = 1 ; h < posX ; h++) {
				for (float l = 0; l < 360; l+=1) {
					world.setBlock(x + (int) (Math.round(Math.cos(Math.toRadians(l)) * h)), y, z + (int) (Math.round(Math.sin(Math.toRadians(l)) * h)), Blocks.stone, 0, 2);
				}
				
			}
			*/
			
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
