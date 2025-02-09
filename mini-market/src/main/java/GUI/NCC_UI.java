package main.java.GUI;
import main.java.BLL.NCCBLL;
import main.java.DTO.NCCDTO;
import main.java.DTO.SanPhamDTO;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class NCC_UI extends JPanel{
    private JPanel NCC_Panel;
    private JLabel JL_TenNCC;
    private JTextField JT_TenNCC;
    private JLabel JL_MaNCC;
    private JLabel JL_SdtNCC;
    private JTextField JT_SdtNCC;
    private JButton JB_XoaNCC;
    private JButton JB_SuaNCC;
    private JButton JB_TimNCC;
    private JTextField JT_MaNCC;
    private JButton JB_ThemNCC;
    private JTextField JT_EmailNCC;
    private JLabel JL_EmailNCC;
    private JTable JTB_NCC;
    private JScrollPane JSNCC;
    NCCBLL nccBLL = new NCCBLL();
    NCC_UI(){
        NCC_Panel.setPreferredSize(new Dimension(1130, 545));
        add(NCC_Panel);
        NCC_Panel.setVisible(true); // hiển thị JPanel
        setSize(1128, 510);
//        setLocationRelativeTo(null);
        setVisible(true);
        initComponents();
        loadNCC();
    }
    public void initComponents(){
        JB_ThemNCC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if(     JT_MaNCC.getText().trim().equals("")||
                            JT_TenNCC.getText().trim().equals("") ||
                            JT_SdtNCC.getText().trim().equals("")||
                            JT_EmailNCC.getText().trim().equals(""))
                        JOptionPane.showMessageDialog(NCC_Panel,"Vui lòng nhập lại thông tin");
                    else{
                        NCCDTO ncc = new NCCDTO();
                        ncc.setMaNCC(JT_MaNCC.getText());
                        ncc.setTenNCC(JT_TenNCC.getText());
                        ncc.setSdtNCC(JT_SdtNCC.getText());
                        ncc.setEmailNCC(JT_EmailNCC.getText());
                        JOptionPane.showMessageDialog(NCC_Panel,nccBLL.addNCC(ncc));
                        loadNCC();
                    }
                }catch (NumberFormatException ex ){
                    JOptionPane.showMessageDialog(NCC_Panel,"Thông tin không hợp lệ");
                }
            }
        });

        JB_XoaNCC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int selectRow = JTB_NCC.getSelectedRow();
                    String MaNCC = JTB_NCC.getValueAt(selectRow,0).toString();
                    NCCDTO ncc = new NCCDTO();
                    ncc.setMaNCC(MaNCC);
                    JOptionPane.showMessageDialog(NCC_Panel,nccBLL.deleteNCC(ncc));
                    loadNCC();
                }catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(NCC_Panel,"Thông tin không hợp lệ");
                }
            }
        });

        JB_SuaNCC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if(JT_MaNCC.getText().trim().equals("")||
                            JT_TenNCC.getText().trim().equals("") ||
                            JT_SdtNCC.getText().trim().equals("")||
                            JT_EmailNCC.getText().trim().equals(""))
                        JOptionPane.showMessageDialog(NCC_Panel,"Vui lòng nhập lại thông tin");
                    else {
                        int selectRow = JTB_NCC.getSelectedRow();
                        String MaNCC = JTB_NCC.getValueAt(selectRow, 0).toString(); //Lấy mã nhân viên của hàng được chọn
                        NCCDTO ncc = new NCCDTO();
                        ncc.setMaNCC(JT_MaNCC.getText());
                        ncc.setTenNCC(JT_TenNCC.getText());
                        ncc.setSdtNCC(JT_SdtNCC.getText());
                        ncc.setEmailNCC(JT_EmailNCC.getText());
                        JOptionPane.showMessageDialog(NCC_Panel,nccBLL.updateNCC(ncc,MaNCC));
                        loadNCC();
                    }
                } catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(NCC_Panel,"Thôn tin không hợp lệ");
                }
            }
        });

        JB_TimNCC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (JT_MaNCC.getText().trim().equals(""))
                        JOptionPane.showMessageDialog(NCC_Panel, "Vui lòng nhập lại thông tin");

                    else {
                        DefaultTableModel model = new DefaultTableModel();
                        model.addColumn("Mã NCC");
                        model.addColumn("Tên NCC");
                        model.addColumn("Số điện thoại");
                        model.addColumn("Email");

                        JTB_NCC.setModel(model);
                        String  MaNCC_old = JT_MaNCC.getText();
                        Vector<NCCDTO> ncc_arr = new Vector<NCCDTO>();
                        nccBLL.searchNCC(ncc_arr, MaNCC_old);
                        for (int i = 0; i < ncc_arr.size(); i++){
                            NCCDTO ncc = ncc_arr.get(i);
                            String MaNCC = ncc.getMaNCC();
                            String TenNCC = ncc.getTenNCC();
                            String SDT = ncc.getSdtNCC();
                            String Email = ncc.getEmailNCC();
                            Object[] row = {MaNCC, TenNCC, SDT, Email};
                            model.addRow(row);
                        }
                    }

                } catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(NCC_Panel,"Thông tin không hợp lệ");
                }
            }
        });
    }

    public void loadNCC(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Mã NCC");
        model.addColumn("Tên NCC ");
        model.addColumn("Số điện thoại NCC");
        model.addColumn("Email NCC");


        JTB_NCC.setModel(model);
        Vector<NCCDTO> arr = new Vector<NCCDTO>();
        arr = nccBLL.getAllNCC();
        for (int i = 0; i < arr.size(); i++){
            System.out.println("Đang load sp lên GUI");
            NCCDTO ncc = arr.get(i);
            String MaNCC = ncc.getMaNCC();
            String TenNCC = ncc.getTenNCC();
            String SdtNCC = ncc.getSdtNCC();
            String EmailNCC = ncc.getEmailNCC();
            Object[] row = {MaNCC,TenNCC,SdtNCC,EmailNCC};
            model.addRow(row);
        }

    }

        public static void main(String[] args){
            new NCC_UI();
        }
}

