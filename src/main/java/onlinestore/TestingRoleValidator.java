package onlinestore;


import java.time.LocalDate;

public class TestingRoleValidator {


    @HasRole(User.Role.ADMIN)
    public void userInfo(User user) {
        System.out.println(user.getRole());
    }

    @HasRole(User.Role.USER)
    public void userInfo2(User user) {
        System.out.println(user.getRole());
    }

    public static void main(String[] args) {

        User user = new User.UserBuilder()
                .role(User.Role.ADMIN)
                .build();
        User user3 = new User.UserBuilder()
                .name("emil")
                .build();
        User user1 = new User(1L, "name1", "nickname", 18, "1234", User.Role.ADMIN, LocalDate.of(2001, 2, 3),
                "mail");

        User user2 = new User(2L, "name2", "nickname", 18, "1234", User.Role.USER, LocalDate.of(2001, 2, 3),
                "mail");

        TestingRoleValidator testingRoleValidator = new TestingRoleValidator();
        testingRoleValidator.userInfo(user1);
        testingRoleValidator.userInfo2(user2);

    }
}
