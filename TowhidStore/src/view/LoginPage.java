/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class LoginPage extends javax.swing.JFrame {

    /**
     * Creates new form LoginPage
     */
    public LoginPage() {
        initComponents();
    }

    public void getLogin() {

//        String user = "Towhid";
//        String pass = "1234";
//
//        txtUserName.setText(user);
//        txtPassword.setText(pass);
//
//        if (user.equalsIgnoreCase(user) && pass.equals(pass)) {
//            JOptionPane.showMessageDialog(rootPane, "Login Succesfully");
//            loginClear();
//            ProductView productView = new ProductView();
//            productView.setVisible(true);
//
//        } else {
//            JOptionPane.showMessageDialog(rootPane, "Login Unsuccesfully");
//        }


        if (txtUserName.getText().equals("")) {

            JOptionPane.showMessageDialog(rootPane, "Please fill your name");

        } 
        
        
        else if (txtPassword.getText().equals("")) {

            JOptionPane.showMessageDialog(rootPane, "Please fill your password");
        } 
        
        
//        else if (txtUserName.getText().contains("Towhid") && txtPassword.getText().contains("1234")) {
//            JOptionPane.showMessageDialog(rootPane, "Login Succesfully");
//            ProductView productView = new ProductView();
//            productView.setVisible(true);
//        }
        
        
//           else if (user.equalsIgnoreCase(user) && pass.equals(pass)) {
//            JOptionPane.showMessageDialog(rootPane, "Login Succesfully");
//            loginClear();
//            ProductView productView = new ProductView();
//            productView.setVisible(true);
//
//        }
        
        
         else if (txtUserName.getText().equalsIgnoreCase("Towhid") && txtPassword.getText().equals("1234")) {
            JOptionPane.showMessageDialog(rootPane, "Welcome Towhid Store");
            ProductView productView = new ProductView();
            this.setVisible(false);
            productView.setVisible(true);
        }

             else {
                JOptionPane.showMessageDialog(rootPane, "Wrong Username or Password !!!", "massage", JOptionPane.ERROR_MESSAGE);
            }

        }

    

   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        btnLoginExit = new javax.swing.JButton();
        txtPassword = new javax.swing.JPasswordField();
        checkBoxLogin = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 255, 51));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Login Application");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 80));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 80));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("User Name");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 110, 20));
        jPanel3.add(txtUserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 270, 40));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Password");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, 90, 30));

        btnLogin.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnLogin.setText("Login");
        btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLoginMouseClicked(evt);
            }
        });
        jPanel3.add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 310, 90, 30));

        btnLoginExit.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnLoginExit.setText("Exit");
        btnLoginExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLoginExitMouseClicked(evt);
            }
        });
        jPanel3.add(btnLoginExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 310, 100, 30));
        jPanel3.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 270, 40));

        checkBoxLogin.setText("Show Password");
        checkBoxLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxLoginActionPerformed(evt);
            }
        });
        jPanel3.add(checkBoxLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 250, -1, 20));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 80, 360, 400));

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon("F:\\GitHub\\SwingProject\\Towhid-Store\\TowhidStore\\Login picture\\240_F_256947386_VJGb69dBUeVGRBJEV56nnan2N1hMA8CW.jpg")); // NOI18N
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 340, 400));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 340, 400));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMouseClicked
        // TODO add your handling code here:
        getLogin();
        
        
    }//GEN-LAST:event_btnLoginMouseClicked

    private void btnLoginExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginExitMouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnLoginExitMouseClicked

    private void checkBoxLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxLoginActionPerformed
        // TODO add your handling code here:
        if (checkBoxLogin.isSelected()) {
            txtPassword.setEchoChar((char) 0);
        } else {
            txtPassword.setEchoChar('*');

        }
    }//GEN-LAST:event_checkBoxLoginActionPerformed

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
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnLoginExit;
    private javax.swing.JCheckBox checkBoxLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
