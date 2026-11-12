package id.toko.pos;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.core.content.FileProvider;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import java.io.File;

/** Kirim gambar struk langsung ke WhatsApp (tanpa menu pilih aplikasi). */
@CapacitorPlugin(name = "WaShare")
public class WaSharePlugin extends Plugin {

    @PluginMethod
    public void share(PluginCall call) {
        String uriStr = call.getString("uri");
        String phone = call.getString("phone", "");
        String text = call.getString("text", "");
        if (uriStr == null) { call.reject("uri kosong"); return; }
        try {
            Context ctx = getContext();
            File f = new File(Uri.parse(uriStr).getPath());
            Uri uri = FileProvider.getUriForFile(ctx, ctx.getPackageName() + ".fileprovider", f);
            String[] pkgs = {"com.whatsapp", "com.whatsapp.w4b"};
            for (String pkg : pkgs) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("image/png");
                i.putExtra(Intent.EXTRA_STREAM, uri);
                if (text != null && !text.isEmpty()) i.putExtra(Intent.EXTRA_TEXT, text);
                if (phone != null && !phone.isEmpty()) i.putExtra("jid", phone + "@s.whatsapp.net");
                i.setPackage(pkg);
                i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_ACTIVITY_NEW_TASK);
                try {
                    ctx.startActivity(i);
                    call.resolve(new JSObject().put("pkg", pkg));
                    return;
                } catch (android.content.ActivityNotFoundException e) { /* coba paket berikutnya */ }
            }
            call.reject("NO_WA: WhatsApp not installed");
        } catch (Exception e) {
            call.reject(String.valueOf(e.getMessage()));
        }
    }
}
