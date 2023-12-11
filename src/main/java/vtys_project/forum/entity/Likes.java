package vtys_project.forum.entity;

import jakarta.persistence.Id;
import org.springframework.stereotype.Component;

@Component
public class Likes {
    @Id
    private int LikeID;
    private int userID;
    private int topicID;

    // Getter and Setter Methods


    public int getLikeID() {
        return LikeID;
    }

    public void setLikeID(int likeID) {
        LikeID = likeID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getTopicID() {
        return topicID;
    }

    public void setTopicID(int topicID) {
        this.topicID = topicID;
    }
}
