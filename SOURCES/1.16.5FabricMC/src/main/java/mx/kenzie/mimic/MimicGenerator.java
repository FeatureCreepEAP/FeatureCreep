package mx.kenzie.mimic;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

public class MimicGenerator {
    
    private static volatile int counter;
    protected final ClassWriter writer;
    protected final String internal;
    protected final Class<?> top;
    protected final Class<?>[] interfaces;
    protected final List<MethodErasure> finished;
    protected final MethodWriter handler;
    protected final Map<MethodErasure, MethodWriter> overrides = new HashMap<>();
    protected final Map<FieldErasure, Object> fields = new HashMap<>();
    protected ClassDefiner definer = new InternalAccess();
    protected int index;
    
    protected MimicGenerator(String location, Class<?> top, Class<?>... interfaces) {
        this(new MethodWriter(), location, top, interfaces);
    }
    
    protected MimicGenerator(MethodWriter handler, String location, Class<?> top, Class<?>... interfaces) {
        this.handler = handler;
        this.writer = new ClassWriter(0);
        this.internal = location;
        this.top = top;
        this.interfaces = interfaces;
        this.finished = new ArrayList<>();
    }
    
    static synchronized int count() {
        return counter++;
    }
    
    @SuppressWarnings("unchecked")
    public <Template> Template create(ClassLoader loader, MethodExecutor executor) {
        final boolean complex = !top.isInterface() || !overrides.isEmpty() || !fields.isEmpty();
        final byte[] bytecode = writeCode();
        final Class<?> type = definer.define(loader, internal.replace('/', '.'), bytecode);
        assert type != null;
        final Object object = this.allocateInstance(type);
        if (complex) {
            for (Map.Entry<FieldErasure, Object> entry : fields.entrySet()) {
                this.setField(object, entry.getKey().name(), entry.getValue());
            }
            this.setField(object, "executor", executor);
            this.setField(object, "methods", finished.toArray(new MethodErasure[0]));
        } else { // trivial
            this.putValue(object, 12, executor);
            this.putValue(object, 16, finished.toArray(new MethodErasure[0]));
        }
        return (Template) object;
    }
    
    protected byte[] writeCode() {
        this.writer.visit(61, 1 | 16 | 32, internal, null, Type.getInternalName(top != null && !top.isInterface() ? top : Object.class), this.getInterfaces());
        this.writer.visitField(0x00000080 | 0x00000004, "executor", "Lmx/kenzie/mimic/MethodExecutor;", null, null)
            .visitEnd();
        this.writer.visitField(0x00000080 | 0x00000004, "methods", "[Lmx/kenzie/mimic/MethodErasure;", null, null)
            .visitEnd();
        for (final FieldErasure erasure : fields.keySet()) {
            this.writer.visitField(0, erasure.name(), Type.getDescriptor(erasure.type()), null, null).visitEnd();
        }
        if (top != null && top != Object.class) this.scrapeMethods(top);
        for (final Class<?> template : interfaces) this.scrapeMethods(template);
        this.writer.visitEnd();
        return writer.toByteArray();
    }
    
    protected Object allocateInstance(Class<?> type) {
        return InternalAccess.allocateInstance(type);
    }
    
    private void setField(Object owner, String name, Object value) {
        final Field field = this.getField(owner.getClass(), name);
        if (field == null) return;
        final long offset = this.offset(this.getField(owner.getClass(), name));
        this.putValue(owner, offset, value);
    }
    
    protected void putValue(final Object object, final long offset, final Object value) {
        InternalAccess.put(object, offset, value);
    }
    
    protected String[] getInterfaces() {
        final Set<String> strings = new HashSet<>();
        if (top.isInterface()) strings.add(Type.getInternalName(top));
        for (final Class<?> type : interfaces) strings.add(Type.getInternalName(type));
        return strings.toArray(new String[0]);
    }
    
    protected void scrapeMethods(Class<?> template) {
        for (final Method method : template.getMethods()) {
            if (Modifier.isStatic(method.getModifiers())) continue;
            if (Modifier.isFinal(method.getModifiers())) continue;
            if (Modifier.isPrivate(method.getModifiers())) continue;
            final MethodErasure erasure = new MethodErasure(method);
            if (finished.contains(erasure)) continue;
            this.finished.add(erasure);
            this.writeCaller(method);
            this.index++;
        }
    }
    
    private Field getField(Class<?> owner, String name) {
        try {
            return owner.getDeclaredField(name);
        } catch (NoSuchFieldException | NullPointerException ex) {
            return null;
        }
    }
    
    protected long offset(final Field field) {
        return InternalAccess.offset(field);
    }
    
    protected void writeCaller(Method method) {
        final MethodErasure erasure = new MethodErasure(method);
        final MethodWriter writer;
        if (overrides.containsKey(erasure)) writer = this.overrides.get(erasure);
        else writer = this.handler;
        writer.write(this, method, this.writer, internal, index);
    }
    
    
    
    

    
    //This is all re-added by FeatureCreep It was removed in Mimic 1.10, but mirror still needes it so i put it here so it can be used i also readded box and a few others
    
    

     protected int instructionOffset(Class<?> type) {
        if (type == Integer.TYPE) {
           return 1;
        } else if (type == Boolean.TYPE) {
           return 1;
        } else if (type == Byte.TYPE) {
           return 1;
        } else if (type == Short.TYPE) {
           return 1;
        } else if (type == Long.TYPE) {
           return 2;
        } else if (type == Float.TYPE) {
           return 3;
        } else if (type == Double.TYPE) {
           return 4;
        } else {
           return type == Void.TYPE ? 6 : 5;
        }
     }

