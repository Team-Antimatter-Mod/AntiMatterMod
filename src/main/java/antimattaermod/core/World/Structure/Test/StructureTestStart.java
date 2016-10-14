package antimattaermod.core.World.Structure.Test;

import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Raiti on 2016/10/12.
 */
@SuppressWarnings("unchecked")
public class StructureTestStart extends StructureStart {
	
	public StructureTestStart(){
		
	}
	
	public StructureTestStart(World world, Random random, int par3, int par4){
		super(par3,par4);
		// 構造物の構成パーツを決定する
		// 基点はComponentSampleDungeon1
		ComponentTest1 componentTest1 = new ComponentTest1(0, random, (par3 << 4)+2, (par4 << 4)+2);
		this.components.add(componentTest1);
		
		//次のパーツ
		componentTest1.buildComponent(componentTest1,this.components,random);
		
		List<StructureComponent> list = componentTest1.structureComponents;
		while (!list.isEmpty()){
			int k = random.nextInt(list.size());
			StructureComponent structureComponent = list.remove(k);
			structureComponent.buildComponent(componentTest1,this.components,random);
		}
		this.updateBoundingBox();
	}
	
	
	
	public class ComponentTest1 extends StructureComponent {
		
		public List<StructureComponent> structureComponents = new ArrayList<>();
		
		public ComponentTest1(){
			
		}
		
		public ComponentTest1(int par1, Random random, int par3, int par4){
			this.coordBaseMode = random.nextInt(4);
			switch(this.coordBaseMode) {
				case 0:
				case 1:
				case 2:
				case 3:
				default:
					// 占有範囲を設定(このサンプルではどの方角を向いてても同じ)
					// (x,y,z) = (par3, 64, par4)の地点から4x10x4ブロックが占有範囲
					this.boundingBox = new StructureBoundingBox(par3, 64, par4, par3 + 4, 74, par4 + 4);
					break;
			}
		}
		
		@Override
		public void buildComponent(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_) {
			// 次のパーツはComponentSampleDungeon2を斜めに接続
			StructureComponent structureComponent = new ComponentTest2(0, p_74861_3_, this.boundingBox.maxX + 1, this.boundingBox.maxZ + 1, 0);
			((ComponentTest1)p_74861_1_).structureComponents.add(structureComponent);
			p_74861_2_.add(structureComponent);
		}
		
		@Override
		protected void func_143012_a(NBTTagCompound p_143012_1_) {
			
		}
		
		@Override
		protected void func_143011_b(NBTTagCompound p_143011_1_) {
			
		}
		
