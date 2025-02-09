package main.java.GUI;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Vector;

import main.java.DTO.CTHDDTO;
import main.java.BLL.CTHDBLL;
import main.java.DTO.HoaDonDTO;
import main.java.BLL.HoaDonBLL;


public class HoaDon extends JPanel {
    HoaDonBLL hdBLL = new HoaDonBLL();
    CTHDBLL cthdBLL = new CTHDBLL();
    private JPanel JPHoaDon;
    private JButton JBThem;
    private JTextField JTTimKiem;
    private JTable JTHoaDon;
    private JTextField JTMaHD;
    private JTextField JTMaKH;
    private JTextField JTMaNV;
    private JTextField JTTongTien;
    private JTextField JTNgayTao;
    private JTextField JTTrangThai;
    private JButton JBSua1;
    private JButton JBTimKiem1;
    private JTextField JTMaSP;
    private JTextField JTMaHD2;
    private JTextField JTSLmua;
    private JButton JBSua2;
    private JPanel JPHD;
    private JPanel JPCTHD;
    private JTable JTCTHD;
    private JTextField JTGiaTien;
    private JTextField JTTimKiem2;
    //private JButton JBXoa1;
    //private JButton JBXoa2;

    public HoaDon() {
        initComponents();
        loadHoaDonList();
        loadCTHDList();
    }

    public void initComponents() {
        JPHoaDon.setPreferredSize(new Dimension(1130, 545));
        add(JPHoaDon);
        JPHoaDon.setVisible(true); // hiển thị JPanel


        JBThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JTMaHD.getText().trim().equals("") ||
                        JTMaKH.getText().trim().equals("") ||
                        JTMaNV.getText().trim().equals("") ||
                        JTTongTien.getText().trim().equals("") ||
                        JTNgayTao.getText().trim().equals("") ||
                        JTTrangThai.getText().trim().equals(""))
                    JOptionPane.showMessageDialog(JPHoaDon, "Vui long nhap day du thong tin");
                else {
                    HoaDonDTO hd = new HoaDonDTO();
                    hd.setHD_ID(JTMaHD.getText());
                    hd.setKH(JTMaKH.getText());
                    hd.setNV(JTMaNV.getText());
                    String ngayTaoText = JTNgayTao.getText();
                    Date nt = Date.valueOf(ngayTaoText);
                    hd.setNgayTao(nt);
                    hd.setTongTien(Float.parseFloat(JTTongTien.getText()));
                    hd.setTrangThai(JTTrangThai.getText());
                    JOptionPane.showMessageDialog(JPHoaDon, hdBLL.addHoaDon(hd));
                    loadHoaDonList();
                }
            }
        });
