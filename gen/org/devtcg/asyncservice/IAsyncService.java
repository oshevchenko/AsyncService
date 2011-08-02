/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /home/CORPUSERS/23060024/workspace/Tutorial/AsyncService/src/org/devtcg/asyncservice/IAsyncService.aidl
 */
package org.devtcg.asyncservice;
public interface IAsyncService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements org.devtcg.asyncservice.IAsyncService
{
private static final java.lang.String DESCRIPTOR = "org.devtcg.asyncservice.IAsyncService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an org.devtcg.asyncservice.IAsyncService interface,
 * generating a proxy if needed.
 */
public static org.devtcg.asyncservice.IAsyncService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = (android.os.IInterface)obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof org.devtcg.asyncservice.IAsyncService))) {
return ((org.devtcg.asyncservice.IAsyncService)iin);
}
return new org.devtcg.asyncservice.IAsyncService.Stub.Proxy(obj);
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
case TRANSACTION_startCount:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
org.devtcg.asyncservice.IAsyncServiceCounter _arg1;
_arg1 = org.devtcg.asyncservice.IAsyncServiceCounter.Stub.asInterface(data.readStrongBinder());
this.startCount(_arg0, _arg1);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements org.devtcg.asyncservice.IAsyncService
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
/* Start the asynchronous counting sequence.  The service will count to `to', pausing
	 * 1 second between each interval. */
public void startCount(int to, org.devtcg.asyncservice.IAsyncServiceCounter callback) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(to);
_data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_startCount, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_startCount = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
/* Start the asynchronous counting sequence.  The service will count to `to', pausing
	 * 1 second between each interval. */
public void startCount(int to, org.devtcg.asyncservice.IAsyncServiceCounter callback) throws android.os.RemoteException;
}
