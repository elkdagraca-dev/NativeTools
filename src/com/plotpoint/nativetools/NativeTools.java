package com.plotpoint.nativetools;

import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.ComponentContainer;

@DesignerComponent(
        version = 1,
        versionName = "1.0",
        description = "Ferramentas nativas simples para Android.",
        category = ComponentCategory.EXTENSION,
        nonVisible = true,
        iconName = "icon.png"
)
@SimpleObject(external = true)
public class NativeTools extends AndroidNonvisibleComponent {

    private final Context context;

    public NativeTools(ComponentContainer container) {
        super(container.$form());
        context = container.$context();
    }

    @SimpleFunction(
            description = "Retorna a versão do Android instalada no dispositivo."
    )
    public String GetAndroidVersion() {
        return Build.VERSION.RELEASE;
    }

    @SimpleFunction(
            description = "Retorna o modelo do dispositivo."
    )
    public String GetDeviceModel() {
        return Build.MODEL;
    }

    @SimpleFunction(
            description = "Faz o dispositivo vibrar durante o número de milissegundos informado."
    )
    public void Vibrate(int milliseconds) {

        if (milliseconds < 1) {
            return;
        }

        Vibrator vibrator =
                (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

        if (vibrator == null || !vibrator.hasVibrator()) {
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            VibrationEffect effect =
                    VibrationEffect.createOneShot(
                            milliseconds,
                            VibrationEffect.DEFAULT_AMPLITUDE
                    );

            vibrator.vibrate(effect);

        } else {

            vibrator.vibrate(milliseconds);
        }
    }
  }
