package com.myapp.mygithubapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.myapp.mygithubapp.model.repo.Repo;
import com.myapp.mygithubapp.network.Api;
import com.myapp.mygithubapp.util.Constants;
import com.myapp.mygithubapp.util.DataValidator;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/*
This Activity class has 2 options:
1. To display the commit information with the default userName and repo name.
2. To display the commit information from the user input username and repo name.
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ArrayList<Repo> repoList = null;

    @Inject
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((MyGithubApplication) getApplication()).getNetComponent().inject(this);
        Log.i("TAG", "Started");
        repoList = new ArrayList<>();
        final EditText usernameText = findViewById(R.id.username);
        final EditText repoNameText = findViewById(R.id.repo_name);
        final Button displayCommitBtn = findViewById(R.id.display_commit_btn);
        final Button displayDefaultCommitBtn = findViewById(R.id.display_default_commit_btn);

        displayCommitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameText.getText().toString();
                String repositoryName = repoNameText.getText().toString();

                if(DataValidator.isUserInputValid(username) == false) {
                    displayToast(R.string.enter_valid_username);
                }
                else if(DataValidator.isUserInputValid(repositoryName) == false) {
                    displayToast(R.string.enter_valid_repo_name);
                }
                else {
                    getRepoDataForUsername(username, repositoryName);
                }
            }
        });

        displayDefaultCommitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRepoDataForUsername(Constants.DEFAULT_USERNAME, Constants.DEFAULT_REPO_NAME);
            }
        });
    }

    /**
     *
     * @param username
     * @param repositoryName
     * This method make a network request to fetch the repo information of the given username
     */
    private void getRepoDataForUsername(String username, String repositoryName) {
        Log.d(TAG, "Get repo data for User:"+username+" Repo:"+repositoryName);
        Api api = retrofit.create(Api.class);
        Call<List<Repo>> call = api.getRepoInformationForUser(username);
        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                Log.i(TAG, "Response received:" + response.body());

                repoList.clear();
                Response<List<Repo>> responseObj = response;
                int messageId = DataValidator.getMessageOnResponseReceived(responseObj);
                if(messageId != R.string.valid_response){
                    displayToast(messageId);
                }
                else {
                    Log.i(TAG, "Response received:" + response.body());
                    List<Repo> repositoryList = response.body();
                    if(repositoryList!= null && !repositoryList.isEmpty()) {
                        repoList.addAll(repositoryList);
                        handleRepoResponse(username, repositoryName);
                    }
                    else
                    {
                        displayToast(R.string.repository_not_found); //When response is empty array with success code
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                Log.i(TAG, "The request has failed: " + t.getMessage());
                displayToast(R.string.repo_request_failed);
            }
        });
    }

    /**
     *
     * @param username
     * @param repositoryName
     * The method displys the list of commits if repository found else display
     * the message that "Repository not found"
     */
    private void handleRepoResponse(String username, String repositoryName){
        Log.i(TAG, "Response received");
        if(DataValidator.isRepositoryFound(repoList, repositoryName)){
            Log.d(TAG,"Repository Found");
            displayToast(R.string.valid_response);
        }
        else {
            displayToast(R.string.repository_not_found);
        }
    }

    private void displayToast(int stringId){
        String message = getResources().getString(stringId);
        Log.i(TAG, "String to display:"+message);
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
