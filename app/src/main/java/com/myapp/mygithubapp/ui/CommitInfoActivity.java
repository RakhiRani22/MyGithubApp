package com.myapp.mygithubapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.myapp.mygithubapp.R;

/**
 * This activity shall display the list of commit information of a specific
 * user and repo
 */
public class CommitInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commit_information);
    }
}
