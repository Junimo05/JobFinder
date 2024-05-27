package viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import data.model.LoginUser;
import data.model.User;
import utils.LoginStatus;

public class LoginViewModel extends ViewModel {
    public MutableLiveData<Integer> getLoginStatus() {
        return loginstatus;
    }

    private MutableLiveData<Integer> loginstatus = new MutableLiveData<>();
    private LoginUser loginUser = new LoginUser();

    public void onclickLogin(){
        try {
            if (validateData()) {
                //call api login here
                loginstatus.setValue(LoginStatus.loginSuccess);
            }
        } catch (Exception ex) {
            System.out.println("Login viewmodel" + ex.getMessage());
        }
    }

    private boolean validateData() {
        try {
            if (loginUser.getUsername().isEmpty()) {
                loginstatus.setValue(LoginStatus.emptyUsername);
                return false;
            } else if (loginUser.getPassword().isEmpty()) {
                loginstatus.setValue(LoginStatus.emptyPassWord);
                return false;
            }
        } catch (Exception ex) {
            System.out.println("Validate viewmodel" + ex.getMessage());
        }
        return true;
    }

    public LoginUser getLoginUser() {
        return loginUser;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
