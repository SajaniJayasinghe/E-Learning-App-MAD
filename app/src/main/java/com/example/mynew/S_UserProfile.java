package com.example.mynew;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;


public class S_UserProfile extends AppCompatActivity {

    Button updateButton, logout, deleteBtn;
    ImageButton img;
    TextView edtName, edtPhoneNumber, edtEmail;
    ImageView imageView;
    Uri imageUri;
    FirebaseUser mUser;
    FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    StorageReference storageReference;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode ==1000){  //gallery request code 1000
            if(resultCode == Activity.RESULT_OK){
                imageUri = data.getData();
                uploadImageToFirebase(imageUri);
            }
        }
    }
    //upload image
    private void uploadImageToFirebase(Uri imageUri) {
        StorageReference fileRef = storageReference.child("users/"+mAuth.getCurrentUser().getUid()+"profile.jpg");
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(imageView);
                    }
                });
                Toast.makeText(S_UserProfile.this,"Image Uploaded",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(S_UserProfile.this, "Image Uploaded Failed !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("User");
        updateButton = findViewById(R.id.update);
        logout = findViewById(R.id.logout);
        deleteBtn = findViewById(R.id.delete);
        img = findViewById(R.id.imageButton2);
        imageView = findViewById(R.id.userpro_image);
        mAuth = FirebaseAuth.getInstance();
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        img = findViewById(R.id.imageButton2);
        imageView = findViewById(R.id.userpro_image);
        edtName = findViewById(R.id.user_name);
        edtPhoneNumber = findViewById(R.id.user_phoneNo);
        edtEmail = findViewById(R.id.user_email);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("User");
        storageReference = FirebaseStorage.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        //data fetch
        edtName.setText(S_GlobalVariable.currentUser.getName());
        edtPhoneNumber.setText(S_GlobalVariable.currentUser.getPhoneNumber());
        edtEmail.setText(S_GlobalVariable.currentUser.getEmail());
        mAuth = FirebaseAuth.getInstance();
        mUser = FirebaseAuth.getInstance().getCurrentUser();

        //LogOut button
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                signOutUser();
            }
        });

        //update button
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(S_UserProfile.this, S_EditProfile.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        //Image Upload
        StorageReference profileRef = storageReference.child("users/" + mAuth.getCurrentUser().getUid() + "profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(imageView);
            }
        });

        //image button click and open the gallery
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open the gallery
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGalleryIntent, 1000); //gallery request code 1000
            }
        });

        //delete the user Account
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(S_UserProfile.this);
                dialog.setTitle("Are you sure ?");
                dialog.setMessage("Deleting this account will result in completely removing your account from the system and you won't be able to access the app");
                dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(S_UserProfile.this, "Account Deleted", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(S_UserProfile.this, S_LoginPage.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(S_UserProfile.this, "Invalid User Name Or Password", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
                dialog.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = dialog.create();
                alertDialog.show();
            }
        });
    }
    private void signOutUser() {
        Toast.makeText(S_UserProfile.this,"Logout Success",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(S_UserProfile.this, S_LoginPage.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}