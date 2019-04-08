package com.Entities;

public class ChiTietKeHoachNauAn {
	private String _maChiTietKeHoach;
	private String _maKeHoach;
	private String _tenChiTiet;
	private float _tienChiTiet;
	private String _noiDungThucHien;
	public String get_maChiTietKeHoach() {
		return _maChiTietKeHoach;
	}
	public void set_maChiTietKeHoach(String _maChiTietKeHoach) {
		this._maChiTietKeHoach = _maChiTietKeHoach;
	}
	public String get_maKeHoach() {
		return _maKeHoach;
	}
	public void set_maKeHoach(String _maKeHoach) {
		this._maKeHoach = _maKeHoach;
	}
	public String get_tenChiTiet() {
		return _tenChiTiet;
	}
	public void set_tenChiTiet(String _tenChiTiet) {
		this._tenChiTiet = _tenChiTiet;
	}
	public float get_tienChiTiet() {
		return _tienChiTiet;
	}
	public void set_tienChiTiet(float _tienChiTiet) {
		this._tienChiTiet = _tienChiTiet;
	}

	public String get_noiDungThucHien() {
		return _noiDungThucHien;
	}

	public void set_noiDungThucHien(String _noiDungThucHien) {
		this._noiDungThucHien = _noiDungThucHien;
	}
	public ChiTietKeHoachNauAn() {
	}
	public ChiTietKeHoachNauAn(String _maChiTietKeHoach, String _maKeHoach,
			String _tenChiTiet, float _tienChi, String _noiDungThucHien) {
		this._maChiTietKeHoach = _maChiTietKeHoach;
		this._maKeHoach = _maKeHoach;
		this._tenChiTiet = _tenChiTiet;
		this._tienChiTiet = _tienChi;
		this._noiDungThucHien = _noiDungThucHien;

	}

}
