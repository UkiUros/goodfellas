package rs.forexample.goodfellas.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import rs.forexample.goodfellas.R;

/**
 * Created by deki on 28.5.16..
 */
public class ImageAdapter extends RecyclerView.Adapter {

    public interface OnImageClickedListener {
        void onImageClicked(int image);
    }

    ArrayList<Integer> mImages;
    OnImageClickedListener mOnImageClickedListener;

    public ImageAdapter(ArrayList<Integer> imageList, OnImageClickedListener onImageClickedListener)
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
       int imageId = mImages.get(position);
        ((ImageViewHolder) holder).imageView.setImageResource(imageId);
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
