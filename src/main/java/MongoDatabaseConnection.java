import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Created by Lennovo on 4/9/2017.
 */
public class MongoDatabaseConnection {

    public static void main(String args[]) {
        createMongoClient();
    }

    public static void createMongoClient() {
        final MongoClient mongoClient = new MongoClient();
        final MongoDatabase db = mongoClient.getDatabase("test");
        final Document doc = new Document("title", "Hari Om Namo Shiva Shakti Namo")
                .append("lyrics", "database");
        final MongoCollection collection = db.getCollection("bhajan");
        collection.insertOne(doc);
    }


}
