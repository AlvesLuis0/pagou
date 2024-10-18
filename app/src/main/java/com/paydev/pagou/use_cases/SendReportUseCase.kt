package com.paydev.pagou.use_cases

import android.content.Context
import com.paydev.pagou.services.WhatsAppService

class SendReportUseCase (private val context: Context) {
    fun execute(id:Long) {
        val person = GetPersonReportUseCase(context).execute(id)
        val message = buildString {
            append("*${person.info.name}*\n")
            append("*Saldo:* R$ ${person.info.total}\n")
            if(person.info.total.isNegative()){
                append("_Saldo negativo_\n")
            } else if (person.info.total.isPositive()){
                append("_Saldo positivo_\n")
            } else{
                append("_Saldo zerado_\n")
            }
            for (transaction in person.transactions){
                transaction.description = transaction.description ?: "(Sem descrição)"
                if(transaction.isActive) {
                    append("* ${transaction.registeredAt}  |  ${transaction.description}  |  R$ ${transaction.value}\n")
                } else{
                    append("* ~${transaction.registeredAt}  |  ${transaction.description}  |  R$ ${transaction.value}~\n")
                }
            }
        }
        WhatsAppService(context).send(person.info.contact, message)
    }
}