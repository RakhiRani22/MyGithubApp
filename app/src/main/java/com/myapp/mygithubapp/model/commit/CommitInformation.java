package com.myapp.mygithubapp.model.commit;

public class CommitInformation {
    String sha;
    Commit commit;

    public CommitInformation(String sha, Commit commit) {
        this.sha = sha;
        this.commit = commit;
    }

    @Override
    public String toString() {
        return "CommitInformation{" +
                "sha='" + sha + '\'' +
                ", commit=" + commit +
                '}';
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public Commit getCommit() {
        return commit;
    }

    public void setCommit(Commit commit) {
        this.commit = commit;
    }
}