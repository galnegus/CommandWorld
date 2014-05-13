package kth.inda13.commandWorld.data;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * This enum is in practice the dictionary for the application. It contains the
 * definition an meaning of all words the program recognizes.
 * 
 * @author rodrigo
 *
 */
public enum Word {
	PERSON(new Image("img/person.png")),
	MONKEY(new Image("img/MONKEY.png")),
	SNAKE(new Image("img/snake.png")),
	TIGER(new Image("img/tiger.png")),
//	CAT(new Image("img/.png")),
	UP(new Location(0, -100)),
	RIGHT(new Location(200, 0)),
	DOWN(new Location(0, 100)),
	LEFT(new Location(-200, 0)),
	CENTER(new Location(0, 0)),
	BIG(new Size(2, 2)), 
	SMALL(new Size(0.5, 0.5)), 
	THIN(new Size(0.5, 1)), 
	THICK(new Size(2, 1)), 
	SHORT(new Size(1, 0.5)), 
	LONG(new Size(1, 2)),
	ALICEBLUE(Color.ALICEBLUE),
	ANTIQUEWHITE(Color.ANTIQUEWHITE),
	AQUA(Color.AQUA),
	AQUAMARINE(Color.AQUAMARINE),
	AZURE(Color.AZURE),
	BEIGE(Color.BEIGE),
	BISQUE(Color.BISQUE),
	BLACK(Color.BLACK),
	BLANCHEDALMOND(Color.BLANCHEDALMOND),
	BLUE(Color.BLUE),
	BLUEVIOLET(Color.BLUEVIOLET),
	BROWN(Color.BROWN),
	BURLYWOOD(Color.BURLYWOOD),
	CADETBLUE(Color.CADETBLUE),
	CHARTREUSE(Color.CHARTREUSE),
	CHOCOLATE(Color.CHOCOLATE),
	CORAL(Color.CORAL),
	CORNFLOWERBLUE(Color.CORNFLOWERBLUE),
	CORNSILK(Color.CORNSILK),
	CRIMSON(Color.CRIMSON),
	CYAN(Color.CYAN),
	DARKBLUE(Color.DARKBLUE),
	DARKCYAN(Color.DARKCYAN),
	DARKGOLDENROD(Color.DARKGOLDENROD),
	DARKGRAY(Color.DARKGRAY),
	DARKGREEN(Color.DARKGREEN),
	DARKGREY(Color.DARKGREY),
	DARKKHAKI(Color.DARKKHAKI),
	DARKMAGENTA(Color.DARKMAGENTA),
	DARKOLIVEGREEN(Color.DARKOLIVEGREEN),
	DARKORANGE(Color.DARKORANGE),
	DARKORCHID(Color.DARKORCHID),
	DARKRED(Color.DARKRED),
	DARKSALMON(Color.DARKSALMON),
	DARKSEAGREEN(Color.DARKSEAGREEN),
	DARKSLATEBLUE(Color.DARKSLATEBLUE),
	DARKSLATEGRAY(Color.DARKSLATEGRAY),
	DARKSLATEGREY(Color.DARKSLATEGREY),
	DARKTURQUOISE(Color.DARKTURQUOISE),
	DARKVIOLET(Color.DARKVIOLET),
	DEEPPINK(Color.DEEPPINK),
	DEEPSKYBLUE(Color.DEEPSKYBLUE),
	DIMGRAY(Color.DIMGRAY),
	DIMGREY(Color.DIMGREY),
	DODGERBLUE(Color.DODGERBLUE),
	FIREBRICK(Color.FIREBRICK),
	FLORALWHITE(Color.FLORALWHITE),
	FORESTGREEN(Color.FORESTGREEN),
	FUCHSIA(Color.FUCHSIA),
	GAINSBORO(Color.GAINSBORO),
	GHOSTWHITE(Color.GHOSTWHITE),
	GOLD(Color.GOLD),
	GOLDENROD(Color.GOLDENROD),
	GRAY(Color.GRAY),
	GREEN(Color.GREEN),
	GREENYELLOW(Color.GREENYELLOW),
	GREY(Color.GREY),
	HONEYDEW(Color.HONEYDEW),
	HOTPINK(Color.HOTPINK),
	INDIANRED(Color.INDIANRED),
	INDIGO(Color.INDIGO),
	IVORY(Color.IVORY),
	KHAKI(Color.KHAKI),
	LAVENDER(Color.LAVENDER),
	LAVENDERBLUSH(Color.LAVENDERBLUSH),
	LAWNGREEN(Color.LAWNGREEN),
	LEMONCHIFFON(Color.LEMONCHIFFON),
	LIGHTBLUE(Color.LIGHTBLUE),
	LIGHTCORAL(Color.LIGHTCORAL),
	LIGHTCYAN(Color.LIGHTCYAN),
	LIGHTGOLDENRODYELLOW(Color.LIGHTGOLDENRODYELLOW),
	LIGHTGRAY(Color.LIGHTGRAY),
	LIGHTGREEN(Color.LIGHTGREEN),
	LIGHTGREY(Color.LIGHTGREY),
	LIGHTPINK(Color.LIGHTPINK),
	LIGHTSALMON(Color.LIGHTSALMON),
	LIGHTSEAGREEN(Color.LIGHTSEAGREEN),
	LIGHTSKYBLUE(Color.LIGHTSKYBLUE),
	LIGHTSLATEGRAY(Color.LIGHTSLATEGRAY),
	LIGHTSLATEGREY(Color.LIGHTSLATEGREY),
	LIGHTSTEELBLUE(Color.LIGHTSTEELBLUE),
	LIGHTYELLOW(Color.LIGHTYELLOW),
	LIME(Color.LIME),
	LIMEGREEN(Color.LIMEGREEN),
	LINEN(Color.LINEN),
	MAGENTA(Color.MAGENTA),
	MAROON(Color.MAROON),
	MEDIUMAQUAMARINE(Color.MEDIUMAQUAMARINE),
	MEDIUMBLUE(Color.MEDIUMBLUE),
	MEDIUMORCHID(Color.MEDIUMORCHID),
	MEDIUMPURPLE(Color.MEDIUMPURPLE),
	MEDIUMSEAGREEN(Color.MEDIUMSEAGREEN),
	MEDIUMSLATEBLUE(Color.MEDIUMSLATEBLUE),
	MEDIUMSPRINGGREEN(Color.MEDIUMSPRINGGREEN),
	MEDIUMTURQUOISE(Color.MEDIUMTURQUOISE),
	MEDIUMVIOLETRED(Color.MEDIUMVIOLETRED),
	MIDNIGHTBLUE(Color.MIDNIGHTBLUE),
	MINTCREAM(Color.MINTCREAM),
	MISTYROSE(Color.MISTYROSE),
	MOCCASIN(Color.MOCCASIN),
	NAVAJOWHITE(Color.NAVAJOWHITE),
	NAVY(Color.NAVY),
	OLDLACE(Color.OLDLACE),
	OLIVE(Color.OLIVE),
	OLIVEDRAB(Color.OLIVEDRAB),
	ORANGE(Color.ORANGE),
	ORANGERED(Color.ORANGERED),
	ORCHID(Color.ORCHID),
	PALEGOLDENROD(Color.PALEGOLDENROD),
	PALEGREEN(Color.PALEGREEN),
	PALETURQUOISE(Color.PALETURQUOISE),
	PALEVIOLETRED(Color.PALEVIOLETRED),
	PAPAYAWHIP(Color.PAPAYAWHIP),
	PEACHPUFF(Color.PEACHPUFF),
	PERU(Color.PERU),
	PINK(Color.PINK),
	PLUM(Color.PLUM),
	POWDERBLUE(Color.POWDERBLUE),
	PURPLE(Color.PURPLE),
	RED(Color.RED),
	ROSYBROWN(Color.ROSYBROWN),
	ROYALBLUE(Color.ROYALBLUE),
	SADDLEBROWN(Color.SADDLEBROWN),
	SALMON(Color.SALMON),
	SANDYBROWN(Color.SANDYBROWN),
	SEAGREEN(Color.SEAGREEN),
	SEASHELL(Color.SEASHELL),
	SIENNA(Color.SIENNA),
	SILVER(Color.SILVER),
	SKYBLUE(Color.SKYBLUE),
	SLATEBLUE(Color.SLATEBLUE),
	SLATEGRAY(Color.SLATEGRAY),
	SLATEGREY(Color.SLATEGREY),
	SNOW(Color.SNOW),
	SPRINGGREEN(Color.SPRINGGREEN),
	STEELBLUE(Color.STEELBLUE),
	TAN(Color.TAN),
	TEAL(Color.TEAL),
	THISTLE(Color.THISTLE),
	TOMATO(Color.TOMATO),
	TRANSPARENT(Color.TRANSPARENT),
	TURQUOISE(Color.TURQUOISE),
	VIOLET(Color.VIOLET),
	WHEAT(Color.WHEAT),
	WHITE(Color.WHITE),
	WHITESMOKE(Color.WHITESMOKE),
	YELLOW(Color.YELLOW),
	YELLOWGREEN(Color.YELLOW);
	
	private final Info info;
	
	//Maximal constructor, ultimately called by all other constructors.
	private Word(Color color, Size size, Location location, Image image) {
		info = new Info(color, size, location, image);
	}
	
	//This would be a color
	private Word(Color color){
		this(color, null, null, null);
	}
	
	//This is a size
	private Word(Size size){
		this(null, size, null, null);
	}
	
	//This is a location
	private Word(Location location){
		this(null, null, location, null);
	}
	
	//This is a noun
	private Word(Image image){
		this(null, null, null, image);
	}
	
	public Info getInfo(){
		return info;
	}
}
