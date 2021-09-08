package notepad;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.*;

import java.awt.event.*;
import java.io.*;

public class Notepad extends JFrame implements ActionListener {

    JTextArea area;
    JScrollPane pane;
    String text;

    Notepad() {
        setBounds(0, 0, 1950, 1050);

        JMenuBar menubar = new JMenuBar();

        JMenu file = new JMenu("File");
        JMenuItem newfile = new JMenuItem("New File");
        newfile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        file.add(newfile);
        newfile.addActionListener(this);

        JMenuItem open = new JMenuItem("Open File");
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        file.add(open);
        open.addActionListener(this);

        JMenuItem save = new JMenuItem("Save");
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        file.add(save);
        save.addActionListener(this);

        JMenuItem print = new JMenuItem("Print");
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        file.add(print);
        print.addActionListener(this);

        JMenuItem exit = new JMenuItem("Exit");
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
        file.add(exit);
        exit.addActionListener(this);

        JMenu edit = new JMenu("Edit");
        JMenuItem copy = new JMenuItem("Copy");
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        edit.add(copy);
        copy.addActionListener(this);

        JMenuItem cut = new JMenuItem("Cut");
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        edit.add(cut);
        cut.addActionListener(this);

        JMenuItem paste = new JMenuItem("Paste");
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        edit.add(paste);
        paste.addActionListener(this);

        JMenu help = new JMenu("Help");
        JMenuItem about = new JMenuItem("About the Notepad");
        help.add(about);
        about.addActionListener(this);

        menubar.add(file);
        menubar.add(edit);
        menubar.add(help);
        setJMenuBar(menubar);

        area = new JTextArea();
        area.setFont(new Font("SAN_SERIF", Font.PLAIN, 18));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        pane = new JScrollPane(area);
        pane.setBorder(BorderFactory.createEmptyBorder());
        add(pane, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("New File")) {

            area.setText("");
        } else if (e.getActionCommand().equals("Open File")) {
            JFileChooser chooser = new JFileChooser();
            chooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .txt Files", "txt");

            chooser.addChoosableFileFilter(restrict);
            int action = chooser.showOpenDialog(this);
            if (JFileChooser.APPROVE_OPTION != action) {
                return;
            }

            File file = chooser.getSelectedFile();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                area.read(reader, null);
            } catch (Exception es) {

            }

        } else if (e.getActionCommand().equals("Save")) {
            JFileChooser saveas = new JFileChooser();
            saveas.setApproveButtonText("Save");
            int action = saveas.showOpenDialog(this);
            if (JFileChooser.APPROVE_OPTION != action) {
                return;
            }

            File filename = new File(saveas.getSelectedFile() + ".txt");
            BufferedWriter outFile = null;
            try {
                outFile = new BufferedWriter(new FileWriter(filename));
                area.write(outFile);
            } catch (Exception es) {
                System.out.print(es.getMessage());
            }
        } else if (e.getActionCommand().equals("Print")) {
            try {
                area.print();
            } catch (Exception es) {

            }

        } else if (e.getActionCommand().equals("Exit")) {
            System.exit(0);
        } else if (e.getActionCommand().equals("Copy")) {
            text = area.getSelectedText();
        } else if (e.getActionCommand().equals("Paste")) {
            area.insert(text, area.getCaretPosition());
        } else if (e.getActionCommand().equals("Cut")) {
            text = area.getSelectedText();
            area.replaceRange("", area.getSelectionStart(), area.getSelectionEnd());
        } else if (e.getActionCommand().equals("About the Notepad")) {
        	new About().setVisible(true);
        }
    }

    public static void main(String[] args) {
        Notepad notepad = new Notepad();
        notepad.setVisible(true);
        notepad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
