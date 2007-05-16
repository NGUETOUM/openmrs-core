package org.openmrs.api.util;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.openmrs.BaseTest;
import org.openmrs.util.OpenmrsUtil;
import org.springframework.test.annotation.NotTransactional;

public class OpenmrsUtilTest extends BaseTest {
	
	@NotTransactional
	public void testGetCheckDigit() throws Exception {
		
		System.out.println("In testGetCheckDigit()");
		
		String[] ids = {"9", "99", "999", "123MT", "asdf"};
		int[] cds = {1, 2, 3, 2, 8} ;
		
		for (int i = 0; i< ids.length; i++) {
			System.out.println(ids[i]);
			assertEquals(OpenmrsUtil.getCheckDigit(ids[i]), cds[i]);
		}
		
	}
	
	@NotTransactional
	public void testIsValidCheckDigit() throws Exception {
		
		System.out.println("In testIsValidCheckDigit()");
		
		String[] ids2 = {"9-1", "99-2", "999-3", "123MT-2", "asdf-8"};
		for (int i = 0; i< ids2.length; i++) {
			System.out.println(ids2[i]);
			assertTrue(OpenmrsUtil.isValidCheckDigit(ids2[i]));
		}
		
		String[] ids3 = {"asdf-7", "9-2", "9-4"};
		for (int i = 0; i< ids3.length; i++) {
			System.out.println(ids3[i]);
			assertFalse(OpenmrsUtil.isValidCheckDigit(ids3[i]));
		}
		
		String[] ids4 = {"#@!", "234-3-3", "-3", "2134"};
		for (int i = 0; i< ids4.length; i++) {
			try {
				System.out.println(ids4[i]);
				OpenmrsUtil.isValidCheckDigit(ids4[i]);
				fail("An exception was not thrown for invalid identifier: " + ids4[i]);
			}
			catch (Exception e) {}
		}
	}
	
	public static Test suite() {
		System.out.println("In suite()");
		return new TestSuite(OpenmrsUtilTest.class);
	}

	
	
}
