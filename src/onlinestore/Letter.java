package onlinestore;

public class Letter {

    enum LetterType {
        SENT,
        RECEIVED
    }

    private String sender;
    private String receiver;
    private String subject;
    private LetterType letterType;
    private String letterBody;

    public Letter(String sender, String receiver, LetterType letterType, String subject, String letterBody) {
        this.sender = sender;
        this.receiver = receiver;
        this.subject = subject;
        this.letterType = letterType;
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

    public LetterType getLetterType() {
        return letterType;
    }

    public void setLetterType(LetterType letterType) {
        this.letterType = letterType;
    }

    @Override
    public String toString() {
        return "Letter{" +
                "sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", subject='" + subject + '\'' +
                ", letterType=" + letterType +
                ", letterBody='" + letterBody + '\'' +
                '}';
    }
}
