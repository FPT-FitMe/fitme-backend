package com.fpt.fitme.dto.post;

public class DisablePostDTO {
    private Long postID;
    private Boolean isActive;

    public Long getPostID() {
        return postID;
    }

    public void setPostID(Long postID) {
        this.postID = postID;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
