package com.asbestosstar.assistremapper;

import com.asbestosstar.assistremapper.remapper.ClassRemapper;

import javassist.CannotCompileException;
import javassist.Modifier;
import javassist.NotFoundException;
import javassist.bytecode.BadBytecode;
import javassist.bytecode.CodeIterator;
import javassist.bytecode.ConstPool;
import javassist.bytecode.MethodInfo;
import javassist.convert.TransformCall;
import javassist.convert.Transformer;

public class MethodTransformer extends TransformCall {

	public ClassRemapper mapper;

	public MethodTransformer(Transformer next, MethodInfo oldMethodName, String substMethod, ClassRemapper mapper)
			throws CannotCompileException, NotFoundException {
		super(next, substMethod, mapper.getMethodFromClass("testMethod", "()",
				mapper.getClassFromPool("donotcreateclazzwithestenombe")));
		// TODO Auto-generated constructor stub
		methodname = oldMethodName.getName();
		methodDescriptor = oldMethodName.getDescriptor();
		classname = newClassname = oldMethodName.getConstPool().getClassName();
		newMethodname = substMethod;
		constPool = null;
		newMethodIsPrivate = Modifier.isPrivate(oldMethodName.getAccessFlags());
		this.mapper = mapper;
	}

	public int transform(int pos, CodeIterator iterator, ConstPool cp) throws BadBytecode {
		int c = iterator.byteAt(pos);
		if (c == INVOKEINTERFACE || c == INVOKESPECIAL || c == INVOKESTATIC || c == INVOKEVIRTUAL) {
			int index = iterator.u16bitAt(pos + 1);
			String cname = cp.eqMember(methodname, methodDescriptor, index);
			if (cname != null && matchClass(cname)) {
				int ntinfo = cp.getMemberNameAndType(index);
				pos = match(c, pos, iterator, cp.getNameAndTypeDescriptor(ntinfo), cp);
			}
		}

		return pos;
	}

	private boolean matchClass(String name) {
		if (classname.equals(name)) {
			return true;
		} else {
			return false;
		}

	}

}
