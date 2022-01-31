package com.example.myecomapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myecomapplication.databinding.FragmentLoginBinding;
import com.utils.HelperFunction;
import com.viewmodels.LoginViewModel;


public class LoginFragment extends Fragment {
    private LoginViewModel loginViewModel;
    private FragmentLoginBinding binding;



    public LoginFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        loginViewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);
        binding = FragmentLoginBinding.inflate(inflater);

        loginViewModel.getStateLiveData()
                .observe(getViewLifecycleOwner(), authState -> {
                    if (authState == LoginViewModel.AuthState.AUTHENTICATED) {
                        Navigation.findNavController(container)
                                .navigate(R.id.action_loginFragment_to_dashboardFragment);
                    }
                });

        loginViewModel.getErrMsgLiveData()
                .observe(getViewLifecycleOwner(), errMsg -> {
                    binding.errMsgTV.setText(errMsg);
                });

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = binding.emailInputET.getText().toString();
                final String password = binding.passwordInputET.getText().toString();
                if(email.isEmpty() || password.isEmpty()){
                    HelperFunction.showToast(getActivity(), "Please provide both field values");
                    return;
                }
                loginViewModel.login(email, password);

            }
        });

        return binding.getRoot();
    }
}