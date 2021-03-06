package drivers.philips.intellivue.dataexport.impl;

import java.nio.ByteBuffer;

import common.io.util.Bits;
import drivers.philips.intellivue.dataexport.CommandType;
import drivers.philips.intellivue.dataexport.DataExportCommand;
import drivers.philips.intellivue.dataexport.DataExportInvoke;
import drivers.philips.intellivue.dataexport.RemoteOperation;
import drivers.philips.intellivue.dataexport.command.CommandFactory;
import drivers.philips.intellivue.util.Util;

public class DataExportInvokeImpl implements DataExportInvoke {

    private int invokeId;
    private CommandType commandType;

    private DataExportCommand command;

    @Override
    public void setCommandType(CommandType commandType) {
        this.commandType = commandType;
    }

    @Override
    public int getInvoke() {
        return invokeId;
    }

    @Override
    public void setInvoke(int i) {
        this.invokeId = i;
    }

    @SuppressWarnings("unused")
    @Override
    public void parse(ByteBuffer bb) {
        invokeId = Bits.getUnsignedShort(bb);
        commandType = CommandType.valueOf(Bits.getUnsignedShort(bb));
        int length = Bits.getUnsignedShort(bb);
        command = CommandFactory.buildCommand(commandType, false);
        command.setMessage(this);
        command.parse(bb);

    }

    @Override
    public void format(ByteBuffer bb) {
        Bits.putUnsignedShort(bb, invokeId);
        Bits.putUnsignedShort(bb, commandType.asInt());

        Util.PrefixLengthShort.write(bb, command);

    }

    @Override
    public CommandType getCommandType() {
        return commandType;
    }

    @Override
    public void setCommand(DataExportCommand dec) {
        this.command = dec;
    }

    @Override
    public DataExportCommand getCommand() {
        return command;
    }

    @Override
    public String toString() {
        return "[invokeId=" + invokeId + ",commandType=" + commandType + ",command=" + command + "]";
    }

    @Override
    public RemoteOperation getRemoteOperation() {
        return RemoteOperation.Invoke;
    }
}
