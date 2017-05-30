package slide.dataaccess;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.sathya.sai.devotion.slide.dataaccess.MongoDBDevotionSlideDAO;
import org.sathya.sai.devotion.slide.datamodel.Slide;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Jagannath Ramesh (jagannath-ramesh)
 *
 * Test {@link org.sathya.sai.devotion.slide.dataaccess.MongoDBDevotionSlideDAO}
 */
public class MongoDBDevotionSlideDAOTest {

    @Before
    public void setUp() {
        final MongoClient mongoClient = new MongoClient();
        final MongoDatabase mongoDatabase = mongoClient.getDatabase("testDB");
        mongoDatabase.getCollection("slide").drop();
    }

    @Test
    public void testConstructor() {
        MongoDBDevotionSlideDAO mongoDBDevotionSlideDAO = createMockDatabase();
        assertNotNull(mongoDBDevotionSlideDAO);
    }

    @Test
    public void testInsertSlide() {
        final Slide mockSlide = createMockSlide("mockTitle", "mockLyrics", "mockMeaning");
        MongoDBDevotionSlideDAO mongoDBDevotionSlideDAO = createMockDatabase();
        assertNotNull(mongoDBDevotionSlideDAO);

        assertTrue(mongoDBDevotionSlideDAO.insertSlides(new LinkedList<>(Arrays.asList(mockSlide))));
    }

    @Test
    public void testfindByTitle() {
        final Slide mockSlide1 = createMockSlide("mockTitle1", "mockLyrics", "mockMeaning");
        final Slide mockSlide2 = createMockSlide("mockTitle2", "mockLyrics", "mockMeaning");

        List<Slide> mockSlideList = new LinkedList<>(Arrays.asList(mockSlide1, mockSlide2));

        MongoDBDevotionSlideDAO mongoDBDevotionSlideDAO = createMockDatabase();
        assertNotNull(mongoDBDevotionSlideDAO);
        mongoDBDevotionSlideDAO.insertSlides(mockSlideList);

        final List<Slide> slideList = mongoDBDevotionSlideDAO.findByTitle("Title2");
        System.out.println(slideList.size());
        System.out.println(slideList.toString());
    }

    @Test
    public void testfindByLyrics() {
        final Slide mockSlide1 = createMockSlide("mockTitle", "the rain in spain flows ", "mockMeaning");
        final Slide mockSlide2 = createMockSlide("mockTitle", "the rain in spain flowed", "mockMeaning");

        List<Slide> mockSlideList = new LinkedList<>(Arrays.asList(mockSlide1, mockSlide2));

        MongoDBDevotionSlideDAO mongoDBDevotionSlideDAO = createMockDatabase();
        assertNotNull(mongoDBDevotionSlideDAO);
        mongoDBDevotionSlideDAO.insertSlides(mockSlideList);

        final List<Slide> slideList = mongoDBDevotionSlideDAO.findByLyrics("sadana");
        System.out.println(slideList.toString());
    }

    private Slide createMockSlide(final String title, final String lyrics, final String meaning) {
        return new Slide(title, lyrics, meaning);
    }

    private MongoDBDevotionSlideDAO createMockDatabase() {
        return new MongoDBDevotionSlideDAO("testDB");
    }
}


