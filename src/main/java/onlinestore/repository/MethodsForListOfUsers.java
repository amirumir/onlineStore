package onlinestore.repository;

import onlinestore.User;

import java.util.*;
import java.util.stream.Collectors;

public class MethodsForListOfUsers {
    public User userByNickname(List<User> users, String nickname) {
        for (User user : users) {
            if (nickname.equals(user.getNickname())) {
                return user;
            }
        }
        return null;
    }

    public List<User> findUsersWithGivenName(List<User> users, String name) {
        List<User> usersWithSameName = new ArrayList<>();
        for (User user : users) {
            if (name.equals(user.getName())) {
                usersWithSameName.add(user);
            }
        }
        return usersWithSameName;
    }

    public void deleteUserWithSameNickname(List<User> users, String nickname) {
        User user1 = null;
        for (User user : users) {
            if (nickname.equals(user.getNickname())) {
                user1 = user;
            }
        }
        if (user1 != null) {
            users.remove(user1);
        }
    }

    public List<User> findAllUsersWithUserRole(List<User> users) {
        List<User> onlyUserRole = new ArrayList<>();
        for (User user : users) {
            if (user.getRole() == User.Role.USER) {
                onlyUserRole.add(user);
            }
        }
        return onlyUserRole;
    }

    public List<User> findAllUsersWithAdminRole(List<User> users) {
        List<User> onlyAdminRole = new ArrayList<>();
        for (User user : users) {
            if (user.getRole() == User.Role.ADMIN) {
                onlyAdminRole.add(user);
            }
        }
        return onlyAdminRole;
    }

    public boolean checkIfUserHasThisNickname(User user, String nickname) {
        return user.getNickname().equals(nickname);
    }

    public Map<String, List<User>> groupUsersByName(List<User> users) {
//        HashMap <String, List<User>> usersGroupedByName = new HashMap<>();
//        String name1 = null;
//        for (User user : users) {
//            name1 = user.getNickname();
//        }

        return users.stream()
                .collect(Collectors.groupingBy(User::getName));
    }

//7*. метод который принимает список юзеров и возвращает сгруппированных юзеров по никнейму
//8*. метод который принимает список юзеров и список пар никнейм-роль и каждому юзеру в списке который содержится в списке пар назначает соответствующую роль


}
