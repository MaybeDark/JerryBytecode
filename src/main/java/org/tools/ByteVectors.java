package org.tools;

import javax.management.RuntimeErrorException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
public class ByteVectors{
    private static final int SIZE = 128;
    private List<ByteVector> context;
    private ByteVector writePoint;
    private int writeLength = 0;
    private int length = 0;

    public ByteVectors(){
        context = new LinkedList<>();
        writePoint = new ByteVector(SIZE);
        context.add(writePoint);
    }

    public ByteVectors putByte(final int num){
        if (writeLength + 1 > SIZE){
            enlarge();
        }
        writePoint.putByte(num);
        this.length += 1;
        this.writeLength += 1;
        return this;
    }

    public ByteVectors putShort(final int num){
        if (writeLength + 2 > SIZE){
            enlarge();
        }
        writePoint.putShort(num);
        this.length += 2;
        this.writeLength += 2;
        return this;
    }

    public ByteVectors putInt(final int num){
        if (writeLength + 4 > SIZE){
            enlarge();
        }
        writePoint.putInt(num);
        this.length += 4;
        this.writeLength += 4;
        return this;
    }

    public ByteVectors putArray(final byte[] bytes){
        if (bytes == null || bytes.length == 0) {
            return this;
        }
        byte[] data = bytes;
        int arrayLength = bytes.length;
        if (writeLength + arrayLength > SIZE) {
            data = putPartArray(bytes);
            enlarge();
            putArray(data);
        } else {
            writePoint.putArray(data);
            this.length += arrayLength;
            this.writeLength += arrayLength;
        }
        return this;
    }

    public boolean tryRemove(int length) {
        try {
            if (writePoint.remove(length)) {
                this.writeLength -= length;
                this.length -= length;
            }
        } catch (RuntimeErrorException e) {
            return false;
        }
        return true;
    }

    private byte[] putPartArray(final byte[] bytes) {
        byte[] part = new byte[4];
        int i = 0, index = 0;
        for (; i < Math.floorDiv(SIZE - writeLength, 4); i++, index += 4) {
            System.arraycopy(bytes, index, part, 0, 4);
            writePoint.putArray(part);
        }
        writeLength += index;
        length += index;
        int length = bytes.length - index;
        byte[] remain = new byte[length];
        System.arraycopy(bytes, 4 * i, remain, 0, length);
        return remain;
    }

    public void enlarge() {
        writePoint = new ByteVector(SIZE);
        context.add(writePoint);
        writeLength = 0;
    }

    public int getLength() {
        return length;
    }

    public byte[] toByteArray() {
        Iterator<ByteVector> iterator = context.iterator();
        ByteVector all = new ByteVector(length);
        while (iterator.hasNext()) {
            byte[] part = iterator.next().end();
            all.putArray(part);
        }
        return all.end();
    }
}
