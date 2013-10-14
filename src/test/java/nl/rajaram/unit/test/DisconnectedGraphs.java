/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.rajaram.unit.test;

import ch.tkuhn.nanopub.MalformedNanopubException;
import ch.tkuhn.nanopub.NanopubImpl;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import nl.rajaram.unit.test.utils.FileOperation;
import static org.junit.Assert.fail;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.openrdf.OpenRDFException;
import org.openrdf.rio.RDFFormat;

/**
 *
 * @author Rajaram
 * @since 14-10-2013
 * @version 1.0
 */
public class DisconnectedGraphs {
    
    public DisconnectedGraphs () {
        
    }
    
    @Rule
    public ExpectedException thrown= ExpectedException.none();
    
    /**
     * <p>
     * This nanopublication has disconnected assertion graph, so the test 
     * is expected to throw MalformedNanopubException.
     * </p>
     * @throws IOException 
     */
    @Test(expected = MalformedNanopubException.class)
    public void disconnectedAssertion() throws MalformedNanopubException, 
    IOException       
    {        
        /**
         * See package src/test/resources/invalid/nanopublication/
         * disconnectedgraph/ for the file.
         */ 
        URL fileURL = this.getClass().
                getResource("/invalid/nanopublication/disconnectedgraph"
                + "/disconnectedAssertionGraph.trig");
        String content = FileOperation.readFile(fileURL.getPath(), 
                StandardCharsets.UTF_8);
        try {
            NanopubImpl test = new NanopubImpl(content, RDFFormat.TRIG);
        }
        catch (OpenRDFException | IOException e) {
            fail("This test is excepted to throw only "
                    + "MalformedNanopubException");
        }   
    }
    
    
}