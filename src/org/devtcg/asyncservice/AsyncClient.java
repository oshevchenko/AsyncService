package org.devtcg.asyncservice;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class AsyncClient extends Activity
{
    private static final String TAG = "AsyncClient";

    private Handler mHandler = new Handler();
    private IAsyncService mService;

    private TextView mCounterText;

    private boolean mCounting = false;

    private boolean mCountUp = true;
    private boolean mIsBound = false;
    private Button b;


    @Override
    public void onCreate(Bundle icicle)
    {
        super.onCreate(icicle);
        setContentView(R.layout.main);

        mCounterText = (TextView)findViewById(R.id.counter);

        b = (Button)findViewById(R.id.start);
        b.setOnClickListener(mFire);

        doBindService();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        doUnbindService();
    }


    protected OnClickListener mFire = new OnClickListener()
    {
        public void onClick(View view)
        {
            if (mService == null)
            {
                Log.d(TAG, "Nothing to do!");
                return;
            }

            if (mCounting == true)
            {
                try
                {
                    if (mCountUp)
                    {
                        mService.setDirectionDown();
                        mCountUp = false;
                        b.setText("Start countup");
                    }
                    else
                    {
                        mService.setDirectionUp();
                        mCountUp = true;
                        b.setText("Start countdown");
                    }

                }
                catch (DeadObjectException e)
                {
                    mCounting = false;
                    Log.d(TAG, Log.getStackTraceString(e));
                } catch (RemoteException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                /* This would be simple to work around, if you're curious. */
                Log.d(TAG, "mCounter is implemented globally and cannot be reused while counting is in progress.");
                return;
            }

            try
            {
                mCounting = true;
                mCountUp = true;
                mService.setDirectionUp();
                mService.startCount(10, mCounter);
                Log.d(TAG, "Counting has begun...");
                b.setText("Start countdown");
                mCountUp = true;
            }
            catch (DeadObjectException e)
            {
                mCounting = false;
                Log.d(TAG, Log.getStackTraceString(e));
            } catch (RemoteException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    };

    private ServiceConnection mConnection = new ServiceConnection()
    {
        public void onServiceConnected(ComponentName className, IBinder service)
        {
            Log.d(TAG, "onServiceConnected");
            mService = IAsyncService.Stub.asInterface(service);
        }

        public void onServiceDisconnected(ComponentName className)
        {
            Log.d(TAG, "onServiceDisconnected");
            mService = null;
        }
    };

    private IAsyncServiceCounter.Stub mCounter = new IAsyncServiceCounter.Stub()
    {
        public void handleCount(final int n)
        {
            Log.d(TAG, "handleCount(" + n + ")");

            mHandler.post(new Runnable()
            {
                public void run()
                {
                    if ((n == 10) || (n == 0))
                    {
                        b.setText("Start Counting!");
                        mCounterText.setText("Done!");
                        mCounting = false;
                    }
                    else
                        mCounterText.setText(String.valueOf(n));
                }
            });
        }
    };
    void doBindService() {
        // Establish a connection with the service.  We use an explicit
        // class name because we want a specific service implementation that
        // we know will be running in our own process (and thus won't be
        // supporting component replacement by other applications).
        bindService(new Intent(IAsyncService.class.getName()), mConnection,
                Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    void doUnbindService() {
        if (mIsBound) {
            // Detach our existing connection.
            unbindService(mConnection);
            mIsBound = false;
        }
    }
}
