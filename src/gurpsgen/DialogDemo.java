
package gurpsgen;

import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.beans.*; //Property change stuff
import java.awt.*;
import java.awt.event.*;

/*
 * DialogDemo.java is a 1.4 application that requires these files:
 *   CustomDialog.java
 *   images/middle.gif
 */
public class DialogDemo extends JPanel implements ActionListener{
    JLabel label, secondLabel;
    JFrame frame;
    String moreDialogDesc = "Some more dialogs";
    JPanel infoPanel;
    
    StarStats starStats = new StarStats();

    String [] choices = {"Choose a name for the star", "Select the size of the star(Ia is the largest)",
            "Select the type (O is the brightest)", "Define Bode's constant (around 0.3)",
            "Is it a binary star?"};
            
            
    
    /** Creates the GUI shown inside the frame's content pane. */
    public DialogDemo(JFrame frame) {
        super(new BorderLayout());
        this.frame = frame;

        secondLabel = new JLabel(choices[Main.index],
                           JLabel.CENTER);
        
        JButton showItButton = null;
        showItButton = new JButton("Next");
        showItButton.addActionListener(this);
        
        label = new JLabel("Describe the star of your new system.");
        Component currentField = createInfoPanel();

        //Lay them out.
        Border padding = BorderFactory.createEmptyBorder(20,20,5,20);
        
        add(label, BorderLayout.PAGE_START);
        label.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        add(secondLabel, BorderLayout.PAGE_START);
        secondLabel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        add(currentField, BorderLayout.CENTER);
        //currentField.setBorder(padding);
        
        add(showItButton, BorderLayout.PAGE_END);
        showItButton.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        
    }

    /** Sets the text displayed at the bottom of the frame. */
    void setLabel(String newText) {
        label.setText(newText);
    }


    private Component createInfoPanel() {

        Component current;
     
        JTextField nameField = new JTextField(20);
        nameField.addActionListener(this);

        //Create the combo box, select the item at index 4.
        JComboBox starTypeSelection = new JComboBox(starStats.starTypes);
        starTypeSelection.setSelectedIndex(4);
        starTypeSelection.addActionListener(this);
        
        JComboBox starSizeSelection = new JComboBox(starStats.starSizes);
        starSizeSelection.setSelectedIndex(4);
        starSizeSelection.addActionListener(this);
        
        JTextField bodeField = new JTextField(20);
        bodeField.addActionListener(this);

        JCheckBox isBinaryChoice = new JCheckBox("Is it a binary star?");
        isBinaryChoice.setSelected(true);
        

        //Lay out the demo.
        switch (Main.index){
            case 0:
                current = nameField;
                break;
            case 1:
                current = starSizeSelection;
                break;
            case 2:
                current = starTypeSelection;
                break;
            case 3:
                current = bodeField;
                break;
            default:
               current = isBinaryChoice;
                break;
                
        }
        

        return current;
    }
    
        /** Listens to the combo box/textfield/button. */
    public void actionPerformed(ActionEvent e) {
        Main.print(""+Main.index);
        Object source = e.getSource();
        if (source instanceof JButton){
            //source is the Next Button
            Main.print("heard a button");
            Main.index++;
            frame.setVisible(false);
            if (Main.index < 5){
                getInput();
            } else {
                
                this.getRidOf();
            }
        } else if(source instanceof JComboBox){
            if( (Main.index == 1) || (Main.index == 2) ) {
            JComboBox cb = (JComboBox)source;
            Main.starDetails[Main.index] = (String)cb.getSelectedItem();
            }
        } else if(source instanceof JTextField){
            JTextField tf = (JTextField)source;
            Main.starDetails[Main.index] = tf.getText();
        } else {
            Main.print("error: random event!");
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public static void getInput() {
        JFrame frame = createFrame();
        
        //Create and set up the content pane.
        DialogDemo newContentPane = new DialogDemo(frame);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    
    public static boolean showSaveQuery(){
        JFrame frame = createFrame();
        boolean response;
        
        int answer = JOptionPane.showConfirmDialog(
                frame,
                "Would you like to save your new solar system?",
                "Keep new system?",
                JOptionPane.YES_NO_OPTION);
        
        
        //Display the window.
        //frame.pack();
        //frame.setVisible(true);
        
        if (answer == JOptionPane.YES_OPTION) {
            // User clicked YES.
            response = true;
        } else {
            // User clicked NO.
            response = false;
        }
        
        return response;
    }
    
   
    
    public static JFrame createFrame() {
        //Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        //Create and set up the window.
        JFrame frame = new JFrame("GURPS World Creator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        return frame;
        
    }
    
private void getRidOf(){
    //frame.setVisible(false);
    frame.dispose();
    Main.index = 6;
}

}