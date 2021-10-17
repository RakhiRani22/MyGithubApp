package com.myapp.mygithubapp.util;

import android.util.Log;
import com.myapp.mygithubapp.R;
import com.myapp.mygithubapp.model.repo.Repo;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Response;

public class DataValidator {
    private static final String TAG = "DataValidator";

    /**
     *
     * @param repositoryList
     * @param repositoryName
     * @return
     * This method returns true if the repository is found in the list else returns false
     */
    public static boolean isRepositoryFound(ArrayList<Repo> repositoryList, String repositoryName) {
        for (Repo repositoryInstance : repositoryList) {
            if (repositoryInstance.getName().equalsIgnoreCase(repositoryName)) {
                Log.i(TAG, "Repository found successfully!");
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param response
     * @return
     * This method returns corresponding string id on the response received.
     * This string will be used to display message to the user in case the response is not valid
     */
    public static int getMessageOnResponseReceived(Response<List<Repo>> response) {
        int messageId = 0;

        if (response != null) {
            if (response.code() == 401) {
                messageId = R.string.two_factor_auth;//"Two-factor authentication is active, please enter code.";
            } else if (response.code() == 403) {
                messageId = R.string.limit_exceeded;//"Maximum number of login attempts exceeded. Please try again later.";
            } else if (!response.isSuccessful()) {
                messageId = R.string.invalid_username;//"Cannot fetch data from GitHub! Please verify the username";
            } else if (response.body().toString().isEmpty()) {
                messageId = R.string.invalid_repository;//"Empty response! No public repository for this user fetched.";
            } else {
                //Valid response
                messageId = R.string.valid_response;
            }
        } else {
            messageId = R.string.invalid_response;//"Response is null!";
        }

        Log.i(TAG, "Error Message:"+messageId);
        return messageId;
    }
}
