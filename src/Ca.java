// import java.util.Vector;

public class Ca {

    RegisterFile registerFile;
    int pcRegister;
    Memory memory;
    static int instructionCounter=1;

    public Ca(String filename) throws Exception {
        registerFile = new RegisterFile();
        pcRegister = 0;
        memory = new Memory(filename);
    }
    // Fetch
    public int fetch() {

        int instruction = memory.instructionMemory.get(pcRegister);
        pcRegister++;
        return instruction;

    }
    // Decode
    public Instruction decode(int instructionValue) {

        int opcode = (instructionValue) >> 28;
        if(opcode < 0){
            opcode = opcode & 0b00000000000000000000000000001111;
        }
        int r1 = (instructionValue & 0b00001111100000000000000000000000) >> 23;
        int r2 = (instructionValue & 0b00000000011111000000000000000000) >> 18;
        int r3 = (instructionValue & 0b00000000000000111110000000000000) >> 13;
        int shamt = (instructionValue & 0b00000000000000000001111111111111);
        int immediate = (instructionValue & 0b00000000000000111111111111111111);
        int address = (instructionValue & 0b00001111111111111111111111111111);

        int valueR1 = registerFile.registers.get(r1).value;
        int valueR2 = registerFile.registers.get(r2).value;
        int valueR3 = registerFile.registers.get(r3).value;

        Character type;

        switch (opcode) {
            case 0:
                type = 'R';
                break;
            case 1:
                type = 'R';
                break;
            case 2:
                type = 'R';
                break;
            case 3:
                type = 'I';
                break;
            case 4:
                type = 'I';
                break;
            case 5:
                type = 'R';
                break;
            case 6:
                type = 'I';
                break;
            case 7:
                type = 'J';
                break;
            case 8:
                type = 'R';
                break;
            case 9:
                type = 'R';
                break;
            case 10:
                type = 'I';
                break;
            case 11:
                type = 'I';
                break;
            default:
                type = null;
        }

        String statement = "";
        switch (opcode){
            case 0: statement = "Instruction: ADD R" + r1 + " R" + r2 + " R" + r3; break;
            case 1: statement = "Instruction: SUB R" + r1 + " R" + r2 + " R" + r3; break;
            case 2: statement = "Instruction: MUL R" + r1 + " R" + r2 + " R" + r3; break;
            case 3: statement = "Instruction: MOVI R" + r1 + " " + r2 + " " + immediate; break;
            case 4: statement = "Instruction: JEQ R" + r1 + " R" + r2 + " " + immediate; break;
            case 5: statement = "Instruction: AND R" + r1 + " R" + r2 + " R" + r3; break;
            case 6: statement = "Instruction: XORI R" + r1 + " R" + r2 + " " + immediate; break;
            case 7: statement = "Instruction: JMP " + address; break;
            case 8: statement = "Instruction: LSL R" + r1 + " R" + r2 + " " + r3 + shamt; break;
            case 9: statement = "Instruction: LSR R" + r1 + " R" + r2 + " " + r3 + shamt; break;
            case 10: statement = "Instruction: MOVR R" + r1 + " R" + r2 + " " + immediate; break;
            case 11: statement = "Instruction: MOVM R" + r1 + " R" + r2 + " " + immediate; break;
            default: break;
        }

        return new Instruction(pcRegister, opcode,r1,r2,r3,shamt,immediate,address,valueR1,valueR2,valueR3,type,statement);

    }

    // Execution
    public void execute(Instruction instruction){

        if(instruction.type.equals('R')){
            switch (instruction.opcode){
                case 0: instruction.valueR1 = instruction.valueR2 + instruction.valueR3; break;
                case 1: instruction.valueR1 = instruction.valueR2 - instruction.valueR3; break;
                case 2: instruction.valueR1 = instruction.valueR2 * instruction.valueR3; break;
                case 5: instruction.valueR1 = instruction.valueR2 & instruction.valueR3; break;
                case 8: instruction.valueR1 = instruction.valueR2 << instruction.shamt; break;
                case 9: instruction.valueR1 = instruction.valueR2 >>> instruction.shamt; break;
                default: break;
            }
        }
        else if(instruction.type.equals('I')){
            switch (instruction.opcode){
                case 3: instruction.valueR1 = instruction.immediate; break;
                case 4:
                    if(instruction.valueR1 == instruction.valueR2) {
                        pcRegister = pcRegister + 1 + instruction.immediate;
                    }
                    break;
                case 6: instruction.valueR1 = instruction.valueR2 ^ instruction.immediate; break;
                case 10: break;
                case 11: break;
                default: break;
            }
        }
        else {
            pcRegister = pcRegister & 0b11110000000000000000000000000000;
            pcRegister = pcRegister | instruction.address;
        }
        if(instruction.r1==0)
            instruction.valueR1=0;

    }

    public void mem(Instruction instruction){
        if(instruction.opcode == 10){
            instruction.valueR1 = memory.dataMemory.get(instruction.r2 + instruction.immediate);
        }
        else if(instruction.opcode == 11){
            memory.dataMemory.set(instruction.valueR2 + instruction.immediate, instruction.valueR1);
            System.out.println("Data Memory Block " + instruction.valueR2 + instruction.immediate + ": " + memory.dataMemory.get(instruction.valueR2 + instruction.immediate));
        }
    }

