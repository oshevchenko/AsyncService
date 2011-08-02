/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /home/CORPUSERS/23060024/workspace/Tutorial/AsyncService/src/org/devtcg/asyncservice/IAsyncServiceCounter.aidl
 */
package org.devtcg.asyncservice;
public interface IAsyncServiceCounter extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements org.devtcg.asyncservice.IAsyncServiceCounter
{
private static final java.lang.String DESCRIPTOR = "org.devtcg.asyncservice.IAsyncServiceCounter";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an org.devtcg.asyncservice.IAsyncServiceCounter interface,
 * generating a proxy if needed.
 */
public static org.devtcg.asyncservice.IAsyncServiceCounter asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = (android.os.IInterface)obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof org.devtcg.asyncservice.IAsyncServiceCounter))) {
return ((org.devtcg.asyncservice.IAsyncServiceCounter)iin);
}
return new org.devtcg.asyncservice.IAsyncServiceCounter.Stub.Proxy(obj);
}
public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_handleCount:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.handleCount(_arg0);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements org.devtcg.asyncservice.IAsyncServiceCounter
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
public void handleCount(int n) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(n);
mRemote.transact(Stub.TRANSACTION_handleCount, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_handleCount = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public void handleCount(int n) throws android.os.RemoteException;
}
