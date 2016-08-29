package tech.boshu.changjiangshidai.ui.activity.home;

import android.app.Dialog;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View.OnClickListener;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

import java.io.IOException;
import java.util.Vector;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;
import tech.boshu.changjiangshidai.libs.utils.ToastUtils;
import tech.boshu.changjiangshidai.zxing.CameraManager;
import tech.boshu.changjiangshidai.zxing.CaptureActivityHandler;
import tech.boshu.changjiangshidai.zxing.InactivityTimer;
import tech.boshu.changjiangshidai.zxing.ViewfinderView;


/**
 * 扫描页面
 */
public class CaptureActivity extends BaseActivity implements Callback, OnClickListener {
    private ViewfinderView viewfinderView;
    private boolean mVibrate;
    private boolean mPlayBeep;
    private boolean mHasSurface;
    private String mCharacterSet;
    private Vector<BarcodeFormat> mDecodeFormats;
    private MediaPlayer mMediaPlayer;
    private CaptureActivityHandler mHandler;
    private InactivityTimer mInactivityTimer;
    private final float BEEP_VOLUME = 0.10f;
    private final long VIBRATE_DURATION = 200L;
    private final long DELAYMILLIS = 2000;


    private final OnCompletionListener beepListener = new OnCompletionListener() {
        public void onCompletion(MediaPlayer mediaPlayer) {
            mediaPlayer.seekTo(0);
        }
    };
    private int status;
    private Dialog mDialogLoading;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPage();
    }

    @Override
    protected void setLayoutResId() {
        layoutResId = R.layout.activity_capture;
    }

    /**
     * 初始化页面
     */
    private void initPage() {
        CameraManager.init(getApplication());
        viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);
        mHasSurface = false;
        mInactivityTimer = new InactivityTimer(this);
        initTitle("商品条形码扫描");
    }


    @Override
    public void onResume() {
        super.onResume();
        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
        SurfaceHolder surfaceHolder = surfaceView.getHolder();

        if (mHasSurface) {
            initCamera(surfaceHolder);
        } else {
            surfaceHolder.addCallback(this);
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }

        mDecodeFormats = null;
        mCharacterSet = null;
        mPlayBeep = true;
        AudioManager audioService = (AudioManager) getSystemService(AUDIO_SERVICE);

        if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
            mPlayBeep = false;
        }

        initBeepSound();
        mVibrate = true;
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mHandler != null) {
            mHandler.quitSynchronously();
            mHandler = null;
        }

        CameraManager.get().closeDriver();
    }

    @Override
    protected void onDestroy() {
        mInactivityTimer.shutdown();
        super.onDestroy();
    }

    private void initCamera(SurfaceHolder surfaceHolder) {
        try {
            CameraManager.get().openDriver(surfaceHolder);
        } catch (Exception e) {
            ToastUtils.show(this, "请确保摄像头完好并开启摄像头权限，或者手动输入");
            return;
        }
        if (mHandler == null) {
            mHandler = new CaptureActivityHandler(this, mDecodeFormats, mCharacterSet);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        if (!mHasSurface) {
            mHasSurface = true;
            initCamera(holder);
        }

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mHasSurface = false;

    }

    public ViewfinderView getViewfinderView() {
        return viewfinderView;
    }

    public Handler getHandler() {
        return mHandler;
    }

    public void drawViewfinder() {
        viewfinderView.drawViewfinder();

    }

    public void handleDecode(Result result, Bitmap barcode) {
        mInactivityTimer.onActivity();
        viewfinderView.drawResultBitmap(barcode);
        playBeepSoundAndVibrate();
        if (result != null) {
            final String resultString = result.getText().trim();
            if (!TextUtils.isEmpty(resultString)) {

            }
            if (mHandler != null) {
                mHandler.sendEmptyMessageDelayed(R.id.restart_preview, DELAYMILLIS);
            }
        }
    }

    private void initBeepSound() {

        if (mPlayBeep && mMediaPlayer == null) {
            setVolumeControlStream(AudioManager.STREAM_MUSIC);// The volume on
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.setOnCompletionListener(beepListener);
            AssetFileDescriptor file = getResources().openRawResourceFd(R.raw.beep);

            try {
                mMediaPlayer.setDataSource(file.getFileDescriptor(), file.getStartOffset(), file
                        .getLength());
                file.close();
                mMediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
                mMediaPlayer.prepare();
            } catch (IOException e) {
                mMediaPlayer = null;
            }

        }

    }

    private void playBeepSoundAndVibrate() {
        if (mPlayBeep && mMediaPlayer != null) {
            mMediaPlayer.start();
        }
        if (mVibrate) {
            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            vibrator.vibrate(VIBRATE_DURATION);
        }
    }
}
