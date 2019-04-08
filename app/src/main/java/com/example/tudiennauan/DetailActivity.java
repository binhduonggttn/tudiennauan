package com.example.tudiennauan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;


import android.view.View;

import android.webkit.WebView;
import android.widget.ImageView;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings.LayoutAlgorithm;


import com.mode.ObjectMonNgon;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;


public class DetailActivity extends Activity {

    Integer myPossion;
    ArrayList<Object> lstMonNgon;
    private WebView wvContent;
    ImageView ivContent;


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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);



        // Khởi tạo đối tượng và thuộc tính cần thiết cho UniversalImageLoader
        mOptions = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.ic_launcher)
                .showImageForEmptyUri(R.drawable.ic_launcher)
                .showImageOnFail(R.drawable.ic_launcher).cacheInMemory()
                .cacheOnDisc().displayer(new SimpleBitmapDisplayer()).build();
        imageLoader.init(ImageLoaderConfiguration
                .createDefault(DetailActivity.this));

        ivContent=(ImageView)findViewById(R.id.ivContent);
        wvContent=(WebView)findViewById(R.id.wvContent);
        wvContent.getSettings().setJavaScriptEnabled(true);

        wvContent.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
        wvContent.setWebChromeClient(new WebChromeClient());

        Bundle myBunder = getIntent().getBundleExtra("extra");
        myPossion = myBunder.getInt("mypossion");
        lstMonNgon = (ArrayList<Object>) myBunder.getSerializable("objects");

        wvContent.loadUrl("file:///android_asset/monan/"+myPossion+".html");


        try {

            imageLoader.displayImage(
                    ((ObjectMonNgon) lstMonNgon.get(myPossion-1)).getImg(),
                    ivContent, mOptions, animateFirstListener);

        } catch (Exception e) {
            // TODO: handle exception
            ivContent.setImageResource(R.drawable.ic_launcher);
        }



       // idTxtx.setText("l:"+((ObjectMonNgon) lstMonNgon.get(myPossion)).getImg());

    }

    public  void  onClickBackList(View view)
    {
        finish();
    }



}
