public class registerFile {
    private String[] registers;
    private final String zeroRegister = "00000000000000000000000000000000"; // 32-bit zero value
    public int programCounter;

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
            return registers[index];
        } else {
            throw new IllegalArgumentException("Invalid register index");
        }
    }

    public void writeRegister(int index, String value) {
        if (index >= 0 && index < 32) {
            if (index != 0) {
                registers[index] = value;
            }
        } else {
            throw new IllegalArgumentException("Invalid register index");
        }
    }

    // Rest of the class methods...
}
