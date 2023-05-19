package onlinestore;

import onlinestore.HasRole;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.Arrays;

public class HasRoleValidation {
    public static void checkMethod(User user ) {
//        User user = new User(1L, "", "", 18, "", User.Role.USER,
//                LocalDate.of(2001, 1, 1), "");
        try {
            for (Method method : user.getClass().getDeclaredMethods()) {
                if (method.isAnnotationPresent(HasRole.class)) {
                    HasRole annotation = method.getAnnotation(HasRole.class);
                    User[] role = user.getClass().getEnumConstants();
                    Arrays.toString(role);
                    if(annotation.value().toString().equals( Arrays.toString(role)))
                    {
                            method.invoke(user);
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
}