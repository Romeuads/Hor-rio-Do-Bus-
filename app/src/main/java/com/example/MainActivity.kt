package com.example

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.OpenInBrowser
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.FrostedGlassCircle
import com.example.ui.theme.FrostedGlassPillBg
import com.example.ui.theme.FrostedGlassPillText
import com.example.ui.theme.FrostedGlassSurfaceLight
import com.example.ui.theme.FrostedGlassTextDark
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.theme.Purple40

enum class AppScreen {
  PRIMEIRA_TELA,
  SEGUNDA_TELA,
}

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      MyApplicationTheme {
        BusHorariosApp()
      }
    }
  }
}

@Composable
fun BusHorariosApp() {
  var currentScreen by rememberSaveable { mutableStateOf(AppScreen.PRIMEIRA_TELA) }

  AnimatedContent(
    targetState = currentScreen,
    transitionSpec = { fadeIn() togetherWith fadeOut() },
    label = "ScreenTransition",
  ) { screen ->
    when (screen) {
      AppScreen.PRIMEIRA_TELA -> {
        PrimeiraTela(onNavigateNext = { currentScreen = AppScreen.SEGUNDA_TELA })
      }
      AppScreen.SEGUNDA_TELA -> {
        SegundaTela(onNavigateBack = { currentScreen = AppScreen.PRIMEIRA_TELA })
      }
    }
  }
}

@Composable
fun PrimeiraTela(onNavigateNext: () -> Unit) {
  val context = LocalContext.current

  Box(modifier = Modifier.fillMaxSize()) {
    // Highway wallpaper background
    Image(
      painter = painterResource(id = R.drawable.img_highway_wallpaper),
      contentDescription = stringResource(R.string.background_description),
      modifier = Modifier.fillMaxSize(),
      contentScale = ContentScale.Crop,
    )

    // Frosted Glass dark gradient scrim overlay
    Box(
      modifier =
        Modifier.fillMaxSize()
          .background(
            Brush.verticalGradient(
              colors =
                listOf(
                  Color(0xCC1A1A2E), // Deep midnight frosted tint
                  Color(0x7716213E),
                  Color(0xDD0F3460),
                )
            )
          )
    )

    // Centered at the top: Title "Primeira Tela" and Button "Próxima tela"
    Column(
      modifier =
        Modifier.align(Alignment.TopCenter)
          .statusBarsPadding()
          .padding(top = 36.dp, start = 20.dp, end = 20.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      // Frosted glass title capsule
      Surface(
        color = Color(0x33FFFFFF),
        shape = RoundedCornerShape(24.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0x4DFFFFFF)),
        shadowElevation = 8.dp,
      ) {
        Text(
          text = stringResource(R.string.primeira_tela),
          style = MaterialTheme.typography.headlineMedium,
          fontWeight = FontWeight.Bold,
          letterSpacing = 0.5.sp,
          color = Color.White,
          textAlign = TextAlign.Center,
          modifier =
            Modifier.testTag("primeira_tela_title")
              .padding(horizontal = 28.dp, vertical = 10.dp),
        )
      }

      Spacer(modifier = Modifier.height(22.dp))

      // Button "Próxima tela" with Frosted Glass lavender pill aesthetic
      Button(
        onClick = onNavigateNext,
        shape = RoundedCornerShape(50),
        colors =
          ButtonDefaults.buttonColors(
            containerColor = FrostedGlassPillBg,
            contentColor = FrostedGlassPillText,
          ),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp),
        modifier =
          Modifier.testTag("proxima_tela_button")
            .heightIn(min = 48.dp)
            .shadow(10.dp, shape = RoundedCornerShape(50)),
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.Center,
          modifier = Modifier.padding(horizontal = 12.dp, vertical = 2.dp),
        ) {
          Text(
            text = stringResource(R.string.proxima_tela),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
          )
          Spacer(modifier = Modifier.width(8.dp))
          Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = null,
            tint = FrostedGlassPillText,
          )
        }
      }
    }

    // Bottom-left corner: Two frosted glass buttons, one below the other
    Column(
      modifier =
        Modifier.align(Alignment.BottomStart)
          .navigationBarsPadding()
          .padding(start = 24.dp, bottom = 28.dp),
      verticalArrangement = Arrangement.spacedBy(14.dp),
      horizontalAlignment = Alignment.Start,
    ) {
      // First button: "jotur" -> https://www.jotur.com.br/horarios/ (Frosted glass card)
      Surface(
        onClick = { openWebUrl(context, "https://www.jotur.com.br/horarios/") },
        shape = RoundedCornerShape(18.dp),
        color = Color(0x2EFFFFFF),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0x4DFFFFFF)),
        shadowElevation = 6.dp,
        modifier =
          Modifier.testTag("jotur_button")
            .heightIn(min = 52.dp)
            .widthIn(min = 190.dp),
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.SpaceBetween,
          modifier = Modifier.padding(horizontal = 18.dp, vertical = 12.dp),
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
          ) {
            Icon(
              imageVector = Icons.Default.DirectionsBus,
              contentDescription = null,
              tint = Color.White,
            )
            Text(
              text = stringResource(R.string.jotur).uppercase(),
              style = MaterialTheme.typography.bodyMedium,
              fontWeight = FontWeight.Bold,
              letterSpacing = 1.5.sp,
              color = Color.White,
            )
          }
          Spacer(modifier = Modifier.width(14.dp))
          Icon(
            imageVector = Icons.Default.OpenInBrowser,
            contentDescription = null,
            tint = Color(0xCCFFFFFF),
          )
        }
      }

      // Second button: "Consórcio Fenix" -> https://www.consorciofenix.com.br/horarios (Frosted glass card)
      Surface(
        onClick = { openWebUrl(context, "https://www.consorciofenix.com.br/horarios") },
        shape = RoundedCornerShape(18.dp),
        color = Color(0x2EFFFFFF),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0x4DFFFFFF)),
        shadowElevation = 6.dp,
        modifier =
          Modifier.testTag("consorcio_fenix_button")
            .heightIn(min = 52.dp)
            .widthIn(min = 190.dp),
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.SpaceBetween,
          modifier = Modifier.padding(horizontal = 18.dp, vertical = 12.dp),
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
          ) {
            Icon(
              imageVector = Icons.Default.DirectionsBus,
              contentDescription = null,
              tint = Color.White,
            )
            Text(
              text = stringResource(R.string.consorcio_fenix).uppercase(),
              style = MaterialTheme.typography.bodyMedium,
              fontWeight = FontWeight.Bold,
              letterSpacing = 1.2.sp,
              color = Color.White,
            )
          }
          Spacer(modifier = Modifier.width(14.dp))
          Icon(
            imageVector = Icons.Default.OpenInBrowser,
            contentDescription = null,
            tint = Color(0xCCFFFFFF),
          )
        }
      }
    }
  }
}

