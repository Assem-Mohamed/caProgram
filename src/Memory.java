import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Memory {
    private static String[] memory;

    public Memory() {
        memory = new String[2048];
    }      

    public void write(int address, String instruction) {
        if (address >= 0 && address < 2048) {
            if (instruction.length() == 32) {
                memory[address] = instruction;
            } else {
                throw new IllegalArgumentException("Invalid instruction length");
            }
        } else {
            throw new IllegalArgumentException("Invalid memory address");
        }
    }

    public String read(int address) {
        if (address >= 0 && address < 2048) {
            return memory[address];
        } else {
            throw new IllegalArgumentException("Invalid memory address");
        }
    }

    public void loadInstructionsFromFile(String filePath) throws CaException {

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int address = 0;
            String delimiter = " ";
            String instructionName;
            String rs;
            String rd;
            String rt;
            String shamt;
            String imm;
            String jmpAddress;

            while ((line = reader.readLine()) != null) {
                StringBuilder binaryInstruction = new StringBuilder(32);
                String[] fields = line.split(delimiter);
                if (fields.length == 5){
                    //R-Format with SHAMT
                    instructionName = fields[0];
                    rd = fields[1]; // R1 fel description
                    rs = fields[2]; // R2 fel description
                    rt = fields[3]; // R3 fel description
                    shamt = fields[4];

                    switch (instructionName) {
                        case "ADD":
                        binaryInstruction.append("0000");
                            break;
                        
                        case "SUB":
                        binaryInstruction.append("0001");
                            break;
    
                        case "MUL":
                        binaryInstruction.append("0010");
                            break;
    
                        case "MOVI":
                        binaryInstruction.append("0011");
                            break;
    
                        case "JEQ":
                        binaryInstruction.append("0100");
                            break;
    
                        case "AND":
                        binaryInstruction.append("0101");
                            break;
    
                        case "XORI":
                        binaryInstruction.append("0110");
                            break;
    
                        case "JMP":
                        binaryInstruction.append("0111");
                            break;
    
                        case "LSL":
                        binaryInstruction.append("1000");
                            break;
    
                        case "LSR":
                        binaryInstruction.append("1001");
                            break;
    
                        case "MOVR":
                        binaryInstruction.append("1010");
                            break;
    
                        case "MOVM":
                        binaryInstruction.append("1011");
                            break;
    
                        default:
                        throw new CaException("Instruction(s) not supported");
                    }
                    switch (rd){
                        case "R1":
                        binaryInstruction.append("00000");
                            break;

                        case "R2":
                        binaryInstruction.append("00001");
                            break;

                        case "R3":
                        binaryInstruction.append("00010");
                            break;

                        case "R4":
                        binaryInstruction.append("00011");
                            break;

                        case "R5":
                        binaryInstruction.append("00100");
                            break;

                        case "R6":
                        binaryInstruction.append("00101");
                            break;
                        case "R7":
                        binaryInstruction.append("00110");
                            break;

                        case "R8":
                        binaryInstruction.append("00111");
                            break;

                        case "R9":
                        binaryInstruction.append("01000");
                            break;

                        case "R10":
                        binaryInstruction.append("01001");
                            break;

                        case "R11":
                        binaryInstruction.append("01010");
                            break;

                        case "R12":
                        binaryInstruction.append("01011");
                            break;

                        case "R13":
                        binaryInstruction.append("01100");
                            break;

                        case "R14":
                        binaryInstruction.append("01101");
                            break;

                        case "R15":
                        binaryInstruction.append("01110");
                            break;

                        case "R16":
                        binaryInstruction.append("01111");
                            break;

                        case "R17":
                        binaryInstruction.append("10000");
                            break;
                        case "R18":
                        binaryInstruction.append("10001");
                            break;

                        case "R19":
                        binaryInstruction.append("10010");
                            break;

                        case "R20":
                        binaryInstruction.append("10011");
                            break;
                        case "R21":
                        binaryInstruction.append("10100");
                            break;

                        case "R22":
                        binaryInstruction.append("10101");
                            break;

                        case "R23":
                        binaryInstruction.append("10110");
                            break;
                            
                        case "R24":
                        binaryInstruction.append("10111");
                            break;

                        case "R25":
                        binaryInstruction.append("11000");
                            break;

                        case "R26":
                        binaryInstruction.append("11001");
                            break;

                        case "R27":
                        binaryInstruction.append("11010");
                            break;

                        case "R28":
                        binaryInstruction.append("11011");
                            break;
                        case "R29":
                        binaryInstruction.append("11100");
                            break;

                        case "R30":
                        binaryInstruction.append("11101");
                            break;

                        case "R31":
                        binaryInstruction.append("11110");
                            break;
                        
                        default:
                        throw new CaException("Wrong Register Number");
                    }
                    switch (rs){
                        case "R1":
                        binaryInstruction.append("00000");
                            break;

                        case "R2":
                        binaryInstruction.append("00001");
                            break;

                        case "R3":
                        binaryInstruction.append("00010");
                            break;

                        case "R4":
                        binaryInstruction.append("00011");
                            break;

                        case "R5":
                        binaryInstruction.append("00100");
                            break;

                        case "R6":
                        binaryInstruction.append("00101");
                            break;
                        case "R7":
                        binaryInstruction.append("00110");
                            break;

                        case "R8":
                        binaryInstruction.append("00111");
                            break;

                        case "R9":
                        binaryInstruction.append("01000");
                            break;

                        case "R10":
                        binaryInstruction.append("01001");
                            break;

                        case "R11":
                        binaryInstruction.append("01010");
                            break;

                        case "R12":
                        binaryInstruction.append("01011");
                            break;

                        case "R13":
                        binaryInstruction.append("01100");
                            break;

                        case "R14":
                        binaryInstruction.append("01101");
                            break;

                        case "R15":
                        binaryInstruction.append("01110");
                            break;

                        case "R16":
                        binaryInstruction.append("01111");
                            break;

                        case "R17":
                        binaryInstruction.append("10000");
                            break;
                        case "R18":
                        binaryInstruction.append("10001");
                            break;

                        case "R19":
                        binaryInstruction.append("10010");
                            break;

                        case "R20":
                        binaryInstruction.append("10011");
                            break;
                        case "R21":
                        binaryInstruction.append("10100");
                            break;

                        case "R22":
                        binaryInstruction.append("10101");
                            break;

                        case "R23":
                        binaryInstruction.append("10110");
                            break;
                            
                        case "R24":
                        binaryInstruction.append("10111");
                            break;

                        case "R25":
                        binaryInstruction.append("11000");
                            break;

                        case "R26":
                        binaryInstruction.append("11001");
                            break;

                        case "R27":
                        binaryInstruction.append("11010");
                            break;

                        case "R28":
                        binaryInstruction.append("11011");
                            break;
                        case "R29":
                        binaryInstruction.append("11100");
                            break;

                        case "R30":
                        binaryInstruction.append("11101");
                            break;

                        case "R31":
                        binaryInstruction.append("11110");
                            break;
                            
                        default:
                        throw new CaException("Wrong Register Number");
                    }
                    switch (rt){
                        case "0":
                        binaryInstruction.append("00000"); 
                            break;

                        case "R1":
                        binaryInstruction.append("00001");
                            break;

                        case "R2":
                        binaryInstruction.append("00010");
                            break;

                        case "R3":
                        binaryInstruction.append("00011");
                            break;

                        case "R4":
                        binaryInstruction.append("00100");
                            break;

                        case "R5":
                        binaryInstruction.append("00101");
                            break;

                        case "R6":
                        binaryInstruction.append("00110");
                            break;

                        case "R7":
                        binaryInstruction.append("00111");
                            break;

                        case "R8":
                        binaryInstruction.append("01000");
                            break;

                        case "R9":
                        binaryInstruction.append("01001");
                            break;

                        case "R10":
                        binaryInstruction.append("01010");
                            break;

                        case "R11":
                        binaryInstruction.append("01011");
                            break;

                        case "R12":
                        binaryInstruction.append("01100");
                            break;

                        case "R13":
                        binaryInstruction.append("01101");
                            break;

                        case "R14":
                        binaryInstruction.append("01110");
                            break;

                        case "R15":
                        binaryInstruction.append("01111");
                            break;

                        case "R16":
                        binaryInstruction.append("10000");
                            break;

                        case "R17":
                        binaryInstruction.append("10001");
                            break;

                        case "R18":
                        binaryInstruction.append("10010");
                            break;

                        case "R19":
                        binaryInstruction.append("10011");
                            break;

                        case "R20":
                        binaryInstruction.append("10100");
                            break;

                        case "R21":
                        binaryInstruction.append("10101");
                            break;

                        case "R22":
                        binaryInstruction.append("10110");
                            break;

                        case "R23":
                        binaryInstruction.append("10111");
                            break;
                            
                        case "R24":
                        binaryInstruction.append("11000");
                            break;

                        case "R25":
                        binaryInstruction.append("11001");
                            break;

                        case "R26":
                        binaryInstruction.append("11010");
                            break;

                        case "R27":
                        binaryInstruction.append("11011");
                            break;

                        case "R28":
                        binaryInstruction.append("11100");
                            break;

                        case "R29":
                        binaryInstruction.append("11101");
                            break;

                        case "R30":
                        binaryInstruction.append("11110");
                            break;

                        case "R31":
                        binaryInstruction.append("11111");
                            break;

                        default:
                        throw new CaException("Wrong Register Number");
                    }    
                    
                    int shamtInt = Integer.parseInt(shamt);
                    shamt = Integer.toBinaryString(shamtInt);
                    shamt = String.format("%013d", Integer.parseInt(shamt)).replace(' ', '0'); 
                    binaryInstruction.append(shamt);

                } else if (fields.length == 4){
                    //I-Format or R-Format without SHAMT
                    instructionName = fields[0];
                    rd = fields[1]; // R1 fel description
                    rs = fields[2]; // R2 fel description
                                      
                    switch (instructionName) {
                        case "ADD":
                        binaryInstruction.append("0000");
                            break;
                        
                        case "SUB":
                        binaryInstruction.append("0001");
                            break;
    
                        case "MUL":
                        binaryInstruction.append("0010");
                            break;
    
                        case "MOVI":
                        binaryInstruction.append("0011");
                            break;
    
                        case "JEQ":
                        binaryInstruction.append("0100");
                            break;
    
                        case "AND":
                        binaryInstruction.append("0101");
                            break;
    
                        case "XORI":
                        binaryInstruction.append("0110");
                            break;
    
                        case "JMP":
                        binaryInstruction.append("0111");
                            break;
    
                        case "LSL":
                        binaryInstruction.append("1000");
                            break;
    
                        case "LSR":
                        binaryInstruction.append("1001");
                            break;
    
                        case "MOVR":
                        binaryInstruction.append("1010");
                            break;
    
                        case "MOVM":
                        binaryInstruction.append("1011");
                            break;
    
                        default:
                        throw new CaException("Instruction(s) not supported");
                    }       
                    switch (rd){
                        case "R1":
                        binaryInstruction.append("00000");
                            break;

                        case "R2":
                        binaryInstruction.append("00001");
                            break;

                        case "R3":
                        binaryInstruction.append("00010");
                            break;

                        case "R4":
                        binaryInstruction.append("00011");
                            break;

                        case "R5":
                        binaryInstruction.append("00100");
                            break;

                        case "R6":
                        binaryInstruction.append("00101");
                            break;
                        case "R7":
                        binaryInstruction.append("00110");
                            break;

                        case "R8":
                        binaryInstruction.append("00111");
                            break;

                        case "R9":
                        binaryInstruction.append("01000");
                            break;

                        case "R10":
                        binaryInstruction.append("01001");
                            break;

                        case "R11":
                        binaryInstruction.append("01010");
                            break;

                        case "R12":
                        binaryInstruction.append("01011");
                            break;

                        case "R13":
                        binaryInstruction.append("01100");
                            break;

                        case "R14":
                        binaryInstruction.append("01101");
                            break;

                        case "R15":
                        binaryInstruction.append("01110");
                            break;

                        case "R16":
                        binaryInstruction.append("01111");
                            break;

                        case "R17":
                        binaryInstruction.append("10000");
                            break;
                        case "R18":
                        binaryInstruction.append("10001");
                            break;

                        case "R19":
                        binaryInstruction.append("10010");
                            break;

                        case "R20":
                        binaryInstruction.append("10011");
                            break;
                        case "R21":
                        binaryInstruction.append("10100");
                            break;

                        case "R22":
                        binaryInstruction.append("10101");
                            break;

                        case "R23":
                        binaryInstruction.append("10110");
                            break;
                            
                        case "R24":
                        binaryInstruction.append("10111");
                            break;

                        case "R25":
                        binaryInstruction.append("11000");
                            break;

                        case "R26":
                        binaryInstruction.append("11001");
                            break;

                        case "R27":
                        binaryInstruction.append("11010");
                            break;

                        case "R28":
                        binaryInstruction.append("11011");
                            break;
                        case "R29":
                        binaryInstruction.append("11100");
                            break;

                        case "R30":
                        binaryInstruction.append("11101");
                            break;

                        case "R31":
                        binaryInstruction.append("11110");
                            break;

                        default:
                        throw new CaException("Wrong Register Number");
                    }
                    switch (rs){
                        case "0":
                        binaryInstruction.append("00000"); 
                            break;

                        case "R1":
                        binaryInstruction.append("00001");
                            break;

                        case "R2":
                        binaryInstruction.append("00010");
                            break;

                        case "R3":
                        binaryInstruction.append("00011");
                            break;

                        case "R4":
                        binaryInstruction.append("00100");
                            break;

                        case "R5":
                        binaryInstruction.append("00101");
                            break;

                        case "R6":
                        binaryInstruction.append("00110");
                            break;

                        case "R7":
                        binaryInstruction.append("00111");
                            break;

                        case "R8":
                        binaryInstruction.append("01000");
                            break;

                        case "R9":
                        binaryInstruction.append("01001");
                            break;

                        case "R10":
                        binaryInstruction.append("01010");
                            break;

                        case "R11":
                        binaryInstruction.append("01011");
                            break;

                        case "R12":
                        binaryInstruction.append("01100");
                            break;

                        case "R13":
                        binaryInstruction.append("01101");
                            break;

                        case "R14":
                        binaryInstruction.append("01110");
                            break;

                        case "R15":
                        binaryInstruction.append("01111");
                            break;

                        case "R16":
                        binaryInstruction.append("10000");
                            break;

                        case "R17":
                        binaryInstruction.append("10001");
                            break;

                        case "R18":
                        binaryInstruction.append("10010");
                            break;

                        case "R19":
                        binaryInstruction.append("10011");
                            break;

                        case "R20":
                        binaryInstruction.append("10100");
                            break;

                        case "R21":
                        binaryInstruction.append("10101");
                            break;

                        case "R22":
                        binaryInstruction.append("10110");
                            break;

                        case "R23":
                        binaryInstruction.append("10111");
                            break;
                            
                        case "R24":
                        binaryInstruction.append("11000");
                            break;

                        case "R25":
                        binaryInstruction.append("11001");
                            break;

                        case "R26":
                        binaryInstruction.append("11010");
                            break;

                        case "R27":
                        binaryInstruction.append("11011");
                            break;

                        case "R28":
                        binaryInstruction.append("11100");
                            break;

                        case "R29":
                        binaryInstruction.append("11101");
                            break;

                        case "R30":
                        binaryInstruction.append("11110");
                            break;

                        case "R31":
                        binaryInstruction.append("11111");
                            break;

                        default:
                        throw new CaException("Wrong Register Number");
                    }    

                    if (fields[3].contains("R")){
                        rt = fields[3];
                        switch (rt){
                            case "R1":
                            binaryInstruction.append("00000");
                                break;
    
                            case "R2":
                            binaryInstruction.append("00001");
                                break;
    
                            case "R3":
                            binaryInstruction.append("00010");
                                break;
    
                            case "R4":
                            binaryInstruction.append("00011");
                                break;
    
                            case "R5":
                            binaryInstruction.append("00100");
                                break;
    
                            case "R6":
                            binaryInstruction.append("00101");
                                break;
                            case "R7":
                            binaryInstruction.append("00110");
                                break;
    
                            case "R8":
                            binaryInstruction.append("00111");
                                break;
    
                            case "R9":
                            binaryInstruction.append("01000");
                                break;
    
                            case "R10":
                            binaryInstruction.append("01001");
                                break;
    
                            case "R11":
                            binaryInstruction.append("01010");
                                break;
    
                            case "R12":
                            binaryInstruction.append("01011");
                                break;
    
                            case "R13":
                            binaryInstruction.append("01100");
                                break;
    
                            case "R14":
                            binaryInstruction.append("01101");
                                break;
    
                            case "R15":
                            binaryInstruction.append("01110");
                                break;
    
                            case "R16":
                            binaryInstruction.append("01111");
                                break;
    
                            case "R17":
                            binaryInstruction.append("10000");
                                break;
                            case "R18":
                            binaryInstruction.append("10001");
                                break;
    
                            case "R19":
                            binaryInstruction.append("10010");
                                break;
    
                            case "R20":
                            binaryInstruction.append("10011");
                                break;
                            case "R21":
                            binaryInstruction.append("10100");
                                break;
    
                            case "R22":
                            binaryInstruction.append("10101");
                                break;
    
                            case "R23":
                            binaryInstruction.append("10110");
                                break;
                                
                            case "R24":
                            binaryInstruction.append("10111");
                                break;
    
                            case "R25":
                            binaryInstruction.append("11000");
                                break;
    
                            case "R26":
                            binaryInstruction.append("11001");
                                break;
    
                            case "R27":
                            binaryInstruction.append("11010");
                                break;
    
                            case "R28":
                            binaryInstruction.append("11011");
                                break;
                            case "R29":
                            binaryInstruction.append("11100");
                                break;
    
                            case "R30":
                            binaryInstruction.append("11101");
                                break;
    
                            case "R31":
                            binaryInstruction.append("11110");
                                break;
    
                            default:
                            throw new CaException("Wrong Register Number");
                        }
                        binaryInstruction.append("0000000000000");  

                    } else{
                        imm = fields[3];
                        int immInt = Integer.parseInt(imm);
                        imm = Integer.toBinaryString(immInt);
                        imm = String.format("%018d", Integer.parseInt(imm)).replace(' ', '0');
                        binaryInstruction.append(imm);
                    }
                } else if (fields.length == 2){
                    //J-Format
                    instructionName = fields[0];
                    jmpAddress = fields[1];

                    switch (instructionName) {
                        case "ADD":
                        binaryInstruction.append("0000");
                            break;
                        
                        case "SUB":
                        binaryInstruction.append("0001");
                            break;
    
                        case "MUL":
                        binaryInstruction.append("0010");
                            break;
    
                        case "MOVI":
                        binaryInstruction.append("0011");
                            break;
    
                        case "JEQ":
                        binaryInstruction.append("0100");
                            break;
    
                        case "AND":
                        binaryInstruction.append("0101");
                            break;
    
                        case "XORI":
                        binaryInstruction.append("0110");
                            break;
    
                        case "JMP":
                        binaryInstruction.append("0111");
                            break;
    
                        case "LSL":
                        binaryInstruction.append("1000");
                            break;
    
                        case "LSR":
                        binaryInstruction.append("1001");
                            break;
    
                        case "MOVR":
                        binaryInstruction.append("1010");
                            break;
    
                        case "MOVM":
                        binaryInstruction.append("1011");
                            break;
    
                        default:
                        throw new CaException("Instruction(s) not supported");
                    }
                    int jmpAddressInt = Integer.parseInt(jmpAddress);
                    jmpAddress = Integer.toBinaryString(jmpAddressInt);
                    jmpAddress = String.format("%028d", Integer.parseInt(jmpAddress)).replace(' ', '0'); 
                    binaryInstruction.append(jmpAddress);
                }

                write(address, binaryInstruction.toString());
                address++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws CaException {
        Memory memory = new Memory();
        Ca CPU = new Ca();
        registerFile registers = Ca.registers;

        String filePath = "D:\\GUC\\Semester 6\\(CSEN601) Computer System Architecture\\Project\\caProgram\\src\\Instructions.txt";
        memory.loadInstructionsFromFile(filePath);

        CPU.fetch();
        
        // Read instructions from memory
        // for (int address = 0; address < 10; address++) {
        //     String instruction = memory.read(address);
        //     // System.out.println("Instruction at address " + address + ": " + Integer.toBinaryString(instruction));
        //     System.out.println("test " + instruction);
        //     // System.out.println(registers.getProgramCounter());
        // }
    }
}