/*
        JBXoa1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int selectRow = JTHoaDon.getSelectedRow();
                    String MaHD = JTHoaDon.getValueAt(selectRow, 0).toString();
                    HoaDonDTO hd = new HoaDonDTO();
                    hd.setHD_ID(MaHD);
                    JOptionPane.showMessageDialog(JPHoaDon, hdBLL.deleteHoaDon(hd));
                    loadHoaDonList();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(JPHoaDon, "Thong tin khong hop le ");
                }
            }
        });
*/
        JBSua1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if (JTMaHD.getText().trim().equals("") ||
                            JTMaKH.getText().trim().equals("") ||
                            JTMaNV.getText().trim().equals("") ||
                            JTTongTien.getText().trim().equals("") ||
                            JTNgayTao.getText().trim().equals("") ||
                            JTTrangThai.getText().trim().equals(""))
                        JOptionPane.showMessageDialog(JPHoaDon, "Vui long nhap day du thong tin");
                    else {
                        int selectRow = JTHoaDon.getSelectedRow();
                        String MaHD = JTHoaDon.getValueAt(selectRow, 0).toString();
                        HoaDonDTO hd = new HoaDonDTO();
                        hd.setHD_ID(JTMaHD.getText());
                        hd.setKH(JTMaKH.getText());
                        hd.setNV(JTMaNV.getText());
                        hd.setTongTien(Float.parseFloat(JTTongTien.getText()));
                        String ngayTaoText = JTNgayTao.getText();
                        Date nt = Date.valueOf(ngayTaoText);
                        System.out.println(nt);
                        hd.setNgayTao(nt);
                        hd.setTrangThai(JTTrangThai.getText());
                        JOptionPane.showMessageDialog(JPHoaDon, hdBLL.updateHoaDon(hd,MaHD));
                        loadHoaDonList();
                    }
                }catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(JPHoaDon,"Thong tin khong hop le ");
                }
            }
        });
        JBTimKiem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if(JTTimKiem.getText().trim().equals(""))
                        JOptionPane.showMessageDialog(JPHoaDon, "Vui lòng nhập đầy đủ thông tin ");
                    else{
                        DefaultTableModel dtm = new DefaultTableModel();
                        dtm.addColumn( "Mã hóa đơn");
                        dtm.addColumn("Mã khách hàng");
                        dtm.addColumn("Mã nhân viên");
                        dtm.addColumn("Tổng tiền");
                        dtm.addColumn( "Ngày tạo");
                        dtm.addColumn( "Trạng thái");
                        JTHoaDon.setModel(dtm);
                        String old_MaHD = JTTimKiem.getText();
                        Vector<HoaDonDTO> arrHD = new Vector<HoaDonDTO>();
                        hdBLL.searchMaHD(arrHD, old_MaHD);
                        for(int i=0 ; i < arrHD.size() ; i++) {
                            HoaDonDTO hd = arrHD.get(i);
                            String hd_id = hd.getHD_ID();
                            String kh_id = hd.getKH();
                            String nv_id = hd.getNV();
                            Float tongtien = hd.getTongTien();
                            Date ngaytao = hd.getNgayTao();
                            String trangthai = hd.getTrangThai();
                            Object[] row = {hd_id, kh_id, nv_id, tongtien, ngaytao, trangthai};
                            dtm.addRow(row);

                            System.out.println("Đang trong hàm search CTHD của GUI");
                            DefaultTableModel dtm2 = new DefaultTableModel();
                            dtm2.addColumn("Mã sản phẩm");
                            dtm2.addColumn("Mã hóa đơn");
                            dtm2.addColumn("Số lượng mua");
                            dtm2.addColumn("Giá tiền");

                            JTCTHD.setModel(dtm2);
                            Vector<CTHDDTO> arrCTHD = new Vector<CTHDDTO>();
                            cthdBLL.searchCTHD(arrCTHD, old_MaHD);
                            System.out.println(old_MaHD);
                            for (int j=0 ; j < arrCTHD.size(); j++){
                                CTHDDTO cthd = arrCTHD.get(j);
                                String hd_id2 = cthd.getHD();
                                String id_sp2 = cthd.getSP();
                                int sl_mua = cthd.getSLmua();
                                float gia_tien = cthd.getGiaTien();
                                Object[] row2 ={hd_id2,id_sp2,sl_mua,gia_tien};
                                dtm2.addRow(row2);
                                System.out.println(hd_id2 + id_sp2 + sl_mua + gia_tien);
                            }
                        }
                    }
                }catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(JPHoaDon, "Thông tin hóa đơn không hợp lệ");
                }
            }
        });
        setVisible(true);
/*
        JBXoa2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int selectRow = JTHoaDon.getSelectedRow();
                    String MaHD = JTHoaDon.getValueAt(selectRow, 0).toString();
                    HoaDonDTO hd = new HoaDonDTO();
                    hd.setHD_ID(MaHD);
                    JOptionPane.showMessageDialog(JPHoaDon, hdBLL.deleteHoaDon(hd));
                    loadHoaDonList();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(JPHoaDon, "Thong tin khong hop le ");
                }
            }
        });
*/
/*
        JBSua2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if (JTMaHD.getText().trim().equals("") ||
                            JTNgayTao.getText().trim().equals("") ||
                            JTTrangThai.getText().trim().equals(""))
                        JOptionPane.showMessageDialog(JPHoaDon, "Vui long nhap day du thong tin");
                    else {
                        int selectRow = JTHoaDon.getSelectedRow();
                        String MaHD = JTHoaDon.getValueAt(selectRow, 0).toString();
                        HoaDonDTO hd = new HoaDonDTO();
                        hd.setHD_ID(MaHD);
                        JOptionPane.showMessageDialog(JPHoaDon, hdBLL.updateHoaDon(hd,MaHD));
                        loadHoaDonList();
                    }
                }catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(JPHoaDon,"Thong tin khong hop le ");
                }
            }
        });
*/
        JBSua2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if (JTMaSP.getText().trim().equals("") ||
                            JTMaHD2.getText().trim().equals("") ||
                            JTSLmua.getText().trim().equals("") ||
                            JTGiaTien.getText().trim().equals(""))
                        JOptionPane.showMessageDialog(JPHoaDon, "Vui lòng nhập đầy đủ thông tin");
                    else {
                        int selectRow = JTCTHD.getSelectedRow();
                        String MaHD = JTCTHD.getValueAt(selectRow, 0).toString();
                        CTHDDTO cthd = new CTHDDTO();
                        cthd.setSP(JTMaSP.getText());
                        cthd.setHD(JTMaHD2.getText());
                        cthd.setSLmua(Integer.parseInt(JTSLmua.getText()));
                        cthd.setGiaTien(Float.parseFloat(JTGiaTien.getText()));
                        JOptionPane.showMessageDialog(JPCTHD, cthdBLL.updateCTHD(cthd,MaHD));
                        loadHoaDonList();
                    }
                }catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(JPCTHD,"Thông tin không hợp lệ ");
                }
            }
        });

