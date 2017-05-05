package wb.app.seek.modules.customtabs;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import wb.app.seek.modules.webview.WebViewActivity;

/**
 * Created by W.b on 2017/2/17.
 */
public class WebViewFallback implements CustomTabActivityHelper.CustomTabFallback {

    @Override
    public void openUri(Activity activity, Uri uri) {
        Intent intent = new Intent(activity, WebViewActivity.class);
        intent.putExtra(WebViewActivity.INTENT_KEY_URL, uri.toString());
        activity.startActivity(intent);
    }
}
