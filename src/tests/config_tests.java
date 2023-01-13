package tests;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;
import org.junit.Before;
import events.*;
import events.eventsExceptions.*;
import events.gameStateChange.*;

import java.util.*;
import java.nio.file.*;

public class config_tests {
    private Path workingDir = Path.of("","src");
    // Seems like we have to do some weird file stuff to get our paths working
    private String filename = "config/correct_config.txt";
    private Path file = this.workingDir.resolve(filename);
    
    // This @Before bit is to get our file path correct as file paths for JUnit tests can be different
    @Before
    public void init(){
        
    }
    @Test
    public void test_reading_config(){
        ArrayList<String> lines = parseConfigFile.readConfigFile(filename);
        // Testing the files are read in correctly 
        assertNotEquals(0, lines.size());
        assertEquals(13, lines.size());
    }

    @Test 
    public void test_correct_config() throws Exception {
        ArrayList<String> lines = parseConfigFile.readConfigFile(file.toString());
        ArrayList<event> events = parseConfigFile.parseConfigLines(lines);
        event event1 = events.get(0);
        event event2 = events.get(1);
        // Testing the events have the correct info
        assertEquals(2, events.size());
        // Testing symbols
        assertEquals('A',event1.symbol);
        assertEquals('B',event2.symbol);
        // Testing text
        assertEquals("Hello I am a door",event1.flavor_text);
        assertEquals("Hello I am not a door",event2.flavor_text);
        // Testing actions
        ArrayList<action> event1_actions = event1.actions;
        assertEquals(2,event1_actions.size());
        assertEquals("Open chest",event1_actions.get(0).flavor_text);
        assertEquals("key",event1_actions.get(0).limiters[0]);
        assertEquals("add_item[jewel]",event1_actions.get(0).gameStateChange.toString());
    }
    @Test
    public void test_incorect_symbol() throws Exception{
        // Test fails if the exception is not caught
        String filename = "config/invalid_symbol_config.txt";
        //Path file = this.workingDir.resolve(filename);
        ArrayList<String> lines = parseConfigFile.readConfigFile(file.toString());
        try{
            ArrayList<event> events = parseConfigFile.parseConfigLines(lines);
        }catch (InvalidConfigSymbolException e){
            return;
        }
        fail();
    }
    @Test
    public void incorrect_config_format() throws Exception{
        // Test fails if the three lines after the astriks are not correct
        String filename = "config/invalid_config_format.txt";
        Path file = this.workingDir.resolve(filename);
        ArrayList<String> lines = parseConfigFile.readConfigFile(file.toString());
        for (int linenum = 0; linenum < lines.size();linenum++) {
            if (lines.get(linenum).equals("***")) {
                assertNotEquals("symbol=",lines.get(linenum+1).substring(0,6));
                assertNotEquals("text=",lines.get(linenum+2).substring(0,4));
                assertNotEquals("actions:",lines.get(linenum+3));
            }
        }
    }
    @Test
    public void incorrect_actions_format() throws Exception{
         // Test fails if the exception is not caught
        String filename = "config/invalid_symbol_config.txt";
        Path file = this.workingDir.resolve(filename);
        ArrayList<String> lines = parseConfigFile.readConfigFile(file.toString());
        try{
            ArrayList<event> events = parseConfigFile.parseConfigLines(lines);
        }catch (InvalidConfigSymbolException e){
            return;
        }
        fail();
    }
}
