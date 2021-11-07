package M226a.project;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

class SnackImporterTest {
    @Test
    void test(){
        SnackImporter si = new SnackImporter();
        System.out.println(si.grabData("SELECT * FROM user.snack"));
        assertTrue(si.grabData("SELECT * FROM user.snack").contains("Snickers"));
    }
    @Test
    void test2(){
        SnackImporter si = new SnackImporter();
        si.testConnection();

    }
}