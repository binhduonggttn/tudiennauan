package com.Entities;

public class MonAnMoi {
	private String _maMon;
	private String _tenMon;
	private String _ngayLap;

	public String get_ngayLap() {
		return _ngayLap;
	}

	public void set_ngayLap(String _ngayLap) {
		this._ngayLap = _ngayLap;
	}

	private String _noiDung;
	private String _maChuDe;

	public String get_maMon() {
		return _maMon;
	}

	public void set_maMon(String _maMon) {
		this._maMon = _maMon;
	}

	public String get_tenMon() {
		return _tenMon;
	}

	public void set_tenMon(String _tenMon) {
		this._tenMon = _tenMon;
	}

	public String get_noiDung() {
		return _noiDung;
	}

	public void set_noiDung(String _noiDung) {
		this._noiDung = _noiDung;
	}

	public String get_maChuDe() {
		return _maChuDe;
	}

	public void set_maChuDe(String _maChuDe) {
		this._maChuDe = _maChuDe;
	}

	public MonAnMoi() {
	}

	public MonAnMoi(String _maMon, String _tenMon, String _ngayLap,
			String _noiDung, String _maChuDe) {
		this._maMon = _maMon;
		this._tenMon = _tenMon;
		this._ngayLap = _ngayLap;
		this._noiDung = _noiDung;
		this._maChuDe = _maChuDe;

	}
}
