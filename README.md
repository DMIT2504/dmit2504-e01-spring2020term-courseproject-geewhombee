# dmit2504-e01-spring2020term-courseproject-geewhombee
dmit2504-e01-spring2020term-courseproject-geewhombee created by GitHub Classroom
Simple Tutorial
***
Step 1: Add required dependencies to build.gradle.
  - https://developer.android.com/jetpack/androidx/releases/room
  The link above provides the following dependancies
  *****
  dependencies {
  def room_version = "2.2.5"

  implementation "androidx.room:room-runtime:$room_version"
  annotationProcessor "androidx.room:room-compiler:$room_version" // For Kotlin use kapt instead of annotationProcessor

  // optional - Kotlin Extensions and Coroutines support for Room
  implementation "androidx.room:room-ktx:$room_version"

  // optional - RxJava support for Room
  implementation "androidx.room:room-rxjava2:$room_version"

  // optional - Guava support for Room, including Optional and ListenableFuture
  implementation "androidx.room:room-guava:$room_version"

  // Test helpers
  testImplementation "androidx.room:room-testing:$room_version"
  }
  ******
  We only require the following for this tutorial:
  ******
  def room_version = "2.2.5"

  implementation "androidx.room:room-runtime:$room_version"
  annotationProcessor "androidx.room:room-compiler:$room_version"
  ******
Step 2: Create a the Entity class
  - Add a new Java class to your project. This class will be used to create a table in our database. You can name it to be the table name, or name it something else and set the    table name within this class. To start, just create a simple object to store some information. Create a field for an int id, and a String name. Create a constructor. Create get methods for the fields.
  ******
  public class Person {
    private int id;
    private String name;
    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public int getId() { return id; }
    public String getName() { return this.name; }
}
  ******
  
  Now we will utilize the dependencies we added earlier.
  

