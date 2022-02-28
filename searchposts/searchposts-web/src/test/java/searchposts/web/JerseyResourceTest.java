/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchposts.web;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ruslan
 */
public class JerseyResourceTest {
    
    public JerseyResourceTest() {
    }

    /**
     * Test of getHTML method, of class JerseyResource.
     */
    @org.junit.Test
    public void testGetHTML() throws Exception {
        System.out.println("getHTML");
        String subject = "тест_тест_тест";
        JerseyResource instance = new JerseyResource();
        String expResult = "No subjects found";
        String result = instance.getHTML(subject);
        assertEquals(expResult, result);
    }
    
}
