package org.devtcg.asyncservice;


import android.app.Service;
import android.content.Intent;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class AsyncService extends Service
{
    private static final String TAG = "AsyncService";
    private boolean mDirectionUp = true;

    @Override
    public void onCreate()
    {
        Log.d(TAG, "onCreate");
    }

    @Override
    public void onDestroy()
    {
        /* TODO: Of course we would need to clean up. */
        Log.d(TAG, "onDestroy");
    }

    public IBinder getBinder()
    {
        return mBinder;
    }

    private final IAsyncService.Stub mBinder = new IAsyncService.Stub()
    {
        public void setDirectionUp()
        {
            mDirectionUp = true;
        }
        public void setDirectionDown()
        {
            mDirectionUp = false;
        }

        public void startCount(final int to, final IAsyncServiceCounter callback)
        {
            Thread t = new Thread()
            {
                /* Survives interruption, but not otherwise more precise. */
                public void preciseSleep(long millis)
                {
                    long endTime = System.currentTimeMillis() + millis;

                    do {
                        try
                        {
                            Thread.sleep(endTime - System.currentTimeMillis());
                        }
                        catch (InterruptedException e)
                        {}
                    } while (System.currentTimeMillis() < endTime);
                }

                public void run()
                {
                    int i = 1;
                    while ((i >= 0) && (i <= to))
//                    for (int i = 1; i <= to; i++)
                    {
                        preciseSleep(1000);

                        try
                        {
                            callback.handleCount(i);
                        }
                        catch (DeadObjectException e)
                        {
                            Log.d(TAG, "Dead peer, aborting...", e);
                            break;
                        } catch (RemoteException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        if (mDirectionUp) i += 1; else i -= 1;
                    }

                }
            };

            t.start();
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        // Select the interface to return.  If your service only implements
        // a single interface, you can just return it here without checking
        // the Intent.
        if (IAsyncService.class.getName().equals(intent.getAction())) {
            return mBinder;
        }
        return null;
    }

}
