package asbestosstar.bootstrap.minecraft;

import java.nio.ByteBuffer;
import java.security.ProtectionDomain;

import org.jboss.modules.ClassTransformer;
import org.objectweb.asm.*;

public class GameInjections implements ClassTransformer {

	public static ByteBuffer inject(String name, ByteBuffer buffer) {

		if ("net/minecraft/server/packs/repository/PackRepository".equals(name)) {
			System.out.println("Adding Pack Repo");
			return transformPackRepository(buffer);
		}

		if ("net/minecraft/core/registries/BuiltInRegistries".equals(name)) {
			System.out.println("Adding Registries");
			return transformBuiltInRegistries(buffer);
		}

		return buffer;
	}

	private static ByteBuffer transformPackRepository(ByteBuffer basicClass) {
		try {
			byte[] originalBytes = new byte[basicClass.remaining()];
			basicClass.get(originalBytes);

			ClassReader reader = new ClassReader(originalBytes);
			ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);

			ClassVisitor visitor = new ClassVisitor(Opcodes.ASM9, writer) {

				@Override
				public MethodVisitor visitMethod(int access, String name, String descriptor, String signature,
						String[] exceptions) {

					MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);

					if ("reload".equals(name) && "()V".equals(descriptor)) {

						return new MethodVisitor(Opcodes.ASM9, mv) {

							@Override
							public void visitCode() {
								super.visitCode();

								// this.addPackFinder(FCPackLoad.INSTANCE);

								// aload_0
								mv.visitVarInsn(Opcodes.ALOAD, 0);

								// getstatic FCPackLoad.INSTANCE
								mv.visitFieldInsn(Opcodes.GETSTATIC, "featurecreep/api/bg/FCPackLoad", "INSTANCE",
										"Lnet/minecraft/server/packs/repository/RepositorySource;");

								// invokevirtual PackRepository.addPackFinder
								mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
										"net/minecraft/server/packs/repository/PackRepository", "addPackFinder",
										"(Lnet/minecraft/server/packs/repository/RepositorySource;)V", false);

								// System.out.println("Injected FCPack into PackRepository");

								mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out",
										"Ljava/io/PrintStream;");

								mv.visitLdcInsn("Injected FCPack into PackRepository");

								mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println",
										"(Ljava/lang/String;)V", false);
							}
						};
					}

					return mv;
				}
			};

			reader.accept(visitor, ClassReader.EXPAND_FRAMES);
			byte[] transformed = writer.toByteArray();

			return ByteBuffer.wrap(transformed);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return basicClass;
	}

	private static ByteBuffer transformBuiltInRegistries(ByteBuffer buffer) {
		byte[] originalBytes = new byte[buffer.remaining()];
		buffer.get(originalBytes);

		ClassReader reader = new ClassReader(originalBytes);
		ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);

		ClassVisitor visitor = new ClassVisitor(Opcodes.ASM9, writer) {

			@Override
			public MethodVisitor visitMethod(int access, String name, String descriptor, String signature,
					String[] exceptions) {

				MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);

				if ("bootStrap".equals(name) && "()V".equals(descriptor)) {

					return new MethodVisitor(Opcodes.ASM9, mv) {

						@Override
						public void visitMethodInsn(int opcode, String owner, String methodName, String methodDesc,
								boolean isInterface) {

							super.visitMethodInsn(opcode, owner, methodName, methodDesc, isInterface);

							// After BuiltInRegistries.createContents()
							if (opcode == Opcodes.INVOKESTATIC
									&& owner.equals("net/minecraft/core/registries/BuiltInRegistries")
									&& methodName.equals("createContents") && methodDesc.equals("()V")) {

								// Inject:
								// FeatureCreepMC.init();

								mv.visitMethodInsn(Opcodes.INVOKESTATIC, "featurecreep/api/bg/mc/FeatureCreepMC",
										"init", "()V", false);
							}
						}
					};
				}

				return mv;
			}
		};

		reader.accept(visitor, ClassReader.EXPAND_FRAMES);
		return ByteBuffer.wrap(writer.toByteArray());
	}

	@Override
	public ByteBuffer transform(ClassLoader loader, String className, ProtectionDomain protectionDomain,
			ByteBuffer classBytes) throws IllegalArgumentException {
		return inject(className, classBytes);
	}
}