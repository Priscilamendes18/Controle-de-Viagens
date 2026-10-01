package com.example.controledeviagens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


// Classe que representa uma viagem
class Viagem(
    val data: String,
    val kmInicial: Double,
    val kmFinal: Double,
    val litros: Double,
    val valorLitro: Double,
    val pedagio: Double
) {

    // Calcula a distância percorrida
    fun calcularDistancia(): Double {
        return kmFinal - kmInicial
    }

    // Calcula o custo do combustível
    fun calcularCustoCombustivel(): Double {
        return litros * valorLitro
    }

    // Calcula o custo total
    fun calcularCustoTotal(): Double {
        return calcularCustoCombustivel() + pedagio
    }

    // Calcula a média de consumo
    fun calcularMediaKmLiter(): Double {
        return if (litros > 0) {
            calcularDistancia() / litros
        } else {
            0.0
        }
    }
}


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            Scaffold(
                modifier = Modifier.fillMaxSize()
            ) { innerPadding ->

                // Campos do formulário
                var data by remember {
                    mutableStateOf("")
                }

                var kmInicial by remember {
                    mutableStateOf("")
                }

                var kmFinal by remember {
                    mutableStateOf("")
                }

                var litros by remember {
                    mutableStateOf("")
                }

                var valorLitro by remember {
                    mutableStateOf("")
                }

                var pedagio by remember {
                    mutableStateOf("")
                }

                // Lista de viagens
                val viagens = remember {
                    mutableStateListOf<Viagem>()
                }

                // Totais gerais
                var totalGastoGeral by remember {
                    mutableStateOf(0.0)
                }

                var totalKmGeral by remember {
                    mutableStateOf(0.0)
                }

                var totalLitrosGeral by remember {
                    mutableStateOf(0.0)
                }

                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(16.dp)
                        .verticalScroll(
                            rememberScrollState()
                        )
                ) {

                    // Título
                    Text(
                        text = "Controle de Viagens",
                        style = MaterialTheme.typography.headlineMedium
                    )

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    // Data
                    TextField(
                        value = data,
                        onValueChange = {
                            data = it
                        },
                        label = {
                            Text("Data")
                        },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    // KM Inicial
                    TextField(
                        value = kmInicial,
                        onValueChange = {
                            kmInicial = it
                        },
                        label = {
                            Text("KM Inicial")
                        },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    // KM Final
                    TextField(
                        value = kmFinal,
                        onValueChange = {
                            kmFinal = it
                        },
                        label = {
                            Text("KM Final")
                        },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    // Litros
                    TextField(
                        value = litros,
                        onValueChange = {
                            litros = it
                        },
                        label = {
                            Text("Litros de Combustível")
                        },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    // Valor do litro
                    TextField(
                        value = valorLitro,
                        onValueChange = {
                            valorLitro = it
                        },
                        label = {
                            Text("Valor do Litro (R$)")
                        },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    // Pedágio
                    TextField(
                        value = pedagio,
                        onValueChange = {
                            pedagio = it
                        },
                        label = {
                            Text("Valor do Pedágio (R$)")
                        },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )

                    // Botão para cadastrar
                    Button(
                        onClick = {

                            val kmInicialValor =
                                kmInicial.toDoubleOrNull() ?: 0.0

                            val kmFinalValor =
                                kmFinal.toDoubleOrNull() ?: 0.0

                            val litrosValor =
                                litros.toDoubleOrNull() ?: 0.0

                            val valorLitroValor =
                                valorLitro.toDoubleOrNull() ?: 0.0

                            val pedagioValor =
                                pedagio.toDoubleOrNull() ?: 0.0

                            // Cria a viagem
                            val novaViagem = Viagem(
                                data = data,
                                kmInicial = kmInicialValor,
                                kmFinal = kmFinalValor,
                                litros = litrosValor,
                                valorLitro = valorLitroValor,
                                pedagio = pedagioValor
                            )

                            // Adiciona à lista
                            viagens.add(novaViagem)

                            // Atualiza os totais
                            totalGastoGeral +=
                                novaViagem.calcularCustoTotal()

                            totalKmGeral +=
                                novaViagem.calcularDistancia()

                            totalLitrosGeral +=
                                novaViagem.litros

                            // Limpa os campos
                            data = ""
                            kmInicial = ""
                            kmFinal = ""
                            litros = ""
                            valorLitro = ""
                            pedagio = ""
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Cadastrar Viagem")
                    }

                    Spacer(
                        modifier = Modifier.height(24.dp)
                    )

                    // Lista de viagens
                    Text(
                        text = "Viagens Realizadas",
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    for (viagem in viagens) {

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        ) {

                            Column(
                                modifier = Modifier.padding(12.dp)
                            ) {

                                Text(
                                    "Data: ${viagem.data}"
                                )

                                Text(
                                    "Distância: %.2f km".format(
                                        viagem.calcularDistancia()
                                    )
                                )

                                Text(
                                    "Custo Combustível: R$ %.2f".format(
                                        viagem.calcularCustoCombustivel()
                                    )
                                )

                                Text(
                                    "Pedágio: R$ %.2f".format(
                                        viagem.pedagio
                                    )
                                )

                                Text(
                                    "Custo Total: R$ %.2f".format(
                                        viagem.calcularCustoTotal()
                                    )
                                )

                                Text(
                                    "Média: %.2f km/L".format(
                                        viagem.calcularMediaKmLiter()
                                    )
                                )
                            }
                        }
                    }

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )

                    HorizontalDivider()

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )

                    // Média geral
                    val mediaGeralKmLiter =
                        if (totalLitrosGeral > 0) {
                            totalKmGeral / totalLitrosGeral
                        } else {
                            0.0
                        }

                    // Resumo
                    Text(
                        text = "Resumo Geral",
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )

                    Text(
                        "Total KM percorrido: %.2f km".format(
                            totalKmGeral
                        )
                    )

                    Text(
                        "Total gasto: R$ %.2f".format(
                            totalGastoGeral
                        )
                    )

                    Text(
                        "Média geral de consumo: %.2f km/L".format(
                            mediaGeralKmLiter
                        )
                    )
                }
            }
        }
    }
}