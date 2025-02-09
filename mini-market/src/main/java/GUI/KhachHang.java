package main.java.GUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import main.java.DTO.KhachHangDTO;
import main.java.BLL.KhachHangBLL;
public class KhachHang extends JPanel{
KhachHangBLL khBLL = new KhachHangBLL();
    private JTable table1;
    private JButton JBThem;
    private JPanel JPKhachHang;
    private JTextField JTMaKH;
    private JTextField JTTenKH;
    private JTextField JTEmail;
    private JButton JBXoa;
    private JButton JBTimKiem;
    private JButton JBSua;
    private JTable JTKhachHang;
    private JTextField JTTimKiem;
    private JTextField JTSDT;

    public KhachHang(){
        initComponents();
        loadKhachHangList();
    }
    public void initComponents(){
        JPKhachHang.setPreferredSize(new Dimension(1130, 545));
        add(JPKhachHang);
        JPKhachHang.setVisible(true); // hiển thị JPanel
        setSize(1128, 510);
        JBThem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (JTMaKH.getText().trim().equals("") ||
                        JTTenKH.getText().trim().equals("") ||
                        JTSDT.getText().trim().equals("") ||
                        JTEmail.getText().trim().equals(""))
                    JOptionPane.showMessageDialog(JPKhachHang, "Vui lòng nhập đầy đủ thông tin");
                else {
                    KhachHangDTO kh = new KhachHangDTO();
                    kh.setMaKH(JTMaKH.getText());
                    kh.setTenKH(JTTenKH.getText());
                    kh.setSdtKH(JTSDT.getText());
                    kh.setKHEmail(JTEmail.getText());
                    JOptionPane.showMessageDialog(JPKhachHang, khBLL.addKhachHang(kh));
                    loadKhachHangList();
                }
            }
        });

        JBXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int selectRow = JTKhachHang.getSelectedRow();
                    String MaKH = JTKhachHang.getValueAt(selectRow, 0).toString();
                    KhachHangDTO kh = new KhachHangDTO();
                    kh.setMaKH(MaKH);
                    JOptionPane.showMessageDialog(JPKhachHang, khBLL.deleteKhachHang(kh));
                    loadKhachHangList();
                }catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(JPKhachHang,"Thông tin không hợp lệ ");
                }
            }
        });

        JBSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (JTMaKH.getText().trim().equals("") ||
                            JTTenKH.getText().trim().equals("") ||
                            JTSDT.getText().trim().equals("") ||
                            JTEmail.getText().trim().equals(""))
                        JOptionPane.showMessageDialog(JPKhachHang, "Vui lòng nhập đầy đủ thông tin");
                    else {
                        int selectRow = JTKhachHang.getSelectedRow();
                        String MaKH = JTKhachHang.getValueAt(selectRow, 0).toString();
                        KhachHangDTO kh = new KhachHangDTO();
                        kh.setMaKH(JTMaKH.getText());
                        kh.setTenKH(JTTenKH.getText());
                        kh.setSdtKH(JTSDT.getText());
                        kh.setKHEmail(JTEmail.getText());
                        JOptionPane.showMessageDialog(JPKhachHang, khBLL.updateKhachHang(kh, MaKH));
                        loadKhachHangList();
                    }
                }catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(JPKhachHang,"Thông tin không hợp lệ ");
                }
            }
        });

        JBTimKiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if(JTTimKiem.getText().trim().equals(""))
                        JOptionPane.showMessageDialog(JPKhachHang,"Vui lòng nhập thông tin đầy đủ");
                    else{
                        DefaultTableModel dtm = new DefaultTableModel();
                        dtm.addColumn("Mã khách hàng :");
                        dtm.addColumn("Tên khách hàng :");
                        dtm.addColumn("SDT");
                        dtm.addColumn("Email :");
                        JTKhachHang.setModel(dtm);
                        String old_MaKH = JTTimKiem.getText();
                        Vector<KhachHangDTO> arr = new Vector<KhachHangDTO>();
                        khBLL.searchMaKH(arr, old_MaKH);
                        for(int i=0 ; i<arr.size() ; i++){
                            KhachHangDTO kh = arr.get(i);
                            String kh_id = kh.getMaKH();
                            String kh_name = kh.getTenKH();
                            String kh_sdt = kh.getSdtKH();
                            String kh_email = kh.getKHEmail();
                            Object[] row= {kh_id,kh_name,kh_sdt,kh_email};
                            dtm.addRow(row);
                        }
                        JOptionPane.showMessageDialog(JPKhachHang, khBLL.searchMaKH(arr,old_MaKH));
                    }
                }catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(JPKhachHang, "Thông tin khng hợp lệ");
                }
            }
        });
        setVisible(true);
    }

    public void loadKhachHangList(){
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Mã khách hàng :");
        dtm.addColumn("Ten khách hàng :");
        dtm.addColumn("SDT");
        dtm.addColumn("Email :");

        JTKhachHang.setModel(dtm);
        Vector<KhachHangDTO> arr = new Vector<KhachHangDTO>();
        arr = khBLL.getAllKhachHang();
        for(int i=0 ; i<arr.size() ; i++){
            KhachHangDTO kh = arr.get(i);
           String kh_id = kh.getMaKH();
           String kh_name = kh.getTenKH();
           String kh_sdt = kh.getSdtKH();
           String kh_email = kh.getKHEmail();
           Object[] row= {kh_id,kh_name,kh_sdt,kh_email};
           dtm.addRow(row);
        }
    }


}
