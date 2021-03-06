/*
 * SecondStarDetails.java
 *
 * Created on July 24, 2006, 2:29 PM
 */

package gurpsgen;
import javax.swing.*;


   /* private void getBinaryDetails(java.awt.event.MouseEvent evt) {
        //called when the user clicks on the 'yes binary' button
        Main.starDetails[4] = "yes";
    }
    */

/**
 *
 * @author  Jac
 */
public class SecondStarDetails extends javax.swing.JFrame {
    
    /** Creates new form SecondStarDetails */
    public SecondStarDetails() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setLayout(new java.awt.GridLayout(6, 2, 6, 6));

        jPanel1.setBorder(new javax.swing.border.TitledBorder("Secondary Star Characteristics"));
        jLabel1.setText("Star Name");
        jPanel1.add(jLabel1);

        jTextField1.setText("Solis II");
        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField1FocusLost(evt);
            }
        });

        jPanel1.add(jTextField1);

        jLabel2.setText("Star Type");
        jPanel1.add(jLabel2);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "O", "B", "A", "F", "G", "K", "M", "D" }));
        jPanel1.add(jComboBox1);

        jLabel3.setText("Star Size");
        jPanel1.add(jLabel3);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ia", "Ib", "II", "III", "IV", "V", "VI" }));
        jPanel1.add(jComboBox2);

        jLabel4.setText("Distance from first star (0 - 10AU)");
        jPanel1.add(jLabel4);

        jTextField2.setText("2.5");
        jPanel1.add(jTextField2);

        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setDefaultCapable(false);
        jButton2.setEnabled(false);
        jButton2.setFocusPainted(false);
        jButton2.setFocusable(false);
        jButton2.setRequestFocusEnabled(false);
        jButton2.setRolloverEnabled(false);
        jButton2.setVerifyInputWhenFocusTarget(false);
        jPanel1.add(jButton2);

        jButton1.setText("Enter Data");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jPanel1.add(jButton1);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }
    // </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusLost

        // TODO add your handling code here:
        String starName = ((JTextField)evt.getSource()).getText();
        if (starName.length() < 1){
            JOptionPane.showMessageDialog((java.awt.Component)evt.getSource(), 
                    "You must enter a value.",
                    "Inane warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jTextField1FocusLost

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
// TODO add your handling code here:
        java.awt.Dimension d = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        JFrame frame = (JFrame)evt.getSource();
        frame.setLocation((d.width - frame.getSize().width) / 2, 
                (d.height -frame.getSize().height) / 2);
    }//GEN-LAST:event_formWindowActivated

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
// TODO add your handling code here:
        //read data from all five entry fields into array secondStarDetails
        Main.secondStarDetails[0] = jTextField1.getText();
        Main.secondStarDetails[1] = (String)jComboBox1.getSelectedItem();
        Main.secondStarDetails[2] = (String)jComboBox2.getSelectedItem();
        Main.secondStarDetails[3] = jTextField2.getText();
        
        /*
        String binary = "no";
        if (jCheckBox1.isSelected()){
            binary = "yes";
        } 
        Main.starDetails[4] = binary;
        */
       
        //close form
        return;
    }//GEN-LAST:event_jButton1MouseClicked
        public void createForm() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SecondStarDetails().setVisible(true);
            }
        });
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
    
}
