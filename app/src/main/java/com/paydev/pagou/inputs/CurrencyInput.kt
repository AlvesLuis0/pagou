package com.paydev.pagou.inputs

import android.text.InputFilter
import android.text.Spanned

class CurrencyInput : InputFilter {
  override fun filter(source: CharSequence?, start: Int, end: Int, dest: Spanned?, dstart: Int, dend: Int): CharSequence? {
    if (source == null) return null

    // Concatena o texto resultante da inserção
    val newValue = dest.toString().substring(0, dstart) + source + dest.toString().substring(dend)

    // Permitir valores vazios
    if (newValue.isEmpty()) return null

    // Verificar se o valor é um número válido
    try {
      // Limitar para 2 casas decimais
      if (newValue.contains(".")) {
        val split = newValue.split(".")

        // Verifica se há mais de 2 dígitos após o ponto decimal
        if (split.size > 1 && split[1].length > 2) {
          return ""
        }
      }
    } catch (e: NumberFormatException) {
      // Se não for um número válido, bloqueia a entrada
      return ""
    }

    return null // Permitir a inserção
  }
}