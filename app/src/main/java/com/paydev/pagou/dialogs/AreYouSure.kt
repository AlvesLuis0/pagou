package com.paydev.pagou.dialogs

import android.app.AlertDialog.Builder
import android.content.Context

class AreYouSure(context: Context, callback: () -> Unit): Builder(context) {
  init {
    setTitle("Deseja mesmo fazer isso? (Ação irreversível)")
    setNegativeButton("não") { _, _ -> }
    setPositiveButton("sim") { _, _ -> callback() }
  }
}