/*
 * Copyright 2015-2018 the original author or authors
 *
 * This software is licensed under the Apache License, Version 2.0,
 * the GNU Lesser General Public License version 2 or later ("LGPL")
 * and the WTFPL.
 * You may choose either license to govern your use of this software only
 * upon the condition that you accept all of the terms of either
 * the Apache License 2.0, the LGPL 2.1+ or the WTFPL.
 */
package org.minidns.dnssec.algorithms;

import org.minidns.constants.DnssecConstants.SignatureAlgorithm;
import org.minidns.dnssec.DnssecValidationFailedException;
import org.junit.Test;

import java.math.BigInteger;

import static org.minidns.dnssec.DnssecWorld.generatePrivateKey;
import static org.minidns.dnssec.DnssecWorld.generateRSAPrivateKey;
import static org.minidns.dnssec.DnssecWorld.publicKey;
import static org.minidns.dnssec.DnssecWorld.sign;

public class RsaSignatureVerifierTest extends SignatureVerifierTest {
    @Test
    public void testShortExponentSHA1RSAValid() {
        verifierTest(generateRSAPrivateKey(1024, BigInteger.valueOf(17)), SignatureAlgorithm.RSASHA1);
    }

    @Test
    public void testLongExponentSHA1RSAValid() {
        verifierTest(generateRSAPrivateKey(3072, BigInteger.valueOf(256).pow(256).add(BigInteger.ONE)), SignatureAlgorithm.RSASHA1);
    }

    @Test(expected = DnssecValidationFailedException.class)
    public void testSHA1RSAIllegalSignature() {
        assertSignatureValid(publicKey(SignatureAlgorithm.RSASHA1, generatePrivateKey(SignatureAlgorithm.RSASHA1, 1024)), SignatureAlgorithm.RSASHA1, new byte[]{0x0});
    }

    @Test(expected = DnssecValidationFailedException.class)
    public void testSHA1RSAIllegalPublicKey() {
        assertSignatureValid(new byte[]{0x0}, SignatureAlgorithm.RSASHA1, sign(generatePrivateKey(SignatureAlgorithm.RSASHA1, 1024), SignatureAlgorithm.RSASHA1, sample));
    }

    @Test
    public void testSHA1RSAWrongSignature() {
        assertSignatureInvalid(publicKey(SignatureAlgorithm.RSASHA1, generatePrivateKey(SignatureAlgorithm.RSASHA1, 1024)), SignatureAlgorithm.RSASHA1, sign(generatePrivateKey(SignatureAlgorithm.RSASHA1, 1024), SignatureAlgorithm.RSASHA1, sample));
    }

    @SuppressWarnings("deprecation")
    @Test
    public void testMD5RSAValid() {
        verifierTest(1024, SignatureAlgorithm.RSAMD5);
    }

    @Test
    public void testSHA256RSAValid() {
        verifierTest(1024, SignatureAlgorithm.RSASHA256);
    }

    @Test
    public void testSHA512RSAValid() {
        verifierTest(1024, SignatureAlgorithm.RSASHA512);
    }
}