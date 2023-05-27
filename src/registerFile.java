import java.util.ArrayList;

public class RegisterFile {

    ArrayList<Register> registers = new ArrayList<>(32);;

    public RegisterFile(){
        registers.add(new zeroRegister());

        for (int i = 1; i<32;i++)
            registers.add(new Register(i));

    }


}