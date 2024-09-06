import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.io.*;

public class TextEditor {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // This is creating the window GUI
            JFrame frame = new JFrame("Simple Text Editor");

            // This is the text area to write in 20rowsx60cols
            JTextArea textArea = new JTextArea(20, 60);
            frame.add(new JScrollPane(textArea)); // Scrollable text area
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // When closed shuts down

            // Adding menu bar along with open and save
            JMenuBar menuBar = new JMenuBar();
            JMenu fileMenu = new JMenu("File");
            JMenuItem openItem = new JMenuItem("Open");
            JMenuItem saveItem = new JMenuItem("Save");

            fileMenu.add(openItem);
            fileMenu.add(saveItem);
            menuBar.add(fileMenu);

            JMenu editMenu = new JMenu("Edit");
            JMenuItem cutItem = new JMenuItem("Cut");
            JMenuItem copyItem = new JMenuItem("Copy");
            JMenuItem pasteItem = new JMenuItem("Paste");
            JMenuItem undoItem = new JMenuItem("Undo");
            JMenuItem redoItem = new JMenuItem("Redo");

            editMenu.add(cutItem);
            editMenu.add(copyItem);
            editMenu.add(pasteItem);
            editMenu.add(undoItem);
            editMenu.add(redoItem);

            menuBar.add(editMenu);
            frame.setJMenuBar(menuBar);

            // Undo manager
            UndoManager undoManager = new UndoManager();
            textArea.getDocument().addUndoableEditListener(undoManager);

            // File operations
            JFileChooser fileChooser = new JFileChooser();

            openItem.addActionListener(e -> {
                int result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        textArea.read(reader, null);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(frame, "Error reading file", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            saveItem.addActionListener(e -> {
                int result = fileChooser.showSaveDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                        textArea.write(writer);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(frame, "Error saving file", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            // Edit operations
            cutItem.addActionListener(e -> textArea.cut());
            copyItem.addActionListener(e -> textArea.copy());
            pasteItem.addActionListener(e -> textArea.paste());

            undoItem.addActionListener(e -> {
                if (undoManager.canUndo()) {
                    undoManager.undo();
                }
            });

            redoItem.addActionListener(e -> {
                if (undoManager.canRedo()) {
                    undoManager.redo();
                }
            });

            frame.pack();
            frame.setVisible(true);
        });
    }
}