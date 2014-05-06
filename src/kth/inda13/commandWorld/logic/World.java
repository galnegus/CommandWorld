package kth.inda13.commandWorld.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.CacheHint;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.ColorInput;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import kth.inda13.commandWorld.data.Word;

public class World {
	private Map<Entity, ImageView> entityMap;
	private StackPane imagePane;

	public World(StackPane imagePane) {
		entityMap = new HashMap<Entity, ImageView>();
		this.imagePane = imagePane;
	}

	/**
	 * add adds an entity to the world
	 * 
	 * @param e
	 *            the entity being added
	 */
	public void add(Word word) {
		Entity e = new Entity();
		e.modify(word);

		Image image = e.getInfo().image;

		if (image != null) {
			ImageView iv = new ImageView(image);
			entityMap.put(e, iv);

			imagePane.getChildren().add(iv);
		}

	}

	/**
	 * get returns an entity from the World that matches the given word.
	 * 
	 * @param word
	 *            the word that the entity is created from
	 * @return the entity, or null if no such entity exists
	 */
	public Entity get(String word) {
		for (Entity e : entityMap.keySet()) {
			if (e.getWord().name().equalsIgnoreCase(word)) {
				return e;
			}
		}
		return null;
	}

	/**
	 * size returns the number of entitites in the world.
	 * 
	 * @return the number of entities
	 */
	public int size() {
		return entityMap.size();
	}

	/**
	 * alignment based movement test
	 * 
	 * @param word
	 * @param location
	 */
	public void move(String word, String location) {
		Entity e = get(word);

		if (e != null) {
			ImageView iv = entityMap.get(e);

			if (location.equals("top")) { // temporary test thing
				StackPane.setAlignment(iv, Pos.TOP_CENTER);
			}
		}
	}

	/**
	 * experimental animated movement
	 * 
	 * @param word
	 * @param x
	 * @param y
	 */
	public void moveTo(String word, int x, int y) {
		Entity e = get(word);

		if (e != null) {
			ImageView imageView = entityMap.get(e);
			
			double widthOffset = e.getInfo().image.getWidth() / 2;
			double heightOffset = e.getInfo().image.getHeight() / 2;
			
			Path path = new Path();
			path.getElements().add(new MoveTo(0 + widthOffset, 0 + heightOffset));
			path.getElements().add(new LineTo(x + widthOffset, y + heightOffset));

			PathTransition pathTransition = new PathTransition();

			pathTransition.setDuration(Duration.millis(1000));
			pathTransition.setNode(imageView);
			pathTransition.setPath(path);
			pathTransition.setOrientation(OrientationType.NONE);
			pathTransition.setCycleCount(1);
			pathTransition.setAutoReverse(true);

			pathTransition.play();
		}
	}

	/**
	 * coloring test
	 * 
	 * @param word
	 * @param color
	 */
	public void color(String word, String color) {
		Entity e = get(word);

		Color c = Color.RED;

		if (color.equals("blue")) {
			c = Color.BLUE;
		}

		if (e != null) {
			ImageView imageView = entityMap.get(e);

			imageView.setClip(new ImageView(e.getInfo().image));

			ColorAdjust monochrome = new ColorAdjust();
			monochrome.setSaturation(-1.0);

			Blend blush = new Blend(BlendMode.MULTIPLY, monochrome, new ColorInput(0, 0, imageView.getImage()
					.getWidth(), imageView.getImage().getHeight(), c));

			imageView.setEffect(blush);

			imageView.setCache(true);
			imageView.setCacheHint(CacheHint.SPEED);
		}
	}
}
