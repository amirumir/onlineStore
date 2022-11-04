package onlinestore;

import java.time.LocalDate;

public class User {

    private String name;
    private String nickname;
    private int age;
    private String password;
    private Long id;
    private Role role;

    private LocalDate birthDate;
    private String mail;
    private byte[] avatar;

    enum Role {
        USER,
        ADMIN
    }


    public User(Long id, String enteredName, String enteredNickname, int enteredAge,
                String enteredPassword, Role role, LocalDate birthDate, String mail) {
        this.name = enteredName;
        this.nickname = enteredNickname;
        this.age = enteredAge;
        this.password = enteredPassword;
        this.id = id;
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
}


