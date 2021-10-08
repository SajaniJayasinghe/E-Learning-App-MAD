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
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class R_back_MainAdapter extends FirebaseRecyclerAdapter<R_back_MainModel, R_back_MainAdapter.myViewholder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public R_back_MainAdapter(@NonNull  FirebaseRecyclerOptions<R_back_MainModel> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull  myViewholder holder, final int position, @NonNull R_back_MainModel model) {
        holder.subject_name.setText(model.getSubject_name());
        holder.subject_title.setText(model.getSubject_title());
        holder.subject_description.setText(model.getSubject_description());


        //image upload
        Glide.with(holder.img.getContext())
                .load(model.getSurl())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(R.drawable.common_google_signin_btn_icon_dark)
                .into(holder.img);


        //edit button part
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.r_update_popup))
                        .setExpanded(true,1650)
                        .create();

                //dialogPlus.show();

                View view = dialogPlus.getHolderView();

                EditText name = view.findViewById(R.id.RsubName);
                EditText title = view.findViewById(R.id.RsubtitleName);
                EditText description = view.findViewById(R.id.Rsubdescription);
                EditText asurl = view.findViewById(R.id.RimageUrl);

                Button btnUpdate = view.findViewById(R.id.RbtnUpdate);

                name.setText(model.getSubject_name());
                title.setText(model.getSubject_title());
                description.setText(model.getSubject_description());
                asurl.setText(model.getSurl());

                dialogPlus.show();


                //update button part
                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //data store firebase using hashmap
                        Map<String,Object> map = new HashMap<>();
                        map.put("subject_name",name.getText().toString());
                        map.put("subject_title",title.getText().toString());
                        map.put("subject_description",description.getText().toString());
                        map.put("surl",asurl.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Subjects")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.subject_name.getContext(), "Data Updated Sucessfully", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {

                                    //Exception
                                    @Override
                                    public void onFailure(Exception e) {
                                        Toast.makeText(holder.subject_name.getContext(), "Error While Updating", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });

                    }
                });
            }
        });

        //delete button part
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.subject_name.getContext());
                builder.setTitle("Are You Sure?");
                builder.setMessage("Deleted data can't be Undo");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("Subjects")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.subject_name.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });

    }

    //retrieve part
    @NonNull
    @Override
    public myViewholder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.r_main_item,parent,false);
        return new myViewholder(view);
    }

    class myViewholder extends RecyclerView.ViewHolder{

        CircleImageView img;
        TextView subject_name,subject_title,subject_description,surl;


        Button btnEdit,btnDelete;

        public myViewholder(@NonNull  View itemView) {
            super(itemView);

            img = (CircleImageView)itemView.findViewById(R.id.Rimg1);
            subject_name = (TextView)itemView.findViewById(R.id.Rsubjectname);
            subject_title = (TextView)itemView.findViewById(R.id.Rsubjecttitle);
            subject_description = (TextView)itemView.findViewById(R.id.Rsubjectdescription);


            btnEdit = (Button)itemView.findViewById(R.id.RbtnEdit);
            btnDelete = (Button)itemView.findViewById(R.id.RbtnDelete);


        }
    }
}

