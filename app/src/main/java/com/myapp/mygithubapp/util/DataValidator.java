package com.myapp.mygithubapp.util;

import com.myapp.mygithubapp.R;
import com.myapp.mygithubapp.model.repo.Repo;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Response;

public class DataValidator {
    private static final String TAG = "DataValidator";

    /**
     * This method returns true if the repository is found in the list else returns false
     * @param repositoryList
     * @param repositoryName
     * @return
     */
    public static boolean isRepositoryFound(ArrayList<Repo> repositoryList, String repositoryName) {
        if ((repositoryList != null) && (!repositoryList.isEmpty())) {
            for (Repo repositoryInstance : repositoryList) {
                if (repositoryInstance.getName().equalsIgnoreCase(repositoryName)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method returns corresponding string id on the response received.
     * This string will be used to display message to the user in case the response is not valid
     * @param response
     * @return
     */
    public static int getMessageOnResponseReceived(Response<List<Repo>> response) {
        int messageId = R.string.invalid_response;

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

        return messageId;
    }

    /**
     * This method validates if the user input is empty
     * @param userInput
     * @return true if not empty else false
     */
    public static boolean isUserInputValid(String userInput) {
        if (userInput != null) {
            if (userInput.isEmpty() == true) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    public static String getConcatenatedString(String firstString, String secondString){
        if(firstString == null || firstString.isEmpty()){
            return secondString;
        }
        else if(secondString == null || secondString.isEmpty()){
            return firstString;
        }
        else {
            StringBuffer stringBuffer = new StringBuffer(firstString).append(secondString);
            return stringBuffer.toString();
        }
    }
}