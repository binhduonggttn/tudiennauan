package com.Database;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;

import com.Entities.ChuDeMon;
import com.Entities.MonAnMoi;

public class MonMoiDatabase {
	Context con;

	public MonMoiDatabase(Context con) {
		this.con = con;

	}

	Mydatabase db = new Mydatabase(con);

	public ArrayList<MonAnMoi> TraVeTuDanhSachMonTheoChuDe(String _maChuDe) {
		ArrayList<MonAnMoi> rs = new ArrayList<MonAnMoi>();
		String[] columns = { "TenMon", "NgayLap" };
		try {
			Cursor c = db.GetCursorQuyery("MonTheoChuDe", columns, "MaChuDe='"+_maChuDe+"'");

			while (c.moveToNext()) {
				MonAnMoi emp = new MonAnMoi();

				emp.set_tenMon(c.getString(0));
				emp.set_ngayLap(c.getString(1));

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

	public ArrayList<MonAnMoi> TraVeDanhSachMonTheChuDeList() {
		ArrayList<MonAnMoi> rs = new ArrayList<MonAnMoi>();
		String[] columns = { "TenMon" };
		try {
			Cursor c = db.GetCursorQuyery("MonTheoChuDe", columns, null);
			String result = "";
			while (c.moveToNext()) {
				MonAnMoi emp = new MonAnMoi();

				emp.set_tenMon(c.getString(0));
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
	public String TraVeIDMon(String _tenMon) {
		String[] columns = new String[] { "MaMon" };
		Cursor c = db.GetCursorQuyery("MonTheoChuDe", columns, null);
		String result = "";
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			result = c.getString(0);
		}
		c.close();
		db.close();
		// Log.v("Result", result);
		return result;
	}
}
