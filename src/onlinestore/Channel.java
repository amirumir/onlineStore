package onlinestore;

import java.util.ArrayList;
import java.util.List;

public class Channel {
    private Long id;
    private List<String> messages = new ArrayList<>();
    private List<Long> followers = new ArrayList<>();
    private String name;


    private Long userId;

    public Long getId() {
        return id;
    }

    public List<Long> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Long> followers) {
        this.followers = followers;
    }

    public List<String> getMessages() {
        return messages;
    }

    public String getName() {
        return name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public void setName(String name) {
        this.name = name;
    }
}