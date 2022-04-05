// nút thoát
// kiểm tra quá số lượng trong kho mà vẫn thêm đc ( bị xuống -1)
// xóa bớt chứ ko xóa hết
package GUI;

import static GUI.frmKhachHang.listKH;
import static GUI.frmSanPham.listSP;
import OBJ.HoaDon;
import OBJ.KhachHang;
import OBJ.NhanVien;
import OBJ.SanPham;
import java.awt.Window;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author ADMIN
 */
public class frmBanHang extends javax.swing.JPanel {

    KhachHang kh = new KhachHang();
    SanPham sp = new SanPham();
    ArrayList<HoaDon> listGH = new ArrayList<>();
    ArrayList<HoaDon> listHD = new ArrayList<>();
    private DefaultTableModel defaultTableModel;
    NumberFormat formater = new DecimalFormat("###,###");
    HoaDon hd = new HoaDon();

    /**
     * Creates new form frmBanHang
     */
    public frmBanHang() {
        initComponents();
        getDataKH(loadFileKH());
        getDataSP(loadFileSP());

    }

    public void saveFile() {
        try {
            FileWriter fw = null;
            BufferedWriter bw = null;
            String data = "";
            for (int i = 0; i < listHD.size(); i++) {
                String row = "";
                row += listHD.get(i).getMaHD() + "\t";
                row += listHD.get(i).getMaKH() + "\t";
                row += listHD.get(i).getMaNV() + "\t";
                row += listHD.get(i).getMaSP() + "\t";
                row += listHD.get(i).getDonGia() + "\t";
                row += listHD.get(i).getSoluong() + "\t";
                row += listHD.get(i).getNgayBan() + "\t";
                row += listHD.get(i).getThanhTien() + "\n";
                data += row;
            }
            fw = new FileWriter("hoadon.txt", true);
            bw = new BufferedWriter(fw);
            bw.write(data);
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(frmKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    int indexMaSanPhamTrongGioHang;

    private boolean checkMaSPExistGioHang(String maSP, ArrayList<HoaDon> listHD) {
        for (int i = 0; i < listHD.size(); i++) {
            if (maSP.equalsIgnoreCase(listGH.get(i).getMaSP())) {
                indexMaSanPhamTrongGioHang = i;
                return true;
            }
        }
        return false;
    }

    public ArrayList<KhachHang> loadFileKH() {
        frmKhachHang khachHang = new frmKhachHang();
        khachHang.loadFile();
        return frmKhachHang.listKH;
    }

    public ArrayList<SanPham> loadFileSP() {
        frmSanPham sp = new frmSanPham();
        sp.loadFile();
        return frmSanPham.listSP;
    }
    public ArrayList<NhanVien> loadFileNV() {
        frmNhanVien nv = new frmNhanVien();
        nv.loadFile();
        return frmNhanVien.listNV;
    }

    public void getDataSP(ArrayList<SanPham> listSP) {
        defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("Mã sản phẩm");
        defaultTableModel.addColumn("Tên sản phẩm");
        defaultTableModel.addColumn("Số lượng");
        defaultTableModel.addColumn("Đơn giá");

        for (SanPham sp : listSP) {
            Vector vector = new Vector();
            vector.add(sp.getMaSP());
            vector.add(sp.getTenSP());
            vector.add(sp.getSoLuong());
            vector.add(sp.getGiaBan() + " vnd");
            defaultTableModel.addRow(vector);
        }
        tbl_SP.setModel(defaultTableModel);
    }

    public void getDataKH(ArrayList<KhachHang> listKH) {
        defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("Mã KH");
        defaultTableModel.addColumn("Tên KH");
        defaultTableModel.addColumn("Số điện thoại");
        defaultTableModel.addColumn("Email");
        defaultTableModel.addColumn("Địa chỉ");

        for (KhachHang kh : listKH) {
            Vector vector = new Vector();
            vector.add(kh.getMaKH());
            vector.add(kh.getTenKH());
            vector.add(kh.getSdtKH());
            vector.add(kh.getEmailKH());
            vector.add(kh.getDiaChiKH());
            defaultTableModel.addRow(vector);
        }
        tbl_KH.setModel(defaultTableModel);
    }

    public void getDataGH(ArrayList<HoaDon> listGH) {
        defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("Mã SP");
        defaultTableModel.addColumn("Tên SP");
        defaultTableModel.addColumn("ĐVT");
        defaultTableModel.addColumn("SL mua");
        defaultTableModel.addColumn("Đơn giá");
        defaultTableModel.addColumn("Thành tiền");

        for (HoaDon hd : listGH) {
            SanPham sanPham = new frmBanHang().getSPByCode(hd.getMaSP());
            Vector vector = new Vector();
            vector.add(hd.getMaSP());
            vector.add(sanPham.getTenSP());
            vector.add(sanPham.getDonViTinh());
            vector.add(formater.format(hd.getSoluong()));
            vector.add(formater.format(hd.getDonGia()));
            vector.add(formater.format(hd.getThanhTien()));
            defaultTableModel.addRow(vector);
        }
        tbl_gioHang.setModel(defaultTableModel);
    }

    public SanPham getSPByCode(String maSP) {
        ArrayList<SanPham> arrayList = loadFileSP();
        for (int i = 0; i < arrayList.size(); i++) {
            if (maSP.equals(arrayList.get(i).getMaSP())) {
                return arrayList.get(i);
            }
        }
        return null;
    }
    public NhanVien getNVByCode(String maNV) {
        ArrayList<NhanVien> arrayList = loadFileNV();
        for (int i = 0; i < arrayList.size(); i++) {
            if (maNV.equals(arrayList.get(i).getMaNhanVien())) {
                return arrayList.get(i);
            }
        }
        return null;
    }
    
    public void reset() {
        label_diaChi.setText("...");
        label_email.setText("...");
        label_hoTen.setText("...");
        label_maKH.setText("...");
        label_sdt.setText("...");
        label_tongTien.setText("...");
        tongTien = 0;
    }

    public void updateSoluong(String maSP, int soLuongMua, ArrayList<SanPham> listSP) {
        for (int i = 0; i < listSP.size(); i++) {
            if (maSP.equals(listSP.get(i).getMaSP())) {
                SanPham sp = listSP.get(i);
                sp.setSoLuong(sp.getSoLuong() - soLuongMua);
                listSP.set(i, sp);
            }
        }
    }

    public String timKiemTenSanPhamTheoMSP(String maSP) {
        for (int i = 0; i < loadFileSP().size(); i++) {
            if (maSP.equals(loadFileSP().get(i).getMaSP())) {
                return loadFileSP().get(i).getTenSP();
            }
        }
        return null;
    }

//    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_searchSP = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_SP = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txt_soLuong = new javax.swing.JTextField();
        btn_themVaoGioHang = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_searchKH = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_KH = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_gioHang = new javax.swing.JTable();
        btn_xoaKhoiGioHang = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        label_maKH = new javax.swing.JLabel();
        label_hoTen = new javax.swing.JLabel();
        label_sdt = new javax.swing.JLabel();
        label_email = new javax.swing.JLabel();
        label_diaChi = new javax.swing.JLabel();
        label_tongTien = new javax.swing.JLabel();
        btn_thanhToan = new javax.swing.JButton();
        btn_Thoat = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1098, 676));
        setLayout(null);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/product.png"))); // NOI18N
        jLabel2.setText("Sản Phẩm");

        txt_searchSP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_searchSPKeyReleased(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Search.png"))); // NOI18N
        jButton2.setText("Tìm kiếm");

        tbl_SP.setForeground(new java.awt.Color(102, 102, 0));
        tbl_SP.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_SP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_SPMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_SP);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Số lượng:");

        txt_soLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_soLuongActionPerformed(evt);
            }
        });

        btn_themVaoGioHang.setText("Thêm vào giỏ hàng");
        btn_themVaoGioHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themVaoGioHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(txt_searchSP, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(jButton2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(txt_soLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btn_themVaoGioHang)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_searchSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(36, 36, 36)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_soLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_themVaoGioHang))
                .addGap(33, 33, 33))
        );

        add(jPanel1);
        jPanel1.setBounds(42, 296, 464, 322);

        jPanel2.setForeground(new java.awt.Color(0, 153, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/users.png"))); // NOI18N
        jLabel1.setText("Khách Hàng");

        txt_searchKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_searchKHActionPerformed(evt);
            }
        });
        txt_searchKH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_searchKHKeyReleased(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Search.png"))); // NOI18N
        jButton1.setText("Tìm kiếm");

        tbl_KH.setForeground(new java.awt.Color(51, 0, 204));
        tbl_KH.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_KH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_KHMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_KH);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(txt_searchKH, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_searchKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        add(jPanel2);
        jPanel2.setBounds(42, 13, 464, 276);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 51, 0));
        jLabel4.setText("Thông tin hóa đơn");

        jLabel5.setText("Mã KH");

        jLabel6.setText("Họ và tên");

        jLabel7.setText("SDT");

        jLabel8.setText("Email");

        jLabel9.setText("Địa chỉ");

        jLabel10.setText("Giỏ hàng");

        tbl_gioHang.setForeground(new java.awt.Color(153, 0, 153));
        tbl_gioHang.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_gioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_gioHangMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_gioHang);

        btn_xoaKhoiGioHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
        btn_xoaKhoiGioHang.setText("Xóa khỏi giỏ hàng");
        btn_xoaKhoiGioHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaKhoiGioHangActionPerformed(evt);
            }
        });

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/money.png"))); // NOI18N
        jLabel11.setText("Tổng tiền");

        jLabel12.setText("(VNĐ)");

        label_maKH.setForeground(new java.awt.Color(51, 0, 255));
        label_maKH.setText("...");

        label_hoTen.setForeground(new java.awt.Color(51, 0, 255));
        label_hoTen.setText("...");

        label_sdt.setForeground(new java.awt.Color(51, 0, 255));
        label_sdt.setText("...");

        label_email.setForeground(new java.awt.Color(51, 0, 255));
        label_email.setText("...");

        label_diaChi.setForeground(new java.awt.Color(51, 0, 255));
        label_diaChi.setText("...");

        label_tongTien.setForeground(new java.awt.Color(204, 0, 204));
        label_tongTien.setText("...");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(label_diaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label_maKH, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(label_hoTen, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                            .addComponent(label_email, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_xoaKhoiGioHang)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(34, 34, 34)
                        .addComponent(label_tongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)))
                .addGap(19, 19, 19)
                .addComponent(jLabel12)
                .addGap(118, 118, 118))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(label_maKH)
                    .addComponent(label_hoTen))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(label_sdt)
                    .addComponent(label_email))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(label_diaChi))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addGap(28, 28, 28)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btn_xoaKhoiGioHang)
                        .addGap(0, 51, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(label_tongTien)
                            .addComponent(jLabel12))
                        .addContainerGap())))
        );

        add(jPanel3);
        jPanel3.setBounds(535, 23, 602, 470);

        btn_thanhToan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pay.png"))); // NOI18N
        btn_thanhToan.setText("Thanh Toán");
        btn_thanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_thanhToanActionPerformed(evt);
            }
        });
        add(btn_thanhToan);
        btn_thanhToan.setBounds(770, 530, 121, 25);

        btn_Thoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logout.png"))); // NOI18N
        btn_Thoat.setText("Thoát");
        btn_Thoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThoatActionPerformed(evt);
            }
        });
        add(btn_Thoat);
        btn_Thoat.setBounds(990, 530, 85, 25);

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tai-hinh-nen-dep-cho-may-tinh-min.jpg"))); // NOI18N
        jLabel13.setText("jLabel13");
        add(jLabel13);
        jLabel13.setBounds(0, 0, 1140, 670);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_soLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_soLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_soLuongActionPerformed
    double tongTien;
     int soTrung;
    public int trungMaSP(String ma){
        for (int i = 0; i < listGH.size(); i++) {
            if(listGH.get(i).getMaSP().equals(ma)){
                soTrung = i;
                return i;
            }
        }
        return -1;
    }
    private void btn_themVaoGioHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themVaoGioHangActionPerformed
        // TODO add your handling code here:
        int soLuongMua = Integer.parseInt(txt_soLuong.getText());
        if (trungMaSP(maTrung) != -1) {
            if (listGH.get(soTrung).getSoluong() + soLuongMua > getSPByCode(maTrung).getSoLuong()) {
                JOptionPane.showMessageDialog(this, "Vượt quá số lượng hiện có!");
                return;
            }
        }
        if (soLuongMua <= 0) {
            JOptionPane.showMessageDialog(this, "Hãy mua ít nhất 1 sản phẩm");
        } else if (soLuongMua > sp.getSoLuong()) {
            JOptionPane.showMessageDialog(this, "Số lượng bạn chọn lớn hơn số lượng có trong kho!");
        } else {
            HoaDon hoaDon = new HoaDon();
            hoaDon.setMaSP(sp.getMaSP());
            hoaDon.setDonGia(sp.getGiaBan());
            hoaDon.setSoluong(soLuongMua);
            hoaDon.setThanhTien(sp.getGiaBan() * soLuongMua);
            if (checkMaSPExistGioHang(hoaDon.getMaSP(), listGH)) {
                HoaDon hoaDon1 = listGH.get(indexMaSanPhamTrongGioHang);
                hoaDon1.setSoluong(Integer.parseInt(txt_soLuong.getText()) + hoaDon1.getSoluong());
                hoaDon1.setThanhTien(hoaDon1.getSoluong() * hoaDon.getDonGia());
                listGH.set(indexMaSanPhamTrongGioHang, hoaDon1);
            } else {
                listGH.add(hoaDon);

            }
            getDataGH(listGH);
            JOptionPane.showMessageDialog(this, "Thêm vào giỏ hàng thành công");
            tongTien = 0;
            for (int i = 0; i < listGH.size(); i++) {
                tongTien += listGH.get(i).getThanhTien();
            }
            label_tongTien.setText(formater.format(tongTien));
            txt_soLuong.setText("1");
        }


    }//GEN-LAST:event_btn_themVaoGioHangActionPerformed

    private void tbl_KHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_KHMouseClicked
        // TODO add your handling code here:
        int row = tbl_KH.getSelectedRow();
        String maKH = tbl_KH.getValueAt(row, 0).toString();
        for (int i = 0; i < loadFileKH().size(); i++) {
            if (maKH.equals(loadFileKH().get(i).getMaKH())) {
                kh = loadFileKH().get(i);
                break;
            }
        }
        label_hoTen.setText(kh.getTenKH());
        label_maKH.setText(kh.getMaKH());
        label_sdt.setText(kh.getSdtKH());
        label_email.setText(kh.getEmailKH());
        label_diaChi.setText(kh.getDiaChiKH());
    }//GEN-LAST:event_tbl_KHMouseClicked
    String maTrung = "";
    private void tbl_SPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_SPMouseClicked
        // TODO add your handling code here:

        int row = tbl_SP.getSelectedRow();
        String maSP = tbl_SP.getValueAt(row, 0).toString();
        maTrung = maSP;
        for (int i = 0; i < loadFileSP().size(); i++) {
            if (maSP.equals(loadFileSP().get(i).getMaSP())) {
                sp = loadFileSP().get(i);
                break;
            }
        }
        txt_soLuong.setText("1");
        System.out.println(sp.getMaSP());
    }//GEN-LAST:event_tbl_SPMouseClicked

    private void btn_thanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_thanhToanActionPerformed
        // TODO add your handling code here:
        if (kh.getMaKH() == null || kh.getMaKH().equals(" ")) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng trước!");
            return;
        } else {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
            SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
            String maHD = kh.getMaKH() + "-" + sdf.format(date);
            for (int i = 0; i < listGH.size(); i++) {
                // HoaDon hd = new HoaDon();
                HoaDon hd = listGH.get(i);
                hd.setMaHD(maHD);
                hd.setMaKH(kh.getMaKH());
                // hd.setMaNV("01"); 
                hd.setMaNV(frmLogin.maNV);
                hd.setMaSP(listGH.get(i).getMaSP());
                hd.setNgayBan(sdf2.format(date));
//                hd.setDonGia(listGH.get(i).getDonGia());
//                hd.setSoluong(listGH.get(i).getSoluong());
//                hd.setThanhTien(listGH.get(i).getThanhTien());
                listGH.set(i, hd);
                listHD.add(hd);
            }
        }
        JTextField txtSoTien = new JTextField(10);
        JPanel myPanel = new JPanel();
        myPanel.add(Box.createHorizontalStrut(10)); // a spacer
        myPanel.add(new JLabel("Số tiền khách đưa:"));
        myPanel.add(txtSoTien);
        int result = JOptionPane.showConfirmDialog(null, myPanel, "Số tiền khách thanh toán ", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            double soTienKhachDua = Double.parseDouble(txtSoTien.getText());
            if (soTienKhachDua < tongTien) {
                JOptionPane.showMessageDialog(this, "Số tiền khách đưa chưa đủ!");
                return;
            } else if (soTienKhachDua > tongTien) {
                //  JOptionPane.showMessageDialog(this,"Trả lại cho khách "+(soTienKhachDua - tongTien)+" !");
                JOptionPane.showMessageDialog(this, "Trả lại cho khách " + formater.format(soTienKhachDua - tongTien) + " đồng !");
            }
            saveFile();
            JOptionPane.showMessageDialog(this, "Thanh toán thành công!");
            frmSanPham sanPham = new frmSanPham();
            sanPham.loadFile();
            for (int i = 0; i < listGH.size(); i++) {
                updateSoluong(listGH.get(i).getMaSP(), listGH.get(i).getSoluong(), frmSanPham.listSP);
            }
            sanPham.saveFile();
//            sendmail();
            listGH.clear();
            reset();
            getDataSP(loadFileSP());
            getDataGH(listGH);
        }

    }//GEN-LAST:event_btn_thanhToanActionPerformed

    private void btn_xoaKhoiGioHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaKhoiGioHangActionPerformed
        // TODO add your handling code here:
        double tienGiam;
        for (int i = 0; i < listGH.size(); i++) {
            if (maSP.equals(listGH.get(i).getMaSP())) {
                tienGiam = listGH.get(i).getThanhTien();
                listGH.remove(i);
                tongTien = tongTien - tienGiam;
                label_tongTien.setText(String.valueOf(tongTien));
                label_tongTien.setText(formater.format(tongTien));
            }
        }
        getDataGH(listGH);
    }//GEN-LAST:event_btn_xoaKhoiGioHangActionPerformed
    String maSP;
    private void tbl_gioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_gioHangMouseClicked
        // TODO add your handling code here:
        int row = tbl_gioHang.getSelectedRow();
        maSP = tbl_gioHang.getValueAt(row, 0).toString();
    }//GEN-LAST:event_tbl_gioHangMouseClicked

    private void txt_searchKHKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKHKeyReleased
        // TODO add your handling code here:
        String tuKhoaKH = txt_searchKH.getText();
        if (tuKhoaKH.equals("")) {
            getDataKH(loadFileKH());
        } else {
            ArrayList<KhachHang> listTimKiem = new ArrayList<>();
            for (int i = 0; i < listKH.size(); i++) {
                if (listKH.get(i).getMaKH().contains(tuKhoaKH) || listKH.get(i).getSdtKH().contains(tuKhoaKH) || listKH.get(i).getEmailKH().contains(tuKhoaKH)) {
                    listTimKiem.add(listKH.get(i));
                }
            }
            getDataKH(listTimKiem);
        }
    }//GEN-LAST:event_txt_searchKHKeyReleased

    private void txt_searchSPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchSPKeyReleased
        // TODO add your handling code here:
        String tuKhoaSP = txt_searchSP.getText();
        if (tuKhoaSP.equals("")) {
            getDataSP(listSP);
        } else {
            ArrayList<SanPham> listTimKiem = new ArrayList<>();
            for (int i = 0; i < listSP.size(); i++) {
                if (listSP.get(i).getMaSP().contains(tuKhoaSP)) {
                    listTimKiem.add(listSP.get(i));
                }
            }
            getDataSP(listTimKiem);
        }
    }//GEN-LAST:event_txt_searchSPKeyReleased

    private void btn_ThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThoatActionPerformed
        // TODO add your handling code here:
        JComponent comp = (JComponent) evt.getSource();
        Window win = SwingUtilities.getWindowAncestor(comp);
        win.dispose();
        frmMain main = new frmMain();
        main.setVisible(true);
    }//GEN-LAST:event_btn_ThoatActionPerformed

    private void txt_searchKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_searchKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_searchKHActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Thoat;
    private javax.swing.JButton btn_thanhToan;
    private javax.swing.JButton btn_themVaoGioHang;
    private javax.swing.JButton btn_xoaKhoiGioHang;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel label_diaChi;
    private javax.swing.JLabel label_email;
    private javax.swing.JLabel label_hoTen;
    private javax.swing.JLabel label_maKH;
    private javax.swing.JLabel label_sdt;
    private javax.swing.JLabel label_tongTien;
    private javax.swing.JTable tbl_KH;
    private javax.swing.JTable tbl_SP;
    private javax.swing.JTable tbl_gioHang;
    private javax.swing.JTextField txt_searchKH;
    private javax.swing.JTextField txt_searchSP;
    private javax.swing.JTextField txt_soLuong;
    // End of variables declaration//GEN-END:variables
}
