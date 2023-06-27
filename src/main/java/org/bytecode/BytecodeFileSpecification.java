package org.bytecode;

public interface BytecodeFileSpecification {
    byte[] CLASS_FILE_HEAD = new byte[]{-54,-2,-70,-66};
    int CLASS_FILE_HEAD_LENGTH = 4;
    int MINOR_VERSION_LENGTH = 2;
    int MAJOR_VERSION_LENGTH = 2;

    int CONSTANT_POOL_COUNT_LENGTH = 2;
    int CONSTANT_POOL_INFO_TYPETAG_LENGTH = 1;
    int CONSTANT_POOL_INFO_INDEX_LENGTH = 2;
    int CONSTANT_POOL_CLASS_INFO_LENGTH = CONSTANT_POOL_INFO_TYPETAG_LENGTH + CONSTANT_POOL_INFO_INDEX_LENGTH;
    int CONSTANT_POOL_DOUBLE_INFO_LENGTH = CONSTANT_POOL_INFO_TYPETAG_LENGTH + 8;
    int CONSTANT_POOL_FIELDREF_INFO_LENGTH = CONSTANT_POOL_INFO_TYPETAG_LENGTH + CONSTANT_POOL_INFO_INDEX_LENGTH * 2;
    int CONSTANT_POOL_FLOAT_INFO_LENGTH = CONSTANT_POOL_INFO_TYPETAG_LENGTH + 4;
    int CONSTANT_POOL_INTEGER_INFO_LENGTH = CONSTANT_POOL_INFO_TYPETAG_LENGTH + 4;
    int CONSTANT_POOL_INTERFACEMETHODREF_INFO_LENGTH = CONSTANT_POOL_INFO_TYPETAG_LENGTH + CONSTANT_POOL_INFO_INDEX_LENGTH * 2;
    int BOOTSTRAPMETHOD_INDEX_LENGTH = 2;
    int CONSTANT_POOL_INVOKEDYNAMIC_INFO_LENGTH = BOOTSTRAPMETHOD_INDEX_LENGTH + CONSTANT_POOL_INFO_INDEX_LENGTH;
    int CONSTANT_POOL_LONG_INFO_LENGTH = CONSTANT_POOL_INFO_TYPETAG_LENGTH + 8;
    int CONSTANT_POOL_METHODHANDLE_TYPETAG_LENGTH = 1;
    int CONSTANT_POOL_METHODHANDLE_INFO_LENGTH = CONSTANT_POOL_METHODHANDLE_TYPETAG_LENGTH + BOOTSTRAPMETHOD_INDEX_LENGTH;
    int CONSTANT_POOL_METHODREF_INFO_LENGTH = CONSTANT_POOL_INFO_TYPETAG_LENGTH + CONSTANT_POOL_INFO_INDEX_LENGTH * 2;
    int CONSTANT_POOL_METHODTYPE_INFO_LENGTH = CONSTANT_POOL_INFO_TYPETAG_LENGTH + CONSTANT_POOL_INFO_INDEX_LENGTH;
    int CONSTANT_POOL_NAMEANDTYPE_INFO_LENGTH = CONSTANT_POOL_INFO_TYPETAG_LENGTH + CONSTANT_POOL_INFO_INDEX_LENGTH * 2;
    int CONSTANT_POOL_STRING_INFO_LENGTH = CONSTANT_POOL_INFO_TYPETAG_LENGTH + CONSTANT_POOL_INFO_INDEX_LENGTH;

    int CLASS_ACC_MARK_LENGTH = 2;
    int CLASS_NAME_LENGTH = CONSTANT_POOL_INFO_INDEX_LENGTH;
    int CLASS_SUPERCLASS_NAME_LENGTH = CONSTANT_POOL_INFO_INDEX_LENGTH;
    int CLASS_INTERFACE_COUNT_LENGTH = 2;

    int CLASS_FIELD_COUNT_LENGTH = 2;
}