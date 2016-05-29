package es.palmademallorca.bg.common.controls.custom;

import java.io.*;
import javafx.fxml.*;
import java.util.regex.*;
import javafx.beans.value.*;
import javafx.scene.control.*;
import javafx.util.converter.*;

/*
	This class prevents anything but numbers from being entered into the text
	field. In addition, the entered number is forced to be >= mMin and <= mMax.

	We employ a kludge here to get around leaking "this" in our constructor.
	(Leaking "this" in a constructor can cause concurrency problems - the
	recipient of a "this" injected from within a constructor could start
	using the object referenced by "this" before it has finished constructing
	itself.) We override the layoutChildren() method of our super class,
	because it is guaranteed to be called early on, and we want that guaranteed
	early call so the class can load itself from its fxml file outside the
	constructor. (See, for example, the "fxmlLoader.setRoot(this);" line below.)

	We don't use a factory design pattern because we want this class's jar to
	appear as a custom component in SceneBuilder and SceneBuilder requires a public
	default constructor. So we work around this impasse by using a boolean
	flag ("mHasBeenInitialized") that is checked in our layoutChildren() method
	and if false we call the init() method that, among other things, loads the
	class's fxml file.
*/
public class NumberTextField extends TextField implements ChangeListener<String> {

	// class variables
	private static final String cMyFXMLPath = "NumberTextField.fxml";
	private static final long cDefaultMin = 0;
	private static final long cDefaultMax = 100;
	private static final int cWidth = 40;
	private static final int cHeight = 40;
	private static final Pattern cNumberPattern = Pattern.compile( "^(-?0|-?[1-9]\\d*)(\\.\\d+)?(E\\d+)?$" );
	private static final NumberStringConverter	cConverter = new NumberStringConverter();

	// member variables
	private boolean	mHasBeenInitialized = false;    // See above
	private final long mMin;
	private final long mMax;


	public NumberTextField() {
		// SceneBuilder requires a default constructor.
		this(cDefaultMin, cDefaultMax);
	}


	public NumberTextField(long min, long max) {
		super();
		mMin = min;
		mMax = max;
	}


	private void init() {
		// See note above.
		FXMLLoader	fxmlLoader;

		if (mHasBeenInitialized)
		return;

		mHasBeenInitialized = true;
		fxmlLoader = new FXMLLoader(getClass().getResource(cMyFXMLPath));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
		textProperty().addListener(this);
	}


	@Override protected void layoutChildren() {
		if (!mHasBeenInitialized)	// See note above.
		init();
		super.layoutChildren();
	}

	/*
		This is the code that prevents anything but numbers from
		being entered into the text field. In addition, the entered
		number is forced to be >= mMin and <= mMax.
	*/
	@Override public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

		Number value;
		if (cNumberPattern.matcher(newValue).matches()) {
			value = cConverter.fromString(newValue);

			if (value.longValue() > mMax) {
				setText(cConverter.toString(mMax));
			} else if (value.longValue() < mMin) {
				setText(cConverter.toString(mMin));
			}
		} else if (!newValue.isEmpty()) {
			setText(oldValue);
		}
	}
}