package exercice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloWorldTest {

    @Test
    void getHello() {
        String name = "foo";
        assertEquals("Hello " + name, HelloWorld.getHello(name));
    }
}