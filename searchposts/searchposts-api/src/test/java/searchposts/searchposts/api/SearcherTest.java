/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchposts.searchposts.api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Ruslan
 */
public class SearcherTest {
    
    public SearcherTest() {
    }

    /**
     * Test of getPostsBySubject method, of class Searcher.
     */
    @org.junit.jupiter.api.Test
    public void testGetPostsBySubject() throws Exception {
        System.out.println("getPostsBySubject");
        String subject = "тест_тест_тест";
        Searcher instance = new Searcher();
        String expResult = "No subjects found";
        String result = instance.getPostsBySubject(subject);
        assertEquals(expResult, result);
    }
    
}
