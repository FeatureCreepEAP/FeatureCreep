package my.hardbasspawered.bass.transformer;

import my.hardbasspawered.fluffyasm.processor.implementaions.unrestricted.AbstractClassEditor;
import my.hardbasspawered.fluffyasm.processor.transformer.AbstractTransformer;
import my.hardbasspawered.fluffyasm.storage.ClassContentNode;
import my.hardbasspawered.fluffyasm.storage.method.MethodContentNode;
import my.hardbasspawered.fluffyasm.storage.method.UtilityMethodDescriptors;
import my.hardbasspawered.fluffyasm.storage.method.instruction.implementations.MethodInsnInstruction;
import my.hardbasspawered.fluffyasm.storage.read.ReadUtils;
import org.objectweb.asm.Opcodes;

import java.io.IOException;
import java.util.function.Consumer;

public class MainInjector extends AbstractClassEditor {
    private static final MethodContentNode injectMe;

    static {
        try {
            injectMe = ReadUtils.readNode(MainInjectorDonor.class)
                    .findMethod("injectMe", UtilityMethodDescriptors.noParamVoid())
                    .get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public MainInjector(AbstractTransformer last) {
        super(last);
    }

    @Override
    protected void editClass(String s, ClassContentNode classContentNode) {
        classContentNode.findMethod("main", UtilityMethodDescriptors.voidMain()).ifPresent(methodContentNode -> {
            methodContentNode.getInstructions().add(0,
                    new MethodInsnInstruction(
                            Opcodes.INVOKESTATIC,
                            classContentNode.getClassName(),
                            injectMe.getMethodName(),
                            UtilityMethodDescriptors.noParamVoid(),
                            false)
            );
            classContentNode.addMethod(injectMe.asmClone());
        });
    }
}
