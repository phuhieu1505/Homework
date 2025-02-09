package main.java.GUI;

import java.sql.Date;
import main.java.BLL.PhieuNhapBLL;
import main.java.BLL.CTPNBLL;
import main.java.DTO.CTPN_DTO;
import main.java.DTO.NhanVienDTO;
import main.java.DTO.PhieuNhapDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class PhieuNhapView extends JPanel{
    private JTextField JTMaPN;
    private JTextField JTNgayNhap;
    private JTextField JTMaNCC;
    private JTextField JTTongTien;
    private JTextField JTMaPN2;
    private JTextField JTMaSP;
    private JTextField JTTenSP;
    private JTextField JTSoLuong;
    private JTextField JTDonGia;
    private JTextField JTThanhTIen;
    private JPanel JPPhieuNhap;
    private JButton JBThem;
    private JButton JBXoa;
    private JButton JBSua;
    private JButton JBCTPN_Them;
    private JButton JBCTPN_Sua;
    private JTable JTQLPhieuNhap;
    private JTable JTCTPN;
    private JTextField JTTimKiem;
    private JButton JBTimkiem;
    private JPanel JPQLPhieuNhap;
    private JScrollPane JSQLPhieuNhap;
    private JPanel JPCTPN;
    private JScrollPane JSCTPN;
    private JPanel JPNhapPN;
    private JPanel JPNhapCTPN;
    private JLabel JLMaPN;
    private JLabel JLNgayNhap;
    private JLabel JLMaNCC;
    private JLabel JLTongTIen;
    private JLabel JLMaPN2;
    private JLabel JLMaSP;
    private JLabel JLTenSP;
    private JLabel JLSoLuong;
    private JLabel JLDonGia;
    private JLabel JLThanhTien;
    private JLabel JLNhap_CTPN;
    private JLabel JLNhap;
    private JLabel JLPhieuNhap;
    private JLabel JLTimKiem;
    private JTextField JTTrangThai;
    private JLabel JLTrangThai;

    PhieuNhapBLL pnBLL = new PhieuNhapBLL();
    CTPNBLL ctpnBLL = new CTPNBLL();

    public PhieuNhapView(){
        initComponents();
        loadPhieuNhap();
        loadCTPN();
    }

    public void initComponents() {
        JPPhieuNhap.setPreferredSize(new Dimension(1130, 545));
        add(JPPhieuNhap);
        JPPhieuNhap.setVisible(true); // hiển thị JPanel
        JBThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //Kiểm tra thông tin nhập vào có đúng không
                    if (JTMaPN.getText().trim().equals("") ||
                            JTNgayNhap.getText().trim().equals("") ||
                            JTMaNCC.getText().trim().equals("") ||
                            JTTongTien.getText().trim().equals("") ||
                            JTTrangThai.getText().trim().equals(""))
                        JOptionPane.showMessageDialog(JPPhieuNhap, "Vui lòng nhập đủ thông tin");
                    else {
                        // Lấy dữ liệu người dùng nhập vào để chuyển xuống BLL
                        PhieuNhapDTO pn = new PhieuNhapDTO();
                        pn.setMaPN(JTMaPN.getText());
                        String NgayNhap = JTNgayNhap.getText();
                        Date ngaynhap = Date.valueOf(NgayNhap);
                        pn.setNgayNhap(ngaynhap);
                        pn.setMaNCC(JTMaNCC.getText());
                        pn.setTongTien(Float.parseFloat(JTTongTien.getText()));
                        pn.setTrangThai(JTTrangThai.getText());
                        JOptionPane.showMessageDialog(JPPhieuNhap, pnBLL.addPhieuNhap(pn));
                        loadPhieuNhap();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(JPPhieuNhap, "Thông tin không hợp lệ");
                }
            }
        });


        JBSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (JTMaPN.getText().trim().equals("") ||
                            JTNgayNhap.getText().trim().equals("") ||
                            JTMaNCC.getText().trim().equals("") ||
                            JTTongTien.getText().trim().equals("") ||
                            JTTrangThai.getText().trim().equals(""))
                        JOptionPane.showMessageDialog(JPPhieuNhap, "Vui lòng nhập đủ thông tin");
                    else {
                        //Lấy chỉ chỉ số của hàng cần sửa
                        int selectRow = JTQLPhieuNhap.getSelectedRow();
                        String MaPN = JTQLPhieuNhap.getValueAt(selectRow, 0).toString(); //Lấy mã nhân viên của hàng được chọn
                        //Cập nhật nhân viên mới
                        PhieuNhapDTO pn = new PhieuNhapDTO();
                        pn.setMaPN(JTMaPN.getText());
                        String NgayNhap = JTNgayNhap.getText();
                        Date ngaynhap = Date.valueOf(NgayNhap);
                        pn.setNgayNhap(ngaynhap);
                        pn.setMaNCC(JTMaNCC.getText());
                        pn.setTongTien(Float.parseFloat(JTTongTien.getText()));
                        pn.setTrangThai(JTTrangThai.getText());
                        JOptionPane.showMessageDialog(JPPhieuNhap, pnBLL.updatePhieuNhap(pn, MaPN));
                        loadPhieuNhap();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(JPPhieuNhap, "Thông tin không hợp lệ");
                }
            }
        });

        JBTimkiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (JTTimKiem.getText().trim().equals(""))
                        JOptionPane.showMessageDialog(JPPhieuNhap, "Vui lòng nhập thông tin cần tìm");
                    else {
                        // Tạo model và thêm các cột vào model
                        DefaultTableModel model = new DefaultTableModel();
                        model.addColumn("Mã Phiếu Nhập");
                        model.addColumn("Ngày Nhập");
                        model.addColumn("Mã Nhà Cung Cấp");
                        model.addColumn("Tổng Tiền");
                        model.addColumn("Trạng Thái");

                        // Gắn model vào bảng
                        JTQLPhieuNhap.setModel(model);
                        String MaPN_old = JTTimKiem.getText();
                        Vector<PhieuNhapDTO> pn_arr = new Vector<PhieuNhapDTO>();
                        pnBLL.searchmaPN(pn_arr, MaPN_old);
                        for (int i = 0; i < pn_arr.size(); i++) {
                            PhieuNhapDTO pn = pn_arr.get(i);
                            String MaPN = pn.getMaPN();
                            Date NgayNhap = pn.getNgayNhap();
                            String MaNCC = pn.getMaNCC();
                            Float TongTien = pn.getTongTien();
                            String TrangThai = pn.getTrangThai();
                            Object[] row = {MaPN, NgayNhap, MaNCC, TongTien, TrangThai};
                            model.addRow(row);
                        }

                        DefaultTableModel model2 = new DefaultTableModel();
                        model2.addColumn("Mã Phiếu Nhập");
                        model2.addColumn("Mã Sản Phẩm");
                        model2.addColumn("Tên Sản Phẩm");
                        model2.addColumn("Số Lượng");
                        model2.addColumn("Đơn Giá");
                        model2.addColumn("Thành Tiền");

                        // Gắn model vào bảng
                        JTCTPN.setModel(model2);
                        Vector<CTPN_DTO> ctpn_arr = new Vector<CTPN_DTO>();
                        ctpnBLL.searchCTPN(ctpn_arr, MaPN_old);
                        for (int i = 0; i < ctpn_arr.size(); i++) {
                            CTPN_DTO ctpn = ctpn_arr.get(i);
                            String MaPN2 = ctpn.getMaPN();
                            String MaSP = ctpn.getMaSP();
                            String TenSP = ctpn.getTenSP();
                            Integer SoLuong = ctpn.getSoLuong();
                            Float DonGia = ctpn.getDonGia();
                            Float ThanhTien = ctpn.getThanhTien();
                            Object[] row2 = {MaPN2, MaSP, TenSP, SoLuong, DonGia, ThanhTien};
                            model2.addRow(row2);
                        }

                        if (!(pnBLL.searchmaPN(pn_arr, MaPN_old)))
                            JOptionPane.showMessageDialog(JPQLPhieuNhap, "Mã Phiếu Nhập không tồn tại");
                        if (!(ctpnBLL.searchCTPN(ctpn_arr, MaPN_old)))
                            JOptionPane.showMessageDialog(JPQLPhieuNhap, "Mã CTPN không tồn tại");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(JPQLPhieuNhap, "Thông tin không hợp lệ");
                }
            }
        });

        JBCTPN_Them.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //Kiểm tra thông tin nhập vào có đúng không
                    if (JTMaPN2.getText().trim().equals("") ||
                            JTMaSP.getText().trim().equals("") ||
                            JTTenSP.getText().trim().equals("") ||
                            JTSoLuong.getText().trim().equals("") ||
                            JTDonGia.getText().trim().equals("") ||
                            JTThanhTIen.getText().trim().equals(""))
                        JOptionPane.showMessageDialog(JPPhieuNhap, "Vui lòng nhập đủ thông tin");
                    else {
                        // Lấy dữ liệu người dùng nhập vào để chuyển xuống BLL
                        CTPN_DTO ctpn = new CTPN_DTO();
                        ctpn.setMaPN(JTMaPN2.getText());
                        ctpn.setMaSP(JTMaSP.getText());
                        ctpn.setTenSP(JTTenSP.getText());
                        ctpn.setSoLuong(Integer.parseInt(JTSoLuong.getText()));
                        ctpn.setDonGia(Float.parseFloat(JTDonGia.getText()));
                        ctpn.setThanhTien(Float.parseFloat(JTThanhTIen.getText()));
                        JOptionPane.showMessageDialog(JPPhieuNhap, ctpnBLL.addCTPN(ctpn));
                        loadCTPN();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(JPPhieuNhap, "Thông tin không hợp lệ");
                }
            }
        });

        JBCTPN_Sua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (JTMaPN2.getText().trim().equals("") ||
                            JTMaSP.getText().trim().equals("") ||
                            JTTenSP.getText().trim().equals("") ||
                            JTSoLuong.getText().trim().equals("") ||
                            JTDonGia.getText().trim().equals("") ||
                            JTThanhTIen.getText().trim().equals(""))
                        JOptionPane.showMessageDialog(JPPhieuNhap, "Vui lòng nhập đủ thông tin");
                    else {
                        //Lấy chỉ chỉ số của hàng cần sửa
                        int selectRow = JTCTPN.getSelectedRow();
                        String MaPN = JTCTPN.getValueAt(selectRow, 0).toString(); //Lấy mã nhân viên của hàng được chọn
                        //Cập nhật nhân viên mới
                        CTPN_DTO ctpn = new CTPN_DTO();
                        ctpn.setMaPN(JTMaPN.getText());
                        ctpn.setMaSP(JTMaNCC.getText());
                        ctpn.setTenSP(JTTenSP.getText());
                        ctpn.setSoLuong(Integer.parseInt(JTSoLuong.getText()));
                        ctpn.setDonGia(Float.parseFloat(JTDonGia.getText()));
                        ctpn.setThanhTien(Float.parseFloat(JTThanhTIen.getText()));
                        JOptionPane.showMessageDialog(JPPhieuNhap, ctpnBLL.updateCTPN(ctpn, MaPN));
                        loadCTPN();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(JPPhieuNhap, "Thông tin không hợp lệ");
                }
            }
        });
    }

    public void loadPhieuNhap(){
        // Tạo model và thêm các cột vào model
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Mã Phiếu Nhập");
        model.addColumn("Ngày Nhập");
        model.addColumn("Mã Nhà Cung Cấp");
        model.addColumn("Tổng Tiền");
        model.addColumn("Trạng Thái");

        // Gắn model vào bảng
        JTQLPhieuNhap.setModel(model);
        Vector<PhieuNhapDTO> pn_arr = new Vector<PhieuNhapDTO>();
        pn_arr = pnBLL.getAllPhieuNhap();
        for (int i = 0; i < pn_arr.size(); i++){
            PhieuNhapDTO pn = pn_arr.get(i);
            String MaPN = pn.getMaPN();
            Date NgayNhap = pn.getNgayNhap();
            String MaNCC = pn.getMaNCC();
            Float TongTien = pn.getTongTien();
            String TrangThai = pn.getTrangThai();
            Object[] row = {MaPN, NgayNhap, MaNCC, TongTien, TrangThai};
            model.addRow(row);
        }
    }

    public void loadCTPN(){
        // Tạo model và thêm các cột vào model
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Mã Phiếu Nhập");
        model.addColumn("Mã Sản Phẩm");
        model.addColumn("Tên Sản Phẩm");
        model.addColumn("Số Lượng");
        model.addColumn("Đơn Giá");
        model.addColumn("Thành Tiền");

        // Gắn model vào bảng
        JTCTPN.setModel(model);
        Vector<CTPN_DTO> ctpn_arr = new Vector<CTPN_DTO>();
        ctpn_arr = ctpnBLL.getAllCTPN();
        for (int i = 0; i < ctpn_arr.size(); i++){
            CTPN_DTO ctpn = ctpn_arr.get(i);
            String MaPN = ctpn.getMaPN();
            String MaSP = ctpn.getMaSP();
            String TenSP = ctpn.getTenSP();
            Integer SoLuong = ctpn.getSoLuong();
            Float DonGia = ctpn.getDonGia();
            Float ThanhTien = ctpn.getThanhTien();
            Object[] row = {MaPN, MaSP, TenSP, SoLuong, DonGia, ThanhTien};
            model.addRow(row);
        }
    }

}
