/**
 * 
 */
/**
 * @author DoanDinhChuc
 *
 */
package com.Database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.Entities.ChiTietKeHoachNauAn;
import com.Entities.ChuDeMon;
import com.Entities.KeHoachNauAn;
import com.Entities.MonAnMoi;

public class Mydatabase extends SQLiteOpenHelper {
	public static String DB_PATH = "/sdcard/TuDienNauAn/database/";

	private static String DB_NAME = "TuDienNauAn.db";
	private SQLiteDatabase database;
	private final Context mContext;

	public Mydatabase(Context con) {
		super(con, DB_NAME, null, 1);
		this.mContext = con;
	}

	public boolean KiemTraFile() {
		File file = new File(DB_PATH);
		boolean isfile = false;
		if (!file.exists()) {
			file.mkdirs();
			isfile = true;
		}
		return isfile;
	}

	public boolean isCreatedDatabase() throws IOException {
		boolean result = true;
		if (!checkExistDataBase()) {
			this.getReadableDatabase();
			try {
				copyDataBase();
				result = false;
			} catch (Exception e) {
				throw new Error("Error copying database");
			}
		}
		return result;
	}

	/**
	 * check whether database exist on the device?
	 * 
	 * @return true if existed
	 */
	private boolean checkExistDataBase() {
		try {
			String myPath = DB_PATH + DB_NAME;
			File fileDB = new File(myPath);

			if (fileDB.exists()) {

				return true;
			} else
				return false;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * copy database from assets folder to the device
	 * 
	 * @throws IOException
	 */
	private void copyDataBase() throws IOException {
		InputStream myInput = mContext.getAssets().open(DB_NAME);
		OutputStream myOutput = new FileOutputStream(DB_PATH + DB_NAME);
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}
		myOutput.flush();
		myOutput.close();
		myInput.close();
	}

	private final static int BUFFER_SIZE = 1024;

	private static void copyAssetFiles(InputStream in, OutputStream out) {
		try {
			byte[] buffer = new byte[BUFFER_SIZE];
			int read;

			while ((read = in.read(buffer)) != -1) {
				out.write(buffer, 0, read);
			}

			in.close();
			in = null;
			out.flush();
			out.close();
			out = null;

		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * delete database file
	 * 
	 * @return
	 */
	public boolean deleteDatabase() {
		File file = new File(DB_PATH + DB_NAME);
		return file.delete();
	}

	/**
	 * open database
	 * 
	 */
	public void openDataBase() throws SQLException {
		database = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null,
				SQLiteDatabase.OPEN_READWRITE);
	}

	@Override
	public synchronized void close() {
		if (database != null)
			database.close();
		super.close();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// do nothing
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		// update phien ban mới

	}

	public Cursor GetCursor(String sql) {
		openDataBase();
		Cursor c = database.rawQuery(sql, null);
		database.close();
		return c;
	}

	public void ThucThi(String sql) {

		openDataBase();
		database.execSQL(sql);
		database.close();
	}

	public Cursor GetCursorQuyery(String Table, String[] columns, String _where) {
		openDataBase();
		Cursor c = database.query(Table, columns, _where, null, null, null,
				null);
		database.close();
		return c;
	}

	public int deleteData_From_Table(String tbName) {

		int result = 0;
		try {
			openDataBase();
			database.beginTransaction();
			result = database.delete(tbName, null, null);
			if (result >= 0) {
				database.setTransactionSuccessful();
			}
		} catch (Exception e) {
			database.endTransaction();
			close();
		} finally {
			database.endTransaction();
			close();
		}

		return result;
	}

	// CHủ đề món
	public ArrayList<ChuDeMon> TraVeTuDanhSachChuDeMon() {
		openDataBase();
		ArrayList<ChuDeMon> rs = new ArrayList<ChuDeMon>();
		String[] columns = { "TenChuDe", "HinhAnh" };
		try {
			Cursor c = database.query("ChuDeMon", columns, null, null, null,
					null, null, null);

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
			database.close();
		}
		return rs;
	}

	public ArrayList<ChuDeMon> TraVeDanhSachChuDeMonList() {
		openDataBase();
		ArrayList<ChuDeMon> rs = new ArrayList<ChuDeMon>();
		String[] columns = { "TenChuDe" };
		try {

			Cursor c = database.query("ChuDeMon", columns, null, null, null,
					null, null, null);

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
			database.close();
		}
		return rs;
	}

	public String TraVeID(String _tenChuDe) {
		openDataBase();
		String[] columns = new String[] { "MaChuDeMon" };

		Cursor c = database.query("ChuDeMon", columns, "TenChuDe='" + _tenChuDe
				+ "'", null, null, null, null, null);

		String result = "";

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			result = c.getString(0);
		}
		c.close();
		database.close();
		// Log.v("Result", result);
		return result;
	}

	public List<ChuDeMon> getAllContacts() {

		List<ChuDeMon> contactList = new ArrayList<ChuDeMon>();
		// Select All Query
		openDataBase();
		String selectQuery = "SELECT  * FROM ChuDeMon ORDER BY MaChuDeMon";

		Cursor cursor = database.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				ChuDeMon contact = new ChuDeMon();
				contact.set_maChuDe(Integer.parseInt(cursor.getString(0)));
				contact.set_tenChuDe(cursor.getString(1));
				contact.set_hinhAnh(cursor.getBlob(2));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		// close inserting data from database
		database.close();
		// return contact list
		return contactList;

	}

	// Món Mới
	public ArrayList<MonAnMoi> TraVeTuDanhSachMonTheoChuDe(String _maChuDe) {
		openDataBase();
		ArrayList<MonAnMoi> rs = new ArrayList<MonAnMoi>();
		String[] columns = { "TenMon", "NgayLap" };
		try {
			Cursor c = database.query("MonTheoChuDe", columns, "MaChuDe='"
					+ _maChuDe + "'", null, null, null, null);

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
			database.close();
		}
		return rs;
	}

	public ArrayList<MonAnMoi> TraVeDanhSachMonTheChuDeList() {
		openDataBase();
		ArrayList<MonAnMoi> rs = new ArrayList<MonAnMoi>();
		String[] columns = { "TenMon" };
		try {
			Cursor c = database.query("MonTheoChuDe", columns, null, null,
					null, null, null, null);
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
			database.close();
		}
		return rs;
	}

	public String TraVeIDMon(String _tenMon) {
		openDataBase();
		String[] columns = new String[] { "MaMon" };
		Cursor c = database.query("MonTheoChuDe", columns, "TenMon='" + _tenMon
				+ "'", null, null, null, null, null);
		String result = "";
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			result = c.getString(0);
		}
		c.close();
		database.close();
		// Log.v("Result", result);
		return result;
	}

