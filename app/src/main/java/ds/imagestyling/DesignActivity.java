package ds.imagestyling;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.knef.stickerview.StickerImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


import de.hdodenhof.circleimageview.CircleImageView;
import ds.imagestyling.custom.FirstFragment;
import ds.imagestyling.custom.GPUImageFilterTools;
import ds.imagestyling.custom.Image_Text_Editor;

import ds.imagestyling.utils.App;
import ds.imagestyling.utils.Constants;
import ds.imagestyling.utils.Preferences;
import jp.co.cyberagent.android.gpuimage.GPUImageFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageView;

public class DesignActivity extends AppCompatActivity implements View.OnTouchListener,View.OnClickListener,RadioGroup.OnCheckedChangeListener{
    public Button mClear;
    public TextView mGetSign,mCancel;
    ImageView img_camera;
    GPUImageView img;
    LinearLayout relview__,layout,layout1;
    RelativeLayout mContent;
    ImageView imgback;
    public View mView;
    RadioButton radio_sticker,radio_filter,radio_text;
    private Bitmap mBitmap;

    private long timestamp;
    public File rootsd;
    public String current = null,imgpath="";;
    public File mypath;
    FrameLayout[] rel,rel1;
    public List<GPUImageFilterTools.FilterType> filters = new LinkedList<GPUImageFilterTools.FilterType>();


    private String[] img_name_list = { "Normal", "Kelvin", "Amaro", "Rise",
            "Hudson", "X_proll", "Lomo_fi", "EarlyBird", "Sutro", "Toaster",
            "Brannan", "InkWell", "Walden", "Hefe", "Valencia", "Nashville",
            "1997" };
    public static Bitmap bm;

    private ViewPagerAdapter mAdapter;
    private HorizontalScrollView hz_list;
    private TextView _view;
    private LinearLayout ll_delete;

    private ArrayList<Drawable> stickerData=new ArrayList<>();


