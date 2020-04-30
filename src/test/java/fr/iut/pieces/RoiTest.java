
package fr.iut.pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class RoiTest {
	
	private Roi roiNoir;
    private Roi roiBlanc;

    @BeforeEach
    public void initialisation() {
        roiNoir = new Roi(true);
        roiBlanc = new Roi(false);
    }
	
	@Test
	public void testToStringRoi() {
		assertEquals("RoB", roiBlanc.toString());
		assertEquals("RoN", roiNoir.toString());
	}

}
