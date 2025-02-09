package main.java.GUI;

import main.java.BLL.AdminBLL;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DangNhap extends JFrame{
    private JButton JBDangnhap;
    private JPanel JPDangnhap;
    private JButton JBDangNhap;
    private JTextField JTTenTaiKhoan;
    private JPasswordField JTMatKhau;
    AdminBLL adminBLL = new AdminBLL();


    public DangNhap() {
        setContentPane(JPDangnhap);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setVisible(true);

        JBDangNhap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JTTenTaiKhoan.getText().trim().equals("") ||
                        JTMatKhau.getText().trim().equals(""))
                    showMessageDialog("Bạn chưa nhập đủ thông tin");
                else{
                    System.out.println(JTTenTaiKhoan.getText() + JTMatKhau.getText());
                    if (adminBLL.CheckLogin(JTTenTaiKhoan.getText(), JTMatKhau.getText())){
                        showMessageDialog("Đăng Nhập Thành Công");
                        MainView mainView = new MainView();
                        mainView.setLocationRelativeTo(null); // Đặt vị trí của frame DangNhap giữa màn hình
                        mainView.setVisible(true);
                        dispose();
                    }
                    else{
                        showMessageDialog("Tên Tài Khoản hoặc Mật Khẩu không chính xác");
                    }
                }
            }
        });
    }

    public void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public static void main(String[] args) {
        new DangNhap();
    }
}
