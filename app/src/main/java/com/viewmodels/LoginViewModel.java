package com.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.callback.OnCheckAdminListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.repos.AdminRepository;

public class LoginViewModel extends ViewModel {
    private AdminRepository adminRepository;
    public enum AuthState {
        AUTHENTICATED, UNAUTHENTICATED
    }
    private MutableLiveData<AuthState> stateLiveData;
    private MutableLiveData<String> errMsgLiveData;
    private FirebaseAuth auth;
    private FirebaseUser user;

    public LoginViewModel() {
        adminRepository = new AdminRepository();
        stateLiveData = new MutableLiveData<>();
        errMsgLiveData = new MutableLiveData<>();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        if (user == null) {
            stateLiveData.postValue(AuthState.UNAUTHENTICATED);
        }else {
            stateLiveData.postValue(AuthState.AUTHENTICATED);
        }
    }

    public LiveData<AuthState> getStateLiveData() {
        return stateLiveData;
    }

    public LiveData<String> getErrMsgLiveData() {
        return errMsgLiveData;
    }

    public FirebaseUser getUser() {
        return user;
    }

    public void login(String email, String password) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {

                    adminRepository.checkAdmin(authResult.getUser().getUid(), new OnCheckAdminListener() {
                        @Override
                        public void onCheckAdmin(boolean status) {
                            if(status){
                                user = authResult.getUser();
                                 stateLiveData.postValue(AuthState.AUTHENTICATED);
                            }else {
                                auth.signOut();
                                errMsgLiveData.postValue("This email is not register as Admin, Please use a valid account!");
                            }
                        }
                    });
                }).addOnFailureListener(e -> {
                    errMsgLiveData.postValue(e.getLocalizedMessage());
                });
    }



    public void logout() {
        if (user != null) {
            auth.signOut();
            stateLiveData.postValue(AuthState.UNAUTHENTICATED);
        }
    }

    public boolean isEmailVerified() {
        return user.isEmailVerified();
    }

    public void sendVerificationMail() {
        user.sendEmailVerification()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

}
