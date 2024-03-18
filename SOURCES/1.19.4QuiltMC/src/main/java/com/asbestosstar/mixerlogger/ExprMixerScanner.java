package com.asbestosstar.mixerlogger;

import com.asbestosstar.mixerlogger.MixerLoggerMain.ClassInfo;

import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.FieldAccess;
import javassist.expr.MethodCall;

public class ExprMixerScanner extends ExprEditor {

	public ClassInfo info;

	public ExprMixerScanner(ClassInfo info) {
		this.info = info;
	}

	@Override
	public void edit(MethodCall m) throws CannotCompileException {
		info.method_refs.add(m.getClassName() + "." + m.getMethodName() + m.getSignature());
	}

	@Override
	public void edit(FieldAccess f) throws CannotCompileException {
		info.field_refs.add(f.getClassName() + "." + f.getFieldName() + ":" + f.getSignature());
	}

}
