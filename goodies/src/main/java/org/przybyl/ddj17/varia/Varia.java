/*
 *  Copyright (C) 2021 Piotr Przybył
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package org.przybyl.ddj17.varia;


import java.io.*;
import java.net.*;
import java.util.*;

// run as e.g.
// java org/przybyl/ddj17/varia/Varia.java
public class Varia {

    public static void main(String[] args) throws IOException {
        java.applet.Applet appletDeprecatedForRemoval;
        SecurityManager securityManager = System.getSecurityManager();
        var serialFilerFactory = ObjectInputFilter.Config.getSerialFilterFactory();
        //jdk.jfr.events.DeserializationEvent event = null;
        System.out.println(System.getProperty("native.encoding"));
        System.out.println(HexFormat.isHexDigit('g'));
        System.out.println(HexFormat.fromHexDigits("CAFEBABE"));
        ServerSocket.setSocketFactory(() -> null);
    }

    public static strictfp double aDoubleOperation(double a, double b) {
        return Math.pow(a, b);
    }
}
