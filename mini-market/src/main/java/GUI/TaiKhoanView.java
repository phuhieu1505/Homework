package main.java.GUI;

import main.java.BLL.AdminBLL;
import main.java.DTO.AdminDTO;
import main.java.DAL.AdminDAL;
import main.java.DTO.NhanVienDTO;

import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class TaiKhoanView extends  JPanel{
    private JButton JBThem;
    private JButton JBXoa;
    private JTable TBTaiKhoan;
    private JButton JBCapNhat;
    private JTextField JTMaTK;
    private JTextField JTTenTK;
    private JTextField JTMatKhau;
    private JPanel JPTaiKhoan;
    private JPanel JPTable;
    private JLabel JLNhap;
    private JLabel JLMaTK;
    private JLabel JLTenTK;
    private JLabel JLMatKhau;
    private JScrollPane JSTaiKhoan;

    AdminBLL tkBLL = new AdminBLL();
    public TaiKhoanView(){
        initComponents();
        loadTaiKhoan();
    }

    public void initComponents(){
        JPTaiKhoan.setPreferredSize(new Dimension(1130, 545));
        add(JPTaiKhoan);
        JBThem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    if (JTMaTK.getText().trim().equals("") ||
                            JTTenTK.getText().trim().equals("") ||
                            JTMatKhau.getText().trim().equals(""))
                        JOptionPane.showMessageDialog(JPTaiKhoan,"Vui lòng nhập đủ thông tin");
                    else {
                        //Lấy dữ liệu người dùng nhập vào để chuyển xuống BLL
                        AdminDTO tk = new AdminDTO();
                        tk.setMaTK(JTMaTK.getText());
                        tk.setTenTK(JTTenTK.getText());
                        tk.setMatKhau(JTMatKhau.getText());
                        JOptionPane.showMessageDialog(JPTaiKhoan, tkBLL.addTaiKhoan(tk));
                        loadTaiKhoan();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(JPTaiKhoan, "Thông tin không hợp lệ");
                }
            }
        });

        JBXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //Kiểm tra thông tin nhập vào có đúng không
                    int selectRow = TBTaiKhoan.getSelectedRow();
                    String MaNV = TBTaiKhoan.getValueAt(selectRow, 0).toString(); //Lấy mã nhân viên của hàng được chọn
                    AdminDTO tk = new AdminDTO();
                    tk.setMaTK(MaNV);
                    JOptionPane.showMessageDialog(JPTaiKhoan, tkBLL.deleteTaiKhoan(tk));
                    loadTaiKhoan();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(JPTaiKhoan, "Thông tin không hợp lệ");
                }
            }
        });

        JBCapNhat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if ( JTMaTK.getText().trim().equals("")    ||
                         JTTenTK.getText().trim().equals("")   ||
                         JTMatKhau.getText().trim().equals(""))
                        JOptionPane.showMessageDialog(JPTaiKhoan,"Vui lòng nhập đủ thông tin");
                    else {
                        //Lấy chỉ chỉ số của hàng cần sửa
                        int selectRow = TBTaiKhoan.getSelectedRow();
                        String MaTK_old = TBTaiKhoan.getValueAt(selectRow, 0).toString(); //Lấy mã nhân viên của hàng được chọn
                        AdminDTO tk = new AdminDTO();
                        tk.setMaTK(JTMaTK.getText());
                        tk.setTenTK(JTTenTK.getText());
                        tk.setMatKhau(JTMatKhau.getText());
                        JOptionPane.showMessageDialog(JPTaiKhoan, tkBLL.updateTaiKhoan(tk, MaTK_old));
                        loadTaiKhoan();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(JPTaiKhoan, "Thông tin không hợp lệ");
                }
            }
        });

        JPTaiKhoan.setVisible(true); // hiển thị JPanel
    }

    public void loadTaiKhoan(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Mã Tài Khoản");
        model.addColumn("Tên Người dùng ");
        model.addColumn("Mật Khẩu");

        // Gắn model vào bảng
        TBTaiKhoan.setModel(model);
        Vector<AdminDTO> arr = new Vector<AdminDTO>();
        arr = tkBLL.getAllTK();
        for (int i = 0; i < arr.size(); i++){
            AdminDTO tk = arr.get(i);
            String MaTK = tk.getMaTK();
            String TenTK = tk.getTenTK();
            String MatKhau = tk.getMatKhau();
            Object[] row = {MaTK, TenTK, MatKhau};
            model.addRow(row);
        }
    }

}
