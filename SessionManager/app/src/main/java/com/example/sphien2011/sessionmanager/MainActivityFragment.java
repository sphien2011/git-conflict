package com.example.sphien2011.sessionmanager;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    AlertDialogManager alert = new AlertDialogManager();
    SessionManager session;
    Button logout;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        session = new SessionManager(getContext());

        TextView lblname = (TextView) view.findViewById(R.id.lbl_name);
        TextView lblemail = (TextView) view.findViewById(R.id.lbl_email);

        logout = (Button) view.findViewById(R.id.btn_logout);
        Toast.makeText(getContext(), "User login status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();

        //check login or not
        session.checkLogin();

        //only need when want to show information of user
        HashMap<String, String> user = session.getUserDetails();
        String name = user.get(SessionManager.KEY_NAME);
        String email = user.get(SessionManager.KEY_EMAIL);

        lblname.setText(name);
        lblemail.setText(email);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session.logoutUser();
            }
        });

        return view;
    }
}
