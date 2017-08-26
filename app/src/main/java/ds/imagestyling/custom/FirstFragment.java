package ds.imagestyling.custom;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;



import java.util.List;

import ds.imagestyling.DesignActivity;
import ds.imagestyling.R;
import jp.co.cyberagent.android.gpuimage.GPUImage;

/**
 * Created by admin
 * Created on 6/24/2017.
 * Modified on 24,June,2017
 */

public class FirstFragment extends Fragment {

     ImageView img1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.image_item, container, false);

      img1 = (ImageView) v.findViewById(R.id.img_item);
        final LinearLayout ll_bottom = (LinearLayout) v.findViewById(R.id.ll_bottom);
        if( getArguments().getInt("msg")==0) {
            img1.setImageBitmap(((ds.imagestyling.DesignActivity) getActivity()).bm);
            return v;
        }
             new LoadBitpmap().execute();








        return v;
    }
    private class LoadBitpmap extends AsyncTask<String,Void,Bitmap> {



        @Override
        protected Bitmap doInBackground(String... params) {
            try {
                final GPUImage gpuImage = new GPUImage(getActivity());
                gpuImage.setFilter(GPUImageFilterTools.createFilterForType(getActivity(),((DesignActivity) getActivity()).filters.get(        getArguments().getInt("msg"))));

                Bitmap effectBitmap = gpuImage.getBitmapWithFilterApplied(((DesignActivity) getActivity()).bm);
                return effectBitmap;
            } catch(Exception e) {
                System.out.println(e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            img1.setImageBitmap(bitmap);

        }
    }

    public static FirstFragment newInstance(int text, List<GPUImageFilterTools.FilterType> filterTypes) {

        FirstFragment f = new FirstFragment();
        Bundle b = new Bundle();
        b.putInt("msg", text);


        f.setArguments(b);

        return f;
    }
}