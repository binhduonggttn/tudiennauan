package com.Database;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;

import com.Entities.ChuDeMon;

public class ChuDeMonDatabase {

	Context con;

	public ChuDeMonDatabase(Context con) {
		this.con = con;

	}

	Mydatabase db = new Mydatabase(con);

	public ArrayList<ChuDeMon> TraVeTuDanhSachChuDeMon() {
		ArrayList<ChuDeMon> rs = new ArrayList<ChuDeMon>();
		String[] columns = { "TenChuDe", "HinhAnh" };
		try {
			Cursor c = db.GetCursorQuyery("ChuDeMon", columns, null);

			String result = "";

			while (c.moveToNext()) {
				ChuDeMon emp = new ChuDeMon();

				emp.set_tenChuDe(c.getString(0));
				emp.set_hinhAnh(c.getBlob(1));

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

	public ArrayList<ChuDeMon> TraVeDanhSachChuDeMonList() {
		ArrayList<ChuDeMon> rs = new ArrayList<ChuDeMon>();
		String[] columns = { "TenChuDe" };
		try {

			Cursor c = db.GetCursorQuyery("ChuDeMon", columns, null);

			String result = "";
			while (c.moveToNext()) {
				ChuDeMon emp = new ChuDeMon();

				emp.set_tenChuDe(c.getString(0));
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

	public String TraVeID(String _tenChuDe) {

		String[] columns = new String[] { "MaChuDe" };

		Cursor c = db.GetCursorQuyery("ChuDeMon", columns, null);

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
