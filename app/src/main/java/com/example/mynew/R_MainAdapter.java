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

import org.jetbrains.annotations.NotNull;

import de.hdodenhof.circleimageview.CircleImageView;

public class R_MainAdapter extends FirebaseRecyclerAdapter<R_MainModel, R_MainAdapter.myViewholder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public R_MainAdapter(@NonNull  FirebaseRecyclerOptions<R_MainModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull  myViewholder holder, int position, @NonNull @NotNull R_MainModel model) {
        holder.subject_name.setText(model.getSubject_name());
        holder.subject_title.setText(model.getSubject_title());
        holder.subject_description.setText(model.getSubject_description());

        Glide.with(holder.img.getContext())
                .load(model.getSurl())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(R.drawable.common_google_signin_btn_icon_dark)
                .into(holder.img);
    }


    @NonNull
    @Override
    public myViewholder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.r_main_item1,parent,false);
        return new myViewholder(view);
    }

    class myViewholder extends RecyclerView.ViewHolder{

        CircleImageView img;
        TextView subject_name,subject_title,subject_description,surl;

        public myViewholder(@NonNull View itemView) {
            super(itemView);

            img = (CircleImageView)itemView.findViewById(R.id.RDimg1);
            subject_name = (TextView)itemView.findViewById(R.id.RDsubjectname);
            subject_title = (TextView)itemView.findViewById(R.id.RDsubjecttitle);
            subject_description = (TextView)itemView.findViewById(R.id.RDsubjectdescription);
        }
    }
}
