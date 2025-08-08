package coder.sentient.filecryptx.utility

import android.content.Context
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class AboutUs(private val context: Context) {

    fun showAboutUsDialog() {
        val aboutText = """
            Welcome to filecryptx, a powerful and secure file management and encryption app designed to keep your data safe and organized.

            About the Developer:
            My goal is to build innovative and efficient applications that enhance digital security and file management.
           
        """.trimIndent()

        val textView = TextView(context)
        textView.text = aboutText
        textView.setPadding(40, 20, 40, 20)
        textView.autoLinkMask = Linkify.EMAIL_ADDRESSES or Linkify.WEB_URLS
        textView.movementMethod = LinkMovementMethod.getInstance()

        val builder = AlertDialog.Builder(context)
        builder.setTitle("About")
            .setView(textView)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}