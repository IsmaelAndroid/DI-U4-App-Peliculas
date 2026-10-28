package com.dam.peliculas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dam.valoracion.Valoracion   // ⭐ el mismo componente que usa la app de recetas

/** Una película: la media del público (fija) y tu nota sobre 10 (la cambias tú). */
data class Pelicula(val titulo: String, val anio: Int, val mediaPublico: Int, val tuNota: Int = 0)

private val Rojo = Color(0xFFE53935)
private val Gris = Color(0xFF616161)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme(colorScheme = darkColorScheme()) {
                Surface(Modifier.fillMaxSize()) {
                    Cartelera()
                }
            }
        }
    }
}

@Composable
fun Cartelera() {
    val peliculas = remember {
        mutableStateListOf(
            Pelicula("El laberinto del fauno", 2006, mediaPublico = 4),
            Pelicula("Mar adentro", 2004, mediaPublico = 4),
            Pelicula("Ocho apellidos vascos", 2014, mediaPublico = 3),
            Pelicula("Campeones", 2018, mediaPublico = 4),
            Pelicula("Torrente", 1998, mediaPublico = 2),
        )
    }

    Column(Modifier.safeDrawingPadding()) {
        Text(
            "Cartelera",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp),
        )
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(peliculas.indices.toList(), key = { peliculas[it].titulo }) { i ->
                TarjetaPelicula(
                    pelicula = peliculas[i],
                    onNota = { nueva -> peliculas[i] = peliculas[i].copy(tuNota = nueva) },
                )
            }
        }
    }
}

@Composable
private fun TarjetaPelicula(pelicula: Pelicula, onNota: (Int) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF212121)),
    ) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                "${pelicula.titulo} (${pelicula.anio})",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
            )

            // ⭐ Uso 1: solo lectura (sin onValorCambiado), pequeño y en rojo
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Público  ", style = MaterialTheme.typography.bodySmall)
                Valoracion(
                    valor = pelicula.mediaPublico,
                    tamano = 16.dp,
                    colorActivo = Rojo,
                    colorInactivo = Gris,
                )
            }

            // ⭐ Uso 2: interactiva y sobre 10, cambiando las propiedades maximo y tamano
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Tu nota  ", style = MaterialTheme.typography.bodySmall)
                Valoracion(
                    valor = pelicula.tuNota,
                    maximo = 10,
                    tamano = 24.dp,
                    colorActivo = Color(0xFFFFD54F),
                    colorInactivo = Gris,
                    onValorCambiado = onNota,
                )
            }
            if (pelicula.tuNota > 0) {
                Text("Le has dado un ${pelicula.tuNota}", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun VistaPreviaCartelera() {
    MaterialTheme(colorScheme = darkColorScheme()) { Surface { Cartelera() } }
}
