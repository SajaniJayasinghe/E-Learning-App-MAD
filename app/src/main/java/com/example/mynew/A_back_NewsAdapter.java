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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class A_back_NewsAdapter extends FirebaseRecyclerAdapter<A_NewsModel,A_back_NewsAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public A_back_NewsAdapter(@NonNull FirebaseRecyclerOptions<A_NewsModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, final int position, @NonNull A_NewsModel model) {

        holder.news.setText(model.getNews());

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.news.getContext())
                        .setContentHolder(new ViewHolder(R.layout.a_back_update_popup1))
                        .setExpanded(true,850)
                        .create();

                //dialogPlus.show();

                View view = dialogPlus.getHolderView();

                EditText news = view.findViewById(R.id.news);

                Button btnUpdate = view.findViewById(R.id.btnUpdate);

                news.setText(model.getNews());

                dialogPlus.show();

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("news",news.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("News")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.news.getContext(),"Data Updated Successfully", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(Exception e) {
                                        Toast.makeText(holder.news.getContext(),"Error While Updating", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });

                    }
                });
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.news.getContext());
                builder.setTitle("Are You Sure?");
                builder.setMessage("Deleted Data can't be Undo.");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        FirebaseDatabase.getInstance().getReference().child("News")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.news.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.show();
            }
        });

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.a_back_main_news,parent,false);

        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        TextView news;

        Button btnEdit, btnDelete;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            news = (TextView)itemView.findViewById(R.id.news);

            btnEdit = (Button)itemView.findViewById(R.id.btnEdit);
            btnDelete = (Button)itemView.findViewById(R.id.btnDelete);

        }
    }



}