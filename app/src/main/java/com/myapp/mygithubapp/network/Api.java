package com.myapp.mygithubapp.network;

import com.myapp.mygithubapp.model.commit.CommitInformation;
import com.myapp.mygithubapp.model.repo.Repo;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {
    String BASE_URL = "https://api.github.com/";

    @GET("/users/{user}/repos")
    Call<List<Repo>> getRepoInformationForUser(@Path("user") String user);

    @GET("/repos/{user}/{repo}/commits?")
    Call<List<CommitInformation>> getCommitInformationForRepos(@Path ("user") String user, @Path("repo") String repo, @Query("per_page") int pageSize, @Query("page") int currentPage);

}
