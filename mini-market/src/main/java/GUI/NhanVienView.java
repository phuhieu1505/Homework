package main.java.GUI;

import main.java.BLL.NhanVienBLL;
import main.java.DAL.NhanVienDAL;
import main.java.DTO.NhanVienDTO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class NhanVienView extends JPanel{
    private JButton JBXoa;
    private JButton JBSua;
    private JButton JBTimKiem;
    private JTextField JTSĐT;
    private JTextField JTEmail;
    private JTextField JTChucvu;
    private JTable JTNhanVien;
    private JPanel JPChucNang;
    private JPanel JPThongTin;
    private JLabel JLHoTenNV;
    private JLabel JLSĐT;
    private JLabel JLEmail;
    private JLabel JLChucvu;
    private JPanel JPNhanVien;
    private JTextField JTHoTenNV;
    private JTextField JTTimkiem;
    private JTextField JTMaNV;
    private JButton JBThem;
    private JLabel JLMaNV;
    private JLabel JLNhapthongtin;
    private JScrollPane JSNhanVien;
    private JLabel JLTimkiem;
    private JPanel JPTimKiem;

    NhanVienBLL nvBLL = new NhanVienBLL();
    public NhanVienView(){
        initComponents();
        loadNhanVien();
    }

    public void initComponents(){
        JPNhanVien.setPreferredSize(new Dimension(1130, 545));
        add(JPNhanVien);
        JPNhanVien.setVisible(true); // hiển thị JPanel
        JBThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //Kiểm tra thông tin nhập vào có đúng không
                    if ( JTMaNV.getText().trim().equals("")    ||
                         JTHoTenNV.getText().trim().equals("") ||
                         JTSĐT.getText().trim().equals("")     ||
                         JTEmail.getText().trim().equals("")   ||
                         JTChucvu.getText().trim().equals(""))
                    JOptionPane.showMessageDialog(JPNhanVien,"Vui lòng nhập đủ thông tin");
                    else {
                        // Lấy dữ liệu người dùng nhập vào để chuyển xuống BLL
                        NhanVienDTO nv = new NhanVienDTO();
                        nv.setMaNV(JTMaNV.getText());
                        nv.setTenNV(JTHoTenNV.getText());
                        nv.setSdtNV(JTSĐT.getText());
                        nv.setEmailNV(JTEmail.getText());
                        nv.setChucvu(JTChucvu.getText());
                        JOptionPane.showMessageDialog(JPNhanVien, nvBLL.addNhanVien(nv));
                        loadNhanVien();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(JPNhanVien, "Thông tin không hợp lệ");
                }
            }
        });

        JBXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //Kiểm tra thông tin nhập vào có đúng không
                    int selectRow = JTNhanVien.getSelectedRow();
                    String MaNV = JTNhanVien.getValueAt(selectRow, 0).toString(); //Lấy mã nhân viên của hàng được chọn
                    NhanVienDTO nv = new NhanVienDTO();
                    nv.setMaNV(MaNV);
                    JOptionPane.showMessageDialog(JPNhanVien, nvBLL.deleteNhanVien(nv));
                    loadNhanVien();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(JPNhanVien, "Thông tin không hợp lệ");
                }
            }
        });

        JBSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if ( JTMaNV.getText().trim().equals("")    ||
                         JTHoTenNV.getText().trim().equals("") ||
                         JTSĐT.getText().trim().equals("")     ||
                         JTEmail.getText().trim().equals("")   ||
                         JTChucvu.getText().trim().equals(""))
                         JOptionPane.showMessageDialog(JPNhanVien,"Vui lòng nhập đủ thông tin");
                    else {
                        //Lấy chỉ chỉ số của hàng cần sửa
                        int selectRow = JTNhanVien.getSelectedRow();
                        String MaNV = JTNhanVien.getValueAt(selectRow, 0).toString(); //Lấy mã nhân viên của hàng được chọn
                        //Cập nhật nhân viên mới
                        NhanVienDTO nv = new NhanVienDTO();
                        nv.setMaNV(JTMaNV.getText());
                        nv.setTenNV(JTHoTenNV.getText());
                        nv.setSdtNV(JTSĐT.getText());
                        nv.setEmailNV(JTEmail.getText());
                        nv.setChucvu(JTChucvu.getText());
                        JOptionPane.showMessageDialog(JPNhanVien, nvBLL.updateNhanVien(nv, MaNV));
                        loadNhanVien();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(JPNhanVien, "Thông tin không hợp lệ");
                }
            }
        });

        JBTimKiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if ( JTTimkiem.getText().trim().equals("") )
                        JOptionPane.showMessageDialog(JPNhanVien,"Vui lòng nhập thông tin cần tìm");
                    else {
                        // Tạo model và thêm các cột vào model
                        DefaultTableModel model = new DefaultTableModel();
                        model.addColumn("Mã Nhân Viên");
                        model.addColumn("Họ Tên Nhân Viên");
                        model.addColumn("SĐT");
                        model.addColumn("Email");
                        model.addColumn("Chức vụ");

                        // Gắn model vào bảng
                        JTNhanVien.setModel(model);
                        String MaNV_old = JTTimkiem.getText();
                        Vector<NhanVienDTO> nv_arr = new Vector<NhanVienDTO>();
                        nvBLL.searchmaNV(nv_arr, MaNV_old);
                        for (int i = 0; i < nv_arr.size(); i++){
                            NhanVienDTO nv = nv_arr.get(i);
                            String MaNV = nv.getMaNV();
                            String TenNV = nv.getTenNV();
                            String SdtNV = nv.getSdtNV();
                            String EmailNV = nv.getEmailNV();
                            String Chucvu = nv.getChucvu();
                            Object[] row = {MaNV, TenNV, SdtNV, EmailNV, Chucvu};
                            model.addRow(row);
                        }
                        JOptionPane.showMessageDialog(JPNhanVien, nvBLL.searchmaNV(nv_arr, MaNV_old));
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(JPNhanVien, "Thông tin không hợp lệ");
                }
            }
        });

        setVisible(true);
    }

    public void loadNhanVien(){
        // Tạo model và thêm các cột vào model
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Mã Nhân Viên");
        model.addColumn("Họ Tên Nhân Viên");
        model.addColumn("SĐT");
        model.addColumn("Email");
        model.addColumn("Chức vụ");

        // Gắn model vào bảng
        JTNhanVien.setModel(model);
        Vector<NhanVienDTO> nv_arr = new Vector<NhanVienDTO>();
        nv_arr = nvBLL.getAllNhanVien();
        for (int i = 0; i < nv_arr.size(); i++){
            NhanVienDTO nv = nv_arr.get(i);
            String MaNV = nv.getMaNV();
            String TenNV = nv.getTenNV();
            String SdtNV = nv.getSdtNV();
            String EmailNV = nv.getEmailNV();
            String Chucvu = nv.getChucvu();
            Object[] row = {MaNV, TenNV, SdtNV, EmailNV, Chucvu};
            model.addRow(row);
        }
    }
}
