package com.archavez.eartraining;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

// Expone PackageManager.getPackageInfo().firstInstallTime — la fecha (epoch ms) en que esta
// app se instaló por primera vez en el dispositivo. No cambia con actualizaciones, solo con
// una desinstalación/reinstalación. Se usa para reconocer a usuarios que ya pagaron la app
// cuando era de pago único, comparando contra la fecha en que la ficha pasó a Gratis
// (ver SUBSCRIPTION_CUTOVER en www/index.html).
@CapacitorPlugin(name = "InstallInfo")
public class InstallInfoPlugin extends Plugin {
    @PluginMethod
    public void getFirstInstallTime(PluginCall call) {
        try {
            PackageManager pm = getContext().getPackageManager();
            PackageInfo info = pm.getPackageInfo(getContext().getPackageName(), 0);
            JSObject r = new JSObject();
            r.put("firstInstallTime", info.firstInstallTime);
            call.resolve(r);
        } catch (Exception e) {
            call.reject(e.getMessage());
        }
    }
}
