
public class Ca {
    public static void fetch() {
        
        int instruction = 0;
        
        // Complete the fetch() body...
        while(programCounter<20){
            instruction = Memory.read(programCounter);
        
        decode(instruction);
        
        // Complete the fetch() body...
        programCounter++;
        }
        
    }
    public static void decode(int instruction) {
        
        //For the decode method 
        int opcode = 0;  // bits 31:28
        int rd = 0;      // bits 27:23
        int rs = 0;      // bit 22:18
        int rt = 0;      // bits 17:13
        int shamt = 0;   // bits 12:0
        int imm = 0;     // bits 17:0
        int jmpAddress = 0; // bits 27:0
        
        int valueRS = 0;
        int valueRT = 0;
        
        // Complete the decode() body...
        opcode = (instruction>>28) & 0b1111;
        if (opcode == 0000 || opcode == 0001 || opcode == 0010 || opcode == 0101 || opcode == 1000 || opcode == 1001){
            
        }
        rd = (instruction>>23) & 0b11111;
        rs = (instruction>>17) & 0b11111;
        rt = (instruction>>13) & 0b11111;
        shamt = (instruction) & 0b1111111111111;
        imm = (instruction) & 0b111111111111111111;
        jmpAddress = (instruction) & 0b1111111111111111111111111111;
        
        // valueRS = registerFile[rs];
        // valueRT = registerFile[rt];
        
       
        // Printings
        
        // System.out.println("Instruction "+pc);
        // System.out.println("opcode = "+opcode);
        // System.out.println("rs = "+rs);
        // System.out.println("rt = "+rt);
        // System.out.println("rd = "+rd);
        // System.out.println("shift amount = "+shamt);
        // System.out.println("function = "+funct);
        // System.out.println("immediate = "+imm);
        // System.out.println("address = "+address);
        // System.out.println("value[rs] = "+valueRS);
        // System.out.println("value[rt] = "+valueRT);
        // System.out.println("----------");
        
        
    }
}
