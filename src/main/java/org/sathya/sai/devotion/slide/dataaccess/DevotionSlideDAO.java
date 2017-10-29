package org.sathya.sai.devotion.slide.dataaccess;

import org.bson.Document;
import org.sathya.sai.devotion.slide.datamodel.Slide;

import java.util.List;

/**
 * Created by Sai Prasanna Baskaran on 4/16/2017.
 */
public interface DevotionSlideDAO {

    boolean insertSlides(final List<Slide> slide);

    List<Slide> findByTitle(final String titleSearchString);

    List<Slide> findByLyrics(final String lyricsSearchString);

    Slide findPartial();

    boolean updateSlide(final Slide slide);

    boolean deleteSlide(final Slide slide);

}
