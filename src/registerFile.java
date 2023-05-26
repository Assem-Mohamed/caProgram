public class registerFile {
    private String[] registers;
    private final String zeroRegister = "00000000000000000000000000000000"; // 32-bit zero value
    private int programCounter;

    public registerFile() {
        registers = new String[32];
        programCounter = 0;

        // Initialize all registers to hold the value 0, except the zero register
        for (int i = 0; i < 32; i++) {
            if (i == 0) {
                registers[i] = zeroRegister;
            } else {
                registers[i] = "00000000000000000000000000000000"; // Initialize other registers as desired
            }
        }
    }

    public String readRegister(int index) {
        if (index >= 0 && index < 32) {
            return String.format("%32s", registers[index]).replace(' ', '0');
        } else {
            throw new IllegalArgumentException("Invalid register index");
        }
    }

    public void writeRegister(int index, String value) {
        if (index >= 0 && index < 32) {
            if (index != 0) {
                registers[index] = String.format("%32s", value).replace(' ', '0');
            }
        } else {
            throw new IllegalArgumentException("Invalid register index");
        }
    }

    public int getProgramCounter() {
        return programCounter;
    }

    public void setProgramCounter(int value) {
        programCounter = value;
    }
}
