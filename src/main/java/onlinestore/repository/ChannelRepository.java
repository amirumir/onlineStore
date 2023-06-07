package onlinestore.repository;

import onlinestore.Channel;
import onlinestore.User;

import java.util.List;

public interface ChannelRepository {
    void create(Channel channel, User user);

    void follow(Channel channel, Long userId);

    List<Channel> findALl();

    List<Channel> findOwnChannelsByUserId(Long userId);

    List<Channel> findFollowedChannelsByUserId(Long userId);

    Channel findById(Long id);
}
