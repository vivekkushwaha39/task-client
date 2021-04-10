/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taskclient.gui;

import java.util.LinkedList;
import javax.swing.SwingUtilities;
import javax.swing.text.DefaultCaret;

public class ClientUI extends javax.swing.JFrame {

    /**
     * Creates new form ClientUI
     */
    public ClientUI() {
        initComponents();
    }

    public void addLog(String text) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtLog.append(text);
                txtLog.append("\n");
                
            }
        });

    }

    public void step1() {
        cmbTaskList.removeAllItems();
        btnDwnldTsk.setEnabled(false);
        cmbTaskList.setEnabled(false);
        btnAvTask.setEnabled(true);
    }

    public void setTasks(LinkedList<String> desc) {
        cmbTaskList.removeAllItems();
        for (int i = 0; i < desc.size(); i++) {
            cmbTaskList.addItem(desc.get(i));
        }
        cmbTaskList.setSelectedIndex(0);
    }

    public void step2() {
        btnAvTask.setEnabled(true);
        btnDwnldTsk.setEnabled(true);
        cmbTaskList.setEnabled(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        cmbTaskList = new javax.swing.JComboBox<>();
        btnAvTask = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtLog = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        btnDwnldTsk = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtHost = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTSPort = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtFSPort = new javax.swing.JTextField();
        btnApplyConf = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        getContentPane().add(jPanel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 87;
        gridBagConstraints.weightx = 0.3;
        getContentPane().add(cmbTaskList, gridBagConstraints);

        btnAvTask.setText("Get Available tasks");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        getContentPane().add(btnAvTask, gridBagConstraints);

        txtLog.setColumns(20);
        txtLog.setRows(5);
        txtLog.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtLog.setEnabled(false);
        txtLog.setName(""); // NOI18N
        jScrollPane1.setViewportView(txtLog);
        DefaultCaret caret = (DefaultCaret) txtLog.getCaret();
        caret.setUpdatePolicy(DefaultCaret.OUT_BOTTOM);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.weighty = 2.8;
        getContentPane().add(jScrollPane1, gridBagConstraints);

        jLabel1.setText("Log:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(jLabel1, gridBagConstraints);

        btnDwnldTsk.setText("Perform Task");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weighty = 0.6;
        getContentPane().add(btnDwnldTsk, gridBagConstraints);

        jLabel2.setText("Available Tasks");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        getContentPane().add(jLabel2, gridBagConstraints);

        jLabel3.setText("Server IP/Host (File And Task)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        getContentPane().add(jLabel3, gridBagConstraints);
        jLabel3.getAccessibleContext().setAccessibleDescription("");

        txtHost.setText("localhost");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(txtHost, gridBagConstraints);

        jLabel4.setText("Task Server Port");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        getContentPane().add(jLabel4, gridBagConstraints);

        txtTSPort.setText("1234");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.3;
        getContentPane().add(txtTSPort, gridBagConstraints);

        jLabel6.setText("File Server Port");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        getContentPane().add(jLabel6, gridBagConstraints);

        txtFSPort.setText("1235");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(txtFSPort, gridBagConstraints);

        btnApplyConf.setText("Apply Config Changes");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(btnApplyConf, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ClientUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton btnApplyConf;
    protected javax.swing.JButton btnAvTask;
    protected javax.swing.JButton btnDwnldTsk;
    protected javax.swing.JComboBox<String> cmbTaskList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    protected javax.swing.JTextField txtFSPort;
    protected javax.swing.JTextField txtHost;
    protected javax.swing.JTextArea txtLog;
    protected javax.swing.JTextField txtTSPort;
    // End of variables declaration//GEN-END:variables
}
