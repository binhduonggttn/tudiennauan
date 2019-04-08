package com.Database;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;

import com.Entities.ChiTietKeHoachNauAn;
import com.Entities.KeHoachNauAn;

public class ChiTietKeHoachNauAnDatabase {
	
	Context con;

	public ChiTietKeHoachNauAnDatabase(Context con) {
		this.con = con;

	} 

	Mydatabase db = new Mydatabase(con);

	public ArrayList<ChiTietKeHoachNauAn> TraVeTuDanhSachChiTietKeHoach() {
		ArrayList<ChiTietKeHoachNauAn> rs = new ArrayList<ChiTietKeHoachNauAn>();
		String[] columns = { "TenChiTiet"  };
		try {
			db.openDataBase();
			Cursor c = db.GetCursorQuyery("ChiTietKeHoach", columns, null);

			String result = "";

			while (c.moveToNext()) {
				ChiTietKeHoachNauAn emp = new ChiTietKeHoachNauAn();

				emp.set_tenChiTiet(c.getString(0));
				

				rs.add(emp);
			}
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
		return rs;
	}

	public ArrayList<ChiTietKeHoachNauAn> TraVeDanhSachChiTietKeHoach() {
		ArrayList<ChiTietKeHoachNauAn> rs = new ArrayList<ChiTietKeHoachNauAn>();
		String[] columns = { "TenChiTiet" };
		try {

			Cursor c = db.GetCursorQuyery("ChiTietKeHoach", columns, null);

			String result = "";
			while (c.moveToNext()) {
				ChiTietKeHoachNauAn emp = new ChiTietKeHoachNauAn();

				emp.set_tenChiTiet(c.getString(0));
				rs.add(emp);
			}
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
		return rs;
	}

	public String TraVeChiTietMaKeHoach(String _tenChiTiet) {

		String[] columns = new String[] { "MaChiTietKeHoach" };

		Cursor c = db.GetCursorQuyery("ChiTietKeHoach", columns, "TenChiTiet='"+_tenChiTiet+"'");

		String result = "";

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			result = c.getString(0);
		}
		c.close();
		db.close();
		// Log.v("Result", result);
		return result;
	}
	public void ThemChiTietKeHoach(int  _maKeHoach, String _tenChiTiet, Float _tienChi) {
		String sql = "Insert into ChiTietKeHoach(MaKeHoach,TenChiTiet,TienChi) values('" + _maKeHoach+ "','"
				+ _tenChiTiet + "','"+_tienChi+"')";
		db.ThucThi(sql);
		db.close();
	}
	public void SuaChiTietKeHoach(int _maChiTiet  ,int _maKeHoach, String _tenChiTiet, Float _tienChi) {
		String sql = "Update ChiTietKeHoach set  MaKeHoach='"+_maKeHoach+"', TenChiTiet='"+_tenChiTiet+"', TienChi='"+_tienChi+"'where MaChiTietKeHoach='"+_maChiTiet+"'";
		db.ThucThi(sql);
		db.close();
	}
	public void XoaChiTietKeHoach(String _maChiTiet){
		String sql ="delete from ChiTietKeHoach where MaChiTietKeHoach='"+_maChiTiet+"'";
		db.ThucThi(sql);
		db.close();
	}
	
	

}