    private RadioGroup rg_button;
    private boolean loadFirstTime;
    private App mInstance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_design);
        mInstance=App.getApp();
        stickerData.add(getResources().getDrawable(R.drawable.stiker_1));
        stickerData.add(getResources().getDrawable(R.drawable.stiker_2));
        stickerData.add(getResources().getDrawable(R.drawable.stiker_3));
        stickerData.add(getResources().getDrawable(R.drawable.stiker_4));
        init();


        GPUImageFilterTools.setFilterList();
        filters=GPUImageFilterTools.getFiletList();




      bm = BitmapFactory.decodeResource(getResources(),
                R.drawable.images);

        int nh = (int) ( bm.getHeight() * (512.0 / bm.getWidth()) );
        bm= Bitmap.createScaledBitmap(bm, 512, nh, true);


        img.setImage(bm);


    }


    CircleImageView[] image1;

    ViewPager viewPager;
    private void SetLayout() {

        viewPager.setOffscreenPageLimit(12);
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);

    }



    private void SetLayout1() {
        rel1 = new FrameLayout[stickerData.size()];
        image1 = new CircleImageView[stickerData.size()];
        for (int i = 0; i < stickerData.size(); i++) {
            View hiddenInfo = getLayoutInflater().inflate(R.layout.image_item_row, layout1, false);
            layout1.addView(hiddenInfo);
            rel1[i] = (FrameLayout) hiddenInfo.findViewById(R.id.rel);
            image1[i] =(CircleImageView) hiddenInfo.findViewById(R.id.image);
            // Picasso.with(this).load(stickerData.get(i).getSticker_name()).fit().placeholder(R.drawable.image_placeholder).into(image1[i]);
            image1[i].setImageDrawable(stickerData.get(i));
            //   image1[i].setBorderWidth(5);
            image1[i].setId(i);
            rel1[i].setId(i);
            rel1[i].setTag(stickerData.get(i));
            rel1[i].setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(final View v) {
                    StickerImageView iv_sticker = new StickerImageView(DesignActivity.this);
                    iv_sticker.setImageDrawable((Drawable) v.getTag());
                    iv_sticker.setOnTouchListener(DesignActivity.this);
                    mContent.addView(iv_sticker);

                }
            });




        }
        rect= new Rect();
        hz_list.getHitRect(rect);
    }
    Rect rect;
    private GPUImageFilter mFilter;
    @Override
    public void onBackPressed() {
        if(getIntent().hasExtra("isDB"))
        {
            finish();
            return;
        }

    }



    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.radio_sticker:
                layout1.setVisibility(View.VISIBLE);
                layout.setVisibility(View.GONE);
                //  mView. setDrawingCacheEnabled(true);
                //  drawBitmap(mView,true);
                if(!loadSticker) {
                    SetLayout1();
                    loadSticker=true;
                }
                break;
            case R.id.radio_filter:
                layout1.setVisibility(View.GONE);
                layout.setVisibility(View.VISIBLE);
                //  viewPager.setVisibility(View.VISIBLE);
                //   viewPager.setOnTouchListener(null);
                if(!loadColorFilter) {
                    SetLayout();
                    layout.setVisibility(View.GONE);
                    loadColorFilter=true;
                }
                else
                {
                    drawBitmapForFiler(mView);

                    mAdapter.notifyDataSetChanged();
                }
                img.setVisibility(View.GONE);
                viewPager.setVisibility(View.VISIBLE);
                break;

        }
    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {

            return FirstFragment.newInstance(pos,filters);


        }
        @Override
        public void destroyItem(View collection, int position, Object o) {
            Log.d("DESTROY", "destroying view at position " + position);
            View view = (View) o;
            ((ViewPager) collection).removeView(view);
            view = null;
        }
        @Override
        public int getCount() {
            return 17;
        }
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        try {
            if (!loadFirstTime) {
         /*   new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {*/
                if (!getIntent().hasExtra("isDB"))
                    radio_filter.setChecked(true);


          /*   }},1000);*/
                loadFirstTime = true;
            }
            if (Preferences.getString(Preferences.SAVE).equalsIgnoreCase("yes")) {

                _view = new TextView(this);
                Log.d("System out",
                        "Fontsize"
                                + Integer.parseInt(Preferences.getString(("FontSize")))
                                + "path" + Preferences.getString("FontType") + "color"
                                + Constants.color);
                _view.setText(Preferences.getString("font"));
                _view.setTextSize(Integer.parseInt(Preferences.getString("FontSize")));
                Typeface tf1 = Typeface.createFromFile(Preferences.getString("FontType"));
                _view.setTypeface(tf1);
                if (Constants.color == -1)
                    _view.setTextColor(getResources().getColor(R.color.black));
                else
                    _view.setTextColor(getResources().getColor(Constants.color));
                _view.setId(2);
                view_count = 2;
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.leftMargin = 50;
                layoutParams.topMargin = 50;
                layoutParams.bottomMargin = -250;
                layoutParams.rightMargin = -250;
                _view.setLayoutParams(layoutParams);

                _view.setOnTouchListener(this);
                mContent.addView(_view, ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }








    @Override
    public boolean onTouch(View view, MotionEvent event) {
        final int X = (int) event.getRawX();
        final int Y = (int) event.getRawY();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                ll_delete.setVisibility(View.VISIBLE);
                RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view
                        .getLayoutParams();
                _xDelta = X - lParams.leftMargin;
                _yDelta = Y - lParams.topMargin;
                Log.d("System out", "x" + _xDelta + "y" + _yDelta);

                break;
            case MotionEvent.ACTION_UP:

                if(containsView(ll_delete,view))
                    mContent.removeView(view);
                ll_delete.setVisibility(View.GONE);
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view
                        .getLayoutParams();
                layoutParams.leftMargin = X - _xDelta;
                layoutParams.topMargin = Y - _yDelta;
                layoutParams.rightMargin = -250;
                layoutParams.bottomMargin = -250;
                view.setLayoutParams(layoutParams);
                break;
        }
        // _root.invalidate();
        return true;
    }
    private int _xDelta;
    private int _yDelta;
    private int view_count;
    public boolean containsView(View dropZone, View draggedView){
        // Create the Rect for the view where items will be dropped
        int[] pointA = new int[2];
        dropZone.getLocationOnScreen(pointA);
        Rect rectA = new Rect(pointA[0], pointA[1], pointA[0] + dropZone.getWidth(), pointA[1] + dropZone.getHeight());

        // Create the Rect for the view been dragged
        int[] pointB = new int[2];
        draggedView.getLocationOnScreen(pointB);
        Rect rectB = new Rect(pointB[0], pointB[1], pointB[0] + draggedView.getWidth(), pointB[1] + draggedView.getHeight());
        if (pointB[0]+100 < pointA[0] || pointB[0] > pointA[0] + dropZone.getWidth() +100||  pointB[1]+100 <  pointA[1]  ||  pointB[1] > pointA[1] + dropZone.getHeight()+100) {
            return false;
        }
        return true;
        // Check if the dropzone currently contains the dragged view
    }

    public void init() {

        img_camera = (ImageView) findViewById(R.id.img_camera);
        viewPager = (ViewPager) findViewById(R.id.vw_gallery_pager);
        mGetSign = (TextView) findViewById(R.id.getsign);



        hz_list = (HorizontalScrollView) findViewById(R.id.hz_list);
        mCancel = (TextView) findViewById(R.id.cancel);
        mContent = (RelativeLayout) findViewById(R.id.linearLayout);
        layout = (LinearLayout) findViewById(R.id.layout);
        layout1 = (LinearLayout) findViewById(R.id.layout1);
        ll_delete = (LinearLayout) findViewById(R.id.ll_delete);
        img = (GPUImageView) findViewById(R.id.img);
        radio_sticker = (RadioButton) findViewById(R.id.radio_sticker);
        radio_filter = (RadioButton) findViewById(R.id.radio_filter);
        radio_text = (RadioButton) findViewById(R.id.radio_text);
        rg_button = (RadioGroup) findViewById(R.id.rg_button);
        mView = mContent;
        img_camera.setOnClickListener(this);
        mGetSign.setOnClickListener(this);
        mCancel.setOnClickListener(this);
        radio_text.setOnClickListener(this);
        rg_button.setOnCheckedChangeListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v == radio_text) {
            layout1.setVisibility(View.GONE);
            layout.setVisibility(View.GONE);

            Intent i = new Intent(DesignActivity.this, Image_Text_Editor.class);
            startActivity(i);
        }

        else if (v == mGetSign) {
            Toast.makeText(this, "Successfully saved", Toast.LENGTH_SHORT).show();

            for(int index=0; index<((ViewGroup)mContent).getChildCount(); ++index) {
                View nextChild = ((ViewGroup)mContent).getChildAt(index);
                if(nextChild instanceof StickerImageView)
                {
                     ((StickerImageView) nextChild).setControlItemsHidden(true);
                }
            }

            mView.setDrawingCacheEnabled(true);
            if(getIntent().hasExtra("isDB")) {
                save(mView);
                return;
            }


            save(mView);
            if (mBitmap!=null) {
                mBitmap=null;
            }


        }









    }


    boolean loadSticker,loadColorFilter;

    private void drawBitmapForFiler(View v) {
        if (mBitmap == null) {
            mBitmap=null;
            mBitmap = Bitmap.createBitmap(mContent.getWidth(),
                    mContent.getHeight(), Bitmap.Config.RGB_565);
        }
        Canvas canvas = new Canvas(mBitmap);
        v.draw(canvas);
        bm=mBitmap;

    }




    public void save(View v) {

        if (mBitmap == null) {
            mBitmap=null;
            mBitmap = Bitmap.createBitmap(mContent.getWidth(),
                    mContent.getHeight(), Bitmap.Config.RGB_565);
        }
        Canvas canvas = new Canvas(mBitmap);
        v.draw(canvas);
        App.getApp().setImageBit(mBitmap);
        Intent intent=new Intent(DesignActivity.this,FinalImageActivity.class);
        startActivity(intent);
        finish();


    }



}
