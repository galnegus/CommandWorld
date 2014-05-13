package kth.inda13.commandWorld.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurveTo;
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

	// used for picking random entities when multiple are available
	private Random rng;

	public World(StackPane imagePane) {
		entityMap = new HashMap<Entity, ImageView>();
		this.imagePane = imagePane;
		rng = new Random();
	}

	/**
	 * add adds an entity to the world
	 * 
	 * @param e
	 *            the entity being added
	 */
	public void create(Word word, LinkedList<Word> description) {
		Entity entity = new Entity(word);

		Image image = entity.getInfo().image;
		if (image != null) {
			ImageView imageView = new ImageView(image);
			entityMap.put(entity, imageView);

			for (Word w : description) {
				this.event(entity, w);
			}

			imagePane.getChildren().add(imageView);

			// fade in entity to visibility
			FadeTransition ft = new FadeTransition(Duration.millis(1000), imageView);
			ft.setFromValue(0.0);
			ft.setToValue(1);
			ft.play();
		}

	}

	/**
	 * get returns a random entity from the World that matches the given word.
	 * 
	 * @param entity
	 *            the word that the entity is created from
	 * @return the entity, or null if no such entity exists
	 */
	public Entity get(Word entity) {
		List<Entity> results = new ArrayList<Entity>();

		for (Entity e : entityMap.keySet()) {
			if (e.getWord() == entity) {
				results.add(e);
			}
		}

		if (!results.isEmpty()) {
			return results.get(rng.nextInt(results.size()));
		}
		return null;
	}

	/**
	 * get returns a random entity from the World that matches the given word and the given descriptions. <br />
	 * <br />
	 * For example, this method could be called with get(Word.PERSON, Arrays.asList(Word.RED, Word.SMALL)), which would
	 * return an entity that was created from the word "person", with an info object that is a superset of both
	 * Word.RED's and Word.SMALL's info objects.
	 * 
	 * @param word
	 *            the word that the entity is created from
	 * @param descriptions
	 *            a list of words that describes the entity
	 * @return the entity, or null if no such entity exists
	 */
	public Entity get(Word entity, List<Word> descriptions) {
		List<Entity> results = new ArrayList<Entity>();

		for (Entity e : entityMap.keySet()) {
			if (e.getWord() == entity && e.matchesDescriptions(descriptions)) {
				results.add(e);
			}
		}

		if (!results.isEmpty()) {
			return results.get(rng.nextInt(results.size()));
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
	 * Performs an event, if the word contains some data, it is applied to the intent. Use this method if you want to
	 * perform several modifications on the same Entity.
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
				move(intent, event.getInfo().location);
			}
			if (event.getInfo().size != null) {
				size(intent, event.getInfo().size);
			}
		}
	}

	/**
	 * Performs an event, if the word contains some data, it is applied to the intent. Do no use this method if you want
	 * to perform several modifications on the same Entity.
	 * 
	 * @param intent
	 *            the entity being altered
	 * @param event
	 *            the event being performed
	 */
	public void event(Word intent, Word event) {
		this.event(this.get(intent), event);
	}

	/**
	 * Moves an entity to the specified position relative to the node alignment. <br />
	 * For example calling move(person, -100.0, 0.0) will move the person entity 100 to left of the alignment node. <br />
	 * By default, alignment will be the center of the imagePane.
	 * 
	 * @param entity
	 *            entity to move
	 * @param location
	 *            location that entity will be moved to
	 */
	private void move(Entity entity, Location location) {
		if (entity != null) {
			PathTransition pathTransition = prepareMove(entity, location);
			pathTransition.play();
		}
	}

	/**
	 * Does all the legwork for move, returns a reusable Animation object that could be useful for other events (see
	 * eat).
	 * 
	 * @param entity
	 *            entity to move
	 * @param location
	 *            location that entity will be moved to
	 * @return a pathTransition object
	 */
	private PathTransition prepareMove(Entity entity, Location location) {
		if (location == null) {
			location = Word.CENTER.getInfo().location;
		}

		entity.getInfo().location = location;

		ImageView imageView = entityMap.get(entity);

		// for some reason coordinate systems when creating and moving nodes are different
		double widthOffset = entity.getInfo().image.getWidth() / 2.0;
		double heightOffset = entity.getInfo().image.getHeight() / 2.0;

		// starting position for animation
		double startX = imageView.getTranslateX() + widthOffset;
		double startY = imageView.getTranslateY() + heightOffset;

		// target position for animation
		double targetX = location.x + widthOffset;
		double targetY = location.y + heightOffset;

		// path from start to finish
		Path path = new Path();
		path.getElements().add(new MoveTo(startX, startY));

		// debug stuff
		// System.err.println("start: " + imageView.getTranslateX() + "," + imageView.getTranslateY() + ", target: " +
		// targetX + "," + targetY);

		// do not do a line path if start == target, it will cause entity to disappear for some weird reason
		// same thing will also happens if at any point the path intersects with the starting point
		if (startX != targetX || startY != targetY) {
			path.getElements().add(new LineTo(targetX, targetY));
		} else {
			double x1 = targetX - 100;
			double y1 = targetY + 100;
			double x2 = targetX + 100;
			double y2 = targetY + 100;
			path.getElements().add(new CubicCurveTo(x1, y1, x2, y2, targetX, targetY));
		}

		// animation stuff
		PathTransition pathTransition = new PathTransition();
		pathTransition.setDuration(Duration.millis(1000));
		pathTransition.setNode(imageView);
		pathTransition.setPath(path);
		pathTransition.setInterpolator(Interpolator.EASE_BOTH);

		return pathTransition;
	}

	/**
	 * Changes size of entity to given scale. <br />
	 * For example, calling size(person, Word.THICK.getInfo().size) will make the person twice as wide as the original.
	 * 
	 * @param entity
	 *            entity being resized
	 * @param size
	 *            the new size of the entity
	 */
	private void size(Entity entity, Size size) {
		if (entity != null) {
			ImageView imageView = entityMap.get(entity);

			double fromX = 1, fromY = 1;
			// update info values and retrieve old scale if there is one
			if (entity.getInfo().size != null) {
				fromX = entity.getInfo().size.x;
				fromY = entity.getInfo().size.y;
			}
			entity.getInfo().size = size;

			// transition
			ScaleTransition st = new ScaleTransition(Duration.millis(1000), imageView);
			st.setFromX(fromX);
			st.setFromY(fromY);
			st.setToX(size.x);
			st.setToY(size.y);
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
			Blend blush = new Blend(BlendMode.SCREEN, colorInput, null);
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

	/**
	 * Shakes the entity forward slightly.
	 * 
	 * @param entity
	 *            entity being shaked
	 */
	public void shake(Entity entity) {
		if (entity != null) {
			RotateTransition rt = prepareShake(entity);

			rt.play();
		}

	}

	/**
	 * Does legwork for shake. Returns Animation object useful to other events (see eat).
	 * 
	 * @param entity
	 *            entity being shaked
	 * @return object containing cool animation
	 */
	public RotateTransition prepareShake(Entity entity) {
		ImageView imageView = entityMap.get(entity);

		RotateTransition rt = new RotateTransition(Duration.millis(100), imageView);
		rt.setByAngle(90);
		rt.setCycleCount(10);
		rt.setAutoReverse(true);

		return rt;
	}

	/**
	 * One entity moves to another entity, then eats it. Uses other events to make it happen.
	 * 
	 * @param agent
	 *            entity getting fed
	 * @param intent
	 *            entity getting eaten
	 */
	public void eat(final Entity agent, final Entity intent) {
		if (agent != null && intent != null) {
			PathTransition move = prepareMove(agent, intent.getInfo().location);

			final RotateTransition shake = prepareShake(agent);
			final FadeTransition remove = prepareRemove(intent);
			move.setOnFinished(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					shake.play();
					remove.play();
				}
			});
			move.play();

		}
	}

	/**
	 * clear fades out all entities from the world and then removes them from the imagePane and the entityMap.
	 */
	public void clear() {
		/*
		 * a new hashset copy must be created for iteration, otherwise a ConcurrentModificiationException might be
		 * thrown when an element is removed
		 */
		for (Entity entity : new HashSet<Entity>(entityMap.keySet())) {
			remove(entity);
		}
	}

	/**
	 * remove fades out an entity and removes it from the imagePane once it has faded completely. <br />
	 * The entity is also removed from the entityMap once it is removed from the imagePane.
	 * 
	 * @param entity
	 *            the entity being removed
	 */
	private void remove(final Entity entity) {
		if (entity != null) {
			FadeTransition ft = prepareRemove(entity);
			ft.play();
		}
	}

	/**
	 * Does hard work for remove. Used in other events (see eat).
	 * 
	 * @param entity
	 *            event being removed
	 * @return an animation object.
	 */
	private FadeTransition prepareRemove(final Entity entity) {
		FadeTransition ft = new FadeTransition(Duration.millis(1000), entityMap.get(entity));
		ft.setFromValue(1);
		ft.setToValue(0);
		ft.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				imagePane.getChildren().remove(entityMap.get(entity));
				entityMap.remove(entity);
			}
		});

		return ft;
	}

	/**
	 * prints size of entityMap in console, useful for debugging.
	 */
	public void printSize() {
		System.out.println(entityMap.size());
	}
}
