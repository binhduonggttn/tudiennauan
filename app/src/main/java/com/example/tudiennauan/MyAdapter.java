/**
 * 
 */
package com.example.tudiennauan;

/**
 * @author DoanChuc
 *
 */
import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/*

 */
abstract public class MyAdapter extends BaseAdapter {

	ArrayList<Object> m_ListView;
	Context m_Context;
	protected LayoutInflater m_Inflater;
	int m_SourceOfView;

	// Custom view
	abstract public View getLayout(Object obj, View v, int position);

	@SuppressWarnings("static-access")
	public MyAdapter(Context context) {
		super();
		this.m_Context = context;
		m_Inflater = (LayoutInflater) getCon().getSystemService(
				getCon().LAYOUT_INFLATER_SERVICE);
		if (getListView() == null) {
			m_ListView = getData();
		}

	}

	/* Dùng hàm này trong TH set dữ liệu tĩnh */
	public ArrayList<Object> getData() {
		return new ArrayList<Object>();
	}

	public int getCount() {
		return m_ListView.size();
	}

	public Object getItem(int index) {
		return m_ListView.get(index);
	}

	/**
	 * @return the listView
	 */
	public ArrayList<Object> getListView() {
		return m_ListView;
	}

	/**
	 * @param arrayList
	 *            the listView to set
	 */
	public void setListView(ArrayList<Object> arrayList) {
		this.m_ListView = arrayList;
	}

	/**
	 * @return the con
	 */
	public Context getCon() {
		return m_Context;
	}

	/**
	 * @param con
	 *            the con to set
	 */
	public void setCon(Context con) {
		this.m_Context = con;
	}
	public long getItemId(int arg0) {

		return 0;
	}
	public View getView(int position, View myview, ViewGroup arg2) {
		try {
			myview = getLayout(m_ListView.get(position), myview, position);
		} catch (Exception e) {
			// Log.w("Move", "Loi khi scroll listview - xem lai muc gan data");
		}
		return myview;
	}

}

