package kth.inda13.commandWorld.logic;

import java.util.HashMap;
import java.util.Map;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.scene.CacheHint;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import kth.inda13.commandWorld.data.Location;
import kth.inda13.commandWorld.data.Size;
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
		Entity entity = new Entity();
		entity.modify(word);

		Image image = entity.getInfo().image;
		if (image != null) {
			ImageView imageView = new ImageView(image);
			entityMap.put(entity, imageView);

			imagePane.getChildren().add(imageView);

			// fade in entity to visibility
			FadeTransition ft = new FadeTransition(Duration.millis(1000), imageView);
			ft.setFromValue(0.0);
			ft.setToValue(1);
			ft.play();
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
	 * Performs an event, if the word contains some data, it is applied to the intent.
	 * 
	 * @param intent
	 *            the entity being altered
	 * @param event
	 *            the event being performed
	 */
	public void event(Entity intent, Word event) {
		if (intent != null && event != null) {
			if (event.getInfo().color != null) {
				color(intent, event.getInfo().color);
			}
			if (event.getInfo().location != null) {
				move(intent, event.getInfo().location.x, event.getInfo().location.y);
			}
			if (event.getInfo().size != null) {
				size(intent, event.getInfo().size.x, event.getInfo().size.y);
			}
		}
	}

	/**
	 * Moves an entity to the specified position relative to the node alignment. <br />
	 * For example calling move(person, -100.0, 0.0) will move the person entity 100 to left of the alignment node. <br />
	 * By default, alignment will be the center of the imagePane.
	 * 
	 * @param entity
	 *            entity to move
	 * @param x
	 *            amount of pixels to move entity by horizontally
	 * @param y
	 *            amount of pixels to move entity by vertically
	 */
	private void move(Entity entity, double x, double y) {
		if (entity != null) {
			if (entity.getInfo().location == null) {
				entity.getInfo().location = new Location(x, y);
			} else {
				entity.getInfo().location.x = x;
				entity.getInfo().location.y = y;
			}

			ImageView imageView = entityMap.get(entity);

			// for some reason coordinate systems when creating and moving nodes are different
			double widthOffset = entity.getInfo().image.getWidth() / 2.0;
			double heightOffset = entity.getInfo().image.getHeight() / 2.0;

			// starting position for animation
			double startX = imageView.getTranslateX() + widthOffset;
			double startY = imageView.getTranslateY() + heightOffset;

			// target position for animation
			double targetX = x + widthOffset;
			double targetY = y + heightOffset;

			// path from start to finish
			Path path = new Path();
			path.getElements().add(new MoveTo(startX, startY));
			path.getElements().add(new LineTo(targetX, targetY));

			// animation stuff
			PathTransition pathTransition = new PathTransition();
			pathTransition.setDuration(Duration.millis(1000));
			pathTransition.setNode(imageView);
			pathTransition.setPath(path);
			pathTransition.setInterpolator(Interpolator.EASE_BOTH);
			pathTransition.play();
		}
	}

	/**
	 * Changes size of entity to given scale. <br />
	 * For example, calling size(person, 2, 1) will make the person twice as wide as the original.
	 * 
	 * @param entity
	 *            entity being resized
	 * @param x
	 *            the x scale to resize by
	 * @param y
	 *            the y scale to resize by
	 */
	private void size(Entity entity, double x, double y) {
		if (entity != null) {
			ImageView imageView = entityMap.get(entity);

			double fromX = 1, fromY = 1;

			// update info values and retrieve old scale if there is one
			if (entity.getInfo().size == null) {
				entity.getInfo().size = new Size(x, y);
			} else {
				fromX = entity.getInfo().size.x;
				fromY = entity.getInfo().size.y;
				entity.getInfo().size.x = x;
				entity.getInfo().size.y = y;
			}

			// transition
			ScaleTransition st = new ScaleTransition(Duration.millis(1000), imageView);
			st.setFromX(fromX);
			st.setFromY(fromY);
			st.setToX(x);
			st.setToY(x);
			st.play();
		}
	}

	/**
	 * Changes color of entity to color defined by given color word. <br />
	 * 
	 * @param entity
	 *            the entity that is being colored
	 * @param color
	 *            the new color of the entity
	 */
	private void color(Entity entity, Color color) {
		// set current color, needed for animation
		Color startColor = entity.getInfo().color;
		if (startColor == null) {
			startColor = new Color(0, 0, 0, 0);
		}

		// set new color
		Color targetColor = color;

		if (entity != null) {
			// update entity color data
			entity.getInfo().color = targetColor;

			ImageView imageView = entityMap.get(entity);

			// mask the area that is being colored
			imageView.setClip(new ImageView(entity.getInfo().image));

			// create the coloring effect
			ColorInput colorInput = new ColorInput(0, 0, imageView.getImage().getWidth(), imageView.getImage()
					.getHeight(), targetColor);
			Blend blush = new Blend(BlendMode.MULTIPLY, colorInput, null);
			imageView.setEffect(blush);

			// animate coloring effect from startColor to targetColor
			Timeline timeline = new Timeline();
			timeline.getKeyFrames().addAll(
					new KeyFrame(Duration.ZERO, new KeyValue(colorInput.paintProperty(), startColor)),
					new KeyFrame(new Duration(1000), new KeyValue(colorInput.paintProperty(), targetColor)));
			timeline.play();

			// cache imageView for performance or something
			imageView.setCache(true);
			imageView.setCacheHint(CacheHint.SPEED);
		}
	}
}
