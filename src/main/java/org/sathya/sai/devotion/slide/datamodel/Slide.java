package org.sathya.sai.devotion.slide.datamodel;


/**
 * @author Sai Prasanna Baskaran (saibaskaran)
 *
 * This represents a single unit (a song or a chant or a bhajan) which can be displayed as a slide)
 */
public class Slide {

    private String title, lyrics, meaning;

    public Slide(String title, String lyrics, String meaning) {
        this.title = title;
        this.lyrics = lyrics;
        this.meaning = meaning;
    }
    
    public Slide() {
        this.title = "OPEN SLOT";
        this.lyrics = "";
        this.meaning = "";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((lyrics == null) ? 0 : lyrics.hashCode());
        result = prime * result + ((meaning == null) ? 0 : meaning.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Slide other = (Slide) obj;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (lyrics == null) {
            if (other.lyrics != null)
                return false;
        } else if (!lyrics.equals(other.lyrics))
            return false;
        return true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}