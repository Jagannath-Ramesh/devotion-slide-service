/**
 * 
 */
package org.sathya.sai.devotion.slide.util;

import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import org.sathya.sai.devotion.slide.dataaccess.MongoDBDevotionSlideDAO;
import org.sathya.sai.devotion.slide.datamodel.Slide;

/**
 * @author Sai Prasanna Baskaran
 *
 */
public class BhajanFileParser {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		File bhajansMasterBook = new File("C:\\Workspace\\Bhajans Master Book.txt");
		BufferedReader bhajansMasterBookReader = new BufferedReader(new FileReader(bhajansMasterBook));
		String line = "";
		StringBuilder title = new StringBuilder();
		StringBuilder lyrics = new StringBuilder();
		StringBuilder meaning = new StringBuilder();
		boolean inLyricsSection = false;
		boolean inMeaningSection = false;
		boolean previousLineEmpty = false;
		boolean lyricsSectionAlreadyStarted = false;
		List<Slide> slidesToBeAdded = new LinkedList<Slide>();
		
		while((line = bhajansMasterBookReader.readLine()) != null) {
			if (line.equals("Title")) { // nextBhajan starts here 
				// insert bhajan here
				if (title != null && title.toString() != null && !title.toString().isEmpty()) {
					slidesToBeAdded.add(new Slide(title.toString().trim(), lyrics.toString().trim(), meaning.toString().trim()));
				}
				title.setLength(0); // clearing
				lyrics.setLength(0); // clearing
				meaning.setLength(0); // clearing
				inLyricsSection = false;
				inMeaningSection = false;
				lyricsSectionAlreadyStarted = false;
				
			} else if (line.trim().isEmpty()) {
				if (previousLineEmpty) {
					if (lyricsSectionAlreadyStarted) {
						inLyricsSection = false;
						inMeaningSection = true;						
					}
					else {
						inLyricsSection = true;
					}
				}
				else {
					previousLineEmpty = true;
				}
			} else {
				previousLineEmpty = false;
				if (inLyricsSection) {
					lyrics.append(line.trim() + "\n");
					lyricsSectionAlreadyStarted = true;
				}
				else if (inMeaningSection) {
					meaning.append(line.trim() + " ");
				}
				else {
					title.append(line.trim() + " ");
				}
			}
		}
		bhajansMasterBookReader.close();
		MongoDBDevotionSlideDAO mongoDBDevotionSlideDAO = new MongoDBDevotionSlideDAO("test");
		mongoDBDevotionSlideDAO.insertSlides(slidesToBeAdded);
	}
}