     protected void box(MethodVisitor visitor, Class<?> value) {
        if (value == Byte.TYPE) {
           visitor.visitMethodInsn(184, Type.getInternalName(Byte.class), "valueOf", "(B)Ljava/lang/Byte;", false);
        }

        if (value == Short.TYPE) {
           visitor.visitMethodInsn(184, Type.getInternalName(Short.class), "valueOf", "(S)Ljava/lang/Short;", false);
        }

        if (value == Integer.TYPE) {
           visitor.visitMethodInsn(184, Type.getInternalName(Integer.class), "valueOf", "(I)Ljava/lang/Integer;", false);
        }

        if (value == Long.TYPE) {
           visitor.visitMethodInsn(184, Type.getInternalName(Long.class), "valueOf", "(J)Ljava/lang/Long;", false);
        }

        if (value == Float.TYPE) {
           visitor.visitMethodInsn(184, Type.getInternalName(Float.class), "valueOf", "(F)Ljava/lang/Float;", false);
        }

        if (value == Double.TYPE) {
           visitor.visitMethodInsn(184, Type.getInternalName(Double.class), "valueOf", "(D)Ljava/lang/Double;", false);
        }

        if (value == Boolean.TYPE) {
           visitor.visitMethodInsn(184, Type.getInternalName(Boolean.class), "valueOf", "(Z)Ljava/lang/Boolean;", false);
        }

        if (value == Void.TYPE) {
           visitor.visitInsn(1);
        }

     }

     protected int wideIndexOffset(Class<?> thing) {
        return thing != Long.TYPE && thing != Double.TYPE ? 0 : 1;
     }

     protected Class<?> getWrapperType(Class<?> primitive) {
        if (primitive == Byte.TYPE) {
           return Byte.class;
        } else if (primitive == Short.TYPE) {
           return Short.class;
        } else if (primitive == Integer.TYPE) {
           return Integer.class;
        } else if (primitive == Long.TYPE) {
           return Long.class;
        } else if (primitive == Float.TYPE) {
           return Float.class;
        } else if (primitive == Double.TYPE) {
           return Double.class;
        } else if (primitive == Boolean.TYPE) {
           return Boolean.class;
        } else {
           return primitive == Void.TYPE ? Void.class : primitive;
        }
     }

     protected void unbox(MethodVisitor visitor, Class<?> parameter) {
        if (parameter == Byte.TYPE) {
           visitor.visitMethodInsn(182, Type.getInternalName(Byte.class), "byteValue", "()B", false);
        }

        if (parameter == Short.TYPE) {
           visitor.visitMethodInsn(182, Type.getInternalName(Short.class), "shortValue", "()S", false);
        }

        if (parameter == Integer.TYPE) {
           visitor.visitMethodInsn(182, Type.getInternalName(Integer.class), "intValue", "()I", false);
        }

        if (parameter == Long.TYPE) {
           visitor.visitMethodInsn(182, Type.getInternalName(Long.class), "longValue", "()J", false);
        }

        if (parameter == Float.TYPE) {
           visitor.visitMethodInsn(182, Type.getInternalName(Float.class), "floatValue", "()F", false);
        }

        if (parameter == Double.TYPE) {
           visitor.visitMethodInsn(182, Type.getInternalName(Double.class), "doubleValue", "()D", false);
        }

        if (parameter == Boolean.TYPE) {
           visitor.visitMethodInsn(182, Type.getInternalName(Boolean.class), "booleanValue", "()Z", false);
        }

     }

     protected int wideIndexOffset(Class<?>[] params, Class<?> ret) {
        int i = 0;
        Class[] var4 = params;
        int var5 = params.length;

        for(int var6 = 0; var6 < var5; ++var6) {
           Class<?> param = var4[var6];
           i += this.wideIndexOffset(param);
        }

        return Math.max(i, this.wideIndexOffset(ret));
     }

     protected void doTypeConversion(MethodVisitor visitor, Class<?> from, Class<?> to) {
        if (from != to) {
           if (from != Void.TYPE && to != Void.TYPE) {
              if (from.isPrimitive() && to.isPrimitive()) {
                 short opcode;
                 if (from == Float.TYPE) {
                    if (to == Double.TYPE) {
                       opcode = 141;
                    } else if (to == Long.TYPE) {
                       opcode = 140;
                    } else {
                       opcode = 139;
                    }
                 } else if (from == Double.TYPE) {
                    if (to == Float.TYPE) {
                       opcode = 144;
                    } else if (to == Long.TYPE) {
                       opcode = 143;
                    } else {
                       opcode = 142;
                    }
                 } else if (from == Long.TYPE) {
                    if (to == Float.TYPE) {
                       opcode = 137;
                    } else if (to == Double.TYPE) {
                       opcode = 138;
                    } else {
                       opcode = 136;
                    }
                 } else if (to == Float.TYPE) {
                    opcode = 134;
                 } else if (to == Double.TYPE) {
                    opcode = 135;
                 } else if (to == Byte.TYPE) {
                    opcode = 145;
                 } else if (to == Short.TYPE) {
                    opcode = 147;
                 } else if (to == Character.TYPE) {
                    opcode = 146;
                 } else {
                    opcode = 133;
                 }

                 visitor.visitInsn(opcode);
              } else {
                 if (from.isPrimitive() ^ to.isPrimitive()) {
                    String var10002 = from.getSimpleName();
                    throw new IllegalArgumentException("Type wrapping is currently unsupported due to side-effects: '" + var10002 + "' -> '" + to.getSimpleName() + "'");
                 }

                 visitor.visitTypeInsn(192, Type.getInternalName(to));
              }

           }
        }
     }
     
    
    
}
