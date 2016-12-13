package com.rajesh.gallery.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.rajesh.gallery.R;
import com.rajesh.gallery.activities.ImagesActivity;
import com.rajesh.gallery.model.GalleryPhotoAlbum;
import com.rajesh.gallery.utils.Utils;


import java.util.ArrayList;

public class ImageAlbumAdapter extends RecyclerView.Adapter<ImageAlbumAdapter.MyViewHolder> {

    private ArrayList<GalleryPhotoAlbum> moviesList;
    private Context context;

    public ImageAlbumAdapter(ArrayList<GalleryPhotoAlbum> moviesList, Context ctx) {
        this.moviesList = moviesList;
        this.context = ctx;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_gallery_album, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder,final int position) {
        final GalleryPhotoAlbum movie = moviesList.get(position);

        holder.albumName.setText(movie.getBucketName());
        holder.albumCount.setText(String.valueOf(movie.getTotalCount()));

        if(!TextUtils.isEmpty(movie.getData())){
            Utils.loadImageFromUrl(holder.imgThumb,context,movie.getData());
        }

        holder.imgThumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, ImagesActivity.class);
                i.putExtra("BucketName",moviesList.get(position).getBucketName());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imgThumb;
        TextView albumName;
        TextView albumCount;

        public MyViewHolder(View view) {
            super(view);

            albumName = (TextView) view.findViewById(R.id.album_name);
            albumCount = (TextView) view.findViewById(R.id.album_count);
            imgThumb = (ImageView) view.findViewById(R.id.album_thumbnail);

        }
    }

}
