package org.bytecode.attributes.code.bracket;

import org.bytecode.attributes.code.instruction.Instruction;

public class IfBracket extends Bracket {
    private Instruction instruction;
    public IfBracket(int startPc, Instruction instruction) {
        super(startPc);
        this.instruction = instruction;
    }

    public Instruction getInstruction() {
        return instruction;
    }
}
