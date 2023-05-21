package onlinestore;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;

public class HasRoleValidation {
    public static void checkMethod(User user, TestingRoleValidator testingRoleValidator) {

        try {
            for (Method method : TestingRoleValidator.class.getDeclaredMethods()) {
                if (method.isAnnotationPresent(HasRole.class)) {
                    HasRole annotation = method.getAnnotation(HasRole.class);
//                    User[] role = user.getClass().getEnumConstants();
//                    Arrays.toString(role);

                    if (annotation.value().toString().equals(User.Role.USER.toString())) {
                        method.invoke(testingRoleValidator, user );
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

    public static void main(String[] args) {
        User user = new User(1L, "", "", 18, "", User.Role.USER,
                LocalDate.of(2001, 1, 1), "");
        TestingRoleValidator testingRoleValidator = new TestingRoleValidator();
        checkMethod(user, testingRoleValidator);
    }
}