import java.util.Vector;

public class Ca {
    static registerFile registers = new registerFile();
    Memory memory = new Memory();
    int clk = 1;
    
    // Fetch
    public String fetch() {
        int address = 0;
        String instruction;

        address = registers.getProgramCounter();
        instruction = memory.read(address);
        registers.setProgramCounter(registers.getProgramCounter() + 1);
        return(instruction);
    }
    
    // Decode
    public String[] decode(String instruction) {

        String[] decodedInstruction = new String[32];

        // For the decode method
        String opcode; // bits 31:28
        String rd; // bits 27:23
        String rs; // bit 22:18
        String rt; // bits 17:13
        String shamt; // bits 12:0
        String imm; // bits 17:0
        String jmpAddress; // bits 27:0

        String valueRS;
        String valueRT;
        String valueRD;

        opcode = instruction.substring(0, 4);
        rd = instruction.substring(4, 9);
        rs = instruction.substring(9, 14);
        rt = instruction.substring(14, 19);
        shamt = instruction.substring(19, 32);
        imm = instruction.substring(14, 32);
        jmpAddress = instruction.substring(4, 32);

        valueRS = registers.readRegister(Integer.parseInt(rs, 2));
        valueRT = registers.readRegister(Integer.parseInt(rt, 2));
        valueRD = registers.readRegister(Integer.parseInt(rd, 2));

        decodedInstruction[0] = opcode;
        decodedInstruction[1] = valueRS;
        decodedInstruction[2] = valueRT;
        decodedInstruction[3] = rd; 
        decodedInstruction[4] = shamt;
        decodedInstruction[5] = imm;
        decodedInstruction[6] = jmpAddress;
        decodedInstruction[7] = valueRD;

        // Printings

        // System.out.println("Instruction " + registers.getProgramCounter());
        // System.out.println("opcode = " + opcode);
        // System.out.println("rs = " + rd);
        // System.out.println("rt = " + rs);
        // System.out.println("rd = " + rt);
        // System.out.println("shift amount = " + shamt);
        // System.out.println("immediate = " + imm);
        // System.out.println("jmpAddress = " + jmpAddress);
        // System.out.println("----------");
        
        return decodedInstruction;

    }

    // Execution
    public String[] execute(String[] decodedInstruction){
        String[] executedInstruction = new String[3];
        executedInstruction[1] = decodedInstruction[3];
        executedInstruction[2] = decodedInstruction[7];


        if (decodedInstruction[0].equals("0000")) { // ADD
            int rsContent = Integer.parseInt(decodedInstruction[1], 2);
            int rtContent = Integer.parseInt(decodedInstruction[2], 2);
            int rdContent = rsContent + rtContent;
            executedInstruction[0] = String.format("%32s", Integer.toBinaryString(rdContent)).replace(' ', '0');

        } else if (decodedInstruction[0].equals("0001")) { // SUB
            int rsContent = Integer.parseInt(decodedInstruction[1], 2);
            int rtContent = Integer.parseInt(decodedInstruction[2], 2);
            int rdContent = rsContent - rtContent;
            executedInstruction[0] = String.format("%32s", Integer.toBinaryString(rdContent)).replace(' ', '0');

        } else if (decodedInstruction[0].equals("0010")) { // MUL
            int rsContent = Integer.parseInt(decodedInstruction[1], 2);
            int rtContent = Integer.parseInt(decodedInstruction[2], 2);
            int rdContent = rsContent * rtContent;
            executedInstruction[0] = String.format("%32s", Integer.toBinaryString(rdContent)).replace(' ', '0');

        } else if (decodedInstruction[0].equals("0011")) { // MOVI
            String rdContent = String.format("%32s", decodedInstruction[5]).replace(' ', '0');
            executedInstruction[0] = rdContent;

        } else if (decodedInstruction[0].equals("0100")) { // JEQ
            int rsContent = Integer.parseInt(decodedInstruction[1], 2);
            int rdContent = Integer.parseInt(registers.readRegister(Integer.parseInt(decodedInstruction[3] , 2)), 2);
            if (rsContent == rdContent) {
                registers.setProgramCounter(registers.getProgramCounter() + Integer.parseInt(decodedInstruction[5], 2));
            }

        } else if (decodedInstruction[0].equals("0101")) { // AND
            int rsContent = Integer.parseInt(decodedInstruction[1], 2);
            int rtContent = Integer.parseInt(decodedInstruction[2], 2);
            int rdContent = rsContent & rtContent;
            executedInstruction[0] = String.format("%32s", Integer.toBinaryString(rdContent)).replace(' ', '0');

        } else if (decodedInstruction[0].equals("0110")) { // XORI
            int rsContent = Integer.parseInt(decodedInstruction[1], 2);
            int rdContent = rsContent ^ Integer.parseInt(decodedInstruction[5], 2);
            executedInstruction[0] = String.format("%32s", Integer.toBinaryString(rdContent)).replace(' ', '0');

        } else if (decodedInstruction[0].equals("0111")) { // JMP
            registers.setProgramCounter(Integer
                    .parseInt((Integer.toBinaryString(registers.getProgramCounter()).substring(0, 4) + decodedInstruction[6]), 2));

        } else if (decodedInstruction[0].equals("1000")) { // LSL
            int rsContent = Integer.parseInt(decodedInstruction[1], 2);
            int rdContent = rsContent << Integer.parseInt(decodedInstruction[4], 2);
            executedInstruction[0] = String.format("%32s", Integer.toBinaryString(rdContent)).replace(' ', '0');

        } else if (decodedInstruction[0].equals("1001")) { // LSR
            int rsContent = Integer.parseInt(decodedInstruction[1], 2);
            int rdContent = rsContent >>> Integer.parseInt(decodedInstruction[4], 2);
            executedInstruction[0] = String.format("%32s", Integer.toBinaryString(rdContent)).replace(' ', '0');

        } else if (decodedInstruction[0].equals("1010")) { // MOVR
            int rsContent = Integer.parseInt(decodedInstruction[1], 2);
            String rdContent = memory.read(rsContent + Integer.parseInt(decodedInstruction[5], 2));
            executedInstruction[0] = String.format("%32s", rdContent).replace(' ', '0');

        } else if (decodedInstruction[0].equals("1011")) { // MOVM
            int rsContent = Integer.parseInt(decodedInstruction[1], 2);
            int memAddress = rsContent + Integer.parseInt(decodedInstruction[5], 2);
            executedInstruction[0] = Integer.toBinaryString(memAddress);
        }
        return executedInstruction;
    }
    public void memory(String[] executedInstruction){
        memory.write(Integer.parseInt(executedInstruction[0], 2), executedInstruction[2]);
    }
    public void writeBack(String[] executedInstruction){
        registers.writeRegister(Integer.parseInt(executedInstruction[1], 2), executedInstruction[0]);
    }
    
