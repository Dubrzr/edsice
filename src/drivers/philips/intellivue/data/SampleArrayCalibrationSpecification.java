/*******************************************************************************
 * Copyright (c) 2014, MD PnP Program
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 ******************************************************************************/
package drivers.philips.intellivue.data;

import java.nio.ByteBuffer;

import common.io.util.Bits;

/**
 * @author Jeff Plourde
 *
 */
public class SampleArrayCalibrationSpecification implements Value {
    private final Float lowerAbsoluteValue = new Float();
    private final Float upperAbsoluteValue = new Float();
    private int lowerScaledValue, upperScaledValue, increment, calType;

    @Override
    public void format(ByteBuffer bb) {
        lowerAbsoluteValue.format(bb);
        upperAbsoluteValue.format(bb);
        Bits.putUnsignedShort(bb, lowerScaledValue);
        Bits.putUnsignedShort(bb, upperScaledValue);
        Bits.putUnsignedShort(bb, increment);
        Bits.putUnsignedShort(bb, calType);
    }

    @Override
    public void parse(ByteBuffer bb) {
        lowerAbsoluteValue.parse(bb);
        upperAbsoluteValue.parse(bb);
        lowerScaledValue = Bits.getUnsignedShort(bb);
        upperScaledValue = Bits.getUnsignedShort(bb);
        increment = Bits.getUnsignedShort(bb);
        calType = Bits.getUnsignedShort(bb);
    }

    @Override
    public java.lang.String toString() {
        return "[lowerAbsoluteValue=" + lowerAbsoluteValue + ",upperAbsoluteValue=" + upperAbsoluteValue + ",lowerScaledValue=" + lowerScaledValue
                + ",upperScaledValue=" + upperScaledValue + ",increment=" + increment + ",calType=" + calType + "]";
    }
}
