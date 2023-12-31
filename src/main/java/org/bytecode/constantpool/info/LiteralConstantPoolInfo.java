package org.bytecode.constantpool.info;


import org.bytecode.constantpool.ConstantPoolTag;

/**
 * @author 12923
 * @date 2023/10/21
 */
public abstract class LiteralConstantPoolInfo<T> extends AbsConstantPoolInfo{

    protected T literal;
    protected byte[] literalBytes;

    public LiteralConstantPoolInfo(ConstantPoolTag tag,T literal, byte[] literalBytes) {
        if (!tag.isTiteralConstantPoolInfo()){
            throw new RuntimeException("This tag does not belong to LiteralConstantPoolInfo");
        }
        if ((literalBytes.length > (1 << 16) - 1)){
            throw new RuntimeException("Literal too long");
        }
        this.tag = tag;
        this.literalBytes = literalBytes;
        this.literal = literal;
        setValue(literalBytes);
    }

    public T getLiteral(){
        return literal;
    }

    public String literalToString() {
        return String.valueOf(literal);
    }
}
