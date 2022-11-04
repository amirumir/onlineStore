package onlinestore.repository;

import onlinestore.Document;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DocumentRepositoryImpl implements DocumentRepository {
    Map<Long, Document> mapOfDocuments = new HashMap<>();
//    Scanner scanner = new Scanner(System.in);
    Long idGenerator = 0L;


    public Long generateID() {
        return idGenerator++;
    }

//    public void addDocument(User user) {
//        System.out.println(" Give document path:");
//        String filePath = scanner.nextLine();
//        System.out.println(" Enter document name:");
//        String docName = scanner.nextLine();
//
//        Long docId = generateID();
//        Document document = new Document(user.getId(), docName, filePath, docId);
//        if (mapOfDocuments.get(user) != null) {
//            mapOfDocuments.get(user).put(docId, document);
//        } else {
//            HashMap<Long, Document> innerMap = new HashMap<>();
//            innerMap.put(docId, document);
//            mapOfDocuments.put(user, innerMap);
//        }
//
//
//    }




    @Override
    public List<Document> findAllByUserId(Long userId) {
        return mapOfDocuments.values()
                .stream()
                .filter(document -> document.getOwner().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public Document findById(Long id) {
        return mapOfDocuments.get(id);
    }

    @Override
    public void addDocument(Document document) {
        Long documentId = generateID();
        document.setDocId(documentId);
        mapOfDocuments.put(documentId,document);

    }
}
