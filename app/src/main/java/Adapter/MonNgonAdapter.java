package Adapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.tudiennauan.R;
import com.mode.ObjectMonNgon;


import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;


public class MonNgonAdapter extends ArrayAdapter<Object>  {



    ArrayList<Object> m_ListView;

    protected LayoutInflater m_Inflater;
    int m_SourceOfView;
    ArrayList<String> lstBookMask;
    String type;

    // Khai báo đối tượng Universal và các thuộc tính cần thiết của nó
    // Universal Image Loader
    static DisplayImageOptions mOptions;
    ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
    public ImageLoader imageLoader = ImageLoader.getInstance();

    // Universal Image Loader
    private class AnimateFirstDisplayListener extends
            SimpleImageLoadingListener {

        final List<String> displayedImages = Collections
                .synchronizedList(new LinkedList<String>());

        @Override
        public void onLoadingComplete(String imageUri, View view,
                                      Bitmap loadedImage) {
            if (loadedImage != null) {
                ImageView imageView = (ImageView) view;
                boolean firstDisplay = !displayedImages.contains(imageUri);
                if (firstDisplay) {
                    FadeInBitmapDisplayer.animate(imageView, 500);
                    displayedImages.add(imageUri);
                }
                // L.e("=========== TAI ANH XONG ==========");
            }
        }
    }


    public MonNgonAdapter(Context context, int resource, ArrayList<Object> objects) {
        super(context, resource, objects);
        // TODO Auto-generated constructor stub
        this.m_ListView=objects;
        this.m_Inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);



        // Khởi tạo đối tượng và thuộc tính cần thiết cho UniversalImageLoader
        mOptions = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.ic_launcher)
                .showImageForEmptyUri(R.drawable.ic_launcher)
                .showImageOnFail(R.drawable.ic_launcher).cacheInMemory()
                .cacheOnDisc().displayer(new SimpleBitmapDisplayer()).build();
        imageLoader.init(ImageLoaderConfiguration.createDefault(context));

    }

    // Kết thúc khởi tạo

    public View getLayout(Object obj, View v, int position) {

        ViewHolder viewHolder;
        if (v == null) {
            viewHolder = new ViewHolder();
            v = (View) m_Inflater.inflate(R.layout.item_mau, null);

            viewHolder.ivMonNgon = (ImageView) v.findViewById(R.id.thumb);
            viewHolder.tvTitle= (TextView) v.findViewById(R.id.title);


            v.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) v.getTag();
        }
        ObjectMonNgon feed = (ObjectMonNgon) obj;



        viewHolder.tvTitle.setText(feed.getShow());

        try {
            String imgchuan = feed.getImg();

            imageLoader.displayImage(imgchuan, viewHolder.ivMonNgon, mOptions,
                    animateFirstListener);

        } catch (Exception e) {
            // TODO: handle exception
            viewHolder.ivMonNgon.setImageResource(R.drawable.ic_launcher);
        }



        return v;
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
     * @param listView
     *            the listView to set
     */
    public void setListView(ArrayList<Object> listView) {
        this.m_ListView = listView;
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

    static class ViewHolder {
        TextView tvTitle;
        ImageView ivMonNgon;

    }

}
