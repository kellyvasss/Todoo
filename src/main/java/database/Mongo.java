package database;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.types.ObjectId;
import todo.Todo;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Mongo {

    MongoClient mongoClient;
    MongoDatabase mongoDatabase;
    MongoCollection<Document> mongoCollection;
    String collectionName;
    String databaseName;
    String connectionString;
    public Mongo(String connectionString, String collectionName, String databaseName) {
        this.connectionString = connectionString;
        this.collectionName = collectionName;
        this.databaseName = databaseName;
        Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
        connect();
    }

    private void connect() {
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();
        MongoClientSettings settings = MongoClientSettings.builder()
                .serverApi(serverApi)
                .build();
        try {
            mongoClient = MongoClients.create(settings);
            mongoDatabase = mongoClient.getDatabase(databaseName);
            mongoCollection = mongoDatabase.getCollection(collectionName);
        } catch (Exception e) {
            throw new RuntimeException(); // Vid eventuellt fel, kasta fel.
        }
    }
    public void create(Todo todo) {
        Document document = todo.toDoc();
        var find = mongoCollection.find(document);
        if(find.first() == null) {
            mongoCollection.insertOne(document);
        }
    }
    public Todo read(String objectID) {
        ObjectId id = new ObjectId(objectID);
        Document document = new Document("_id", id);
        if(mongoCollection.find(document) != null) {
            return Todo.fromDoc(document);
        } return null;
    }
    public void update(String objectID) {
        ObjectId id = new ObjectId(objectID);
        Document document = new Document("_id", id);
        if(mongoCollection.find(document) != null) {
            Document update = new Document("$set", new Document("done", true));
            mongoCollection.updateOne(document, update);
        }
    }
    public void delete(String objectID) {
        ObjectId id = new ObjectId(objectID);
        Document document = new Document("_id", id);
        mongoCollection.deleteOne(document);
    }
    public ArrayList<Todo> getAll() {
        FindIterable<Document> todos = mongoCollection.find();
        ArrayList<Todo> allTodos = new ArrayList<>();
        for (Document doc : todos) {
            allTodos.add(Todo.fromDoc(doc));
        } return allTodos;
    }

}
