package com.myapp.mygithubapp.model;

/**
 * This class is the structure of the Commit information to be displayed on GUI.
 */
public class CommitInformation {
    private String authorName = null;
    private String commitHash = null;
    private String commitMessage = null;

    public CommitInformation() {
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getCommitHash() {
        return commitHash;
    }

    public void setCommitHash(String commitHash) {
        this.commitHash = commitHash;
    }

    public String getCommitMessage() {
        return commitMessage;
    }

    public void setCommitMessage(String commitMessage) {
        this.commitMessage = commitMessage;
    }
}
