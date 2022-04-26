package com.EZALLC.securityapp;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
class ValidIPURL{
    @Test
    public void iPIsValid() {
        assertTrue(Search.isValidIPAddress("1.1.1.1"));
    }
    public void invalidIpNumber() {
        assertFalse(Search.isValidIPAddress("260.1.1.1"));
    }

    public void invalidIpManyNumbers() {
        assertFalse(Search.isValidIPAddress("1.1.1.1.1"));
    }
    public void invalidIpNotNumber() {
        assertFalse(Search.isValidIPAddress("1.1.1.A"));
    }
    public void invalidIpRand() {
        assertFalse(Search.isValidIPAddress("-1.#####.@.9="));
    }
    public void urlIsValid() {
        assertTrue(Search.validURl("https://www.google.com"));
    }
    public void invalidURLNoProtocol() {
        assertFalse(Search.validURl("www.google.com"));
    }

    public void invalidURLNoComGovNet() {
        assertFalse(Search.validURl(""));
    }
    public void invalidURLBADDomainName() {
        assertFalse(Search.validURl(""));
    }
    public void invalidURLRand() {
        assertFalse(Search.validURl(""));
    }
}