    // Pipelining
    public void run() throws CaException{
        String fetchedInstruction = "";
        // Vector<String[]> decodedInstuction = new Vector<>();
        String[] decodedInstuction = new String[32];
        String[] decodedInstuctionRun2 = new String[32];
        String[] executedInstrction = new String[32];
        // String[] instruction = new String[32];
        // int decodeCounter = 0;
        // int executeCounter = 0;
        // int noOfDecodedInstruction = 0 ;
        // int noOfExecutedInstruction = 0;
        int totalCycles = (((memory.numberOfInstructions())-1)*2) + 7;
        for(int counter = 0; counter < totalCycles; counter++){
            System.out.println("clock = "+clk);
            System.out.println("n = "+memory.numberOfInstructions());
            System.out.println("total = "+totalCycles);
            System.out.println("counter = "+counter);
            System.out.println("----------");

            // if(clk == 1){
            //     instruction[counter] = fetch();
            // }
            // else if(clk == 2 && clk%2 == 1){
            //     instruction[counter] = fetch();
            //     decode(instruction[decodeCounter]);
            //     noOfDecodedInstruction++;
            // }
            // else if(clk == 3){
            //     instruction[counter] = fetch();
            //     if(noOfDecodedInstruction != 2){
            //     decode(instruction[decodeCounter]);
            //     noOfDecodedInstruction++;
            // }
            //     else{
            //         decodeCounter++;
            //         decode(instruction[decodeCounter]);
            //         noOfDecodedInstruction = 1;
            //     }
            //     execute(instruction[executeCounter]);
            // }            

            if (clk == 1){
                fetchedInstruction = fetch();
                clk++;
            }
            else if (clk == 2){
                decodedInstuction = decode(fetchedInstruction);
                clk++;
            }
            else if (clk == 3){
                decodedInstuction = decode(fetchedInstruction);
                decodedInstuctionRun2 = decode(fetchedInstruction);
                fetchedInstruction = fetch();
                clk++;
            }
            else if (clk == 4){
                executedInstrction = execute(decodedInstuction);
                decodedInstuction = decode(fetchedInstruction);       
                clk++;         
            }
            else if (clk == 5){ 
                executedInstrction = execute(decodedInstuctionRun2);
                decodedInstuction = decode(fetchedInstruction);     
                fetchedInstruction = fetch();
                clk++;
            }
            else if (clk%2 == 0){
                memory(executedInstrction);
                executedInstrction = execute(decodedInstuction);
                decodedInstuction = decode(fetchedInstruction);
                clk++;
            }
            else{
                writeBack(executedInstrction);
                executedInstrction = execute(decodedInstuction);
                decodedInstuction = decode(fetchedInstruction);
                fetchedInstruction = fetch();
                clk++;
            }
        }
    }
}
