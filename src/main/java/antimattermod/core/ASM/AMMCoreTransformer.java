package antimattermod.core.ASM;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;
import cpw.mods.fml.relauncher.FMLLaunchHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.launchwrapper.IClassTransformer;
import org.apache.logging.log4j.Level;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodNode;

/**
 * @author C6H2Cl2
 */
public class AMMCoreTransformer implements IClassTransformer,Opcodes {

    private static final String TARGET_CLASS = "net.minecraft.world.WorldServer";

    @Override
    public byte[] transform(String name, String unTransformedName, byte[] basicClass) {
        System.out.println(name);
        //FMLLaunchHandler.side() != Side.SERVER ||
        if(!name.equals(TARGET_CLASS)){
            return basicClass;
        }
        byte[] classChanged = hookWorldTick(name, unTransformedName, basicClass);
        return classChanged;
    }

    private byte[] hookWorldTick(String name, String unTransformedName, byte[] basicClass){
        ClassNode classNode = new ClassNode();
        ClassReader classReader = new ClassReader(basicClass);
        classReader.accept(classNode,0);

        String targetMethodName = "tick";
        String targetMethoddesc = "()V";

        FMLLog.log(Level.DEBUG,"Class WorldServer was found!");

        MethodNode methodNode = null;
        for (MethodNode currentMethodNode : classNode.methods){
            if (targetMethodName.equals(currentMethodNode.name) || targetMethodName.equals(FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(name,targetMethodName,targetMethoddesc)) || targetMethodName.equals(FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(unTransformedName,targetMethodName,targetMethoddesc))){
                methodNode = currentMethodNode;
                break;
            }
        }
        if(methodNode != null){
            InsnList overrideList = new InsnList();
            //テスト用のログ
            FMLLog.log(Level.DEBUG,"Method was found!");

            //メソッドコールの追加などをここで行う
            //Modding Wikiのコピペ
            /*
            overrideList.add(new VarInsnNode(ALOAD, 1));
            overrideList.add(new VarInsnNode(DLOAD, 2));
            overrideList.add(new VarInsnNode(DLOAD, 4));
            overrideList.add(new VarInsnNode(DLOAD, 6));
            overrideList
                    .add(new MethodInsnNode(INVOKESTATIC, "tutorial/test", "passTestRender", "(LEntityLiving;DDD)V"));
                    */
            // mnode.instructions.get(1)で、対象のメソッドの先頭を取得
            // mnode.instructions.insertで、指定した位置にバイトコードを挿入します。
            //mnode.instructions.insert(mnode.instructions.get(1), overrideList);
            // 改変したクラスファイルをバイト列に書き出します
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
            classNode.accept(cw);
            return cw.toByteArray();
        }
        return basicClass;
    }
}
