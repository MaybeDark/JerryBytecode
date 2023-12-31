package org.wrapper;

import com.sun.istack.internal.Nullable;
import org.Type;
import org.bytecode.constantpool.ConstantPool;

import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Method;

import static org.bytecode.ConstructorWriter.CONSTRUCTOR_METHODNAME;

public class MethodWrapper {

    private final String className;
    private final String methodName;
    private String methodDesc;
    private Type returnType;
    private Type[] parameterTypes;
    private int parameterCount;
    private int pop;
    private int    put;
    private boolean loaded = false;
    private short  methodInfoIndex;

    public MethodWrapper(Executable method){
//        classInfo = ;
//        if (method instanceof Constructor){
//            methodName = Specification.CONSTRUCTOR_METHODNAME;
//        }else if (method instanceof Method){
//            methodName = method.getName();
//            this.returnType = Type.getType(((Method) method).getReturnType());
//        }else {
//            throw new RuntimeException("Just support JDK1.8");
//        }
//        this.parameterTypes = Type.getType(method.getParameterTypes());
        this(Type.getType(method.getDeclaringClass()).getClassInfo(),
                method instanceof Constructor ? CONSTRUCTOR_METHODNAME : method.getName(),
                method instanceof Constructor ? null : Type.getType(((Method) method).getReturnType()),
                Type.getType(method.getParameterTypes())
        );
    }

    public MethodWrapper(String classInfo, String methodName, @Nullable Type returnType, Type... parameterType) {
        this.className = classInfo;
        this.methodName = methodName;
        this.returnType = returnType;
        parseMethod(returnType, parameterType);
    }


//    public MethodWrapper(Type classType, String methodName, @Nullable Type returnType, Type... parameterType){
//        this(classType.getclassInfo(),methodName,returnType,parameterType);
//    }
//
//    public MethodWrapper(Type classType, String methodName,Type methodType){
//        this(classType.getclassInfo(),methodName,methodType);
//    }
//    public MethodWrapper(String classInfo, String methodName,Type methodType){
//        this.classInfo = classInfo;
//        this.methodName = methodName;
//        if (methodType == null) {
//            parseMethod(null, null);
//            return;
//        }
//        if (!methodType.isMethodType()) {
//            throw new TypeErrorException("type must be is a method type");
//        }
//        parseMethod(methodType);
//    }
//    private void parseMethod(Type methodType){
//        parseMethod(Type.getReturnType(methodType),Type.getArgumentTypes(methodType));
//    }

    private void parseMethod(@Nullable Type returnType,@Nullable Type[] parameterType){
        this.methodDesc =Type.getMethodDescriptor(returnType,parameterType);
        if (returnType != null && !returnType.isVoidType()){
            put = 1;
        }

        if (parameterType != null && parameterType.length != 0) {
            if (parameterType.length == 1 && parameterType[0].equals(Type.VOID)) {
                return;
            }
            parameterCount = parameterType.length;
            pop = parameterCount;
        }
    }

    public short load(ConstantPool constantPool) {
        if (loaded) {
            return methodInfoIndex;
        }
        methodInfoIndex = constantPool.putMethodrefInfo(className, methodName, methodDesc);
        loaded = true;
        return methodInfoIndex;
    }

//    public static MethodWrapper buildConstructor(String classInfo,Type... parameterType){
//        return new MethodWrapper(classInfo,"<init>",Type.VOID,parameterType);
//    }


    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }

    public short getMethodInfoIndex() {
        return methodInfoIndex;
    }

    public String getMethodDesc() {
        return methodDesc;
    }

    public int getParameterCount() {
        return parameterCount;
    }

    public int getPop() {
        return pop;
    }

    public int getPut() {
        return put;
    }

    public Type getReturnType() {
        return returnType;
    }

    public Type[] getParameterTypes() {
        return parameterTypes;
    }

}
