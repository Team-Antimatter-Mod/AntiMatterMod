package antimattermod.core.ASM;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.Opcodes;

/**
 * @author C6H2Cl2
 */
public class AMMCoreTransformer implements IClassTransformer,Opcodes {
    @Override
    public byte[] transform(String s, String s1, byte[] bytes) {
        return new byte[0];
    }
}
