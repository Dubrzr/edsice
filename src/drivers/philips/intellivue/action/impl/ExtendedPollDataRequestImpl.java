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
package drivers.philips.intellivue.action.impl;

import java.nio.ByteBuffer;

import common.io.util.Bits;
import drivers.philips.intellivue.action.ExtendedPollDataRequest;
import drivers.philips.intellivue.data.AttributeValueList;
import drivers.philips.intellivue.data.OIDType;
import drivers.philips.intellivue.data.Type;
import drivers.philips.intellivue.dataexport.command.ActionResultInterface;

/**
 * @author Jeff Plourde
 *
 */
public class ExtendedPollDataRequestImpl implements ExtendedPollDataRequest {
    private int pollNumber;
    private final Type polledObjectType = new Type();
    private OIDType polledAttributeGroup = OIDType.lookup(0);
    private final AttributeValueList pollExtAttr = new AttributeValueList();

    private ActionResultInterface action;

    @Override
    public ActionResultInterface getAction() {
        return this.action;
    }

    @Override
    public void setAction(ActionResultInterface action) {
        this.action = action;
    }

    @Override
    public void parse(ByteBuffer bb) {
        pollNumber = Bits.getUnsignedShort(bb);
        polledObjectType.parse(bb);
        polledAttributeGroup = OIDType.parse(bb);
        pollExtAttr.parse(bb);
    }

    @Override
    public void format(ByteBuffer bb) {
        Bits.putUnsignedShort(bb, pollNumber);
        polledObjectType.format(bb);
        polledAttributeGroup.format(bb);
        pollExtAttr.format(bb);

    }

    @Override
    public void parseMore(ByteBuffer bb) {
        parse(bb);
    }

    @Override
    public String toString() {
        return "[pollNumber=" + pollNumber + ",polledObjectType=" + polledObjectType + ",oidType=" + polledAttributeGroup + ",pollExtAttr="
                + pollExtAttr + "]";
    }

    @Override
    public OIDType getPolledAttributeGroup() {
        return polledAttributeGroup;
    }

    @Override
    public Type getPolledObjectType() {
        return polledObjectType;
    }

    @Override
    public int getPollNumber() {
        return pollNumber;
    }

    @Override
    public void setPolledAttributeGroup(OIDType lookup) {
        this.polledAttributeGroup = lookup;
    }

    @Override
    public void setPollNumber(int pollNumber) {
        this.pollNumber = pollNumber;
    }

    @Override
    public AttributeValueList getPollExtra() {
        return pollExtAttr;
    }
}
