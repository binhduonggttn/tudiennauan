
package com.Entities;

public class ChuDeMon {

	
	private int _maChuDe;
	private String _tenChuDe;
	private byte [] _hinhAnh;
	public int get_maChuDe() {
		return _maChuDe;
	}
	public void set_maChuDe(int _maChuDe) {
		this._maChuDe = _maChuDe;
	}
	public String get_tenChuDe() {
		return _tenChuDe;
	}
	public void set_tenChuDe(String _tenChuDe) {
		this._tenChuDe = _tenChuDe;
	}
	public byte[] get_hinhAnh() {
		return _hinhAnh;
	}
	public void set_hinhAnh(byte[] _hinhAnh) {
		this._hinhAnh = _hinhAnh;
	}
	public ChuDeMon(){}
	public ChuDeMon(int _maChuDe, String _tenChuDe, byte [] _hinhAnh){
		this._maChuDe  = _maChuDe;
		this._tenChuDe = _tenChuDe;
		this._hinhAnh= _hinhAnh;
	}
	
}