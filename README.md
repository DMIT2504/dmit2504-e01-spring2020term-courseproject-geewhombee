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
  Alter the code above using room annotations
  
  ******
  //@Entity annotation to tell room that this class is an entity in our database
  @Entity(tableName = "person") //tableName will set the SQLite table name.
  public class Person {
    //@PrimaryKey annotation to tell room that this field is the tables primary key
    @PrimaryKey(autoGenerate = true) //Set autoGenerate to true so that the primary key
    private int id;
    //@ColumnInfo is an option annotation. You can use this to set a custom column name.
    //If you want to use the field
    @ColumnInfo(name = "name")
    private String name;
    //NOTE: if using more than one constructor, use the @Ignore annotation on any constructor that we do not want room to use.
    //room requires a constructor so that it can create and return these objects after a database query
    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }
    @Ignore
    public Person(String name) {
        this.name = name;
    }
    public int getId() { return id; }
    public String getName() { return this.name; }
  }
  ******
Step 3: Create the DAO Interface class
  The Dao will be used to define methods that access our database.
  @Dao will be used to annotate the interface
  @Insert, @Update, @Delete, and @Query will be the annotations for methods.
  Create a new interface class in your project.
  ******
  @Dao
  public interface PersonDao {
    //this method will insert the Person object that is passed when it is called.
    //can use long instead of void to return the id after the object is inserted in the database
    @Insert
    void insertPerson(Person person);
    //this method will update the Person object that is passed when it is called.
    @Update
    void updatePerson(Person person);
    //this method will delete the Person object from the database that is passed when it is called.
    @Delete
    void deletePerson(Person person);
    //@Query can be used to create custom database queries. Provide an SQLite statement in the parentheses.
    //Android studio will let you know at compile time if these methods are causing issues. 
    @Query("Select * from person")
    List<Person> getPersonList(); //this method will return a list of all Person objects from our person table.
    @Query("Select * from person Where name Like :name")
    List<Person> getAllPersonsByName(String name); //this method will also return a list, but will contain only objects whos name match the provided parameter "String name"
  }
  ******
