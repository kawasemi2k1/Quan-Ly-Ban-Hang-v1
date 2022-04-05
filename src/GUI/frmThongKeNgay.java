package GUI;

import OBJ.HoaDon;
import OBJ.NhanVien;
import OBJ.SanPham;
import static com.sun.org.apache.xalan.internal.lib.ExsltDynamic.map;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
//import static jdk.nashorn.internal.objects.NativeArray.map;
//import static jdk.nashorn.internal.objects.NativeDebug.map;

public class frmThongKeNgay extends javax.swing.JPanel {

    private DefaultTableModel defaultTableModel;
   //  frmThongKe thongKe = new frmThongKe();
    frmNhanVien c = new frmNhanVien();
    ArrayList<HoaDon> listHD = new ArrayList<>();
    ArrayList<HoaDon> arrayList = new ArrayList<>();
    Map<String, HoaDon> map = new HashMap<>();
     
    public frmThongKeNgay() {
        initComponents();
        loadFile();
       // listHD = thongKe.listHD;
        tinhTongCacPhanTu();
    }
    public void loadFile() {
        try {
            FileReader fr = null;
            BufferedReader br = null;
            listHD = new ArrayList<>();
            fr = new FileReader("hoadon.txt");
            br = new BufferedReader(fr);
            String s = null;

            try {
                while ((s = br.readLine()) != null) {
                    String[] arr = s.split("\t");
                    HoaDon hd = new HoaDon();
                    hd.setMaHD(arr[0]);
                    hd.setMaKH(arr[1]);
                    hd.setMaNV(arr[2]);
                    hd.setMaSP(arr[3]);
                    hd.setDonGia(Double.parseDouble(arr[4]));
                    hd.setSoluong(Integer.parseInt(arr[5]));
                    hd.setNgayBan(arr[6]);
                    hd.setThanhTien(Double.parseDouble(arr[7]));
                    listHD.add(hd);
                }
            } catch (IOException ex) {
                Logger.getLogger(frmKhachHang.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(frmKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getData(ArrayList<HoaDon> list) {
        defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("Mã sản phẩm");
        defaultTableModel.addColumn("Mã nhân viên");
        defaultTableModel.addColumn("Tên sản phẩm");
        defaultTableModel.addColumn("Tên nhân viên");
        defaultTableModel.addColumn("Số lượng");
        defaultTableModel.addColumn("Đơn giá");
        defaultTableModel.addColumn("Thành tiền");
        frmBanHang banHang = new frmBanHang();
        frmNhanVien nhanVien = new frmNhanVien();

        for (HoaDon obj : list) {
            Vector vector = new Vector();
            vector.add(obj.getMaSP());
            vector.add(obj.getMaNV());
            vector.add(banHang.getSPByCode(obj.getMaSP()).getTenSP());
            vector.add(nhanVien.getNameByCode(obj.getMaNV()).getHoTen());
            vector.add(obj.getSoluong());
            vector.add(banHang.getSPByCode(obj.getMaSP()).getGiaBan());
            vector.add(obj.getThanhTien());

            defaultTableModel.addRow(vector);
        }
        tbl_thongKe.setModel(defaultTableModel);
    }

    public void tinhTongCacPhanTu() {

        Collections.sort(listHD, new Comparator<HoaDon>() {
            @Override
            public int compare(HoaDon t, HoaDon t1) {
                return t.getMaSP().compareTo(t1.getMaSP());
            }
        });

        HoaDon hoaDonTK = listHD.get(0);
        for (int i = 1; i < listHD.size(); i++) {
            if (hoaDonTK.getMaSP().equals(listHD.get(i).getMaSP())) {
                hoaDonTK.setThanhTien(hoaDonTK.getThanhTien() + listHD.get(i).getThanhTien());
                hoaDonTK.setSoluong(hoaDonTK.getSoluong() + listHD.get(i).getSoluong());
            } else {
                hoaDonTK = listHD.get(i);
            }
            map.put(hoaDonTK.getMaSP(), hoaDonTK);
        }
        ArrayList<HoaDon> listSort = new ArrayList<HoaDon>(map.values());
        Collections.sort(arrayList, new Comparator<HoaDon>() {
            @Override
            public int compare(HoaDon t, HoaDon t1
            ) {
                return t.getThanhTien() < t1.getThanhTien() ? 1 : -1;
            }

        });
        if (listSort.size() < 5) {
            for (int i = 0; i < listSort.size(); i++) {
                arrayList.add(listSort.get(i));
            }
        } else {
            for (int i = 0; i < 5; i++) {
                arrayList.add(listSort.get(i));
            }
        } 
    }

    public String convertDateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(date);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        datetimeTK = new com.toedter.calendar.JDateChooser();
        txt_NV = new javax.swing.JTextField();
        btn_tkNgayMaNV = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btn_top5SP = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_thongKe = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();

        setLayout(null);

        datetimeTK.setForeground(new java.awt.Color(255, 0, 255));
        add(datetimeTK);
        datetimeTK.setBounds(54, 122, 150, 40);
        add(txt_NV);
        txt_NV.setBounds(220, 130, 130, 30);

        btn_tkNgayMaNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/thongke.png"))); // NOI18N
        btn_tkNgayMaNV.setText("Thống kê");
        btn_tkNgayMaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tkNgayMaNVActionPerformed(evt);
            }
        });
        add(btn_tkNgayMaNV);
        btn_tkNgayMaNV.setBounds(370, 130, 120, 30);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 0));
        jLabel1.setText("Thống kê theo ngày và mã nhân viên tìm kiếm");
        add(jLabel1);
        jLabel1.setBounds(54, 61, 420, 34);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 255, 102));
        jLabel2.setText("Top 5 sản phẩm bán chạy nhất");
        add(jLabel2);
        jLabel2.setBounds(580, 60, 270, 70);

        btn_top5SP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/hot.png"))); // NOI18N
        btn_top5SP.setText("Thống kê");
        btn_top5SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_top5SPActionPerformed(evt);
            }
        });
        add(btn_top5SP);
        btn_top5SP.setBounds(735, 130, 120, 25);

        tbl_thongKe.setForeground(new java.awt.Color(0, 153, 153));
        tbl_thongKe.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbl_thongKe);

        add(jScrollPane1);
        jScrollPane1.setBounds(54, 306, 958, 307);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cbc22ca5a3d7568a742262639a9f6b3f.jpg"))); // NOI18N
        jLabel3.setText("jLabel3");
        add(jLabel3);
        jLabel3.setBounds(0, 0, 1050, 640);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_tkNgayMaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tkNgayMaNVActionPerformed
        // TODO add your handling code here:
        String dateSearch = convertDateToString(datetimeTK.getDate());
        String txtKey = txt_NV.getText();
        ArrayList<HoaDon> ListTK = new ArrayList<>();

        for (int i = 0; i < listHD.size(); i++) {
            if (listHD.get(i).getMaNV().contains(txtKey) && dateSearch.equals(listHD.get(i).getNgayBan())) {
                ListTK.add(listHD.get(i));
            }
        }
        getData(ListTK);
    }//GEN-LAST:event_btn_tkNgayMaNVActionPerformed

    private void btn_top5SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_top5SPActionPerformed
        // TODO add your handling code here:
        getData(arrayList);
    }//GEN-LAST:event_btn_top5SPActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_tkNgayMaNV;
    private javax.swing.JButton btn_top5SP;
    private com.toedter.calendar.JDateChooser datetimeTK;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_thongKe;
    private javax.swing.JTextField txt_NV;
    // End of variables declaration//GEN-END:variables
}
