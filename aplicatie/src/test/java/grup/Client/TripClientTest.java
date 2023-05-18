package grup.Client;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
class TripClientTest {

    @Test
    void testExcursie() throws IOException {
        var client = new TripClient("http://localhost:12500");
        client.addExcursie("123","123",1, (float) 1,12);
        client.updateExcursie(12,"1234","123",1, (float) 1,12);
        client.deleteExcursie(12);
    }
}