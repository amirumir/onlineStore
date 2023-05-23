package onlinestore.repository;

import onlinestore.Channel;

import java.util.List;

public class ChannelRepositoryProxy implements ChannelRepository {

    MapChannelRepository mapChannelRepository = new MapChannelRepository();
    @Override
    public void create(Channel channel) {
        
    }

    @Override
    public void follow(Channel channel, Long userId) {
    }

    @Override
    public List<Channel> findALl() {
        return null;
    }

    @Override
    public List<Channel> findOwnChannelsByUserId(Long userId) {
        return null;
    }

    @Override
    public List<Channel> findFollowedChannelsByUserId(Long userId) {
        return null;
    }

    @Override
    public Channel findById(Long id) {
        return null;
    }
}
