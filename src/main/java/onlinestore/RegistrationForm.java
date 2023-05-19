package onlinestore;


import onlinestore.repository.DocumentRepositoryImpl;
import onlinestore.repository.MailRepository;
import onlinestore.repository.MapChannelRepository;
import onlinestore.repository.UserRepository;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class RegistrationForm {

    public static void main(String[] args) {
        RegistrationForm registrationForm = new RegistrationForm();
        registrationForm.startForm();


    }

    MapChannelRepository mapChannelRepository = new MapChannelRepository();
    UserRepository userRepository = new UserRepository();
    DocumentRepositoryImpl documentRepository = new DocumentRepositoryImpl();
    Scanner scanner = new Scanner(System.in);
    Long idGenerator = 0L;
    Long id = generateID();
    LocalDate adminsBirthday = LocalDate.of(1923, 5, 10);
    MailRepository mailRepository = new MailRepository();
    User administrator = new User(id, "admin", "admin", 23, "admin", User.Role.ADMIN, adminsBirthday, "admin@admin.com");

    public Long generateID() {
        return idGenerator++;
    }

    public void startForm() {
        userRepository.save(administrator);
        System.out.println("1. Sign in");
        System.out.println("2. Register");
        int enteredNumber = Integer.parseInt(scanner.nextLine());
        if (enteredNumber == 1) {
            enter();
        } else if (enteredNumber == 2) {
            registration();
        } else {
            System.out.println("think about what you've done.");
        }
    }

    public void enter() {
        System.out.println("Nickname:");
        String nickname = scanner.nextLine();
        System.out.println("Password:");
        String password = scanner.nextLine();

        User user = userRepository.findByNickname(nickname);
        if (user != null && user.getPassword().equals(password)) {
            if (user.getRole() == User.Role.USER) {
                enteredAsUser(user);
            } else {
                enteredAsAdmin();
            }
        } else {
            System.out.println("off the target");
        }
        startForm();
    }


    public void registration() {
        System.out.println("Enter name:");
        String name = scanner.nextLine();

        System.out.println("Enter nickname:");
        String nickname = scanner.nextLine();


        System.out.println("Year of birth:");
        int yearOfBirth = Integer.parseInt(scanner.nextLine());
        System.out.println("Month of birth:");
        int monthOfBirth = Integer.parseInt(scanner.nextLine());
        System.out.println("Day of birth:");
        int dayOfBirth = Integer.parseInt(scanner.nextLine());

        LocalDate birthDate = LocalDate.of(yearOfBirth, monthOfBirth, dayOfBirth);
        LocalDate currentDate = LocalDate.now();
        int age = Period.between(birthDate, currentDate).getYears();
        if (age < 18) {
            System.out.println("What is Jetix?");
            startForm();
        }

        System.out.println("Enter E-mail name:");
        String eMail = scanner.nextLine() + "@koshka.com";
        User userForMail = userRepository.findByEmail(eMail);
        if (userForMail != null) {
            System.out.println("be creative...");
            startForm();
        }
        System.out.println("Enter password (must contain 8 characters or more):");
        String password = scanner.nextLine();
        if (password.length() < 8) {
            System.out.println("here we go again");
            startForm();
        }
        User user = userRepository.findByNickname(nickname);
        if (user != null) {
            System.out.println("nickname taken");
            startForm();
        }


        Long id = generateID();
        User newUser = new User(id, name, nickname, age, password, User.Role.USER, birthDate, eMail);
        userRepository.save(newUser);
        startForm();


    }


    public void enteredAsUser(User user) {
        System.out.println("1. Check profile info");
        System.out.println("2. Compose a letter");
        System.out.println("3. Inbox");
        System.out.println("4. Sent");
        System.out.println("5. Documents");
        System.out.println("6. Channels");
        System.out.println("7. Exit");

        int enteredNumber = Integer.parseInt(scanner.nextLine());
        if (enteredNumber == 1) {
            printUserInfo(user);
            System.out.println();
            exit();
        }
        if (enteredNumber == 2) {
            System.out.println("Enter receiver address:");
            String receiverAddress = scanner.nextLine();

            System.out.println("Subject:");
            String subject = scanner.nextLine();

            System.out.println("Letter body");
            String letterBody = scanner.nextLine();
            Letter letter = new Letter(user.getMail(), receiverAddress, subject, letterBody);
            mailRepository.sendLetter(user.getMail(), letter);
            enteredAsUser(user);
        }
        if (enteredNumber == 3) {
            System.out.println(mailRepository.getReceivedLetters(user.getMail()));
            System.out.println("1. Back");
            int enteredNumber2 = Integer.parseInt(scanner.nextLine());
            if (enteredNumber2 == 1) {
                enteredAsUser(user);
            }
        }
        if (enteredNumber == 4) {
            System.out.println(mailRepository.getSendLetters(user.getMail()));
            System.out.println("1. Back");
            int enteredNumber2 = Integer.parseInt(scanner.nextLine());
            if (enteredNumber2 == 1) {
                enteredAsUser(user);
            }
        }
        if (enteredNumber == 5) {
            documents(user);
        }
        if (enteredNumber == 6) {
            channels(user);
        }
        if (enteredNumber == 7) {
            System.out.println();
            startForm();
        }
    }

    public void channels(User user) {
        System.out.println("1. Followed channels");
        System.out.println("2. All channels list");
        System.out.println("3. My channels");
        System.out.println("4. Create new channel");
        System.out.println("5. Back");

        int enteredNumber = Integer.parseInt(scanner.nextLine());

        if (enteredNumber == 1) {
            followedChannels(user);
        }
        if (enteredNumber == 2) {
            allChannels(user);
        }
        if (enteredNumber == 3) {
            usersOwnChannels(user);
        }
        if (enteredNumber == 4) {
            createChannel(user);
        }
        if (enteredNumber == 5) {
            enteredAsUser(user);
        }
    }

    public void followedChannels(User user) {
        for (Channel channel : mapChannelRepository.findFollowedChannelsByUserId(user.getId())) {
            System.out.println("- " + channel.getId() + " - " + channel.getName() + " - ");
        }
        System.out.println();
        System.out.println("Enter channel ID: ");
        try {
            Long enteredLong = Long.parseLong(scanner.nextLine());
            Channel channel = mapChannelRepository.findById(enteredLong);
            printChannelMessages(channel);
        } catch (Exception e) {
            System.out.println("Channel with this id does not exist");
            channels(user);
        }
        System.out.println("1. Back to channels menu");
        System.out.println("2. Back to followed channels");
        int enteredNumber = Integer.parseInt(scanner.nextLine());
        try {
            if (enteredNumber == 1) {
                channels(user);
            }
            if (enteredNumber == 2) {
                followedChannels(user);
            }
        } catch (Exception e) {
            System.out.println("Be careful next time...");
            channels(user);
        }
    }

    public void allChannels(User user) {
        for (Channel channel : mapChannelRepository.findALl()) {
            System.out.println("- " + channel.getId() + " - " + channel.getName() + " - ");
        }
        System.out.println();
        System.out.println("Enter channel ID: ");
        try {
            Long enteredLong = Long.parseLong(scanner.nextLine());
            Channel channel = mapChannelRepository.findById(enteredLong);
            printChannelMessages(channel);
            System.out.println("1. Follow");
            System.out.println("2. Back to channels menu");
            int enteredNumber = Integer.parseInt(scanner.nextLine());
            if (enteredNumber == 1) {
                mapChannelRepository.follow(channel, user.getId());
                channels(user);
            }
            if (enteredNumber == 2) {
                channels(user);
            }
        } catch (Exception e) {
            channels(user);
        }
    }

    public void usersOwnChannels(User user) {
        for (Channel channel : mapChannelRepository.findOwnChannelsByUserId(user.getId())) {
            System.out.println("- " + channel.getId() + " - " + channel.getName() + " - ");
        }
        System.out.println();
        System.out.println(" Enter channel ID: ");
        Long enteredLong = Long.parseLong(scanner.nextLine());
        Channel channel = mapChannelRepository.findById(enteredLong);
        printChannelMessages(channel);
        System.out.println("1. New post:");
        channel.getMessages().add(scanner.nextLine());
        try {
            System.out.println(channel.getMessages().get(channel.getMessages().size() - 1));
        } catch (Exception e) {
            channels(user);
        }
    }

    public void createChannel(User user) {
        Channel channel = new Channel();
        System.out.println("Enter channel name: ");
        channel.setName(scanner.nextLine());
        channel.setUserId((user.getId()));
        mapChannelRepository.create(channel);
        channels(user);
    }

    public void printChannelMessages(Channel channel) {
        for (String message : channel.getMessages()) {
            System.out.println("<" + message + ">");
        }
    }


    public void enteredAsAdmin() {
        System.out.println("1. List of Users");
        System.out.println("2. Exit");
        int enteredNumber = Integer.parseInt(scanner.nextLine());
        if (enteredNumber == 1) {
            usersListForAdmin();

        } else if (enteredNumber == 2) {
            System.out.println();
            startForm();
        } else {
            System.out.println("you are in need of help");
            enteredAsAdmin();
        }
    }

    public void usersListForAdmin() {
        for (User user : userRepository.findAll()) {
            System.out.println("- " + user.getId() + " - " + user.getNickname());
        }
        System.out.println();
        System.out.println("1. Check user profile");
        System.out.println("2. Give administrator role");
        System.out.println("3. Exit");
        int enteredNumber = Integer.parseInt(scanner.nextLine());
        if (enteredNumber == 1) {
            printUserProfileByID();
        } else if (enteredNumber == 2) {
            giveAdminRole();
        } else if (enteredNumber == 3) {
            enteredAsAdmin();
        } else {
            System.out.println("try one more time");
            enteredAsAdmin();
        }
    }


    public void giveAdminRole() {
        System.out.println("Enter user ID: ");
        Long enteredID = Long.parseLong(scanner.nextLine());
        User user = userRepository.findByID(enteredID);
        if (user != null) {
            user.setRole(User.Role.ADMIN);

        }
        usersListForAdmin();
    }

    public void printUserProfileByID() {
        System.out.println("Enter ID: ");
        Long enteredID = Long.parseLong(scanner.nextLine());
        User user = userRepository.findByID(enteredID);
        if (user != null) {
            printUserInfo(user);
        } else {
            System.out.println("user does not exist");
            printUserProfileByID();
        }
    }

    public void documents(User user) {
        int enteredNumber = Integer.parseInt(scanner.nextLine());
        System.out.println("1. My documents");
        System.out.println("2. Add document");
        if (enteredNumber == 1) {
            showDocumentsList(user);
        }
        if (enteredNumber == 2) {
            addDocuments(user);
        }
    }

    public void addDocuments(User user) {
        System.out.println("Enter file Path:");
        String enteredFilePath = scanner.nextLine();
        System.out.println("Введите название документа");
        String docName = scanner.nextLine();
        Document document = new Document(user.getId(), docName, enteredFilePath);
        documentRepository.addDocument(document);
    }

    public void showDocumentsList(User user) {
        for (Document document : documentRepository.findAllByUserId(user.getId())) {
            System.out.println(" - " + document.getDocId() + " - " + document.getDocName());
        }
        System.out.println(" Document number:");
        Long enteredNumber = Long.parseLong(scanner.nextLine());
        Document document = documentRepository.findById(enteredNumber);
        if (enteredNumber.equals(document.getDocId())) {
            try {
                Desktop.getDesktop().open(new File(document.getDocPath()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void exit() {
        System.out.println("1. Exit");
        int enteredNumber = Integer.parseInt(scanner.nextLine());
        if (enteredNumber == 1) {
            System.out.println();
            startForm();
        } else {
            System.out.println("чел ты...");
            System.out.println();
            exit();

        }
    }

    public void printUserInfo(User user) {
        System.out.println("User's name: " + user.getName());
        System.out.println("Age: " + user.getAge());
        System.out.println("Nickname: " + user.getNickname());
        System.out.println("E-mail: " + user.getMail());
        System.out.println("Date of birth: " + user.getBirthDate());
        System.out.println("password: ********");
        System.out.println();
        System.out.println("1. Change info");
        System.out.println("2. Show profile picture");
        System.out.println("3. Exit");

        int enteredNumber = Integer.parseInt(scanner.nextLine());

        if (enteredNumber == 1) {
            updateUserInfo(user);
        }
        if (enteredNumber == 2) {
            try {
//                OutputStream out = new BufferedOutputStream(new FileOutputStream("avatar.png"));
//                out.write(user.getAvatar());
//                ByteArrayInputStream inputStream = new ByteArrayInputStream(user.getAvatar());
//                BufferedImage avatar = ImageIO.read(inputStream);
//                ImageIO.write(avatar, "png",new File("avatar.png"));

//                BufferedImage bImage = ImageIO.read(new File("sample.jpg"));
//                ByteArrayOutputStream bos = new ByteArrayOutputStream();
//                ImageIO.write(bImage, "jpg", bos );
//                byte [] data = bos.toByteArray();
                ByteArrayInputStream bis = new ByteArrayInputStream(user.getAvatar());
                BufferedImage bImage2 = ImageIO.read(bis);
                ImageIO.write(bImage2, "jpg", new File("output.jpg"));

                System.out.println("image created");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (enteredNumber == 3) {
            enteredAsUser(user);
        }
    }

    public void updateUserInfo(User user) {
        System.out.println("1. Change Name");
        System.out.println("2. Change Nickname");
        System.out.println("3. Change Birthdate");
        System.out.println("4. Change password");
        System.out.println("5. Change E-mail");
        System.out.println("6. Add profile picture");
        System.out.println("7. Back");
        int enteredNumber = Integer.parseInt(scanner.nextLine());
        if (enteredNumber == 1) {
            System.out.println("Type Name:");
            user.setName(scanner.nextLine());
        }
        if (enteredNumber == 2) {
            System.out.println("Type Nickname:");
            String updatedNickname = scanner.nextLine();
            User userWithEnteredNickname = userRepository.findByNickname(updatedNickname);

            if (userWithEnteredNickname != null) {
                System.out.println("nickname taken");
                updateUserInfo(user);
            }
//            user.setNickname(updatedNickname);
//            userRepository.update(user);
        }
        if (enteredNumber == 3) {
            System.out.println("Year of birth:");
            int newYearOfBirth = Integer.parseInt(scanner.nextLine());
            System.out.println("Month of birth:");
            int newMonthOfBirth = Integer.parseInt(scanner.nextLine());
            System.out.println("Day of birth:");
            int newDayOfBirth = Integer.parseInt(scanner.nextLine());
            LocalDate newBirthDate = LocalDate.of(newYearOfBirth, newMonthOfBirth, newDayOfBirth);
            LocalDate currentDate = LocalDate.now();
            int age = Period.between(newBirthDate, currentDate).getYears();
            if (age >= 18) {
                user.setBirthDate(newBirthDate);
                user.setAge(age);
            } else {
                System.out.println("invalid date of birth");
                updateUserInfo(user);
            }


        }
        if (enteredNumber == 4) {
            System.out.println("Type password (must contain 8 characters or more):");
            user.setPassword(scanner.nextLine());
        }
        if (enteredNumber == 5) {
            System.out.println("type E-mail");
            String newMail = scanner.nextLine() + "@koshka.com";
            user.setMail(newMail);
        }
        if (enteredNumber == 6) {
            System.out.println("Add path to image file");
            try {
                user.setAvatar(Files.readAllBytes(Paths.get(scanner.nextLine())));
            } catch (IOException e) {
                updateUserInfo(user);
            }
        }
        if (enteredNumber == 7) {
            printUserInfo(user);
        }
        updateUserInfo(user);

    }


}