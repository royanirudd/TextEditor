import javax.swing.*;

public class TextEditor {
    public static void main(String[] args) {
        //This is creating the window GUI
        JFrame frame = new JFrame("Simple Text Editor");

        //This is the text area to write in 20rowsx60cols
        JTextArea textArea = new JTextArea(20,60);
        frame.add(new JScrollPane(textArea)); //Scrollable text area
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //When closed shuts down
        frame.pack();
        frame.setVisible(true);
    }
}
