package com.Database;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;

import com.Entities.ChuDeMon;
import com.Entities.KeHoachNauAn;

public class KeHoachNauAnDatabase {
	Context con;

	public KeHoachNauAnDatabase(Context con) {
		this.con = con;

	} 

	Mydatabase db = new Mydatabase(con);

	public ArrayList<KeHoachNauAn> TraVeTuDanhSachCacKeHoach() {
		ArrayList<KeHoachNauAn> rs = new ArrayList<KeHoachNauAn>();
		String[] columns = { "TenKeHoach" };
		try {
			db.openDataBase();
			Cursor c = db.GetCursorQuyery("KeHoachNauAn", columns, null);

			String result = "";

			while (c.moveToNext()) {
				KeHoachNauAn emp = new KeHoachNauAn();

				emp.set_tenKeHoach(c.getString(0));
				

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

	public ArrayList<KeHoachNauAn> TraVeDanhSachKeHoachNauAn() {
		ArrayList<KeHoachNauAn> rs = new ArrayList<KeHoachNauAn>();
		String[] columns = { "TenKeHoach" };
		try {

			Cursor c = db.GetCursorQuyery("KeHoachNauAn", columns, null);

			String result = "";
			while (c.moveToNext()) {
				KeHoachNauAn emp = new KeHoachNauAn();

				emp.set_tenKeHoach(c.getString(0));
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

	public String TraVeMaKeHoach(String _tenChuDe) {

		String[] columns = new String[] { "MaKeHoach" };

		Cursor c = db.GetCursorQuyery("KeHoachNauAn", columns, null);

		String result = "";

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			result = c.getString(0);
		}
		c.close();
		db.close();
		// Log.v("Result", result);
		return result;
	}
	public void ThemKeHoach(String _tenKeHoach, String _ngayThucHien, Float _tienDuTinh) {
		String sql = "Insert into KeHoach(TenKeHoach,NgayThucHien,TienDuTinh) values('" + _tenKeHoach+ "','"
				+ _ngayThucHien + "','"+_tienDuTinh+"')";
		db.ThucThi(sql);
		db.close();
	}
	public void SuaKeHoach(int _maKeHoach,String _tenKeHoach, String _ngayThucHien, Float _tienDuTinh) {
		String sql = "Update KeHoach set TenKeHoach='"+_tenKeHoach+"', NgayThucHien='"+_ngayThucHien+"','"+_tienDuTinh+"'where MaKeHoach='"+_maKeHoach+"'";
		db.ThucThi(sql);
		db.close();
	}
	public void XoaKeHoach(String _maKeHoach){
		String sql ="delete from KeHoach where MaKeHoach='"+_maKeHoach+"'";
		db.ThucThi(sql);
		db.close();
	}
	public String TraVeTienDuTinh() {
		String[] columns = new String[] { "TienDuTinh" };
		Cursor c = db.GetCursorQuyery("KeHoach", columns, null);
		String result = "";
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			result = c.getString(0);
		}
		c.close();
		db.close();
		return result;
	}
	public int  TraMaKeHoachTheoTen(String _tenKeHoach) {
		String[] columns = new String[] { "MaKeHoach" };
		Cursor c = db.GetCursorQuyery("KeHoach", columns, "TenKeHoach='"+_tenKeHoach+"'");
		int  result = 0;
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			result = c.getInt(0);
		}
		c.close();
		db.close();
		return result;
	}

	
}
