package org.wrapper;

import com.sun.istack.internal.NotNull;
import org.Type;
import org.bytecode.constantpool.ConstantPool;
import org.exception.NotNullException;
import org.exception.TypeErrorException;

import java.lang.reflect.Field;

public class FieldWrapper{

    private final String classInfo;
    private final String fieldName;
    private boolean loaded = false;
    private short fieldInfoIndex;
    private Type type;

    public FieldWrapper(@NotNull Field field) {
        type = Type.getType(field.getType());
        fieldName = field.getName();
        classInfo = Type.getType(field.getDeclaringClass()).getClassInfo();
    }

    public FieldWrapper(@NotNull String classInfo, @NotNull String fieldName, @NotNull Type type) {
        if (type == null) {
            throw new NotNullException("type cannot be null");
        }
        if (type.isMethodType() || type.isVoidType()) {
            throw new TypeErrorException("wrong field type");
        }
        this.type = type;
        this.fieldName = fieldName;
        this.classInfo = classInfo;
    }

    public short load(ConstantPool constantPool){
        if (loaded)
            return fieldInfoIndex;
        fieldInfoIndex = constantPool.putFieldrefInfo(classInfo, fieldName, type.getDescriptor());
        loaded = true;
        return fieldInfoIndex;
    }

    public String getClassName() {
        return classInfo;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Type getType() {
        return type;
    }
}
