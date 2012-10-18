package pinkgorilla.handycloset;

import android.graphics.Bitmap;
/**
 * The Article class represents an article of clothing within the application. The Article contains a unique ID, a name specified by
 * the user (optional), a picture of the article (required), a type (optional), and a rating (optional).
 * @author Faiz
 *
 */
public class Article {

	private int id;
	private String name;
	private Bitmap picture;
	private String type;
	private int rating;

	public Article(int id, String name, Bitmap picture, String type, int rating){
		this.setId(id);
		this.setName(name);
		this.setPicture(picture);
		this.setType(type);
		this.setRating(rating);
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the picture
	 */
	public Bitmap getPicture() {
		return picture;
	}

	/**
	 * @param picture the picture to set
	 */
	public void setPicture(Bitmap picture) {
		this.picture = picture;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the rating
	 */
	public int getRating() {
		return rating;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}
}
