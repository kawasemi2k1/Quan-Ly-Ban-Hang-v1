package GUI;

import OBJ.HoaDon;
import OBJ.SanPham;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class frmThongKeTheoSanPham extends javax.swing.JPanel {

    public frmThongKeTheoSanPham() {
        initComponents();
        duyetSP();
        getDataSapHet(listSPSapHet);
        getDataTonKho(listSPTonKho);
    }
     private DefaultTableModel defaultTableModel;

    frmSanPham frmSP1 = new frmSanPham();
    static ArrayList<SanPham> listSP = new ArrayList<>();
    public static ArrayList<SanPham> loadFileSP() {
        frmSanPham frm = new frmSanPham();
        frm.loadFile();
        return frm.listSP;
    }
    ArrayList<HoaDon> listHD = new ArrayList<>();
    static ArrayList<SanPham> listSPTonKho = new ArrayList<>();
    static ArrayList<SanPham> listSPSapHet = new ArrayList<>();

    public static void duyetSP() {
        for (SanPham obj : loadFileSP()) {
            if (obj.getSoLuong() < 5) {
                listSPSapHet.add(obj);
            }
            if (obj.getSoLuong() > 100) {
                listSPTonKho.add(obj);
            }
        }
    }
    public void getDataSapHet(ArrayList<SanPham> list) {
        defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("Mã Sản phẩm");
        defaultTableModel.addColumn("Tên Sản phẩm");
        defaultTableModel.addColumn("Số lượng");

        for (SanPham obj : list) {
            Vector vector = new Vector();
            SanPham sp = new frmBanHang().getSPByCode(obj.getMaSP());
            vector.add(obj.getMaSP());
            vector.add(sp.getTenSP());
            vector.add(obj.getSoLuong());

            defaultTableModel.addRow(vector);
        }
        tbl_sapHet.setModel(defaultTableModel);
    }
    public void getDataTonKho(ArrayList<SanPham> list) {
        defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("Mã Sản phẩm");
        defaultTableModel.addColumn("Tên Sản phẩm");
        defaultTableModel.addColumn("Số lượng");

        for (SanPham obj : list) {
            Vector vector = new Vector();
            SanPham sp = new frmBanHang().getSPByCode(obj.getMaSP());
            vector.add(obj.getMaSP());
            vector.add(sp.getTenSP());
            vector.add(obj.getSoLuong());

            defaultTableModel.addRow(vector);
        }
        tbl_tonKho.setModel(defaultTableModel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_tonKho = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_sapHet = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bgtksp.png"))); // NOI18N

        setLayout(null);

        jLabel1.setFont(new java.awt.Font("Sitka Text", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 153));
        jLabel1.setText("THỐNG KÊ THEO SẢN PHẨM");
        add(jLabel1);
        jLabel1.setBounds(270, 50, 620, 100);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 255));
        jLabel3.setText("Sản Phẩm Tồn Kho");
        add(jLabel3);
        jLabel3.setBounds(70, 170, 250, 60);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 102));
        jLabel4.setText("Sản Phẩm Sắp Hết");
        add(jLabel4);
        jLabel4.setBounds(720, 170, 230, 60);

        tbl_tonKho.setForeground(new java.awt.Color(51, 102, 255));
        tbl_tonKho.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbl_tonKho);

        add(jScrollPane1);
        jScrollPane1.setBounds(50, 270, 452, 280);

        tbl_sapHet.setForeground(new java.awt.Color(102, 0, 102));
        tbl_sapHet.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tbl_sapHet);

        add(jScrollPane2);
        jScrollPane2.setBounds(590, 270, 452, 280);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bgtksp.png"))); // NOI18N
        jLabel5.setText("jLabel5");
        add(jLabel5);
        jLabel5.setBounds(0, 0, 1110, 620);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbl_sapHet;
    private javax.swing.JTable tbl_tonKho;
    // End of variables declaration//GEN-END:variables
}
