package com.example.mynew;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class S_MainBookUserViewAdapter extends FirebaseRecyclerAdapter<S_MainBookModel, S_MainBookUserViewAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public S_MainBookUserViewAdapter(@NonNull FirebaseRecyclerOptions<S_MainBookModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull S_MainBookModel model) {
        holder.bookName.setText(model.getBookName());
        holder.author.setText(model.getAuthor());
        holder.stream.setText(model.getStream());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(v.getContext(),S_BookDetails.class);
                myIntent.putExtra("pid",model.getPid());
                v.getContext().startActivity(myIntent);
            }
        });
//image
        Glide.with(holder.imgurl.getContext())
                .load(model.getImgurl())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.imgurl);

    }
//fetch the data from database
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
