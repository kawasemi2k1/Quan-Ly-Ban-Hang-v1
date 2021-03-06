/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import OBJ.KhachHang;
import Utils.ValidateData;
import java.awt.Window;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class frmKhachHang extends javax.swing.JPanel {

    private DefaultTableModel defaultTableModel;
    KhachHang kh = new KhachHang();
    static ArrayList<KhachHang> listKH = new ArrayList<>();
    ValidateData validate = new ValidateData();

    /**
     * Creates new form frmKhachHang
     */
    public frmKhachHang() {
        initComponents();
        loadFile();
        getData(listKH);
    }

    public void loadFile() {
        try {
            BufferedReader br = null;
            FileReader fr = null;
            listKH = new ArrayList<>(); //lưu ý
            fr = new FileReader("khachhang.txt");
            br = new BufferedReader(fr);
            String s = null;

            try {
                while ((s = br.readLine()) != null) {
                    //Cắt chuỗi:
                    String arr[] = s.split("\t");
                    //Khởi tạo
                    KhachHang kh = new KhachHang();
                    kh.setMaKH(arr[0]);
                    kh.setTenKH(arr[1]);
                    kh.setGioiTinhKH(arr[2]);
                    kh.setEmailKH(arr[3]);
                    kh.setSdtKH(arr[4]);
                    kh.setDiaChiKH(arr[5]);
                    kh.setTrangThaiKH(Integer.parseInt(arr[6]));
                    listKH.add(kh);
                }
            } catch (IOException ex) {
                Logger.getLogger(frmKhachHang.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(frmKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void getData(ArrayList<KhachHang> listKH) {
        defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("Mã KH");
        defaultTableModel.addColumn("Họ tên KH");
        defaultTableModel.addColumn("Giới tính");
        defaultTableModel.addColumn("Email");
        defaultTableModel.addColumn("SĐT");
        defaultTableModel.addColumn("Địa chỉ");
        defaultTableModel.addColumn("Trạng thái");

        for (KhachHang obj : listKH) {
            Vector vector = new Vector();
            vector.add(obj.getMaKH());
            vector.add(obj.getTenKH());
            vector.add(obj.getGioiTinhKH());
            vector.add(obj.getEmailKH());
            vector.add(obj.getSdtKH());
            vector.add(obj.getDiaChiKH());
            vector.add(obj.getTrangThaiKH() == 1 ? "Hoạt đông" : "Đã khóa");
            defaultTableModel.addRow(vector);
        }
        tbl_KhachHang.setModel(defaultTableModel);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        txt_maKH = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_tenKH = new javax.swing.JTextField();
        txt_sdtKH = new javax.swing.JTextField();
        txt_emailKH = new javax.swing.JTextField();
        txt_diaChiKH = new javax.swing.JTextField();
        btn_themKH = new javax.swing.JButton();
        btn_suaKH = new javax.swing.JButton();
        btn_xoaKH = new javax.swing.JButton();
        btn_timKiemKH = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        txt_tuKhoa = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_KhachHang = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txt_gioiTinhKH = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_trangThaiKH = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setLayout(null);

        jLabel1.setText("Mã KH");
        add(jLabel1);
        jLabel1.setBounds(77, 56, 36, 16);
        add(txt_maKH);
        txt_maKH.setBounds(147, 53, 136, 22);

        jLabel2.setText("Họ và tên");
        add(jLabel2);
        jLabel2.setBounds(378, 56, 51, 16);

        jLabel3.setText("SĐT");
        add(jLabel3);
        jLabel3.setBounds(77, 161, 20, 16);

        jLabel4.setText("Email");
        add(jLabel4);
        jLabel4.setBounds(77, 109, 50, 16);

        jLabel5.setText("Địa chỉ");
        add(jLabel5);
        jLabel5.setBounds(77, 204, 36, 16);
        add(txt_tenKH);
        txt_tenKH.setBounds(467, 53, 136, 22);
        add(txt_sdtKH);
        txt_sdtKH.setBounds(147, 158, 136, 22);
        add(txt_emailKH);
        txt_emailKH.setBounds(147, 105, 136, 25);
        add(txt_diaChiKH);
        txt_diaChiKH.setBounds(147, 204, 136, 22);

        btn_themKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        btn_themKH.setText("Thêm");
        btn_themKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themKHActionPerformed(evt);
            }
        });
        add(btn_themKH);
        btn_themKH.setBounds(708, 115, 83, 25);

        btn_suaKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit.png"))); // NOI18N
        btn_suaKH.setText("Sửa");
        btn_suaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suaKHActionPerformed(evt);
            }
        });
        add(btn_suaKH);
        btn_suaKH.setBounds(838, 115, 85, 25);

        btn_xoaKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
        btn_xoaKH.setText("Xóa");
        btn_xoaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaKHActionPerformed(evt);
            }
        });
        add(btn_xoaKH);
        btn_xoaKH.setBounds(708, 161, 85, 25);

        btn_timKiemKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Search.png"))); // NOI18N
        btn_timKiemKH.setText("Tìm Kiếm");
        add(btn_timKiemKH);
        btn_timKiemKH.setBounds(708, 52, 112, 25);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit.png"))); // NOI18N
        jButton1.setText("Thoát");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1);
        jButton1.setBounds(838, 161, 83, 25);

        txt_tuKhoa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_tuKhoaKeyReleased(evt);
            }
        });
        add(txt_tuKhoa);
        txt_tuKhoa.setBounds(838, 53, 164, 22);

        tbl_KhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbl_KhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_KhachHangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_KhachHang);

        add(jScrollPane2);
        jScrollPane2.setBounds(20, 360, 1084, 274);

        jLabel6.setText("Giới tính");
        add(jLabel6);
        jLabel6.setBounds(378, 110, 45, 16);
        add(txt_gioiTinhKH);
        txt_gioiTinhKH.setBounds(467, 107, 136, 22);

        jLabel7.setText("Trạng thái");
        add(jLabel7);
        jLabel7.setBounds(378, 161, 53, 16);

        txt_trangThaiKH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hoạt động", "Đã khóa" }));
        add(txt_trangThaiKH);
        txt_trangThaiKH.setBounds(467, 158, 136, 22);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/hinh-anh-background.jpeg"))); // NOI18N
        jLabel8.setText("jLabel8");
        add(jLabel8);
        jLabel8.setBounds(0, 0, 1140, 680);
    }// </editor-fold>//GEN-END:initComponents
 public boolean kiemTraMaKH(String ma) {
        for (int i = 0; i < listKH.size(); i++) {
            if (listKH.get(i).getMaKH().equals(ma)) {
                return false;
            }
        }
        return true;
    }

    private void btn_themKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themKHActionPerformed
        // TODO add your handling code here:
        String maKH = txt_maKH.getText();
        String tenKH = txt_tenKH.getText();
        String sdtKH = txt_sdtKH.getText();
        String emailKH = txt_emailKH.getText();
        String gioiTinhKH = txt_gioiTinhKH.getText();
        String diaChiKH = txt_diaChiKH.getText();

        if (maKH.equals("")) {
            JOptionPane.showMessageDialog(this, "Mã khách hàng không được để trống");
            return;
        } else if (!kiemTraMaKH(maKH)) {
            JOptionPane.showMessageDialog(this, "Mã khách hàng đã tồn tại");
            return;
        }
        if (tenKH.equals("")) {
            JOptionPane.showMessageDialog(this, "Họ tên không được để trông");
            return;
        } else if (!validate.kiemTraTen(txt_tenKH.getText())) {
            JOptionPane.showMessageDialog(this, "Tên không được chứa số!");
            return;
        }
        if (emailKH.equals("")) {
            JOptionPane.showMessageDialog(this, "Ko được bỏ trống email");
            return;
        } else if (!validate.kiemTraEmail(txt_emailKH.getText())) {
            JOptionPane.showMessageDialog(this, "Không đúng định dạng email");
            return;
        }
        if (sdtKH.equals("")) {
            JOptionPane.showMessageDialog(this, "SDT không được để trống");
            return;
        } else if (!validate.kiemTraSDT(sdtKH).equals("")) {
            JOptionPane.showMessageDialog(this, validate.kiemTraSDT(sdtKH));
            return;

        }
        int trangThaiKH = 0;
        if (txt_trangThaiKH.getSelectedItem().toString().equals("Hoạt động")) {
            trangThaiKH = 1;
        }
        if (maKH.equals("")) {
            JOptionPane.showMessageDialog(this, "Mã nhân viên không được để trống");
            return;
        }
        KhachHang kh = new KhachHang();
        kh.setMaKH(maKH);
        kh.setTenKH(tenKH);
        kh.setEmailKH(emailKH);
        kh.setSdtKH(sdtKH);
        kh.setGioiTinhKH(gioiTinhKH);
        kh.setTrangThaiKH(trangThaiKH);
        kh.setDiaChiKH(diaChiKH);

        listKH.add(kh);
        saveFile();
        getData(listKH);
        JOptionPane.showMessageDialog(this, "Thêm khách hàng thành công");
        reset();
    }//GEN-LAST:event_btn_themKHActionPerformed

    private void txt_tuKhoaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tuKhoaKeyReleased
        // TODO add your handling code here:
        String tuKhoa = txt_tuKhoa.getText();
        if (tuKhoa.equals("")) {
            getData(listKH);
        } else {
            ArrayList<KhachHang> listTimKiem = new ArrayList<>();
            for (int i = 0; i < listKH.size(); i++) {
                if (listKH.get(i).getMaKH().contains(tuKhoa) || listKH.get(i).getSdtKH().contains(tuKhoa) || listKH.get(i).getEmailKH().contains(tuKhoa)) {
                    listTimKiem.add(listKH.get(i));
                }
            }
            getData(listTimKiem);
        }

    }//GEN-LAST:event_txt_tuKhoaKeyReleased

    private void btn_suaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaKHActionPerformed
        // TODO add your handling code here:
        String hoTen = txt_tenKH.getText();
        String sdt = txt_sdtKH.getText();
        String email = txt_emailKH.getText();
        String gioiTinh = txt_gioiTinhKH.getText();
        String diaChi = txt_diaChiKH.getText();
        int trangThai = 0;
        if (txt_trangThaiKH.getSelectedItem().toString().equals("Hoạt động")) {
            trangThai = 1;
        }
        if (hoTen.equals("")) {
            JOptionPane.showMessageDialog(this, "Họ tên không được để trông");
            return;
        } else if (!validate.kiemTraTen(txt_tenKH.getText())) {
            JOptionPane.showMessageDialog(this, "Tên không được chứa số!");
            return;
        }
        if (email.equals("")) {
            JOptionPane.showMessageDialog(this, "Ko được bỏ trống email");
            return;
        } else if (!validate.kiemTraEmail(txt_emailKH.getText())) {
            JOptionPane.showMessageDialog(this, "Không đúng định dạng email");
            return;
        }
        if (sdt.equals("")) {
            JOptionPane.showMessageDialog(this, "SDT không được để trống");
            return;
        } else if (!validate.kiemTraSDT(sdt).equals("")) {
            JOptionPane.showMessageDialog(this, validate.kiemTraSDT(sdt));
            return;

        }

        kh.setTenKH(hoTen);
        kh.setSdtKH(sdt);
        kh.setEmailKH(email);
        kh.setGioiTinhKH(gioiTinh);
        kh.setTrangThaiKH(trangThai);
        kh.setDiaChiKH(diaChi);
        listKH.set(index, kh);

        saveFile();
        getData(listKH);
        JOptionPane.showMessageDialog(this, "Chỉnh sửa khách hàng thành công");
        reset();

    }//GEN-LAST:event_btn_suaKHActionPerformed
    int index;
    private void tbl_KhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_KhachHangMouseClicked
        // TODO add your handling code here:
        int row = tbl_KhachHang.getSelectedRow();
        String maKH = tbl_KhachHang.getValueAt(row, 0).toString();

        for (int i = 0; i < listKH.size(); i++) {
            if (maKH.equals(listKH.get(i).getMaKH())) {
                kh = listKH.get(i);
                index = i;
                break;
            }
        }
        txt_maKH.setEnabled(false);
        txt_maKH.setText(kh.getMaKH());
        txt_tenKH.setText(kh.getTenKH());
        txt_emailKH.setText(kh.getEmailKH());
        txt_diaChiKH.setText(kh.getDiaChiKH());
        txt_gioiTinhKH.setText(kh.getGioiTinhKH());
        txt_sdtKH.setText(kh.getSdtKH());
