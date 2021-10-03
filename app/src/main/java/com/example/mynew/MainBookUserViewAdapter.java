package com.example.mynew;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainBookUserViewAdapter extends FirebaseRecyclerAdapter<MainBookModel,MainBookUserViewAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainBookUserViewAdapter(@NonNull FirebaseRecyclerOptions<MainBookModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull MainBookModel model) {
        holder.bookName.setText(model.getBookName());
        holder.author.setText(model.getAuthor());
        holder.stream.setText(model.getStream());

        Glide.with(holder.imgurl.getContext())
                .load(model.getImgurl())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.imgurl);

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_user_view_book,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder {

        CircleImageView imgurl;
        TextView author, bookName,stream;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            imgurl = (CircleImageView)itemView.findViewById(R.id.book_view_image);
            author = (TextView)itemView.findViewById(R.id.book_view_authorname);
            bookName = (TextView)itemView.findViewById(R.id.book_view_name);
            stream = (TextView)itemView.findViewById(R.id.book_view_stream);

        }
    }
}
