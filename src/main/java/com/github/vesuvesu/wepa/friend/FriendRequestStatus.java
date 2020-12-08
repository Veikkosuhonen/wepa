package com.github.vesuvesu.wepa.friend;

public enum FriendRequestStatus {
    ACCEPTED("Accepted"), DECLINED("Declined"), PENDING("Pending");

    private String name;

    FriendRequestStatus(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}