    public void writeBack(Instruction instruction){
        if(instruction.opcode != 4 && instruction.opcode != 7 && instruction.opcode != 10){
            registerFile.registers.get(instruction.r1).value = instruction.valueR1;
            System.out.println("Register " + instruction.r1 + ": " + registerFile.registers.get(instruction.r1).value);
        }
    }

    
    // Pipelining
    public void pipeline(){
        int n = memory.numberOfInstructions;
        int clk;
        int maxpipe=0;

        int decodeCalling=0;
        int executeCalling=0;
        int memCalling=0;
        int writeBackCalling=0;
        int finishCalling=0;
        int decodeCallingRun2=0;
        int executeCallingRun2=0;
        int decodeTemp=0;
        int jumpingPC = 0;
        int oldPC = 0;
        boolean jumping = false;
        boolean drop = false;

        // pointers
        Integer fetching=null;
        Instruction decoding=null;
        Instruction executing=null;
        Instruction memorying=null;
        Instruction writingbacking=null;



        for(clk=1 ; clk<=(7+ ((n-1)*2)); clk++){

            System.out.println("Clock Cycle = "+ clk);

            // Finishing the instruction
            if(clk==finishCalling){
                maxpipe--;
                writingbacking=null;
            }
            // WriteBack Stage
            if(clk==writeBackCalling){
                writeBack(memorying);
                writingbacking=memorying;
                memorying=null;
                finishCalling=clk+1;
            }
            // Memory Stage
            if(clk==memCalling){
                mem(executing);
                memorying=executing;
                executing=null;
                writeBackCalling=clk+1;
            }

            // Executing in second clk
            if (clk==executeCallingRun2){

                if(executing.opcode==4 || executing.opcode==7){
                    jumping = true;
                    oldPC=pcRegister;
                }
                execute(executing);

                if (oldPC == pcRegister && executing.opcode==7)
                    drop = true;
                if (oldPC == pcRegister && executing.opcode==4){
                    if (executing.valueR1 != executing.valueR2)
                        drop= true;
                }
                if (oldPC==pcRegister+1 && jumping)
                    pcRegister=oldPC;

            }

            // First executing clk
            if(clk==executeCalling){
                executing=decoding;
                decoding=null;
                memCalling=clk+2;
                executeCallingRun2= executeCalling+1;
            }

            // Second decoding clk
            if(clk==decodeCallingRun2){
                decoding=decode(decodeTemp);
                decodeTemp=0;
            }

            // First decoding clk
            if(clk==decodeCalling){
                decodeTemp=fetching;
                decoding=decode(decodeTemp);
                if(decoding.opcode==4 || decoding.opcode==7)
                    jumpingPC = pcRegister-1;
                fetching=null;
                executeCalling=clk+2;
                decodeCallingRun2=decodeCalling+1;
            }

            // Fetching Stage
            if(clk%2!=0 && pcRegister<memory.instructionMemory.size() && maxpipe<=4){
                fetching=fetch();
                maxpipe++;
                decodeCalling=clk+1;

            }
            // To end of we finished the instructions :)
            if (fetching==null&&decoding==null&&executing==null&&memorying==null&&writingbacking==null){
                n=clk;
                break;
            }

            // printing :)
            System.out.println("PC  " + (pcRegister));
            System.out.println("Fetching = " + ((fetching==null)?"---":fetching));
            System.out.println("Decoding = " + ((decoding==null)?"---":decoding));
            System.out.println("Executing = " + ((executing==null)?"---":executing));
            System.out.println("Memory = " + ((memorying==null)?"---":memorying));
            System.out.println("Write Back = " + ((writingbacking==null)?"---":writingbacking));
            System.out.println("-------------------------------------------------------");

            // to null the instructions we are dropping
            if(jumping && executing!=null){
                jumping=false;
                if(jumpingPC>pcRegister)
                    n = n + n;
                if(oldPC!=pcRegister-1 && oldPC!=pcRegister){
                    decoding=null;
                    fetching=null;
                    decodeCalling=0;
                    decodeCallingRun2=0;
                    executeCalling=0;
                    executeCallingRun2=0;
                    maxpipe-=2;
                    jumpingPC=0;
                    oldPC=0;
                    pcRegister--;
                }
                if(drop){
                    decoding=null;
                    decodeCallingRun2=0;
                    executeCalling=0;
                    executeCallingRun2=0;
                    maxpipe-=1;
                    jumpingPC=0;
                    oldPC=0;
                    drop= false;
                }
            }//end of nulling

        }//end of looping

        for(int i = 0; i < registerFile.registers.size(); i++){
            System.out.println("Register: " + registerFile.registers.get(i).name + "  " + " Value: " + registerFile.registers.get(i).value);
        }

        for(int i = 0; i < memory.dataMemory.size(); i++){
            if(memory.dataMemory.get(i) != 0){
                System.out.println("Data Memory Block " + (i+1024) + ": " + memory.dataMemory.get(i));
            }
            
        }
        for(int i = 0; i < memory.instructionMemory.size(); i++){
            System.out.println("Instruction Memory Block " + i + ": " + memory.instructionMemory.get(i));
        }
    }// end of method

    public static void main(String[] args) throws Exception {
        Ca ca = new Ca("Instructions.txt");
        ca.pipeline();
        
    }

}
