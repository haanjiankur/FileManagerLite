package com.epizy.ankurdhuriya.filemanager.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.epizy.ankurdhuriya.filemanager.helper.TouchImageView;
import com.epizy.ankurdhuriya.filemanager.model.MediaFileListModel;
import com.epizy.ankurdhuriya.filemanager.R;
import java.io.File;
import java.util.ArrayList;


/*
 * Created by ankur on 23/06/18.
 */

public class FullScreenImageAdapter extends PagerAdapter {
    private Activity _activity;
    private ArrayList<MediaFileListModel> mediaFileListModelArrayList;

    public FullScreenImageAdapter(Activity activity, ArrayList<MediaFileListModel> mediaFileListModelArrayList) {
        this._activity = activity;
        this.mediaFileListModelArrayList=mediaFileListModelArrayList;
    }

    @Override
    public int getCount() {
        return this.mediaFileListModelArrayList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) _activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewLayout = inflater.inflate(R.layout.layout_fullscreen_image, container,false);
        TouchImageView imgDisplay = (TouchImageView) viewLayout.findViewById(R.id.imgDisplay);
        MediaFileListModel mediaFileListModel=mediaFileListModelArrayList.get(position);
        File imgFile = new File(mediaFileListModel.getFilePath());
        if (imgFile.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            imgDisplay.setImageBitmap(myBitmap);
        }
        ((ViewPager) container).addView(viewLayout);
        return viewLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);
    }
}