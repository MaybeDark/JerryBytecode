package org.bytecode.method.attribute.code.attrubute.stackmaptable.verificationtypeinfo;

public class UninitializedThisVariableInfo implements VariableInfo {
    public static final byte tag = 6;

    @Override
    public byte[] toByteArray() {
        return new byte[]{tag};
    }
}