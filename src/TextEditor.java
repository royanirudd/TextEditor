import javax.swing.*;
import java.io.*;

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

        //Adding menu bar along with open and save
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");

        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);

        //Open and save files
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(frame); //Show the file dialogue window

        //Check if the user selected a file
        if(result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile(); // Get chosen file
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                textArea.read(reader, null); //Display the file's contents in JTextArea
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        //Saving files

        JFileChooser jFileChooser = new JFileChooser();
        int results = fileChooser.showSaveDialog(frame); //Show save dialog

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile(); //Get the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                textArea.write(writer); //Write the contents of textArea to the file

            } catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }
}
