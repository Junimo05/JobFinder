package com.example.jobfinder.main_screen_fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import com.example.jobfinder.MyApplication;
import com.example.jobfinder.R;
import com.example.jobfinder.data.api.ApiInterface;
import com.example.jobfinder.data.image.ImageLoad;
import com.example.jobfinder.data.model.Employee;
import com.example.jobfinder.databinding.FragmentProfileBinding;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountFragment extends Fragment {
    private FragmentProfileBinding binding;

    private ActivityResultLauncher launcher;

    private ImageLoad updateAvatar;

    private ApiInterface apiInterface;

    private SharedPreferences sharedPreferences;

    private Employee oldemployee;

    private boolean changeAvatar = false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_profile, container, false);
//        return view;
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        apiInterface = MyApplication.getRetrofitInstance().create(ApiInterface.class);
        sharedPreferences = getActivity().getSharedPreferences("LoginInfo", getContext().MODE_PRIVATE);
        //
        ImageView avatar = binding.accountAvatar;
        TextView setAvatar = binding.accountEditAvatar;
        Button update = binding.btnUpdate;

        //set up change avatar
        registerResult(avatar);
        setAvatar.setOnClickListener(v -> {
            pickImage();
        });

        //get userid
        String userID = sharedPreferences.getString("user_id", "");
        //call get employee
        prepareData(userID, avatar);

        updateAvatar = new ImageLoad(getContext(), apiInterface);
        //set up save button
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.accountEditName.getText().toString();
                String age = binding.accountEditBirth.getText().toString();
                String email = binding.accountEditMail.getText().toString();
                String phone = binding.accountEditPhone.getText().toString();
                String aboutme = binding.accountEditAboutMe.getText().toString();
                String hobbies = binding.accountEditHobbies.getText().toString();

                if (name.isEmpty() || age.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                    Toast.makeText(getContext(), "Tên, tuổi, email, điện thoại không được bỏ trống", Toast.LENGTH_SHORT).show();
                    // Thực hiện hành động cần thiết, ví dụ hiển thị thông báo lỗi
                } else {
                    Employee updated = new Employee(oldemployee.getEmployeeID(), oldemployee.getUserID(), name, Integer.parseInt(age), phone, email, aboutme, hobbies);
                    Call<Employee> call = apiInterface.updateEmployee(String.valueOf(oldemployee.getEmployeeID()), updated);
                    call.enqueue(new Callback<Employee>() {
                        @Override
                        public void onResponse(Call<Employee> call, Response<Employee> response) {
                            if (response.isSuccessful() && response.body() != null){
                                Toast.makeText(getContext(), "Cập nhật thông tin thành công", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onFailure(Call<Employee> call, Throwable t) {
                            Toast.makeText(getContext(), "Cập nhật thông tin thất bại", Toast.LENGTH_SHORT).show();
                            t.printStackTrace();
                        }
                    });
                    if(changeAvatar){
                        updateAvatar.uploadImage(getContext(), getActivity(), avatar, userID);
                    }
                }
                prepareData(userID, avatar);
            }
        });

    }

    private void prepareData(String userID, ImageView avatar){
        Call<Employee> call = apiInterface.getEmployeeById(userID);
        call.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                if (response.isSuccessful() && response.body() != null ){
                    oldemployee = response.body();
                    binding.accountEditName.setText(oldemployee.getName());
                    binding.accountEditBirth.setText(String.valueOf(oldemployee.getAge()));
                    binding.accountEditMail.setText(oldemployee.getEmail());
                    binding.accountEditPhone.setText(oldemployee.getPhone());
                    binding.accountEditAboutMe.setText(oldemployee.getAboutMe());
                    binding.accountEditHobbies.setText(oldemployee.getHobbies());
                }
            }
            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                t.printStackTrace();
            }
        });

        Picasso.get()
                .load("http://10.0.2.2:8000/users/" + userID + ".jpg")
                .into(avatar);
    }

    private void pickImage(){
        Intent intent = new Intent(MediaStore.ACTION_PICK_IMAGES);
        launcher.launch(intent);
    }

    private void registerResult(ImageView imageView){
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult o) {
                try {
                    Uri uri = o.getData().getData();
                    imageView.setImageURI(uri);
                    changeAvatar = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
