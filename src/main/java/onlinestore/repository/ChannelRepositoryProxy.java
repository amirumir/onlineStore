package onlinestore.repository;

import onlinestore.Channel;
import onlinestore.HasRole;
import onlinestore.TestingRoleValidator;
import onlinestore.User;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class ChannelRepositoryProxy implements ChannelRepository {

    MapChannelRepository mapChannelRepository = new MapChannelRepository();

    private void checkMethod(User user) {

        try {
            for (Method method : MapChannelRepository.class.getDeclaredMethods()) {
                if (method.isAnnotationPresent(HasRole.class)) {
                    HasRole annotation = method.getAnnotation(HasRole.class);
//                    User[] role = user.getClass().getEnumConstants();
//                    Arrays.toString(role);

                    if (annotation.value().toString().equals(user.getRole().toString())) {
                        method.invoke(mapChannelRepository, user);
                    } else {
                        System.out.println("No access for this method");
                    }
                } else {

                    System.out.println("HasRole annotation is absent");
                }
            }

        } catch (InvocationTargetException | IllegalAccessException | RuntimeException e) {
            e.printStackTrace();

        }
    }


    @Override
    public void create(Channel channel, User user) {
        try {
            String methodName = "create";
            Class<?>[] paramTypes = {Channel.class, User.class};
            Method method = mapChannelRepository.getClass().getMethod(methodName, paramTypes);
            if (method.isAnnotationPresent(HasRole.class)) {
                HasRole annotation = method.getAnnotation(HasRole.class);
                if (annotation.value().toString().equals(user.getRole().toString())) {
                    method.invoke(mapChannelRepository, channel, user);
                } else {
                    System.out.println("No access for this method");
                }
            } else {
                System.out.println("HasRole annotation is absent");
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
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
