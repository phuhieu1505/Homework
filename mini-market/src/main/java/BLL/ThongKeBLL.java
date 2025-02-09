package main.java.BLL;

import main.java.DTO.HoaDonDTO;
import main.java.DTO.ThongKeDTO;
import main.java.DAL.ThongKeDAL;

import java.sql.Date;
import java.util.Vector;

public class ThongKeBLL {
    ThongKeDAL tkDAL = new ThongKeDAL();
    public boolean ThongKeSP(Vector<ThongKeDTO> arr, Date NgayDau, Date NgayCuoi){
        if(tkDAL.ThongKeSP(arr, NgayDau, NgayCuoi))
            return true;
        return false;
    }

    public boolean ThongKePN(Vector<ThongKeDTO> arr_pn, Date NgayDau, Date NgayCuoi){
        if(tkDAL.ThongKePN(arr_pn, NgayDau, NgayCuoi))
            return true;
        return false;
    }
}
