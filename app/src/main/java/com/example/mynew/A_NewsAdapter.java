package com.example.mynew;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class A_NewsAdapter extends FirebaseRecyclerAdapter<A_NewsModel,A_NewsAdapter.myViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public A_NewsAdapter(@NonNull FirebaseRecyclerOptions<A_NewsModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull A_NewsAdapter.myViewHolder holder, int position, @NonNull A_NewsModel model) {
        holder.news.setText(model.getNews());

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_news_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        TextView news;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            news = (TextView)itemView.findViewById(R.id.news);


        }
    }



}