import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Memory {

    ArrayList<Integer> dataMemory;
    ArrayList<Integer> instructionMemory;
    int numberOfInstructions=0;

    public Memory(String filename) throws CaException {
        dataMemory = new ArrayList<>(1024);
        instructionMemory = new ArrayList<>(1024);

        for(int i = 0; i < 1024; i++) {
            dataMemory.add(0);
            //instructionMemory.add(0);
        }
        parser("src\\" + filename);
    }
    public void parser(String fileName) throws CaException {
        File file = new File(fileName);
        Scanner reader;
        try {
            reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String[] line = reader.nextLine().split(" ");
                int opcode;
                int r1;
                int r2;
                int r3;
                int immediate;
                int shamt;
                int address;
                switch(line[0]){
                    case "ADD":
                        opcode = 0;
                        r1 = Integer.parseInt(line[1].substring(1)) << 23;
                        r2 = Integer.parseInt(line[2].substring(1)) << 18;
                        r3 = Integer.parseInt(line[3].substring(1)) << 13;
                        instructionMemory.add(opcode | r1 | r2 | r3);
                        break;
                    case "SUB":
                        opcode = 1 << 28;
                        r1 = Integer.parseInt(line[1].substring(1)) << 23;
                        r2 = Integer.parseInt(line[2].substring(1)) << 18;
                        r3 = Integer.parseInt(line[3].substring(1)) << 13;
                        instructionMemory.add(opcode | r1 | r2 | r3);
                        break;
                    case "MUL":
                        opcode = 2 << 28;
                        r1 = Integer.parseInt(line[1].substring(1)) << 23;
                        r2 = Integer.parseInt(line[2].substring(1)) << 18;
                        r3 = Integer.parseInt(line[3].substring(1)) << 13;
                        instructionMemory.add(opcode | r1 | r2 | r3);
                        break;
                    case "MOVI":
                        opcode = 3 << 28;
                        r1 = Integer.parseInt(line[1].substring(1)) << 23;
                        r2 = Integer.parseInt(line[2].substring(0)) << 18;
                        immediate = Integer.parseInt(line[3]);
                        instructionMemory.add(opcode | r1 | r2 | immediate);
                        break;
                    case "JEQ":
                        opcode = 4 << 28;
                        r1 = Integer.parseInt(line[1].substring(1)) << 23;
                        r2 = Integer.parseInt(line[2].substring(1)) << 18;
                        immediate = Integer.parseInt(line[3]);
                        instructionMemory.add(opcode | r1 | r2 | immediate);
                        break;
                    case "AND":
                        opcode = 5 << 28;
                        r1 = Integer.parseInt(line[1].substring(1)) << 23;
                        r2 = Integer.parseInt(line[2].substring(1)) << 18;
                        r3 = Integer.parseInt(line[3].substring(1)) << 13;
                        instructionMemory.add(opcode | r1 | r2 | r3);
                        break;
                    case "XORI":
                        opcode = 6 << 28;
                        r1 = Integer.parseInt(line[1].substring(1)) << 23;
                        r2 = Integer.parseInt(line[2].substring(1)) << 18;
                        immediate = Integer.parseInt(line[3]);
                        instructionMemory.add(opcode | r1 | r2 | immediate);
                        break;
                    case "JMP":
                        opcode = 7 << 28;
                        address = Integer.parseInt(line[1]);
                        instructionMemory.add(opcode | address);
                        break;
                    case "LSL":
                        opcode = 8 << 28;
                        r1 = Integer.parseInt(line[1].substring(1)) << 23;
                        r2 = Integer.parseInt(line[2].substring(1)) << 18;
                        r3 = Integer.parseInt(line[3].substring(0)) << 13;
                        shamt = Integer.parseInt(line[4]);
                        instructionMemory.add(opcode | r1 | r2 | r3 | shamt);
                        break;
                    case "LSR":
                        opcode = 9 << 28;
                        r1 = Integer.parseInt(line[1].substring(1)) << 23;
                        r2 = Integer.parseInt(line[2].substring(1)) << 18;
                        r3 = Integer.parseInt(line[3].substring(0)) << 13;
                        shamt = Integer.parseInt(line[4]);
                        instructionMemory.add(opcode | r1 | r2 | r3 | shamt);
                        break;
                    case "MOVR":
                        opcode = 10 << 28;
                        r1 = Integer.parseInt(line[1].substring(1)) << 23;
                        r2 = Integer.parseInt(line[2].substring(1)) << 18;
                        immediate = Integer.parseInt(line[3]);
                        instructionMemory.add(opcode | r1 | r2 | immediate);
                        break;
                    case "MOVM":
                        opcode = 11 << 28;
                        r1 = Integer.parseInt(line[1].substring(1)) << 23;
                        r2 = Integer.parseInt(line[2].substring(1)) << 18;
                        immediate = Integer.parseInt(line[3]);
                        instructionMemory.add(opcode | r1 | r2 | immediate);
                        break;
                    default: throw new CaException("Incorrect command!");
                   
                }
                numberOfInstructions++;
            }       
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
