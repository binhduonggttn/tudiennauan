package com.Entities;

import java.security.PublicKey;

public class KeHoachNauAn {

	private String _maKeHoach;
	private String _tenKeHoach;
	private String _ngayThucHien;
	private float _tongTienDuTinh;

	public String get_maKeHoach() {
		return _maKeHoach;
	}

	public void set_maKeHoach(String _maKeHoach) {
		this._maKeHoach = _maKeHoach;
	}

	public String get_tenKeHoach() {
		return _tenKeHoach;
	}

	public void set_tenKeHoach(String _tenKeHoach) {
		this._tenKeHoach = _tenKeHoach;
	}

	public String get_ngayThucHien() {
		return _ngayThucHien;
	}

	public void set_ngayThucHien(String _ngayThucHien) {
		this._ngayThucHien = _ngayThucHien;
	}

	public float get_tongTienDuTinh() {
		return _tongTienDuTinh;
	}

	public void set_tongTienDuTinh(float _tongTienDuTinh) {
		this._tongTienDuTinh = _tongTienDuTinh;
	}

	public KeHoachNauAn() {
	}

	public KeHoachNauAn(String _maKeHoach, String _tenKeHoach,
			String _ngayThucHien, float _tongTienDuTinh) {

		this._maKeHoach = _maKeHoach;
		this._tenKeHoach = _tenKeHoach;
		this._ngayThucHien = _ngayThucHien;
		this._tongTienDuTinh = _tongTienDuTinh;
	}
}
