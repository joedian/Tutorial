package org.intercom.tests;

import java.util.TreeMap;

import org.intercom.test.CustomerAppreciation;

import junit.framework.TestCase;

/**
 * 
 * @author joedian
 *
 */
public class CustomerAppreciationTest extends TestCase {
	/**
	 * This test aims to test the functionality of the ArrayFlattenerClass It
	 * uses base and valid cases to test the correct functionality of the
	 * flattener method
	 */
	public void testCustomerAppreciation() {
		String urlOfCustomers = "https://gist.githubusercontent.com/brianw/19896c50afa89ad4dec3/raw/6c11047887a03483c50017c1d451667fd62a53ca/gistfile1.txt";
		TreeMap<String, String> customersToApprectiate = CustomerAppreciation.getCustomersForEvent(urlOfCustomers);

		assertNotNull(customersToApprectiate);
		
		//test will malformed url
		urlOfCustomers = "https://gist.githubusercontent.com/brianw/19896c50afa";
		customersToApprectiate = CustomerAppreciation.getCustomersForEvent(urlOfCustomers);
		assertEquals(customersToApprectiate.keySet().size(), 0); //also error will be thrown

	}

}
