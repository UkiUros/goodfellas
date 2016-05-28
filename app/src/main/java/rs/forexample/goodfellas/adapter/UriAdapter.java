package rs.forexample.goodfellas.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

import rs.forexample.goodfellas.R;

/**
 * Created by deki on 28.5.16..
 */
public class UriAdapter extends RecyclerView.Adapter {

    public interface OnImageClickedListener {
        void onImageClicked(Uri image);
    }

    ArrayList<Uri> mImages;
    OnImageClickedListener mOnImageClickedListener;

    public UriAdapter(ArrayList<Uri> imageList, OnImageClickedListener onImageClickedListener)
    {
        mImages = imageList;
        mOnImageClickedListener = onImageClickedListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_image, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ImageViewHolder imageViewHolder = ((ImageViewHolder) holder);
       Uri uri = mImages.get(position);
        Log.d("Deki", "onBindViewHolder: " + uri);
        File file = new File(uri.toString());
        Picasso.with(imageViewHolder.imageView.getContext()).load(file).resize(100, 100).centerCrop().into(imageViewHolder.imageView);
    }



    @Override
    public int getItemCount() {
        return mImages.size();
    }

    private class ImageViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        public ImageViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnImageClickedListener.onImageClicked(mImages.get(getAdapterPosition()));
                }
            });
        }
    }
}
