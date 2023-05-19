package onlinestore.repository;

import onlinestore.Letter;
import onlinestore.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MailRepository {
    HashMap<String, Mailbox> mailMap = new HashMap<>();

    public List<Letter> getSendLetters(String userEmail) {
        if (mailMap.get(userEmail) == null) {
            return List.of();
        } else
            return mailMap.get(userEmail).sent;

    }

    public List<Letter> getReceivedLetters(String userEmail) {
//        if (mailMap.get(userEmail) == null) {
//            return List.of();
//        } else
//            return mailMap.get(userEmail).received;
        DataSource dataSource = DataSourceConfig.dataSource();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT sender, receiver, subject, letter_body FROM letter WHERE receiver = ?")) {

            statement.setString(1, userEmail);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String sender = resultSet.getString("sender");
                String receiver = resultSet.getString("receiver");

                String subject = resultSet.getString("subject");
                String letter_body = resultSet.getString("letter_body");


                Letter letter = new Letter(sender, receiver,subject, letter_body);
                System.out.println(letter);

            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void sendLetter(String eMail, Letter letter) {
        Mailbox sendersMailBox = mailMap.get(letter.getSender());
        Mailbox receiverMailBox = mailMap.get(letter.getReceiver());
        if (sendersMailBox == null) {
            sendersMailBox = new Mailbox();
            mailMap.put(letter.getSender(), sendersMailBox);
        }
        if (receiverMailBox == null) {
            receiverMailBox = new Mailbox();
            mailMap.put(letter.getReceiver(), receiverMailBox);
        }
        sendersMailBox.sent.add(letter);
        receiverMailBox.received.add(letter);
    }

    public void sendLetterv2(String eMail, Letter letter) {
//        Mailbox sendersMailBox = mailMap.computeIfAbsent(letter.getSender(), x -> new Mailbox());
//        Mailbox receiverMailBox = mailMap.computeIfAbsent(letter.getReceiver(), x -> new Mailbox());
//
//        sendersMailBox.sent.add(letter);
//        receiverMailBox.received.add(letter);
        DataSource dataSource = DataSourceConfig.dataSource();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO letter (sender, receiver, subject, letter_body)" +
                             "VALUES (?,?,?,?) ")) {

            statement.setString(2, letter.getSender());
            statement.setString(3, letter.getReceiver());
            statement.setString(4, letter.getSubject());
            statement.setString(5, letter.getLetterBody());

        } catch (SQLException e) {

        }
    }

    static class Mailbox {
        List<Letter> sent = new ArrayList<>();
        List<Letter> received = new ArrayList<>();
    }


}

