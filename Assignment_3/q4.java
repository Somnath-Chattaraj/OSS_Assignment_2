import java.lang.reflect.*;

class Person {
    private String name;
    private int age;


    public Person() { }
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }


    public void greet() {
        System.out.println("Hello, my name is " + name);
    }


    private void secretMethod() {
        System.out.println("This is a secret method!");
    }
}

public class q4 {
    public static void main(String[] args) {
        try {

            Class<?> personClass = Person.class;
            System.out.println("Class Name: " + personClass.getName());


            Method[] methods = personClass.getMethods();
            System.out.println("\nPublic Methods:");
            for (Method method : methods) {
                System.out.println(method.getName());
            }


            Method[] declaredMethods = personClass.getDeclaredMethods();
            System.out.println("\nAll Declared Methods:");
            for (Method method : declaredMethods) {
                System.out.println(method.getName());
            }


            Constructor<?>[] constructors = personClass.getConstructors();
            System.out.println("\nConstructors:");
            for (Constructor<?> constructor : constructors) {
                System.out.println(constructor);
            }


            Method greetMethod = personClass.getMethod("greet");
            System.out.println("\nSpecific Method: " + greetMethod);


            Method secretMethod = personClass.getDeclaredMethod("secretMethod");
            secretMethod.setAccessible(true);
            Person personInstance = new Person("Alice", 25);
            secretMethod.invoke(personInstance);


            Field nameField = personClass.getDeclaredField("name");
            nameField.setAccessible(true);
            nameField.set(personInstance, "Bob");
            System.out.println("\nModified Name Field: " + nameField.get(personInstance));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
