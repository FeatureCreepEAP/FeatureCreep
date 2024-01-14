package featurecreep;

import java.io.IOException;

import javassist.ByteArrayClassPath;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtMethod;
import javassist.NotFoundException;
import net.minecraft.launchwrapper.IClassTransformer;

public class CoreMod implements IClassTransformer {

  @Override
  public byte[] transform(String arg0, String arg1, byte[] arg2) {
    // TODO Auto-generated method stub

    if (arg1.equals("ayu")) {
      return transformbiome(arg0, arg2);
    } if (arg1.equals("ata")) {
        return transformeitem(arg0, arg2);
      }

    return arg2;
  }



private byte[] transformbiome(String arg1, byte[] arg2) {
    // TODO Auto-generated method stub

    ClassPool pool = ClassPool.getDefault();
    pool.insertClassPath(new ByteArrayClassPath(arg1, arg2));
    pool.appendSystemPath();
    CtClass cc;
    try {
      cc = pool.get(arg1);
      CtConstructor c = cc.getConstructors()[0];
      cc.defrost();
      //c.insertAfter("featurecreep.api.orespawn.OrespawnBasicFeatureParser.place(this);");
      c.insertAfter("for (int f = 0; f < featurecreep.api.bg.orespawn.OrespawnBasicFeatureParser.configed.size(); f++) {" +
        "this.a(boq.b.e, (bpn)featurecreep.api.bg.orespawn.OrespawnBasicFeatureParser.configed.get(f));" +
        "}");
      c.insertAfter("System.out.println(\"HI\");");
      System.out.println("JA");
      //    cc.writeFile("net");
      return cc.toBytecode();
    } catch (NotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (CannotCompileException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return arg2;

  }

  private byte[] transformeitem(String arg1, byte[] arg2) {
    // TODO Auto-generated method stub

    ClassPool pool = ClassPool.getDefault();
    pool.insertClassPath(new ByteArrayClassPath(arg1, arg2));
    pool.appendSystemPath();
    CtClass cc;
    try {
      cc = pool.get(arg1);
      cc.defrost();
      CtMethod c = cc.getDeclaredMethod("a", new CtClass[] {
        pool.get("blc")
      });

      //c.insertAfter("featurecreep.api.orespawn.OrespawnBasicFeatureParser.place(this);");
      c.insertBefore("System.out.println(\"Testing\");" +
        "if ($1.c() instanceof featurecreep.api.bg.blocks.FCBlockAPI){return true;}" +
        "");

      System.out.println("JA");
      //    cc.writeFile("net");
      return cc.toBytecode();
    } catch (NotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (CannotCompileException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return arg2;
  }

}