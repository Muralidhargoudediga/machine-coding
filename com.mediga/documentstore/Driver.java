package com.mediga.documentstore;

import com.mediga.documentstore.exception.DocumentAlreadyExistsException;
import com.mediga.documentstore.exception.PermissionDeniedException;
import com.mediga.documentstore.model.*;
import com.mediga.documentstore.service.DocumentService;

public class Driver {
    public static void main(String[] args) throws Exception{
        User user = new User("Murali");
        String content = "Test project for document store";
        Document document = new Document("testDocument", content);
        DocumentService documentService = new DocumentService();
        documentService.createDocument(user, document, Tier.DISK);

        try{
            documentService.createDocument(user, document, Tier.MEMORY);
            documentService.createDocument(user, document, Tier.MEMORY);
        } catch (DocumentAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }

        User user1 = new User("Gaurav");
        content = "Design and implement a Simple document Service where users can create documents and read the same.";
        document = new Document("document1", content);
        documentService.createDocument(user1, document, Tier.MEMORY);

        try {
            documentService.readDocument(user1, "testDocument");
        } catch (PermissionDeniedException e) {
            System.out.println("PermissionDeniedException");
        }

        User user2 = new User("");
        content = "Design and implement a Simple document Service where users can create documents and read the same.";
        document = new Document("document1", content);
        try {
            documentService.createDocument(user1, document, Tier.MEMORY);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        documentService.changeGrant(user, "testDocument", GrantType.PUBLIC);
        User user3 = new User("Ravi");
        System.out.println( "user3 ::" + documentService.readDocument(user3, "testDocument"));
        try{
            documentService.readDocument(user3, "testDocument2");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        documentService.grantPermission(user, Permission.READ, "testDocument", user1 );
        System.out.println(documentService.readDocument(user1, "testDocument"));
    }
}