//        JBTimKiem2.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try{
//                    if(JTTimKiem2.getText().trim().equals(""))
//                        JOptionPane.showMessageDialog(JPCTHD, "Vui lòng nhập đầy đủ thông tin ");
//                    else{
//                        DefaultTableModel dtm2 = new DefaultTableModel();
//                        dtm2.addColumn( "Mã sản phẩm");
//                        dtm2.addColumn("Mã hóa đơn");
//                        dtm2.addColumn("Số lượng mua");
//                        dtm2.addColumn("Giá tiền");
//                        JTCTHD.setModel(dtm2);
//                        String old_MaHD2 = JTTimKiem2.getText();
//                        Vector<CTHDDTO> arrCTHD = new Vector<CTHDDTO>();
//                        cthdBLL.searchCTHD(arrCTHD, old_MaHD2);
//                        for(int i=0 ; i<=arrCTHD.size() ; i++) {
//                            CTHDDTO cthd = arrCTHD.get(i);
//                            String sp_id = cthd.getSP();
//                            String hd_id2 = cthd.getHD();
//                            int sl_mua = cthd.getSLmua();
//                            Float gia_tien = cthd.getGiaTien();
//                            Object[] row ={sp_id, hd_id2, sl_mua, gia_tien};
//                            dtm2.addRow(row);
//                        }
//                    }
//                }catch(NumberFormatException ex){
//                    JOptionPane.showMessageDialog(JPCTHD, "Thông tin hóa đơn không hợp lệ");
//                }
//            }
//        });
        setVisible(true);
    }

    public void loadHoaDonList() {
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Mã hóa đơn :");
        dtm.addColumn("Mã khách hàng");
        dtm.addColumn("Mã nhân viên");
        dtm.addColumn("Tổng tiền");
        dtm.addColumn("Ngày tạo :");
        dtm.addColumn("Trạng thái: ");
        JTHoaDon.setModel(dtm);
        Vector<HoaDonDTO> arrHD = new Vector<HoaDonDTO>();
        arrHD = hdBLL.getAllHoaDon();
        for (int i = 0; i < arrHD.size(); i++) {
            HoaDonDTO hd = arrHD.get(i);
            String hd_id = hd.getHD_ID();
            String kh_id = hd.getKH();
            String nv_id = hd.getNV();
            Float tongtien = hd.getTongTien();
            Date ngay_tao = hd.getNgayTao();
            String trang_thai = hd.getTrangThai();
            Object[] row = {hd_id, kh_id, nv_id, tongtien, ngay_tao, trang_thai};
            dtm.addRow(row);
        }
    }

    public void loadCTHDList(){
        DefaultTableModel dtm2 = new DefaultTableModel();
        dtm2.addColumn("Mã sản phẩm");
        dtm2.addColumn("Mã hóa đơn");
        dtm2.addColumn("Số lượng mua");
        dtm2.addColumn("Giá tiền");
        JTCTHD.setModel(dtm2);
        Vector<CTHDDTO> arrCTHD = new Vector<CTHDDTO>();
        arrCTHD = cthdBLL.getAllCTHD();
        for (int i = 0 ; i < arrCTHD.size() ; i++){
            CTHDDTO cthd = arrCTHD.get(i);
            String sp_id = cthd.getSP();
            String hd_id2 = cthd.getHD();
            int sl_mua = cthd.getSLmua();
            Float gia_tien = cthd.getGiaTien();
            Object[] row= {sp_id, hd_id2, sl_mua, gia_tien};
            dtm2.addRow(row);
        }
    }
}


