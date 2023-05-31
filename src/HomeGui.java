import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class HomeGui extends JFrame {
    static JTextArea programInstructions;
    static JTextArea cyclePrints;
    static JTextArea registerFile;
    static JTextArea instructionMemory;
    static JTextArea dataMemory;

    public HomeGui(){
        
        JFrame frame = new JFrame("Window Division Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        programInstructions = new JTextArea("Program Instructions");
        // programInstructions.setPreferredSize(new Dimension(200, 300));
        JScrollPane scrollProgramInstructions = new JScrollPane(programInstructions);


        cyclePrints = new JTextArea("PC and Instructions in each phase");
        // cyclePrints.setPreferredSize(new Dimension(200, 300));        
        JScrollPane scrollCyclePrints = new JScrollPane(cyclePrints);

        JButton run = new JButton("Ruuuuunnnnnn");

        // ActionListener buttonListener = new ActionListener() {
        //     public void actionPerformed(ActionEvent e) {
        //         // Perform the action when the button is pressed
        //         try {
        //             File file = new File("Instructions.txt");
        //             BufferedWriter write  = new BufferedWriter(file);
                    
        //             Ca ca = new Ca("Instructions.txt");
        //             ca.pipeline();
        //         } catch (Exception e1) {
        //             // TODO Auto-generated catch block
        //             e1.printStackTrace();
        //         }
                
        //     }
        // };

        ActionListener buttonListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get the text from the text area
                String content = programInstructions.getText();

                // Write the content to a file
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("src//Instructions.txt"))) {
                    writer.write(content);
                    // JOptionPane.showMessageDialog(frame, "Content saved to file.");
                    writer.close();

                    Ca ca = new Ca("Instructions.txt");
                    ca.pipeline();
                  
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
        
        run.addActionListener(buttonListener);

        registerFile = new JTextArea("Register File");
        // registerFile.setPreferredSize(new Dimension(200, 300));
        JScrollPane scrollRegisterFile = new JScrollPane(registerFile);

        instructionMemory = new JTextArea("Instruction Memory");
        // instructionMemory.setPreferredSize(new Dimension(200, 300));
        JScrollPane scrollInstructionMem = new JScrollPane(instructionMemory);

        dataMemory = new JTextArea("Data Memory");
        // dataMemory.setPreferredSize(new Dimension(200, 300));
        JScrollPane scrollDataMemory = new JScrollPane(dataMemory);

        Container container = frame.getContentPane();
        container.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(1, 2));
        topPanel.setPreferredSize(new Dimension(200, 300));
        topPanel.add(scrollProgramInstructions);
        topPanel.add(scrollCyclePrints);

        JPanel middlePanel = new JPanel(new FlowLayout());
        middlePanel.add(run);

        JPanel bottomPanel = new JPanel(new GridLayout(1, 3));
        bottomPanel.setPreferredSize(new Dimension(200, 300));
        bottomPanel.add(scrollRegisterFile);
        bottomPanel.add(scrollInstructionMem);
        bottomPanel.add(scrollDataMemory);

        container.add(topPanel, BorderLayout.NORTH);
        container.add(middlePanel, BorderLayout.CENTER);
        container.add(bottomPanel, BorderLayout.SOUTH);

        // Display the JFrame
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        new HomeGui();
    }
}
