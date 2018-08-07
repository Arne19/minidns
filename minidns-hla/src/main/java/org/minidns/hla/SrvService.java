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
package org.minidns.hla;

import org.minidns.dnsname.DnsName;

public enum SrvService {

    // @formatter:off
    xmpp_client,
    xmpp_server,

    /**
     * @see <a href="https://xmpp.org/extensions/xep-0368.html">XEP-0368: SRV records for XMPP over TLS</a>
     */
    xmpps_client,

    /**
     * @see <a href="https://xmpp.org/extensions/xep-0368.html">XEP-0368: SRV records for XMPP over TLS</a>
     */
    xmpps_server,
    ;
    // @formatter:on

    public final DnsName dnsName;

    SrvService() {
        String enumName = name().replaceAll("_", "-");
        dnsName = DnsName.from('_' + enumName);
    }
}
