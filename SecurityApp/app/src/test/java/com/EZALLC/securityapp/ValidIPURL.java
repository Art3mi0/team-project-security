package com.EZALLC.securityapp;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ValidIPURL{
    @Test
    public void iPIsValid() {
        assertTrue(Search.isValidIPAddress("1.1.1.1"));
    }
    @Test
    public void invalidIpNumber() {
        assertFalse(Search.isValidIPAddress("260.1.1.1"));
    }
    @Test
    public void invalidIpManyNumbers() {
        assertFalse(Search.isValidIPAddress("1.1.1.1.1"));
    }
    @Test
    public void invalidIpNotNumber() {
        assertFalse(Search.isValidIPAddress("1.1.1.A"));
    }
    @Test
    public void invalidIpRand() {
        assertFalse(Search.isValidIPAddress("-1.#####.@.9="));
    }
    @Test
    public void urlIsValid() { assertTrue(Search.validURl("https://www.google.com"));
    }
    @Test
    public void invalidURLNoProtocol() {
        assertFalse(Search.validURl("www.google.com"));
    }
    @Test
    public void invalidURLNoComGovNet() {
        assertFalse(Search.validURl("https://uncw.edu"));
    }
    @Test
    public void invalidURLRand() {
        assertFalse(Search.validURl("https:/www.google.com"));
        assertFalse(Search.validURl("https//www.google.com"));
        assertFalse(Search.validURl("https://www.google"));
        assertFalse(Search.validURl("https:/www.google.co"));
        assertFalse(Search.validURl("https:/www.googlecom"));
    }
}