@Composable
fun SegundaTela(onNavigateBack: () -> Unit) {
  BackHandler(onBack = onNavigateBack)

  Surface(
    modifier = Modifier.fillMaxSize(),
    color = FrostedGlassSurfaceLight,
  ) {
    Box(modifier = Modifier.fillMaxSize()) {
      // Centered at the top: Title "Segunda Tela", Bus circle accent, and Button "voltar"
      Column(
        modifier =
          Modifier.align(Alignment.TopCenter)
            .statusBarsPadding()
            .padding(top = 36.dp, start = 20.dp, end = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        Text(
          text = stringResource(R.string.segunda_tela),
          style = MaterialTheme.typography.headlineMedium,
          fontWeight = FontWeight.SemiBold,
          color = FrostedGlassTextDark,
          textAlign = TextAlign.Center,
          modifier = Modifier.testTag("segunda_tela_title"),
        )

        Spacer(modifier = Modifier.height(36.dp))

        // Decorative frosted circular badge matching Design HTML
        Box(
          modifier =
            Modifier.padding(bottom = 36.dp)
              .shadow(12.dp, shape = CircleShape)
              .background(FrostedGlassCircle, shape = CircleShape)
              .border(
                4.dp,
                Color.White,
                shape = CircleShape,
              )
              .padding(40.dp),
          contentAlignment = Alignment.Center,
        ) {
          Icon(
            imageVector = Icons.Default.DirectionsBus,
            contentDescription = null,
            tint = Color(0x446750A4),
            modifier = Modifier.width(96.dp).height(96.dp),
          )
        }

        // Button "voltar" with Frosted Glass deep violet styling
        Button(
          onClick = onNavigateBack,
          shape = RoundedCornerShape(18.dp),
          colors =
            ButtonDefaults.buttonColors(
              containerColor = Purple40,
              contentColor = Color.White,
            ),
          elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp),
          modifier =
            Modifier.testTag("voltar_button")
              .heightIn(min = 52.dp)
              .widthIn(min = 180.dp)
              .shadow(8.dp, shape = RoundedCornerShape(18.dp)),
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 2.dp),
          ) {
            Icon(
              imageVector = Icons.AutoMirrored.Filled.ArrowBack,
              contentDescription = null,
              tint = Color.White,
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
              text = stringResource(R.string.voltar),
              style = MaterialTheme.typography.titleMedium,
              fontWeight = FontWeight.Medium,
            )
          }
        }
      }

      // Bottom footer text from theme design
      Text(
        text = stringResource(R.string.sistema_horarios_integrado),
        style = MaterialTheme.typography.bodySmall,
        fontWeight = FontWeight.Normal,
        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
        color = FrostedGlassTextDark.copy(alpha = 0.6f),
        textAlign = TextAlign.Center,
        modifier =
          Modifier.align(Alignment.BottomCenter)
            .navigationBarsPadding()
            .padding(bottom = 24.dp),
      )
    }
  }
}

private fun openWebUrl(context: Context, url: String) {
  try {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)
  } catch (e: Exception) {
    Toast.makeText(context, "Não foi possível abrir o link: $url", Toast.LENGTH_SHORT).show()
  }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
  Text(text = "Hello $name!", modifier = modifier)
}

@Preview(showBackground = true)
@Composable
fun PrimeiraTelaPreview() {
  MyApplicationTheme {
    PrimeiraTela(onNavigateNext = {})
  }
}

@Preview(showBackground = true)
@Composable
fun SegundaTelaPreview() {
  MyApplicationTheme {
    SegundaTela(onNavigateBack = {})
  }
}
