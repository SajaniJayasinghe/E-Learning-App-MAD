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

public class S_backend_MainBookAdapter extends FirebaseRecyclerAdapter<S_MainBookModel, S_backend_MainBookAdapter.myViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public S_backend_MainBookAdapter(@NonNull FirebaseRecyclerOptions<S_MainBookModel> options) {
        super(options);
    }
    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, final int position, @NonNull S_MainBookModel model) {
        holder.bookName.setText(model.getBookName());
        holder.author.setText(model.getAuthor());
        holder.stream.setText(model.getStream());
        holder.description.setText(model.getDescription());

//image upload
        Glide.with(holder.imgurl.getContext())
                .load(model.getImgurl())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.imgurl);

//edit button
        holder.book_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.imgurl.getContext())
                        .setContentHolder(new ViewHolder(R.layout.s_book_update_popup))
                        .setExpanded(true,1210)
                        .create();

//update data
                View view = dialogPlus.getHolderView();

                EditText bookName = view.findViewById(R.id.bookname);
                EditText author = view.findViewById(R.id.book_authorname);
                EditText stream = view.findViewById(R.id.book_stream);
                EditText description = view.findViewById(R.id.book_description);
                EditText imgurl = view.findViewById(R.id.book_image);

                Button bookUpdate = view.findViewById(R.id.bookupdate);

                bookName.setText(model.getBookName());
                author.setText(model.getAuthor());
                stream.setText(model.getStream());
                description.setText(model.getDescription());
                imgurl.setText(model.getImgurl());

                dialogPlus.show();

 //data store firebase using hashmap
                bookUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("bookName",bookName.getText().toString());
                        map.put("author",author.getText().toString());
                        map.put("stream",stream.getText().toString());
                        map.put("description",description.getText().toString());
                        map.put("imgurl",imgurl.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Book")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.bookName.getContext(),"Data Updated Successfully !! ",Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
//Exception
                            @Override
                            public void onFailure(Exception e) {
                                Toast.makeText(holder.bookName.getContext(),"Error while Updating !! ",Toast.LENGTH_SHORT).show();
                                dialogPlus.dismiss();
                            }
                        });
                    }
                });
            }
        });

//Delete data from database
        holder.book_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.bookName.getContext());
                builder.setTitle("Are you sure delete ?");
                builder.setMessage("Deleted data can't be Undo.");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("Book")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.bookName.getContext(),"Cancelled.",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
    }

//Fetch the data from database
    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.s_main_book,parent,false);
        return new myViewHolder(view);
    }
    class myViewHolder extends RecyclerView.ViewHolder{
        CircleImageView imgurl;
        TextView author,bookName,description,stream;
        Button book_edit,book_delete;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            imgurl = (CircleImageView)itemView.findViewById(R.id.book_image);
            author = (TextView)itemView.findViewById(R.id.book_authorname);
            bookName = (TextView)itemView.findViewById(R.id.bookname);
            stream = (TextView)itemView.findViewById(R.id.book_stream);
            description = (TextView)itemView.findViewById(R.id.book_description);
            book_edit = (Button) itemView.findViewById(R.id.book_edit);
            book_delete = (Button) itemView.findViewById(R.id.book_delete);
        }
    }
}