		@Override
		public boolean addComponentParts(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
			if(this.isLiquidInStructureBoundingBox(p_74875_1_,p_74875_3_)) return false;
			
			// 占有範囲(structureboundingbox)内の指定範囲を指定ブロック＆メタデータで埋める
			// 占有範囲内の指定範囲は占有範囲原点を基準として(0,0,0)-(4,10,4)の範囲
			this.fillWithMetadataBlocks(p_74875_1_, p_74875_3_, 0, 0, 0, 4, 10, 4, Blocks.stone,0 ,Blocks.air, 0, false);
			
			// 占有範囲(structureboundingbox)内の指定範囲を空気ブロックで埋める
			// 占有範囲内の指定範囲は占有範囲原点を基準として(1,1,1)-(3,9,3)の範囲
			// 要するに中をくりぬいてるってことです
			this.fillWithAir(p_74875_1_, p_74875_3_, 1, 1, 1, 3, 9, 3);
			
			// 占有範囲(structureboundingbox)内の指定範囲を置き換える
			// 占有範囲内の指定範囲は占有範囲原点を基準として(0,1,2), (0,2,2), (0,3,2)の位置を空気ブロックに置き換えている
			// 入り口っぽく壁に穴を空けている。coordBaseModeでランダムな方向になってることを確認できるようにするためです
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.air, 0, 0, 1, 2, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.air, 0, 0, 2, 2, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.air, 0, 0, 3, 2, p_74875_3_);
			return true;
		}
	}
	
	
	public class ComponentTest2 extends StructureComponent{
		
		public  int color = 0;
		
		public ComponentTest2(){
			
		}
		
		public ComponentTest2(int par1, Random random, int par3, int par4, int par5){
			this.coordBaseMode = random.nextInt(4);
			switch(this.coordBaseMode) {
				case 0:
				case 1:
				case 2:
				case 3:
				default:
					// 占有範囲を設定(このサンプルではどの方角を向いてても同じ)
					// (x,y,z) = (par3, 64, par4)の地点から4x10x4ブロックが占有範囲
					this.boundingBox = new StructureBoundingBox(par3, 64, par4, par3 + 4, 74, par4 + 4);
					break;
			}
			this.color = par5;
		}
		
		@Override
		public void buildComponent(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_) {
			// 構成ブロックである羊毛の色を変えながらパーツをつなげる
			if(this.color < 15) {
				StructureComponent structureComponent1 = new ComponentTest2(0, p_74861_3_, this.boundingBox.maxX + 1, this.boundingBox.maxZ + 1, color + 1);
				((ComponentTest1)p_74861_1_).structureComponents.add(structureComponent1);
				p_74861_2_.add(structureComponent1);
			}
			else {
				// 羊毛の色を変えながらパーツをつなげて、最後の色まで到達したら終端のパーツをつなげる
				// 若干ランダムっぽさを演出するためパーツ3とパーツ4をランダムに選択
				if(p_74861_3_.nextBoolean()) {
					StructureComponent structureComponent = new ComponentTest3(0, p_74861_3_, this.boundingBox.minX, this.boundingBox.maxZ + 1);
					((ComponentTest1)p_74861_1_).structureComponents.add(structureComponent);
					p_74861_2_.add(structureComponent);
				}
				else {
					StructureComponent structureComponent = new ComponentTest4(0, p_74861_3_, this.boundingBox.maxX + 1, this.boundingBox.minZ);
					((ComponentTest1)p_74861_1_).structureComponents.add(structureComponent);
					p_74861_2_.add(structureComponent);
				}
			}
		}
		
		@Override
		protected void func_143012_a(NBTTagCompound p_143012_1_) {
			
		}
		
		@Override
		protected void func_143011_b(NBTTagCompound p_143011_1_) {
			
		}
		
		@Override
		public boolean addComponentParts(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
			if(this.isLiquidInStructureBoundingBox(p_74875_1_, p_74875_3_)) {
				return false;
			}
			
			// 指定の色の羊毛ブロックで範囲を埋める
			this.fillWithMetadataBlocks(p_74875_1_, p_74875_3_, 0, 0, 0, 4, 10, 4, Blocks.wool, this.color, Blocks.air, 0, false);
			
			this.fillWithAir(p_74875_1_, p_74875_3_, 1, 1, 1, 3, 9, 3);
			
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.air, 0, 0, 1, 2, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.air, 0, 0, 2, 2, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.air, 0, 0, 3, 2, p_74875_3_);
			
			return true;
		}
	}
	
	
	public class ComponentTest3 extends StructureComponent {
		
		public ComponentTest3(){
			
		}
		
		public ComponentTest3(int par1, Random random, int par3, int par4){
			this.coordBaseMode = random.nextInt(4);
			switch(this.coordBaseMode) {
				case 0:
				case 1:
				case 2:
				case 3:
				default:
					// 占有範囲を設定(このサンプルではどの方角を向いてても同じ)
					// (x,y,z) = (par3, 64, par4)の地点から4x10x4ブロックが占有範囲
					this.boundingBox = new StructureBoundingBox(par3, 64, par4, par3 + 4, 74, par4 + 4);
					break;
			}
		}
		
		@Override
		protected void func_143012_a(NBTTagCompound p_143012_1_) {
			
		}
		
		@Override
		protected void func_143011_b(NBTTagCompound p_143011_1_) {
			
		}
		
		@Override
		public void buildComponent(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_) {
		}
		
		@Override
		public boolean addComponentParts(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
			if(this.isLiquidInStructureBoundingBox(p_74875_1_, p_74875_3_)) {
				return false;
			}
			
			// 鉄ブロックで範囲を埋める
			this.fillWithMetadataBlocks(p_74875_1_, p_74875_3_, 0, 0, 0, 4, 10, 4, Blocks.iron_block, 0, Blocks.air, 0, false);
			
			this.fillWithAir(p_74875_1_, p_74875_3_, 1, 1, 1, 3, 9, 3);
			
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.air, 0, 0, 1, 2, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.air, 0, 0, 2, 2, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.air, 0, 0, 3, 2, p_74875_3_);
			
			return true;
		}
	}
	
	public class ComponentTest4 extends StructureComponent {
		
		public ComponentTest4(){
			
		}
		
		public ComponentTest4(int par1, Random random, int par3, int par4){
			this.coordBaseMode = random.nextInt(4);
			switch(this.coordBaseMode) {
				case 0:
				case 1:
				case 2:
				case 3:
				default:
					// 占有範囲を設定(このサンプルではどの方角を向いてても同じ)
					// (x,y,z) = (par3, 64, par4)の地点から4x10x4ブロックが占有範囲
					this.boundingBox = new StructureBoundingBox(par3, 64, par4, par3 + 4, 74, par4 + 4);
					break;
			}
		}
		
		@Override
		protected void func_143012_a(NBTTagCompound p_143012_1_) {
			
		}
		
		@Override
		protected void func_143011_b(NBTTagCompound p_143011_1_) {
			
		}
		
		@Override
		public void buildComponent(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_) {
		}
		
		@Override
		public boolean addComponentParts(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
			if(this.isLiquidInStructureBoundingBox(p_74875_1_, p_74875_3_)) {
			return false;
		}
			
			// ダイヤブロックで範囲を埋める
			this.fillWithMetadataBlocks(p_74875_1_, p_74875_3_, 0, 0, 0, 4, 10, 4, Blocks.diamond_block, 0, Blocks.air, 0, false);
			
			this.fillWithAir(p_74875_1_, p_74875_3_, 1, 1, 1, 3, 9, 3);
			
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.air, 0, 0, 1, 2, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.air, 0, 0, 2, 2, p_74875_3_);
			this.placeBlockAtCurrentPosition(p_74875_1_, Blocks.air, 0, 0, 3, 2, p_74875_3_);
			
			return true;
		}
	}
	
}
