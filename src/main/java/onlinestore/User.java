package onlinestore;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Arrays;

@Getter
@Setter

public class User {
    private Long id;
    private String name;
    private String nickname;
    private int age;
    private String password;
    private Role role;
    private LocalDate birthDate;
    private String mail;

    private byte[] avatar;

    public enum Role {
        USER,
        ADMIN
    }


    public User(Long id, String enteredName, String enteredNickname, int enteredAge,
                String enteredPassword, Role role, LocalDate birthDate, String mail) {
        this.id = id;
        this.name = enteredName;
        this.nickname = enteredNickname;
        this.age = enteredAge;
        this.password = enteredPassword;
        this.role = role;
        this.birthDate = birthDate;
        this.mail = mail;
        this.avatar = avatar;

    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getPassword() {
        return password;
    }

    public LocalDate getBirthDate() {
        return birthDate;

    }

    public String getMail() {
        return mail;
    }

    public Long getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                ", id=" + id +
                ", role=" + role +
                ", birthDate=" + birthDate +
                ", mail='" + mail + '\'' +
                ", avatar=" + Arrays.toString(avatar) +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }

    public int getAge() {
        return age;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public static class UserBuilder {
        private Long id;
        private String name;
        private String nickname;
        private int age;
        private String password;
        private Role role;
        private LocalDate birthDate;
        private String mail;
        private byte[] avatar;


        public UserBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder nickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public UserBuilder age(int age) {
            this.age = age;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder role(Role role) {
            this.role = role;
            return this;
        }

        public UserBuilder birthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public UserBuilder mail(String mail) {
            this.mail = mail;
            return this;
        }

        public User build() {
            return new User(id, name, nickname, age, password, role, birthDate, mail);
        }


    }
}



