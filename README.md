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

After intstalling, go check out our wiki for tutorials.

NOTE: currently there is a bug that will leave the app running after you close the window.
This will be fixed in the next version, and will default to JFrame.EXIT_ON_CLOSE behavoir.
