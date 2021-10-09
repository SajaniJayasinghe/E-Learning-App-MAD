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

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;


import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class I_MainAdapter extends FirebaseRecyclerAdapter<I_MainModal, I_MainAdapter.myViewHolder> {


    public I_MainAdapter(@NonNull  FirebaseRecyclerOptions<I_MainModal> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull  myViewHolder holder, final int position, @NonNull I_MainModal model) {
        holder.aTitle.setText(model.getaTitle());
        holder.aName.setText(model.getaName());
        holder.aNumber.setText(model.getaNumber());
        holder.aEmail.setText(model.getaEmail());
        holder.aDes.setText(model.getaDes());

        Glide.with(holder.img.getContext())
                .load(model.getAurl())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);

        holder.ibtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.i_update_popup))
                        .setExpanded(true,1320)
                        .create();

                View view = dialogPlus.getHolderView();

                EditText aTitle = view.findViewById(R.id.txtTitle);
                EditText aName = view.findViewById(R.id.txtName);
                EditText aNumber = view.findViewById(R.id.txtNumber);
                EditText aEmail = view.findViewById(R.id.txtEmail);
                EditText aDes = view.findViewById(R.id.txtDescription);
                EditText aurl = view.findViewById(R.id.txtImageUrl);
                
                //update advertisement
                Button ibtnUpdate = view.findViewById(R.id.ibtnUpdate);

                aTitle.setText(model.getaTitle());
                aName.setText(model.getaName());
                aNumber.setText(model.getaNumber());
                aEmail.setText(model.getaEmail());
                aDes.setText(model.getaDes());
                aurl.setText(model.getAurl());

                dialogPlus.show();

                ibtnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("aTitle",aTitle.getText().toString());
                        map.put("aName",aName.getText().toString());
                        map.put("aNumber",aNumber.getText().toString());
                        map.put("aEmail",aEmail.getText().toString());
                        map.put("aDes",aDes.getText().toString());
                        map.put("aurl",aurl.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("advertisements")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.aName.getContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure( Exception e) {
                                        Toast.makeText(holder.aName.getContext(), "Error While Updating", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });
            }
        });
        
        //Delete advertisement
        holder.ibtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.aName.getContext());
                builder.setTitle("Are you Sure?");
                builder.setMessage("Deleted data can't be Undo");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("advertisements")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.aName.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
    }

    @NonNull

    @Override
    public myViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_main_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        
        //decclaration variables
        CircleImageView img;
        TextView aTitle,aName,aNumber,aEmail,aDes;

        Button ibtnEdit,ibtnDelete;

        public myViewHolder(@NonNull  View itemView) {
            super(itemView);
            
            //initializing variables
            img = (CircleImageView)itemView.findViewById(R.id.img1);
            aTitle = (TextView)itemView.findViewById(R.id.titletext);
            aName = (TextView)itemView.findViewById(R.id.nametext);
            aNumber = (TextView)itemView.findViewById(R.id.numbertext);
            aEmail = (TextView)itemView.findViewById(R.id.emailtext);
            aDes = (TextView)itemView.findViewById(R.id.destext);

            ibtnEdit = (Button)itemView.findViewById(R.id.ibtnEdit);
            ibtnDelete = (Button)itemView.findViewById(R.id.ibtnDelete);
        }
    }
}

