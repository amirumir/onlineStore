package onlinestore.repository;

import onlinestore.Document;

import java.util.List;

public interface DocumentRepository {
    List<Document> findAllByUserId(Long userId);
    Document findById(Long id);
    void addDocument(Document document);
}
