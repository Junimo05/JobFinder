package com.example.jobfinder.data.image;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.ImageView;

import com.example.jobfinder.MyApplication;
import com.example.jobfinder.data.api.ApiInterface;
import com.example.jobfinder.data.model.User;

import java.io.ByteArrayOutputStream;
import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

public class ImageLoad {

    private Context context;
    private ApiInterface apiInterface;

    public ImageLoad(Context context, ApiInterface apiInterface) {
        this.context = context;
    }

    public void uploadImage(Context context, Activity activity, ImageView imageView, String id){
        Drawable drawable = imageView.getDrawable();
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        Uri imageUri = getImageUri(context, bitmap);

        // Tạo một File từ Uri
        String filePath = getPathFromUri(activity, imageUri);
        if (filePath == null) {
            // Xử lý lỗi
            return;
        }
        File file = new File(filePath);

        // Tạo một RequestBody từ File
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);

        // Tạo một MultipartBody.Part từ RequestBody
        MultipartBody.Part imagePart = MultipartBody.Part.createFormData("image", file.getName(), requestBody);

        // Tạo các RequestBody khác
        RequestBody key = RequestBody.create(MediaType.parse("text/plain"), "image");
        RequestBody value = RequestBody.create(MediaType.parse("text/plain"), "value");

        // Gọi API
        apiInterface = MyApplication.getRetrofitInstance().create(ApiInterface.class);

        Call<User> call = apiInterface.uploadImage(imagePart, key, value, id);
        call.enqueue(new retrofit2.Callback<User>() {
            @Override
            public void onResponse(Call<User> call, retrofit2.Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                } else {
                    // Xử lý lỗi
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // Xử lý lỗi
            }
        });
    }

    //get image uri
    private Uri getImageUri(Context context, Bitmap bitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "Title", null);
        return Uri.parse(path);
    }

    //get path from uri
    private String getPathFromUri(Activity activity, Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = activity.getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String path = cursor.getString(column_index);
            cursor.close();
            return path;
        } else {
            return null;
        }
    }
}
