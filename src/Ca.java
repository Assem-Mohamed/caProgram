
public class Ca{
    static registerFile registers = new registerFile();  
    Memory memory = new Memory();  
    public void fetch() {

        int address = 0;
        String instruction;

        while(registers.getProgramCounter()<20){
            address = registers.getProgramCounter();
            instruction = memory.read(address);
        
            decode(instruction);

            registers.setProgramCounter(registers.getProgramCounter() + 1);;
        }
    }
    public void decode(String instruction) {
        
        //For the decode method 
        String opcode;  // bits 31:28
        String rd;      // bits 27:23
        String rs;      // bit 22:18
        String rt;      // bits 17:13
        String shamt;   // bits 12:0
        String imm;     // bits 17:0
        String jmpAddress; // bits 27:0
        
        // int valueRS = 0;
        // int valueRT = 0;
        
        // Complete the decode() body...

        opcode = Integer.toBinaryString(Integer.parseInt(instruction.substring(0, 4), 2) & Integer.parseInt("1111", 2));
        opcode = String.format("%4s", opcode).replace(' ', '0');
        rd = Integer.toBinaryString(Integer.parseInt(instruction.substring(4, 9), 2) & Integer.parseInt("11111", 2));
        rd = String.format("%5s", rd).replace(' ', '0');
        rs = Integer.toBinaryString(Integer.parseInt(instruction.substring(9, 14), 2) & Integer.parseInt("11111", 2));
        rs = String.format("%5s", rs).replace(' ', '0');
        rt = Integer.toBinaryString(Integer.parseInt(instruction.substring(14, 19), 2) & Integer.parseInt("11111", 2));
        rt = String.format("%5s", rt).replace(' ', '0');
        shamt = Integer.toBinaryString(Integer.parseInt(instruction.substring(19, 32), 2) & Integer.parseInt("1111111111111", 2));
        shamt = String.format("%13s", shamt).replace(' ', '0');
        imm = Integer.toBinaryString(Integer.parseInt(instruction.substring(14, 32), 2) & Integer.parseInt("111111111111111111", 2));
        imm = String.format("%18s", imm).replace(' ', '0');
        jmpAddress = Integer.toBinaryString(Integer.parseInt(instruction.substring(4, 32), 2) & Integer.parseInt("1111111111111111111111111111", 2));
        jmpAddress = String.format("%28s", jmpAddress).replace(' ', '0');

        if(opcode.equals("0000")){ //ADD
            int rsContent = Integer.parseInt(registers.readRegister(Integer.parseInt(rs, 2)), 2);
            int rtContent = Integer.parseInt(registers.readRegister(Integer.parseInt(rt, 2)), 2);
            int rdContent = rsContent + rtContent;
            registers.writeRegister(Integer.parseInt(rd, 2), String.format("%32s", Integer.toBinaryString(rdContent)).replace(' ', '0'));
        } 
        else if(opcode.equals("0001")){ //SUB
            int rsContent = Integer.parseInt(registers.readRegister(Integer.parseInt(rs, 2)), 2);
            int rtContent = Integer.parseInt(registers.readRegister(Integer.parseInt(rt, 2)), 2);
            int rdContent = rsContent - rtContent;
            registers.writeRegister(Integer.parseInt(rd, 2), String.format("%32s", Integer.toBinaryString(rdContent)).replace(' ', '0'));
        } 
        else if(opcode.equals("0010")){ //MUL
            int rsContent = Integer.parseInt(registers.readRegister(Integer.parseInt(rs, 2)), 2);
            int rtContent = Integer.parseInt(registers.readRegister(Integer.parseInt(rt, 2)), 2);
            int rdContent = rsContent * rtContent;
            registers.writeRegister(Integer.parseInt(rd, 2), String.format("%32s", Integer.toBinaryString(rdContent)).replace(' ', '0'));
        } 
        else if(opcode.equals("0011")){ //MOVI
            registers.writeRegister(Integer.parseInt(rd, 2), String.format("%32s", imm).replace(' ', '0'));
        }
        else if(opcode.equals("0100")){ //JEQ
            int rsContent = Integer.parseInt(registers.readRegister(Integer.parseInt(rs, 2)), 2);
            int rdContent = Integer.parseInt(registers.readRegister(Integer.parseInt(rt, 2)), 2);
            if (rsContent == rdContent){
                registers.setProgramCounter(registers.getProgramCounter() + Integer.parseInt(imm, 2));
            }
        }
        else if(opcode.equals("0101")){ //AND
            int rsContent = Integer.parseInt(registers.readRegister(Integer.parseInt(rs, 2)), 2);
            int rtContent = Integer.parseInt(registers.readRegister(Integer.parseInt(rt, 2)), 2);
            int rdContent = rsContent & rtContent;
            registers.writeRegister(Integer.parseInt(rd, 2), String.format("%32s", Integer.toBinaryString(rdContent)).replace(' ', '0'));
        }
        else if(opcode.equals("0110")){ //XOR
            int rsContent = Integer.parseInt(registers.readRegister(Integer.parseInt(rs, 2)), 2);
            int rdContent = rsContent ^ Integer.parseInt(imm, 2);
            registers.writeRegister(Integer.parseInt(rd, 2), String.format("%32s", Integer.toBinaryString(rdContent)).replace(' ', '0'));
        }
        else if(opcode.equals("0111")){ //JMP
            
        }
        else if(opcode.equals("1000")){ //LSL
            int rsContent = Integer.parseInt(registers.readRegister(Integer.parseInt(rs, 2)), 2);
            int rdContent = rsContent << Integer.parseInt(shamt, 2);
            registers.writeRegister(Integer.parseInt(rd, 2), String.format("%32s", Integer.toBinaryString(rdContent)).replace(' ', '0'));
        }
        else if(opcode.equals("1001")){ //LSR
            int rsContent = Integer.parseInt(registers.readRegister(Integer.parseInt(rs, 2)), 2);
            int rdContent = rsContent >>> Integer.parseInt(shamt, 2);
            registers.writeRegister(Integer.parseInt(rd, 2), String.format("%32s", Integer.toBinaryString(rdContent)).replace(' ', '0'));
        }
        else if(opcode.equals("1010")){ //MOVR
            int rsContent = Integer.parseInt(registers.readRegister(Integer.parseInt(rs, 2)), 2);
            memory.read(rsContent + Integer.parseInt(imm, 2));
            registers.writeRegister(Integer.parseInt(rd, 2), String.format("%32s", memory.read(rsContent + Integer.parseInt(imm, 2))).replace(' ', '0'));
        }
        else if(opcode.equals("1011")){ //MOVM
            int rsContent = Integer.parseInt(registers.readRegister(Integer.parseInt(rs, 2)), 2);
            memory.write(rsContent + Integer.parseInt(imm, 2), registers.readRegister(Integer.parseInt(rs, 2)));
        }
               
        // Printings
        
        System.out.println("Instruction "+registers.getProgramCounter());
        System.out.println("opcode = "+opcode);
        System.out.println("rs = "+rd);
        System.out.println("rt = "+rs);
        System.out.println("rd = "+rt);
        System.out.println("shift amount = "+shamt);
        System.out.println("immediate = "+imm);
        System.out.println("jmpAddress = "+jmpAddress);
        // System.out.println("value[rs] = "+valueRS);
        // System.out.println("value[rt] = "+valueRT);
        System.out.println("----------");
        
        
    }
}
