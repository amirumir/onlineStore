package onlinestore.repository;

import onlinestore.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MethodsForListOfUsersTest {

    @Test
    void userByNickname() {
        User user1 = new User(null, null, "Amir", 0, null, null,null, null);
        User user2 = new User(null, null, "Emil", 0, null, null,null, null);
        User user3 = new User(null, null, "Chingiz", 0, null, null,null, null);
        MethodsForListOfUsers methodsForListOfUsers = new MethodsForListOfUsers();
        List<User> userList = List.of(user1, user2,user3);
        User actualResult = methodsForListOfUsers.userByNickname(userList,"Amir");
        User expectedUser = user1;
        assertEquals(expectedUser,actualResult);
    }

    @Test
    void findUsersWithGivenName() {
        User user1 = new User(null, "Amir", null, 0, null, null,null, null);
        User user2 = new User(null, "Emil",null , 0, null, null,null, null);
        User user3 = new User(null, "Chingiz", null, 0, null, null,null, null);
        MethodsForListOfUsers methodsForListOfUsers = new MethodsForListOfUsers();
        List<User> userList = List.of(user1, user2,user3);
        List<User> actualResult = methodsForListOfUsers.findUsersWithGivenName(userList,"Amir");
        List<User> expectedUser = List.of(user1);
        assertEquals(expectedUser,actualResult);
    }

    @Test
    void deleteUserByNickname() {
        User user1 = new User(null, null, "Amir", 0, null, null,null, null);
        User user2 = new User(null, null, "Emil", 0, null, null,null, null);
        User user3 = new User(null, null, "Chingiz", 0, null, null,null, null);
        MethodsForListOfUsers methodsForListOfUsers = new MethodsForListOfUsers();
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        methodsForListOfUsers.deleteUserWithSameNickname(userList, "Amir");
        List<User> actualResult = userList;
        List<User> expectedUser = List.of(user2,user3);
        assertEquals(expectedUser,actualResult);
    }

    @Test
    void findAllUSERS() {
        User user1 = new User(null, null, "Amir", 0, null, User.Role.ADMIN,null, null);
        User user2 = new User(null, null, "Emil", 0, null, User.Role.USER,null, null);
        User user3 = new User(null, null, "Chingiz", 0, null, User.Role.USER,null, null);
        MethodsForListOfUsers methodsForListOfUsers = new MethodsForListOfUsers();
        List<User> userList = List.of(user1,user2,user3);
        List<User> actualResult = methodsForListOfUsers.findAllUsersWithUserRole(userList);
        List<User> expectedUser = List.of(user2,user3);
        assertEquals(expectedUser,actualResult);
    }

    @Test
    void findAllADMINS() {
        User user1 = new User(null, null, "Amir", 0, null, User.Role.ADMIN,null, null);
        User user2 = new User(null, null, "Emil", 0, null, User.Role.USER,null, null);
        User user3 = new User(null, null, "Chingiz", 0, null, User.Role.USER,null, null);
        MethodsForListOfUsers methodsForListOfUsers = new MethodsForListOfUsers();
        List<User> userList = List.of(user1,user2,user3);
        List<User> actualResult = methodsForListOfUsers.findAllUsersWithAdminRole(userList);
        List<User> expectedUser = List.of(user1);
        assertEquals(expectedUser,actualResult);
    }

    @Test
    void checkIfUserHasThisNickname() {
        User user1 = new User(null, null, "Amir", 0, null, null,null, null);
        User user2 = new User(null, null, "Emil", 0, null, null,null, null);
        User user3 = new User(null, null, "Chingiz", 0, null, null,null, null);
        MethodsForListOfUsers methodsForListOfUsers = new MethodsForListOfUsers();
        boolean actualResult = methodsForListOfUsers.checkIfUserHasThisNickname(user1,"Amir");
        boolean expectedUser = true;
        assertEquals(expectedUser,actualResult);
    }
}