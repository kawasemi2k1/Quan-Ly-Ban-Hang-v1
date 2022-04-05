/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import OBJ.NhanVien;
import Utils.ValidateData;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class frmLogin extends javax.swing.JFrame {

    ArrayList<NhanVien> listNV = new ArrayList<>();
    Map<String, String> mapUP = new HashMap<>();
    String userR = "";
    String passR = "";
    int status = 0;
    static String maNV = "";
    ValidateData val = new ValidateData();
    /**
     * Creates new form frmLogin
     */
    
   public frmLogin() {
        initComponents();
        
        loadFile();
        this.txt_username.setText(userR);
        this.txt_password.setText(passR);
    }
    public void loadFile(){
         try {
            FileReader fr = null;
            BufferedReader br = null;
            listNV = new ArrayList<>();
            fr = new FileReader("userpass.txt");
            br = new BufferedReader(fr);
            String s = null;
            try {
                while ((s = br.readLine()) != null) {
                    String[] arr = s.split("\t");
                    mapUP.put(arr[0],arr[1]);
                    userR=arr[0];
                    passR=arr[1];
                }
            } catch (IOException ex) {
                Logger.getLogger(frmNhanVien.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(frmNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void saveFile() {
        loadFile();
        try {
            BufferedWriter bw = null;
            FileWriter fw = null;
            String data = "";
            String row = "";
            
            row += txt_username.getText() + "\t";
            row += txt_password.getText() +"";
            System.out.println("row"+row);
            data += row;

            fw = new FileWriter("userpass.txt");
            bw = new BufferedWriter(fw);
            bw.write(data);
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(frmNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean checkUserAndPass(String userName, String password) {
        for (int i = 0; i < listNV.size(); i++) {
            if (userName.equals(listNV.get(i).getMaNhanVien()) && password.equals(listNV.get(i).getMatKhau())) {
                if(listNV.get(i).getTrangThai()==1){
                    status = 1;
                }
                return true;
            }
        }
        return false;
    }
 
     public void deleteContentFile() {
        try {
            BufferedWriter bw = null;
            FileWriter fw = null;
            String data = "";
            String row = "";
            fw = new FileWriter("userpass.txt");
            bw = new BufferedWriter(fw);
            bw.write(row);
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(frmNhanVien.class.getName()).log(Level.SEVERE, null, ex);
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

        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_username = new javax.swing.JTextField();
        txt_password = new javax.swing.JPasswordField();
        jButton2 = new javax.swing.JButton();
        rememberpw = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        txt_erroruserpw = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/hoa.jpg"))); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1095, 700));
        getContentPane().setLayout(null);

        jLabel5.setBackground(new java.awt.Color(0, 153, 102));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/users.png"))); // NOI18N
        jLabel5.setText("Username");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/password.png"))); // NOI18N
        jLabel6.setText("Password");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/login.png"))); // NOI18N
        jButton2.setText("Đăng nhập");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        rememberpw.setText("Nhớ mật khẩu");
        rememberpw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rememberpwActionPerformed(evt);
            }
        });

        txt_erroruserpw.setForeground(new java.awt.Color(255, 0, 0));
        txt_erroruserpw.setText("      ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(177, 177, 177)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(rememberpw, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(71, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(62, 62, 62)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_password, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_username, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(94, 94, 94))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(187, 187, 187)
                        .addComponent(jLabel2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(txt_erroruserpw, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(txt_erroruserpw)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(rememberpw)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addGap(33, 33, 33))
        );

        getContentPane().add(jPanel2);
        jPanel2.setBounds(300, 160, 470, 250);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sakura2.jpg"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 1100, 700);

        pack();
    }// </editor-fold>//GEN-END:initComponents

   
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
         if(!rememberpw.isSelected()){
            deleteContentFile();
        }else{
            loadFile();
        }
        String txt_username = this.txt_username.getText();
        String txt_password = this.txt_password.getText();
        txt_password = val.md5(txt_password);
        frmNhanVien nhanVien = new frmNhanVien();
        // JOptionPane.showMessageDialog(this,"");
        nhanVien.loadFile();
        System.out.println(nhanVien.listNV);
        listNV = nhanVien.listNV;
        if (!checkUserAndPass(txt_username, txt_password)) {
            System.out.println("Trạng thái: "+status);
            txt_erroruserpw.setText("Sai tài khoản hoặc mật khẩu!");
        } else {
            if (status==0) {
                JOptionPane.showMessageDialog(this,"Tài khoản đã bị khóa");
                return;
            }
            // saveFile();
            JOptionPane.showMessageDialog(this, "Đăng nhập thành công!");
            maNV = txt_username;
            frmMain main = new frmMain();
            main.setVisible(true);
            this.dispose();
            return;
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void rememberpwActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rememberpwActionPerformed
        // TODO add your handling code here:
        String txt_username = this.txt_username.getText();
        String txt_password = this.txt_password.getText();
        if (rememberpw.isSelected()) {
            if (mapUP.size() > 1) {
                deleteContentFile();
            }
            saveFile();
            loadFile();
            for (Map.Entry<String, String> entry : mapUP.entrySet()) {
                userR =  entry.getKey();
                passR = entry.getValue();
            }
        } else {
            deleteContentFile();
        }

    }//GEN-LAST:event_rememberpwActionPerformed

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
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JCheckBox rememberpw;
    private javax.swing.JLabel txt_erroruserpw;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JTextField txt_username;
    // End of variables declaration//GEN-END:variables
}
