package com.example.sphien2011.sessionmanager;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoginActivityFragment extends Fragment {

    EditText userName, password;
    Button login;
    AlertDialogManager alert = new AlertDialogManager();
    SessionManager session;

    public LoginActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        session = new SessionManager(getContext());
        userName = (EditText) view.findViewById(R.id.ed_user_name);
        password = (EditText) view.findViewById(R.id.ed_password);

        Toast.makeText(getContext(), "User login status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();

        login = (Button) view.findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = userName.getText().toString();
                String pass = password.getText().toString();

                if (user.trim().length() > 0 && pass.trim().length() > 0) {
                    if (user.equals("test") && pass.equals("test")) {
                        session.createLoginSession("Android Hive", "androidhive@gmail.com");

                        Intent intent = new Intent(getContext(), MainActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    } else {
                        alert.showAlergDialog(getContext(), "Login failed", "UserName/password is incorrect", false);
                    }
                } else {
                    alert.showAlergDialog(getContext(), "Login failed", "Please enter username or password", false);
                }
            }
        });
        return view;
    }
}
