package com.example.controledeviagens

import android.content.ContentValues
import android.content.Context

class ViagemRepository(context: Context) {

    private val helper = BancoHelper(context.applicationContext)

    fun inserir(
        data: String,
        kmInicial: Double,
        kmFinal: Double,
        litros: Double,
        combustivel: String,
        valorLitro: Double,
        pedagio: Double
    ): Boolean {
        val db = helper.writableDatabase

        val valores = ContentValues().apply {
            put("data", data)
            put("km_inicial", kmInicial)
            put("km_final", kmFinal)
            put("litros", litros)
            put("combustivel", combustivel)
            put("valor_litro", valorLitro)
            put("pedagio", pedagio)
        }

        val resultado = db.insert(
            "viagem",
            null,
            valores
        )

        return resultado != -1L
    }

    fun listar(): List<Viagem> {
        val db = helper.readableDatabase
        val viagens = mutableListOf<Viagem>()

        val cursor = db.rawQuery(
            "SELECT * FROM viagem",
            null
        )

        cursor.use {
            while (it.moveToNext()) {
                val id = it.getInt(it.getColumnIndexOrThrow("id"))
                val data = it.getString(it.getColumnIndexOrThrow("data"))
                val kmInicial = it.getDouble(it.getColumnIndexOrThrow("km_inicial"))
                val kmFinal = it.getDouble(it.getColumnIndexOrThrow("km_final"))
                val litros = it.getDouble(it.getColumnIndexOrThrow("litros"))
                val combustivel = it.getString(it.getColumnIndexOrThrow("combustivel"))
                val valorLitro = it.getDouble(it.getColumnIndexOrThrow("valor_litro"))
                val pedagio = it.getDouble(it.getColumnIndexOrThrow("pedagio"))

                viagens.add(
                    Viagem(
                        id = id,
                        data = data,
                        kmInicial = kmInicial,
                        kmFinal = kmFinal,
                        litros = litros,
                        combustivel = combustivel,
                        valorLitro = valorLitro,
                        pedagio = pedagio
                    )
                )
            }
        }

        return viagens
    }
}
