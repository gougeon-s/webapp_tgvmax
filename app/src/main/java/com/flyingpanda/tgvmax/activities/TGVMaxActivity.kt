package com.flyingpanda.tgvmax.activities

import android.content.Context
import android.net.Uri
import android.util.Log
import android.webkit.WebView
import com.flyingpanda.tgvmax.webapp.WebAppActivity


class TGVMaxActivity : WebAppActivity() {

    override fun getHomeUrl(): String {
        return "https://www.tgvmax.fr/VSC/fr-FR/reservation"
    }

    override fun isInternalUrl(url: String?): Boolean {
        val internal = arrayOf("www.tgvmax.fr","happycard.force.com","www.oui.sncf","oui.sncf")
        if(Uri.parse(url).host in internal ) return true

        Log.d("InternalUrl","Not in scope : $url")
        return false
    }

    override fun restoreUrl(): String {
        return homeUrl
    }

    override fun pageFinished(view: WebView?, url: String?) {
        Log.i("URL",url)
        when{
            url!!.startsWith("https://happycard.force.com/SiteLogin") -> {
                val prefs = getSharedPreferences("Config",Context.MODE_PRIVATE)
                val username = prefs.getString("username", "")
                val password = prefs.getString("password", "")
                if(username!!.isEmpty()){
                    Log.i("Submit","empty username")
                }
                if(password!!.isEmpty()){
                    Log.i("Submit","empty password")
                }
                if(username!!.isNotEmpty() && password!!.isNotEmpty()) {
                    webView.evaluateJavascript(
                        "document.getElementById('loginPage:SiteTemplate:formulaire:login-field').value = '$username'", // Self XSS ...
                        null
                    )
                    webView.evaluateJavascript(
                        "document.getElementById('loginPage:SiteTemplate:formulaire:password-field').value = '$password'", // Self XSS ...
                        null
                    )
                }else {
                    Log.i("Submit","username or password empty")
                }
            }
        }
        super.pageFinished(view, url)
    }
}
