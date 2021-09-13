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

package org.przybyl.ddj17.foreign;


import java.lang.invoke.*;
import java.nio.*;

import jdk.incubator.foreign.*;

/**
 * Demo of Foreign Memory Access API incubator feature of Java 17, 4th preview,
 * based on https://openjdk.java.net/jeps/412
 * <p>
 * Please don't forget to --add-modules jdk.incubator.foreign
 */
public class ForeignMemoryAccessAPIDemo {

    public static void main(String[] args) throws InterruptedException {
        sameThreadDemo();
    }

    private static void sameThreadDemo() {
        SequenceLayout intArrayLayout = MemoryLayout.sequenceLayout(10, MemoryLayout.valueLayout(32, ByteOrder.nativeOrder()));
        try (ResourceScope scope = ResourceScope.newConfinedScope()) {
            MemorySegment segment = MemorySegment.allocateNative(intArrayLayout, scope);
            populateNativeMem(intArrayLayout, segment);
            examineNativeMem(intArrayLayout, segment);
        }
    }

    private static void populateNativeMem(SequenceLayout intArrayLayout, MemorySegment segment) {
        VarHandle intHandle = MemoryHandles.varHandle(int.class, ByteOrder.nativeOrder());
        VarHandle intElemHandle = intArrayLayout.varHandle(int.class, MemoryLayout.PathElement.sequenceElement());
        for (int i = 0; i < intArrayLayout.elementCount().getAsLong(); i++) {
            MemoryAccess.setIntAtOffset(segment, i * 4L, i);
//                intHandle.set(segment, i * 4L, i);
//                intElemHandle.set(segment, (long) i, i);
        }
    }

    private static void examineNativeMem(SequenceLayout intArrayLayout, MemorySegment segment) {
        MemoryAddress segmentBaseAddress = segment.address();
        System.out.println("Examining memory at: " + segmentBaseAddress.toRawLongValue());
        System.out.println("Segment owned by: " + segment.scope().ownerThread().getName());
        VarHandle intElemHandle = intArrayLayout.varHandle(int.class, MemoryLayout.PathElement.sequenceElement());
        for (int i = 0; i < intArrayLayout.elementCount().getAsLong(); i++) {
            System.out.print(MemoryAccess.getIntAtOffset(segment, i * 4L));
            System.out.print(" ");
            System.out.println(intElemHandle.get(segment, (long) i));
        }
    }
}