	// Chi Tiết món Ăn
	public String TraVeTenMon(int _maMon) {
		openDataBase();
		String[] columns = new String[] { "TenMon" };
		Cursor c = database.query("MonTheoChuDe", columns, "MaMon='" + _maMon
				+ "'", null, null, null, null, null);
		String result = "";
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			result = c.getString(0);
		}
		c.close();
		database.close();
		// Log.v("Result", result);
		return result;
	}

	public String TraVeNoiDungMon(int _maMon) {
		openDataBase();
		String[] columns = new String[] { "NoiDung" };
		Cursor c = database.query("MonTheoChuDe", columns, "MaMon='" + _maMon
				+ "'", null, null, null, null, null);
		String result = "";
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			result = c.getString(0);
		}
		c.close();
		database.close();
		// Log.v("Result", result);
		return result;
	}

	// Kế hoạch nấu ăn
	public ArrayList<KeHoachNauAn> TraVeTuDanhSachKeHoach() {
		openDataBase();
		ArrayList<KeHoachNauAn> rs = new ArrayList<KeHoachNauAn>();
		String[] columns = { "TenKeHoach", "NgayThucHien" };
		try {
			Cursor c = database.query("KeHoachNauAn", columns, null, null,
					null, null, null);

			while (c.moveToNext()) {
				KeHoachNauAn emp = new KeHoachNauAn();

				emp.set_tenKeHoach(c.getString(0));
				emp.set_ngayThucHien(c.getString(1));

				rs.add(emp);
			}
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			database.close();
		}
		return rs;
	}

	public ArrayList<KeHoachNauAn> TraVeDanhSachKeHoachList() {
		openDataBase();
		ArrayList<KeHoachNauAn> rs = new ArrayList<KeHoachNauAn>();
		String[] columns = { "TenKeHoach" };
		try {

			Cursor c = database.query("KeHoachNauAn", columns, null, null,
					null, null, null, null);

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
			database.close();
		}
		return rs;
	}

	public String TraVeMaKeHoach(String _ten) {
		openDataBase();
		String[] columns = new String[] { "MaKeHoach" };

		Cursor c = database.query("KeHoachNauAn", columns, "TenKeHoach='"
				+ _ten + "'", null, null, null, null, null);

		String result = "";

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			result = c.getString(0);
		}
		c.close();
		database.close();
		// Log.v("Result", result);
		return result;
	}

	public ArrayList<ChiTietKeHoachNauAn> TraVeTuDanhSachChiTiet(
			String _maKeHoach) {
		openDataBase();
		ArrayList<ChiTietKeHoachNauAn> rs = new ArrayList<ChiTietKeHoachNauAn>();
		String[] columns = { "TenChiTiet", "TienChi" };
		try {
			Cursor c = database.query("ChiTietKeHoach", columns, "MaKeHoach='"
					+ _maKeHoach + "'", null, null, null, null);

			while (c.moveToNext()) {
				ChiTietKeHoachNauAn emp = new ChiTietKeHoachNauAn();

				emp.set_tenChiTiet(c.getString(0));
				emp.set_tienChiTiet(Float.parseFloat(c.getString(1)));

				rs.add(emp);
			}
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			database.close();
		}
		return rs;
	}

	public float TraVeTienDuDinh(String _maKeHoach) {
		openDataBase();
		String[] columns = new String[] { "TienDuTinh" };

		Cursor c = database.query("KeHoachNauAn", columns, "MaKeHoach='"
				+ _maKeHoach + "'", null, null, null, null, null);

		float result = 0;

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			result = c.getFloat(0);
		}
		c.close();
		database.close();
		// Log.v("Result", result);
		return result;
	}

	public Float TraVeTienChi(String _maKeHoach) {

		// Select All Query
		openDataBase();
		String selectQuery = "SELECT  Sum(TienChi) FROM ChiTietKeHoach where MaKeHoach='"
				+ _maKeHoach + "'";

		Cursor cursor = database.rawQuery(selectQuery, null);
		float s = 0;
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {

				s = cursor.getFloat(0);
			} while (cursor.moveToNext());
		}
		// close inserting data from database
		database.close();
		// return contact list
		return s;

	}

	public void XoaChiTiet(String _ma) {

		// Select All Query
		openDataBase();
		String selectQuery = "Delete FROM ChiTietKeHoach where MaChiTietKeHoach='"
				+ _ma + "'";

		database.execSQL(selectQuery);

		database.close();

	}

	public ArrayList<ChiTietKeHoachNauAn> TraVeDanhSachChiTietList(String _ma) {
		openDataBase();
		ArrayList<ChiTietKeHoachNauAn> rs = new ArrayList<ChiTietKeHoachNauAn>();
		String[] columns = { "TenChiTiet" };
		try {

			Cursor c = database.query("ChiTietKeHoach", columns, "MaKeHoach='"
					+ _ma + "'", null, null, null, null, null);

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
			database.close();
		}
		return rs;
	}

	public String TraVeMaChiTietkeHoach(String _ten) {
		openDataBase();
		String[] columns = new String[] { "MaChiTietKeHoach" };

		Cursor c = database.query("ChiTietKeHoach", columns, "TenChiTiet='"
				+ _ten + "'", null, null, null, null, null);

		String result = "";

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			result = c.getString(0);
		}
		c.close();
		database.close();
		// Log.v("Result", result);
		return result;
	}

	public void ThemChiTiet(String _maKeHoach, String _ten, String _tien,
			String _noiDung) {

		// Select All Query
		openDataBase();
		String selectQuery = "insert into ChiTietKeHoach(MaKeHoach,TenChiTiet,TienChi,NoiDungThucHien) values('"
				+ _maKeHoach
				+ "','"
				+ _ten
				+ "','"
				+ _tien
				+ "','"
				+ _noiDung
				+ "')";

		database.execSQL(selectQuery);

		database.close();

	}

	public String TraVeTenChiTiet(String _ma) {
		openDataBase();
		String[] columns = new String[] { "TenChiTiet" };

		Cursor c = database.query("ChiTietKeHoach", columns,
				"MaChiTietKeHoach='" + _ma + "'", null, null, null, null, null);

		String result = "";

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			result = c.getString(0);
		}
		c.close();
		database.close();
		// Log.v("Result", result);
		return result;
	}

	public Float TraVeTienChiTiet(String _ma) {
		openDataBase();
		String[] columns = new String[] { "TienChi" };

		Cursor c = database.query("ChiTietKeHoach", columns,
				"MaChiTietKeHoach='" + _ma + "'", null, null, null, null, null);

		float result = 0;

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			result = c.getFloat(0);
		}
		c.close();
		database.close();
		// Log.v("Result", result);
		return result;
	}

	public String TraVeNoiDungChi(String _ma) {
		openDataBase();
		String[] columns = new String[] { "NoiDungThucHien" };

		Cursor c = database.query("ChiTietKeHoach", columns,
				"MaChiTietKeHoach='" + _ma + "'", null, null, null, null, null);

		String result = "";

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			result = c.getString(0);
		}
		c.close();
		database.close();
		// Log.v("Result", result);
		return result;
	}

	public void ThemKeHoach(String _tenKeHoach, String _ngayThucHien,
			Float _tienDuDInh) {
		openDataBase();
		database.execSQL("insert into KeHoachNauAn(TenKeHoach,NgayThucHien,TienDuTinh) values('"
				+ _tenKeHoach
				+ "','"
				+ _ngayThucHien
				+ "',"
				+ _tienDuDInh
				+ ")");
		database.close();
	}

	public float TongChiDuDinh() {
		float a = 0;
		openDataBase();
		String[] columns = new String[] { "TienDuDinh" };

		Cursor c = database.query("KeHoach", columns, null, null, null, null,
				null, null);

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			a += c.getFloat(0);
		}
		c.close();
		database.close();
		// Log.v("Result", result);
		return a;
	}

	public float TongDaChiTheoThang() {
		float a = 0;
		openDataBase();
		String[] columns = new String[] { "SoTien" };

		Cursor c = database.query("KhoanChi", columns, null, null, null, null,
				null, null);

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			a += c.getFloat(0);
		}
		c.close();
		database.close();
		// Log.v("Result", result);
		return a;
	}

	public float TongKhoanThuTheoThang(String _thang, String _nam) {
		float a = 0;
		openDataBase();
		String[] columns = new String[] { "SoTien" };

		Cursor c = database.query("KhoanThu", columns, "NgayThu like '_%"
				+ _thang + "/" + _nam + "'", null, null, null, null, null);

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			a += c.getFloat(0);
		}
		c.close();
		database.close();
		// Log.v("Result", result);
		return a;
	}

	public float TongChi(String _thang, String _nam) {
		float a = 0;
		openDataBase();
		String[] columns = new String[] { "TienDuTinh" };

		Cursor c = database.query("KeHoachNauAn", columns,
				"NgayThucHien like '_%" + _thang + "/" + _nam + "'", null,
				null, null, null, null);

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			a += c.getFloat(0);
		}
		c.close();
		database.close();
		// Log.v("Result", result);
		return a;
	}

	public ArrayList<String> LayNgayThu(String _thang, String _nam) {
		openDataBase();
		ArrayList<String> rs = new ArrayList<String>();
		String s = "";
		String[] columns = { "SoTien" };
		try {

			Cursor c = database.query("KhoanThu", columns, "NgayThu like '_%"
					+ _thang + "/" + _nam + "'", null, null, null, null, null);

			while (c.moveToNext()) {

				s = c.getString(0);

				rs.add(s);
			}
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			database.close();
		}
		return rs;
	}

	public void ThemMonMoi(String _tenMon, String _ngayLap, String _noiDung,
			Integer _maChuDe) {
		openDataBase();
		database.execSQL("insert into MonTheoChuDe(TenMon,NgayLap,NoiDung,MaChuDe) values('"
				+ _tenMon
				+ "','"
				+ _ngayLap
				+ "','"
				+ _noiDung
				+ "',"
				+ _maChuDe + ")");
		database.close();
	}

	public void ThemKhoanThu(String _tenKhoan, Float _tienThu, String _ngayThu) {
		openDataBase();
		database.execSQL("insert into KhoanThu(TenKhoanThu,SoTien,NgayThu) values('"
				+ _tenKhoan + "'," + _tienThu + ",'" + _ngayThu + "')");
		database.close();
	}

	public void ThemChuDe(String _tenChuDe) {
		openDataBase();
		database.execSQL("insert into ChuDeMon(TenChuDe,HinhAnh) values('"
				+ _tenChuDe + "','BLOB (Size: 13492)')");
		database.close();
	}

	public void XoaChuDe(String ma) {
		openDataBase();
		database.execSQL("Delete from ChuDeMon where MaChuDeMon="+ma+"");
		database.close();
	}
	public void XoaMon(String ma) {
		openDataBase();
		database.execSQL("Delete from MonTheoChuDe where MaMon="+ma+"");
		database.close();
	}
}