package com.paydev.pagou.services

import android.content.Context
import android.content.Intent
import android.net.Uri
import java.net.URLEncoder

class WhatsAppService(private val context: Context) {
  fun send(contact: String, message: String) {
    val url = buildUrl(contact, message)
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(url)
    context.startActivity(intent)
  }

  private fun buildUrl(contact: String, message: String): String {
    val url = "https://wa.me"
    val encodedMessage = URLEncoder.encode(message, "UTF-8")
    return "$url/$contact?text=$encodedMessage"
  }
}