package onlinestore;

import lombok.Setter;

@Setter
public class Letter {


    private String sender;
    private String receiver;
    private String subject;
    private String letterBody;

    public Letter(String sender, String receiver, String subject, String letterBody) {
        this.sender = sender;
        this.receiver = receiver;
        this.subject = subject;
        this.letterBody = letterBody;

    }

    public String getLetterBody() {
        return letterBody;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getSubject() {
        return subject;
    }


    @Override
    public String toString() {
        return "Letter{" +
                "sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", subject='" + subject + '\'' +
                ", letterBody='" + letterBody + '\'' +
                '}';
    }
}
