package GUI;

import OBJ.HoaDon;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class frmThongKe extends javax.swing.JPanel {

    ArrayList<HoaDon> listHD = new ArrayList<>();
    frmBanHang banHang = new frmBanHang();
    Map<String, HoaDon> map = new HashMap<>();

    public frmThongKe() {
        initComponents();

        loadFile();
        tinhTongCacPhanTu();
        initFrame();
    }

    public boolean checkMa(String maNV) {
        for (int i = 1; i < listHD.size(); i++) {
            if (maNV.equals(listHD.get(i).getMaNV())) {
                return true;
            }
        }
        return false;
    }

    public void tinhTongCacPhanTu() {

        Collections.sort(listHD, new Comparator<HoaDon>() {
            @Override
            public int compare(HoaDon t, HoaDon t1) {
                return t.getMaNV().compareTo(t1.getMaNV());
            }
        });

        HoaDon hoaDonTK = listHD.get(0);
        for (int i = 1; i < listHD.size(); i++) {
            if (hoaDonTK.getMaNV().equals(listHD.get(i).getMaNV())) {
                hoaDonTK.setThanhTien(hoaDonTK.getThanhTien() + listHD.get(i).getThanhTien());
            } else {
                hoaDonTK = listHD.get(i);
            }
            map.put(hoaDonTK.getMaNV(), hoaDonTK);
        }

        for (Map.Entry<String, HoaDon> entry : map.entrySet()) {
            String key = entry.getKey();
            HoaDon value = entry.getValue();
            System.out.println(key + "-----" + value.getThanhTien());
        }
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
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1147, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 639, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
public JFreeChart createChart() {
        JFreeChart barChart = ChartFactory.createBarChart(
                "Thống kê tổng doanh thu của nhân viên",
                "Tên", "Số tiền",
                createDataset(), PlotOrientation.VERTICAL, false, false, false);
       
        return barChart;
    }

    private CategoryDataset createDataset() {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        for (Map.Entry<String, HoaDon> entry : map.entrySet()) {
            String key = entry.getKey();
            HoaDon value = entry.getValue();
            frmNhanVien nhanVien = new frmNhanVien();
            String ten = nhanVien.getNameByCode(key).getHoTen();          
            dataset.addValue(value.getThanhTien(), "Số tiền",  catChuoi(ten) + " - " + key);
        }

        return dataset;
    }
    public String catChuoi(String ma){
        String cat = "";
        String arr[] = ma.split(" ");
        return cat = arr[arr.length-1];
    }
    public void initFrame() {
        ChartPanel chartPanel = new ChartPanel(createChart());
//        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        JFrame frame = new JFrame();
        frame.add(chartPanel);
        frame.setTitle("Biểu đồ nhân viên");
        frame.setSize(1300, 760);
        frame.setMaximumSize(getMaximumSize());
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