//        txt_matKhau.setText(kh.getMatKhau());
//        txt_matKhau.setEnabled(false);

        if (kh.getTrangThaiKH() == 1) {
            txt_trangThaiKH.setSelectedItem("Hoạt động");
        } else {
            txt_trangThaiKH.setSelectedItem("Đã khóa");
        }
        btn_themKH.setEnabled(false);

    }//GEN-LAST:event_tbl_KhachHangMouseClicked

    private void btn_xoaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaKHActionPerformed
        // TODO add your handling code here:
        Integer confirm = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xóa không?", "Xóa", 2);
        if (confirm == JOptionPane.YES_OPTION) {
            if (listKH.remove(kh)) {
                saveFile();
                getData(listKH);
                JOptionPane.showMessageDialog(this, "Xóa thành công ");
                reset();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại");
            }

        }
    }//GEN-LAST:event_btn_xoaKHActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JComponent comp = (JComponent) evt.getSource();
        Window win = SwingUtilities.getWindowAncestor(comp);
        win.dispose();
        frmMain main = new frmMain();
        main.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    public void saveFile() {

        try {
            BufferedWriter bw = null; //Khởi tạo
            FileWriter fw = null; //Khởi tạo
            String data = ""; //Tạo một string data bằng rỗng.
            for (int i = 0; i < listKH.size(); i++) {
                String row = ""; //tạo hàng rỗng
                row = row + listKH.get(i).getMaKH() + "\t";
                row = row + listKH.get(i).getTenKH() + "\t";
                row = row + listKH.get(i).getGioiTinhKH() + "\t";
                row = row + listKH.get(i).getEmailKH() + "\t";
                row = row + listKH.get(i).getSdtKH() + "\t";
                row = row + listKH.get(i).getDiaChiKH() + "\t";
                row = row + listKH.get(i).getTrangThaiKH() + "\n";
                data += row;
            }
            fw = new FileWriter("khachhang.txt");
            bw = new BufferedWriter(fw);
            bw.write(data);
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(frmKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void reset() {
        txt_diaChiKH.setText("");
        txt_emailKH.setText("");
        txt_gioiTinhKH.setText("");
        txt_maKH.setText("");
        txt_sdtKH.setText("");
        txt_tenKH.setText("");
        txt_trangThaiKH.setSelectedIndex(0);
        btn_suaKH.setEnabled(true);
        btn_themKH.setEnabled(true);
        btn_xoaKH.setEnabled(true);
        getData(listKH);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_suaKH;
    private javax.swing.JButton btn_themKH;
    private javax.swing.JButton btn_timKiemKH;
    private javax.swing.JButton btn_xoaKH;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tbl_KhachHang;
    private javax.swing.JTextField txt_diaChiKH;
    private javax.swing.JTextField txt_emailKH;
    private javax.swing.JTextField txt_gioiTinhKH;
    private javax.swing.JTextField txt_maKH;
    private javax.swing.JTextField txt_sdtKH;
    private javax.swing.JTextField txt_tenKH;
    private javax.swing.JComboBox<String> txt_trangThaiKH;
    private javax.swing.JTextField txt_tuKhoa;
    // End of variables declaration//GEN-END:variables
}
