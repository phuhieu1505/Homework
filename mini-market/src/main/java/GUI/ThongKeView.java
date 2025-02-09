package main.java.GUI;

import main.java.BLL.ThongKeBLL;
import main.java.DTO.ThongKeDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Vector;

public class ThongKeView extends JPanel{
    private JTextField JTNgayDau;
    private JTextField JTNgayCuoi;
    private JButton JBThongKe;
    private JTable JTSanPham;
    private JTable JTPhieuNhap;
    private JPanel JPThongKe;
    private JLabel JLNgayDau;
    private JLabel JLNgayCuoi;
    private JPanel JPThuChi;
    private JPanel JPSanPham;
    private JScrollPane JSSanPham;
    private JPanel JPPhieuNhap;
    private JScrollPane JSPhieuNhap;
    private JLabel JLThu;
    private JLabel JLChi;
    ThongKeBLL tkeBLL = new ThongKeBLL();
    ThongKeBLL tkeBLL2 = new ThongKeBLL();

    public ThongKeView(){
        initComponents();
    }
    public void initComponents() {
        JPThongKe.setPreferredSize(new Dimension(1130, 570));
        add(JPThongKe);
        JPThongKe.setVisible(true); // hiển thị JPanel

        JBThongKe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (JTNgayDau.getText().trim().equals("") ||
                            JTNgayCuoi.getText().trim().equals(""))
                        JOptionPane.showMessageDialog(JPThongKe, "Vui lòng nhập đầy đủ thông tin");
                    else {
                        DefaultTableModel dtm = new DefaultTableModel();
                        dtm.addColumn("Mã Hóa Đơn");
                        dtm.addColumn("Mã Sản Phẩm");
                        dtm.addColumn("Số lượng");
                        dtm.addColumn("Tổng tiền");
                        JTSanPham.setModel(dtm);
                        String NgayDau = JTNgayDau.getText();
                        Date nd = Date.valueOf(NgayDau);
                        String NgayCuoi = JTNgayCuoi.getText();
                        Date nc = Date.valueOf(NgayCuoi);
                        Vector<ThongKeDTO> arrTK = new Vector<ThongKeDTO>();
                        tkeBLL.ThongKeSP(arrTK, nd, nc);
                        for (int i = 0; i < arrTK.size(); i++) {
                            ThongKeDTO tke = arrTK.get(i);
                            String MaHD = tke.getMaHD();
                            String MaSP = tke.getMaSP();
                            Integer SLmua = tke.getSLmua();
                            Float TongTien = tke.getTongTien();
                            Object[] row = {MaHD, MaSP, SLmua, TongTien};
                            dtm.addRow(row);
                        }
                        //bảng phiếu nhập
                        DefaultTableModel dtm2 = new DefaultTableModel();
                        dtm2.addColumn("Mã Phiếu Nhập");
                        dtm2.addColumn("Mã Sản Phẩm");
                        dtm2.addColumn("Số lượng");
                        dtm2.addColumn("Tổng tiền");
                        JTPhieuNhap.setModel(dtm2);
                        String NgayDau2 = JTNgayDau.getText();
                        Date nd2 = Date.valueOf(NgayDau2);
                        String NgayCuoi2 = JTNgayCuoi.getText();
                        Date nc2 = Date.valueOf(NgayCuoi2);
                        Vector<ThongKeDTO> arrTK2 = new Vector<ThongKeDTO>();
                        tkeBLL.ThongKePN(arrTK2, nd2, nc2);
                        for (int j = 0; j < arrTK2.size(); j++) {
                            ThongKeDTO tke2 = arrTK2.get(j);
                            String MaPN = tke2.getMaPN();
                            String MaSP2 = tke2.getMaSP();
                            Integer SoLuong = tke2.getSoLuong();
                            Float TongTien2 = tke2.getTongTienPN();
                            Object[] row2 = {MaPN, MaSP2, SoLuong, TongTien2};
                            dtm2.addRow(row2);
                        }

                        float Thu = 0.0f;
                        for (int i = 0; i < JTSanPham.getRowCount(); i++){
                            float TT_Thu = Float.parseFloat(JTSanPham.getValueAt(i, 3).toString());
                            Thu = Thu + TT_Thu;
                        }
                        JLThu.setText(Float.toString(Thu));

                        float Chi = 0.0f;
                        for (int i = 0; i < JTPhieuNhap.getRowCount(); i++){
                            float TT_Chi = Float.parseFloat(JTPhieuNhap.getValueAt(i, 3).toString());
                            Chi = Chi + TT_Chi;
                        }
                        JLChi.setText(Float.toString(Chi));

                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(JPThongKe, "Thông tin không hợp lệ ");
                }
            }
        });
    }
}
