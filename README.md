# Swing-MVC
An MVC framework for Swing.

This is a small framework to allow you to build multi screen
applications in swing just as easily as single screen apps.

### Adding the Dependency

Currently, this framework is in snapshot mode, so you will need
to add the snapshot repository to your `pom.xml`.

```xml
<repositories>
    <repository>
        <id>snapshots-repo</id>
        <url>https://oss.sonatype.org/content/repositories/snapshots</url>
        <releases><enabled>false</enabled></releases>
        <snapshots><enabled>true</enabled></snapshots>
    </repository>
</repositories>
```

Then add the dependency to your `pom.xml`

```xml
<dependencies>
    <dependency>
        <groupId>com.redstoneblocks.java</groupId>
        <artifactId>Swing-MVC</artifactId>
        <version>0.0.3-SNAPSHOT</version>
    </dependency>
</dependencies>
```

### Usage

#### Registering screens

To register a screen, you need to do two things. The first is to extend
the abstract classes and implement the methods for `Model`, `View` and `Controller`.
The second is to add the `@MVC` annotation to each of your classes with the name of
the screen they create.

```java
// ExampleModel.java

// import necessary classes
import com.redstoneblocks.java.swing_mvc.MVC;
import com.redstoneblocks.java.swing_mvc.core.Model;

// annotate the class with @MVC("EXAMPLE")
// to tell Swing-MVC that this class belongs to the
// "EXAMPLE" screen. This is case sensitive.
@MVC("EXAMPLE")
public class ExampleModel extends Model {
    // declare, but not initialize, the variables;
    // we are using package-private for our model variables
    // because these components will normally be in
    // a package together
    int count;

    // state is data that can be passed from the previous screen.
    // we can ignore it as we are not using it.
    @Override
    public void init(Object state) {
        // initialize the data
        count = 0;
    }
}
```

```java
// ExampleView.java

// import necessary classes
import com.redstoneblocks.java.swing_mvc.MVC;
import com.redstoneblocks.java.swing_mvc.core.View;

// annotate the class to tell Swing-MVC that this
// class belongs to the "EXAMPLE" screen
@MVC("TEST")
public class ExampleView extends View {
    // declare, but not initialize the variables here
    // these are package-private to allow easier access in
    // the controller.
    JPanel gui;
    JButton incrementButton;
    JButton decrementButton;
    JLabel countLabel;

    @Override
    public void create(Model model) {
        // due to java's type system, we pass in the model
        // as a `Model` and cast it to the correct type (ExampleModel).
        ExampleModel exampleModel = (ExampleModel) model;

        // we initialize the components here
        gui = new JPanel();
        incrementButton = new JButton("Increment");
        decrementButton = new JButton("Decrement");

        // here we also use data from the model
        // to construct the JLabel with a default value
        count = new JLabel(String.valueOf(exampleModel.count));

        // now we add everything to the JPanel
        gui.add(increaseButton);
        gui.add(decreaseButton);
        gui.add(count);
    }

    // this is used so that Swing-MVC can display
    // the view in the window.
    @Override
    public JPanel getGui() {
        return gui;
    }
}
```

```java
// ExampleController.java

// import necessary classes
import com.redstoneblocks.java.swing_mvc.EntryPoint;
import com.redstoneblocks.java.swing_mvc.MVC;
import com.redstoneblocks.java.swing_mvc.core.Controller;
import com.redstoneblocks.java.swing_mvc.core.Model;
import com.redstoneblocks.java.swing_mvc.core.View;
import com.redstoneblocks.java.swing_mvc.util.Navigator

import java.awt.event.ActionEvent;

// we annotate the controller with the `@EntryPoint`
// annotation to tell Swing-MVC that this screen is
// the entry point. This annotation can be on any of
// the extended classes (ExampleModel, ExampleView, ExampleController)
// or all of them, but it needs to be on at least one
@EntryPoint
// we annotate the controller with @MVC("EXAMPLE") to tell
// Swing-MVC that it belongs to the "EXAMPLE" screen.
@MVC("EXAMPLE")
public class ExampleController extends Controller {
    // these are simply the model and view,
    // but casted already, so you do not need
    // to cast them everytime you use them
    ExampleView exampleView;
    ExampleModel exampleModel;
    
    // Navagator is a utility class for changing
    // screens (navigating). It also allows you
    // to pass data to the next screen's Model.
    // you may store it in a variable.
    @Override
    public void init(Navigator nav) {
        // here we create action listeners for the buttons
        // we increment or decrement count, and then update the label
        exampleView.decrementButton.addActionListener(e -> {
            exampleModel.count--;
            exampleView.countLabel.setText(String.valueOf(exampleModel.count));
        });
        exampleView.incrementButton.addActionListener(e -> {
            exampleModel.count++;
            exampleView.countLabel.setText(String.valueOf(exampleModel.count));
        });
    }
    
    // this method is run before navigating to cleanup any resources
    // that the controller is using, such as network connections or
    // unsaved files. since we don't need to cleanup anything,
    // we keep it empty
    @Override
    public void cleanup() {}
}
```

#### Running the app
The App class is what finds and runs your screens. All you need to do is
run the App#createAndStart method in your application's entry point.

```
// Run.java

// import App class
import com.redstoneblocks.java.swing_mvc.runner.App;

public class Run {
    public static void main(String[] args) {
        // this runs your app
        App.createAndStart();
    }
}
```

Compile and run your app. You will see a window popup with 2 buttons and a label,
and you can increment or decrement the count.

NOTE: currently there is a bug that will leave the app running after you close the window.
This will be fixed in the next version, and will default to EXIT_ON_CLOSE behavoir.
