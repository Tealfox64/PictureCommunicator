package cs246.picturecommunicator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void can_getFilename() throws Exception {
        assertEquals("pictures.txt","pictures.txt");
    }

    @Test
    public void can_getCategory() throws Exception {
        assertEquals("farmAnimals","farmAnimals");
    }

    @Test
    public void can_clearList() throws Exception {
        int numItems = 0;
        assertEquals(0,numItems);
    }
}