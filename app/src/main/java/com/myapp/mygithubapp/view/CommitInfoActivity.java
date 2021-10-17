package com.myapp.mygithubapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.myapp.mygithubapp.MyGithubApplication;
import com.myapp.mygithubapp.R;
import com.myapp.mygithubapp.adapter.CommitInfoAdapter;
import com.myapp.mygithubapp.model.commit.CommitInformation;
import com.myapp.mygithubapp.network.Api;
import com.myapp.mygithubapp.util.Constants;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * This activity shall display the list of commit information of a specified
 * user and repo
 */
public class CommitInfoActivity extends AppCompatActivity implements Callback<List<CommitInformation>>{
    private static final String TAG = "CommitInfoActivity";
    private ArrayList<CommitInformation> commitInformationList;
    private RecyclerView.Adapter commitInfoAdapter;
    private int pageNumber = 1;
    private boolean isLoading = false;
    private String username;
    private String repositoryName;

    @Inject
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commit_information);

        ((MyGithubApplication) getApplication()).getNetComponent().inject(this);
        Intent intent = getIntent();
        username = intent.getStringExtra(Constants.USERNAME);
        repositoryName = intent.getStringExtra(Constants.REPOSITORY_NAME);
        commitInformationList = new ArrayList<>();

        if (repositoryName == null) finish();

        setTitle(new StringBuffer(getResources().getString(R.string.repo)).append(repositoryName));

        RecyclerView recyclerView = findViewById(R.id.commit_info_list);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView. addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration. VERTICAL));
        initScrollListener(recyclerView);

        commitInfoAdapter = new CommitInfoAdapter(this, commitInformationList);
        recyclerView.setAdapter(commitInfoAdapter);
        getCommitInformationInPages(pageNumber);
    }

    private void initScrollListener(RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                Log.i(TAG,"isLoading"+isLoading);
                if (!isLoading) {
                    if ((linearLayoutManager != null) && (linearLayoutManager.findLastCompletelyVisibleItemPosition() == commitInformationList.size() - 1)) {
                        getCommitInformationInPages(getNextPageNumber());
                        isLoading = true;
                    }
                }
            }
        });
    }

    private void getCommitInformationInPages(int pageNumber) {
        Log.d(TAG, "PageNumber to be requested:"+pageNumber);
        Api api = retrofit.create(Api.class);
        Call<List<CommitInformation>> call = api.getCommitInformationForRepos(username, repositoryName, Constants.PER_PAGE_SIZE, pageNumber);
        call.enqueue(this);
    }

    private int getNextPageNumber(){
        pageNumber = pageNumber + 1;
        return pageNumber;
    }

    private void displayMessage(int stringId){
        String message = getResources().getString(stringId);
        Log.i(TAG, "String to display:"+message);
        Toast.makeText(CommitInfoActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(Call<List<CommitInformation>> call, Response<List<CommitInformation>> response) {
        Log.i(TAG, "Response:" + response.body());
        List<CommitInformation> commitInstanceList = response.body();
        if (commitInstanceList != null) { //No commit found
            if (!commitInstanceList.isEmpty()) { //Reached end of server data, no more request needed as last response was empty
                isLoading = false;
                commitInformationList.addAll(commitInstanceList);
                commitInfoAdapter.notifyDataSetChanged();
            }
        } else {
            displayMessage(R.string.no_commit_performed);
            finish();
        }
    }

    @Override
    public void onFailure(Call<List<CommitInformation>> call, Throwable t) {
        Log.i(TAG, "Request failed:" + t.getMessage());
        isLoading = false;
        displayMessage(R.string.commit_info_request_failed);
    }
}
