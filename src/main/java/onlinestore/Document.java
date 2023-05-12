package onlinestore;

public class Document {


    private Long ownerId;

    private String docName;

    private String docPath;

    private Long docId;


    public Document(Long owner, String docName, String docPath) {
        this.ownerId = owner;
        this.docName = docName;
        this.docPath = docPath;
        this.docId = docId;
    }

    public Long getOwner() {
        return ownerId;
    }

    public Long getDocId() {
        return docId;
    }

    public String getDocName() {
        return docName;
    }

    public String getDocPath() {
        return docPath;
    }

    public void setOwner(Long owner) {
        this.ownerId = owner;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public void setDocPath(String docPath) {
        this.docPath = docPath;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
    }

}
