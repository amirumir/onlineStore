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
    Channel channel = new Channel();

    private void checkMethod(User user, String methodName, Class<?>[] paramTypes) {

        try {
            Method method = mapChannelRepository.getClass().getMethod(methodName, paramTypes);
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
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException ex) {
            ex.printStackTrace();
        }

    }

    private boolean checkMethodV2(User user, String methodName, Class<?>[] paramTypes) {

        try {
            Method method = mapChannelRepository.getClass().getMethod(methodName, paramTypes);
            if (method.isAnnotationPresent(HasRole.class)) {
                HasRole annotation = method.getAnnotation(HasRole.class);
//                    User[] role = user.getClass().getEnumConstants();
//                    Arrays.toString(role);

                if (annotation.value().toString().equals(user.getRole().toString())) {
//                        method.invoke(mapChannelRepository, user);
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
//                    System.out.println("HasRole annotation is absent");
            }
        } catch (NoSuchMethodException ex) {
            ex.printStackTrace();
        }

        return false;
    }


    @Override
    public void create(Channel channel, User user) {
        String methodCreate = "create";
        Class<?>[] paramTypes = {Channel.class, User.class};
        this.channel = channel;
        if (mapChannelRepository == null) {
            mapChannelRepository = new MapChannelRepository();
        }
        checkMethod(user, methodCreate, paramTypes);

//        try {
//            String methodName = "create";
//            Class<?>[] paramTypes = {Channel.class, User.class};
//            Method method = mapChannelRepository.getClass().getMethod(methodName, paramTypes);
//            if (method.isAnnotationPresent(HasRole.class)) {
//                HasRole annotation = method.getAnnotation(HasRole.class);
//                if (annotation.value().toString().equals(user.getRole().toString())) {
//                    method.invoke(mapChannelRepository, channel, user);
//                } else {
//                    System.out.println("No access for this method");
//                }
//            } else {
//                System.out.println("HasRole annotation is absent");
//            }
//        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
//            throw new RuntimeException(e);
//        }
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
