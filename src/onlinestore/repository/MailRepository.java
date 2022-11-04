package onlinestore.repository;

import onlinestore.Letter;

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
        if (mailMap.get(userEmail) == null) {
            return List.of();
        } else
            return mailMap.get(userEmail).received;
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
        Mailbox sendersMailBox = mailMap.computeIfAbsent(letter.getSender(), x -> new Mailbox());
        Mailbox receiverMailBox = mailMap.computeIfAbsent(letter.getReceiver(), x -> new Mailbox());

        sendersMailBox.sent.add(letter);
        receiverMailBox.received.add(letter);
    }

    static class Mailbox {
        List<Letter> sent = new ArrayList<>();
        List<Letter> received = new ArrayList<>();
    }


}

