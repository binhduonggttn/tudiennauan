package com.example.tudiennauan;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.SeriesSelection;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.Database.Mydatabase;

public class frmThongKe extends Activity {
	Mydatabase _data = new Mydatabase(this);
	private GraphicalView mChart;

	EditText edThang, edNam;
	Button btThongKe;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.thongke);
		edNam = (EditText) findViewById(R.id.edNam);
		edThang = (EditText) findViewById(R.id.edThang);
		btThongKe = (Button) findViewById(R.id.btThongKe);
		btThongKe.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Toast.makeText(
						getApplicationContext(),
						_data.TongKhoanThuTheoThang(edThang.getText()
								.toString(), edNam.getText().toString())
								+ "", Toast.LENGTH_SHORT).show();

				Toast.makeText(
						getApplicationContext(),
						_data.TongChi(edThang.getText().toString(), edNam
								.getText().toString())
								+ " dda chi", Toast.LENGTH_SHORT).show();
				createChart();
			}
		});

	}

	private void createChart() {
		// Khởi tạo biểu đồ gồm 5 giá trị thời gian
		int count = 1;
		Date[] dt = new Date[1];

		GregorianCalendar gc = new GregorianCalendar(Integer.parseInt(edNam
				.getText().toString()), Integer.parseInt(edThang.getText()
				.toString()), 1);
		dt[0] = gc.getTime();

		// Mảng giá trị đầu vào
		Float[] visits = { _data.TongKhoanThuTheoThang(edThang.getText()
				.toString(), edNam.getText().toString()) };
		Float[] Likes = { _data.TongChi(edThang.getText().toString(), edNam
				.getText().toString()) };

		// Khởi tạo TimeSeries là lượt Visits
		TimeSeries visitsSeries = new TimeSeries("Khoản thu");

		// Khởi tạo TimeSeries là lượt Likes
		TimeSeries LikesSeries = new TimeSeries("Khoản chi");

		// Thêm dữ liệu đồng loạt vào lượt Visits and lượt Likes

		visitsSeries.add(dt[0], visits[0]);
		LikesSeries.add(dt[0], Likes[0]);

		// Khởi tạo 1 dataset để quản lý tất cả các giá trị
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();

		// Thêm tất cả thông tin lượt Visits vào dataset
		dataset.addSeries(visitsSeries);

		// Thêm tất cả thông tin lượt Likes vào dataset
		dataset.addSeries(LikesSeries);

		// Tạo XYSeriesRenderer để tùy chỉnh các giá trị của lượt Visits
		XYSeriesRenderer visitsRenderer = new XYSeriesRenderer();
		visitsRenderer.setChartValuesTextAlign(Align.CENTER);
		visitsRenderer.setColor(Color.RED);// Màu đỏ
		visitsRenderer.setPointStyle(PointStyle.SQUARE);// Chấm tròm
		visitsRenderer.setFillPoints(true);// Đổ đầy chấm
		visitsRenderer.setLineWidth(20);// Độ rộng dòng
		visitsRenderer.setDisplayChartValues(true);// Cho phép hiển thị giá trị

		// Tạo XYSeriesRenderer để tùy chỉnh các giá trị của lượt Likes
		XYSeriesRenderer LikesRenderer = new XYSeriesRenderer();
		LikesRenderer.setColor(Color.GREEN);
		LikesRenderer.setPointStyle(PointStyle.SQUARE);
		LikesRenderer.setFillPoints(true);
		LikesRenderer.setLineWidth(20);
		LikesRenderer.setDisplayChartValues(true);

		// Khởi tạo một đối tượng XYMultipleSeriesRenderer để tùy chỉnh biểu đồ
		// theo ý muốn
		XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();

		// Thiết lập title
		multiRenderer.setChartTitle("Biểu đồ thể hiện khoản chi khoản thu");
		multiRenderer.setXTitle("Ngày/Năm");// Title trục X
	

		multiRenderer.setYAxisAlign(Align.CENTER, 0);
		multiRenderer.setYLabelsAlign(Align.CENTER);// Chữ nằm về phía bên phải
													// của cột

		multiRenderer.setXLabelsColor(Color.CYAN);// Màu sắc cho chữ trục X
		multiRenderer.setYTitle("Số Tiền (VNĐ)");// Title trục Y
		multiRenderer.setZoomButtonsVisible(true);// Không cho phép zoom nút
													// button

		// Thêm visitsRenderer and LikesRenderer vào multipleRenderer
		multiRenderer.addSeriesRenderer(visitsRenderer);
		multiRenderer.addSeriesRenderer(LikesRenderer);

		// Lấy đối tượng LinearLayout từ XML để sử dụng
		LinearLayout chartContainer = (LinearLayout) findViewById(R.id.llchar);

		// Tạo biểu đồ
		mChart = (GraphicalView) ChartFactory.getTimeChartView(
				getBaseContext(), dataset, multiRenderer, "dd-MMM-yyyy");

		multiRenderer.setClickEnabled(true);// Cho phép click
		multiRenderer.setSelectableBuffer(10);// Thiết lập vùng đệm

		// Thiết lập một sự kiện lắng nghe từ giao diện (không cần đoạn này cũng
		// đc)
		mChart.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Format formatter = new SimpleDateFormat("dd-MMM-yyyy");
				SeriesSelection seriesSelection = mChart
						.getCurrentSeriesAndPoint();
				if (seriesSelection != null) {
					int seriesIndex = seriesSelection.getSeriesIndex();
					String selectedSeries = "Khoản Thu";
					if (seriesIndex == 0)
						selectedSeries = "Khoản thu";
					else
						selectedSeries = "Khoản chi";
					long clickedDateSeconds = (long) seriesSelection
							.getXValue();
					Date clickedDate = new Date(clickedDateSeconds);
					String strDate = formatter.format(clickedDate);
					int amount = (int) seriesSelection.getValue();
					// Hiển thị toast
					Toast.makeText(getBaseContext(),
							selectedSeries + " on " + strDate + " : " + amount,
							Toast.LENGTH_SHORT).show();
				}
			}
		});

		// Add cái biểu đồ này vào LinearLayout của xml
		chartContainer.addView(mChart);
	}

}
