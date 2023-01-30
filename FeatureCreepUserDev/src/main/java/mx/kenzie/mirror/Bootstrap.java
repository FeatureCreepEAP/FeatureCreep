package mx.kenzie.mirror;

import java.lang.invoke.*;

import org.objectweb.asm.Type;

public final class Bootstrap {
    
    public static CallSite bootstrap(MethodHandles.Lookup caller, String name, MethodType type, Class<?> owner) throws Exception {
        final MethodHandle handle = caller.in(owner).findStatic(owner, name, type);
        return new ConstantCallSite(handle);
    }
    
    public static CallSite bootstrapPrivate(MethodHandles.Lookup caller, String name, MethodType type, Class<?> owner) throws Exception {
        final MethodHandle handle = caller.in(owner).findStatic(owner, name, type); //MethodHandles.privateLookupIn(owner, caller).findStatic(owner, name, type);
        return new ConstantCallSite(handle);
    }
    
    public static CallSite bootstrapDynamic(MethodHandles.Lookup caller, String name, MethodType type, Class<?> owner) throws Exception {
        final MethodHandle handle;
        final MethodType end = type.dropParameterTypes(0, 1);
        handle = caller.in(owner).findVirtual(owner, name, end);
        return new ConstantCallSite(handle);
    }
    
    public static CallSite bootstrapPrivateDynamic(MethodHandles.Lookup caller, String name, MethodType type, Class<?> owner) throws Exception {
        final MethodHandle handle;
        final MethodType end = type.dropParameterTypes(0, 1);
        handle =  caller.in(owner).findVirtual(owner, name, end);//MethodHandles.privateLookupIn(owner, caller).findVirtual(owner, name, end);
        return new ConstantCallSite(handle);
    }
    
    public static CallSite bootstrapPrivateStaticFieldSetter(MethodHandles.Lookup caller, String name, MethodType type, Class<?> owner)
        throws Exception {
        final MethodHandle handle = caller.in(owner).findStaticSetter(owner, name, type.parameterType(0));
        return new ConstantCallSite(handle);//MethodHandles.privateLookupIn(owner, caller)
    }
    
    public static CallSite bootstrapPrivateStaticFieldGetter(MethodHandles.Lookup caller, String name, MethodType type, Class<?> owner)
        throws Exception {
        final MethodHandle handle = caller.in(owner)
            .findStaticGetter(owner, name, type.returnType());
        return new ConstantCallSite(handle);
    }
    
    public static CallSite bootstrapFieldSetter(MethodHandles.Lookup caller, String name, MethodType type, Class<?> owner) throws Exception {
        final MethodHandle handle = caller.in(owner).findStaticSetter(owner, name, type.parameterType(0));
        return new ConstantCallSite(handle);
    }
    
    public static CallSite bootstrapFieldGetter(MethodHandles.Lookup caller, String name, MethodType type, Class<?> owner) throws Exception {
        final MethodHandle handle = caller.in(owner).findStaticGetter(owner, name, type.returnType());
        return new ConstantCallSite(handle);
    }
    
    public static CallSite bootstrapPrivateFieldSetter(MethodHandles.Lookup caller, String name, MethodType type, Class<?> owner)
        throws Exception {
        final MethodHandle handle = caller.in(owner)
            .findSetter(owner, name, type.parameterType(1));
        return new ConstantCallSite(handle);
    }
    
    public static CallSite bootstrapPrivateFieldGetter(MethodHandles.Lookup caller, String name, MethodType type, Class<?> owner)
        throws Exception {
        final MethodHandle handle = caller.in(owner).findGetter(owner, name, type.returnType());
        return new ConstantCallSite(handle);
    }
    
    public static CallSite bootstrapDynamicConstructor(MethodHandles.Lookup caller, String name, MethodType type, Class<?> owner) throws Exception {
        final MethodHandle handle;
        final MethodType end = type.changeReturnType(void.class);
        handle = caller.in(owner).findConstructor(owner, end);
        return new ConstantCallSite(handle);
    }
    
    public static CallSite bootstrapPrivateDynamicConstructor(MethodHandles.Lookup caller, String name, MethodType type, Class<?> owner) throws Exception {
        final MethodHandle handle;
        final MethodType end = type.changeReturnType(void.class);
        handle = caller.findConstructor(owner, end);
        return new ConstantCallSite(handle);
    }
    
}
