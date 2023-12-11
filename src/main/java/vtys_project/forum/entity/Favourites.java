package vtys_project.forum.entity;

import jakarta.persistence.Id;
import org.springframework.stereotype.Component;

@Component
public class Favourites {
    @Id
    private int favouritesID;;
    private int userID;
    private int topicID;

    // Getter and Setter Methods

    public int getFavouritesID() {
        return favouritesID;
    }

    public void setFavouritesID(int favouritesID) {
        this.favouritesID = favouritesID;
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
