package org.sathya.sai.devotion.slide.dataaccess;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;
import org.sathya.sai.devotion.slide.datamodel.Slide;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.regex;
import static com.mongodb.client.model.Filters.text;

/**
 * @author Jagannath Ramesh (jagannath-ramesh)
 */
public class MongoDBDevotionSlideDAO implements DevotionSlideDAO {
    final MongoDatabase mongoDatabase;

    public MongoDBDevotionSlideDAO(final String databaseName) {
        final MongoDatabase mongoDatabase = getMongoDatabase(databaseName);
        this.mongoDatabase = mongoDatabase;
    }

    @Override
    public boolean insertSlides(final List<Slide> slideList) {

        slideList.forEach(slide -> {
            final String title = slide.getTitle();
            final String lyrics = slide.getLyrics();
            final String meaning = slide.getMeaning();

            final Document slideDoc = new Document("title", title)
                    .append("lyrics", lyrics)
                    .append("meaning", meaning);


            final MongoCollection slideCollection = mongoDatabase.getCollection("slide");
            slideCollection.replaceOne(
                    slideDoc,
                    slideDoc,
                    (new UpdateOptions()).upsert(true)
            );
        });

        return true;
    }

    @Override
    public List<Slide> findByTitle(final String titleSearchString) {
        final List<Slide> slideList = new LinkedList<Slide>();
        final MongoCollection slideCollection = mongoDatabase.getCollection("slide");
        Block<Document> createSlide = new Block<Document>() {
            @Override
            public void apply(Document document) {
                final Slide slide = new Slide();
                slide.setTitle(document.getString("title"));
                slide.setLyrics(document.getString("lyrics"));
                slide.setMeaning(document.getString("meaning"));
                slideList.add(slide);
            }
        };

//        final Pattern regex = Pattern.compile("^.*" + titleSearchString + ".*$", Pattern.CASE_INSENSITIVE);
//        slideCollection.find(regex("title", regex)).forEach(createSlide);
        slideCollection.createIndex(Indexes.text("title"));
        slideCollection.find(text(titleSearchString)).forEach(createSlide);
        return slideList;
    }

    @Override
    public List<Slide> findByLyrics(final String lyricsSearchString) {
        final List<Slide> slideList = new LinkedList<Slide>();
        final MongoCollection slideCollection = mongoDatabase.getCollection("slide");
        Block<Document> createSlide = new Block<Document>() {
            @Override
            public void apply(Document document) {
                final Slide slide = new Slide();
                slide.setTitle(document.getString("title"));
                slide.setLyrics(document.getString("lyrics"));
                slide.setMeaning(document.getString("meaning"));
                slideList.add(slide);
            }
        };

//        final Pattern regex = Pattern.compile("^.*" + lyricsSearchString + ".*$", Pattern.CASE_INSENSITIVE);
//        slideCollection.find(regex("lyrics", regex)).forEach(createSlide);
        slideCollection.createIndex(Indexes.text("lyrics"));
//        slideCollection.find(text(lyricsSearchString)).forEach(createSlide);
        return slideList;
    }

    @Override
    public Slide findPartial() {
        return null;
    }

    @Override
    public boolean updateSlide(final Slide slide) {
        return false;
    }

    @Override
    public boolean deleteSlide(final Slide slide) {
        return false;
    }

    private MongoDatabase getMongoDatabase(final String databaseName) {
        final MongoClient mongoClient = new MongoClient();
        return mongoClient.getDatabase(databaseName);
    }


}
