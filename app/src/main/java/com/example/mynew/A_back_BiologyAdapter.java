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

public class A_back_BiologyAdapter extends FirebaseRecyclerAdapter<A_BiologyModel, A_back_BiologyAdapter.myViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public A_back_BiologyAdapter(@NonNull FirebaseRecyclerOptions<A_BiologyModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull A_back_BiologyAdapter.myViewHolder holder, final int position, @NonNull A_BiologyModel model) {

        holder.question.setText(model.getQuestion());
        holder.optionA.setText(model.getOptionA());
        holder.optionB.setText(model.getOptionB());
        holder.optionC.setText(model.getOptionC());
        holder.optionD.setText(model.getOptionD());
        holder.correctAns.setText(model.getCorrectAns());

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.question.getContext())
                        .setContentHolder(new ViewHolder(R.layout.a_back_update_popup))
                        .setExpanded(true,1640)
                        .create();


                View view = dialogPlus.getHolderView();

                EditText question = view.findViewById(R.id.question);
                EditText optionA = view.findViewById(R.id.optionA);
                EditText optionB = view.findViewById(R.id.optionB);
                EditText optionC = view.findViewById(R.id.optionC);
                EditText optionD = view.findViewById(R.id.optionD);
                EditText correctAns = view.findViewById(R.id.correctAns);

                Button btnUpdate = view.findViewById(R.id.btnUpdate);

                question.setText(model.getQuestion());
                optionA.setText(model.getOptionA());
                optionB.setText(model.getOptionB());
                optionC.setText(model.getOptionC());
                optionD.setText(model.getOptionD());
                correctAns.setText(model.getCorrectAns());

                dialogPlus.show();

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("question",question.getText().toString());
                        map.put("optionA",optionA.getText().toString());
                        map.put("optionB",optionB.getText().toString());
                        map.put("optionC",optionC.getText().toString());
                        map.put("optionD",optionD.getText().toString());
                        map.put("correctAns",correctAns.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Biology")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.question.getContext(),"Data Updated Successfully", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(Exception e) {
                                        Toast.makeText(holder.question.getContext(),"Error While Updating", Toast.LENGTH_SHORT).show();
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
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.question.getContext());
                builder.setTitle("Are You Sure?");
                builder.setMessage("Deleted Data can't be Undo.");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        FirebaseDatabase.getInstance().getReference().child("Biology")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.question.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.show();
            }
        });

    }

    @NonNull
    @Override
    public A_back_BiologyAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.a_back_main_item,parent,false);

        return new A_back_BiologyAdapter.myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        TextView question,optionA,optionB,optionC,optionD,correctAns;

        Button btnEdit, btnDelete;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            question = (TextView)itemView.findViewById(R.id.question);
            optionA = (TextView)itemView.findViewById(R.id.optionA);
            optionB = (TextView)itemView.findViewById(R.id.optionB);
            optionC = (TextView)itemView.findViewById(R.id.optionC);
            optionD = (TextView)itemView.findViewById(R.id.optionD);
            correctAns = (TextView)itemView.findViewById(R.id.correctAns);

            btnEdit = (Button)itemView.findViewById(R.id.btnEdit);
            btnDelete = (Button)itemView.findViewById(R.id.btnDelete);

        }
    }



}

