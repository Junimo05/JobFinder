package com.example.jobfinder.main_screen_fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jobfinder.LoginActivity;
import com.example.jobfinder.MyApplication;
import com.example.jobfinder.R;
import com.example.jobfinder.data.api.ApiInterface;
import com.example.jobfinder.data.image.ImageLoad;
import com.example.jobfinder.data.model.User;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;

import retrofit2.Call;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class SettingsFragment extends Fragment {

    ActivityResultLauncher<Intent> resultLauncher;

    ApiInterface apiInterface = MyApplication.getRetrofitInstance().create(ApiInterface.class);

    private ImageLoad imageLoad;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageLoad = new ImageLoad(getContext(), apiInterface);

        //Lấy imageView từ layout
        ImageView avatar = view.findViewById(R.id.account_icon);

        //load image from server
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("LoginInfo", getContext().MODE_PRIVATE);
        String id = sharedPreferences.getString("user_id", "");
        String username = sharedPreferences.getString("username", "");
        TextView usernameView = view.findViewById(R.id.tv_account_title);
        usernameView.setText(username);
        Picasso.get()
                .load("http://10.0.2.2:8000/users/" + id + ".jpg")
                .into(avatar);

        //Đặt imageView (not push to server)
        registerResult(avatar);
        avatar.setOnClickListener(v -> {
            pickImage();
        });

        //Tiến hành đẩy lên server
        TextView uploadImage = view.findViewById(R.id.upload_image);
        uploadImage.setOnClickListener(v -> {
            imageLoad.uploadImage(getContext(), getActivity(), avatar, id);
        });


        //changePassword
        TextView changePassword = view.findViewById(R.id.change_password);
        changePassword.setOnClickListener(v -> {
//            Intent intent = new Intent(getActivity(), ChangePasswordActivity.class);
//            startActivity(intent);
        });

        //signOut
        TextView signOut = view.findViewById(R.id.sign_out);
        signOut.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        });

        //back
        ImageView back = view.findViewById(R.id.back_button);
        back.setOnClickListener(v -> {
            getActivity().onBackPressed();
        });

    }


    //set image for avatar (not included upload image)
    private void pickImage(){
        Intent intent = new Intent(MediaStore.ACTION_PICK_IMAGES);
        resultLauncher.launch(intent);
    }


    //set up change image for avatar
    private void registerResult(ImageView imageView){
        resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult o) {
                try {
                    Uri uri = o.getData().getData();
                    imageView.setImageURI(uri);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //upload image to server
//    private void uploadImage(ImageView imageView, String id){
//        Drawable drawable = imageView.getDrawable();
//        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
//        Uri imageUri = getImageUri(getContext(), bitmap);
//
//        // Tạo một File từ Uri
//        String filePath = getPathFromUri(imageUri);
//        if (filePath == null) {
//            // Xử lý lỗi
//            return;
//        }
//        File file = new File(filePath);
//
//        // Tạo một RequestBody từ File
//        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
//
//        // Tạo một MultipartBody.Part từ RequestBody
//        MultipartBody.Part imagePart = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
//
//        // Tạo các RequestBody khác
//        RequestBody key = RequestBody.create(MediaType.parse("text/plain"), "image");
//        RequestBody value = RequestBody.create(MediaType.parse("text/plain"), "value");
//
//        // Gọi API
//        apiInterface = MyApplication.getRetrofitInstance().create(ApiInterface.class);
//
//        Call<User> call = apiInterface.uploadImage(imagePart, key, value, id);
//        call.enqueue(new retrofit2.Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, retrofit2.Response<User> response) {
//                if (response.isSuccessful()) {
//                    User user = response.body();
//                } else {
//                    // Xử lý lỗi
//                }
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//                // Xử lý lỗi
//            }
//        });
//    }
//
//    //get image uri
//    private Uri getImageUri(Context context, Bitmap bitmap) {
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
//        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "Title", null);
//        return Uri.parse(path);
//    }
//
//    //get path from uri
//    private String getPathFromUri(Uri uri) {
//        String[] projection = { MediaStore.Images.Media.DATA };
//        Cursor cursor = getActivity().getContentResolver().query(uri, projection, null, null, null);
//        if (cursor != null) {
//            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//            cursor.moveToFirst();
//            String path = cursor.getString(column_index);
//            cursor.close();
//            return path;
//        } else {
//            return null;
//        }
//    }
}