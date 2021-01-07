package com.example.labthreeppo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;
public class UserProfileFragment extends Fragment {

    private Button btnChoose, btnUpload;
    private ImageView imageView;

    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 71;
    FirebaseDatabase database;
    DatabaseReference storageRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);



        database = FirebaseDatabase.getInstance();
        EditText editTextUsername = (EditText) view.findViewById(R.id.editTextUsername);
        editTextUsername.setText(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
        imageView = (ImageView) view.findViewById(R.id.imageView);

        String path = "images/" + "user-" + FirebaseAuth.getInstance().getCurrentUser().getUid();
        btnChoose = (Button) view.findViewById(R.id.btnChooseImage);
        btnUpload = (Button) view.findViewById(R.id.btnUploadImage);
        Button btnSaveUsername = (Button) view.findViewById(R.id.btnSaveUsername);


        btnSaveUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editTextUsername.getText().toString().equals("")) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName(editTextUsername.getText().toString())
                            .build();

                    user.updateProfile(profileUpdates)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(requireActivity(), "OK", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }

            }
        });

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });


        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });

        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            filePath = data.getData();
        }
    }
    public void loadAvatar(DatabaseReference childRef, ImageView imageView) {
        File localFile = null;
        try {
            localFile = File.createTempFile("images", ".jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Choose Image"), PICK_IMAGE_REQUEST);
    }
}
