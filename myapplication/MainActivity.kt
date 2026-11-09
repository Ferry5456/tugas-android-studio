package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

// Data class dengan activePeriod dan story
data class Artwork(
    val imageRes: Int,
    val title: String,
    val story: String,
    val activePeriod: String
)

@Composable
fun ArtSpaceApp() {
    // Daftar 7 kuda legendaris sesuai permintaan
    val artworks = listOf(
        Artwork(
            imageRes = R.drawable.staygold,
            title = "Stay Gold",
            story = "Stay Gold (ステイゴールド) adalah kuda pacu ras murni asal Jepang (1994–2015) yang sangat terkenal karena kepribadiannya yang berapi-api, kisah perjuangannya sebagai underdog, dan kemenangan bersejarah di balapan terakhirnya yang menjadikannya pejantan legendaris.",
            activePeriod = "1996 - 2001"
        ),
        Artwork(
            imageRes = R.drawable.dream_journey,
            title = "Dream Journey",
            story = "Kakak tiri dari Orfevre yang memiliki tubuh relatif kecil namun memiliki tenaga penutup (schluss) yang sangat luar biasa, menjuarai Arima Kinen dan Takarazuka Kinen.",
            activePeriod = "2006 - 2010"
        ),
        Artwork(
            imageRes = R.drawable.orfevre,
            title = "Orfevre",
            story = "Juara Triple Crown Jepang ke-7 yang memiliki bakat luar biasa namun dikenal punya tabiat liar dan 'suka iseng' kepada joki maupun lawannya di lintasan balap.",
            activePeriod = "2010 - 2013"
        ),
        Artwork(
            imageRes = R.drawable.nakayama_festa,
            title = "Nakayama Festa",
            story = "Kuda tangguh spesialis trek berat yang hampir mencetak sejarah luar biasa dengan menjadi runner-up di ajang bergengsi Prix de l'Arc de Triomphe.",
            activePeriod = "2008 - 2011"
        ),
        Artwork(
            imageRes = R.drawable.gold_ship,
            title = "Gold Ship",
            story = "Kuda ikonik yang dicintai fans karena kepribadiannya yang eksentrik, suka 'melakukan prank' di gerbang start, namun memiliki lari luar biasa cepat layaknya roket.",
            activePeriod = "2011 - 2015"
        ),
        Artwork(
            imageRes = R.drawable.fenomeno,
            title = "Fenomeno",
            story = "Juara Tenno Sho (Spring) dua kali berturut-turut yang dikenal karena ketahanan fisik dan konsistensinya di kelas jarak jauh.",
            activePeriod = "2011 - 2015"
        ),
        Artwork(
            imageRes = R.drawable.oju_chosan,
            title = "Oju Chosan",
            story = "Raja lintasan rintangan (Steeplechase) legendaris Jepang yang mendominasi Grand Jump dengan rekor kemenangan beruntun yang hampir tak terkalahkan.",
            activePeriod = "2014 - 2022"
        )
    )

    var currentIndex by remember { mutableIntStateOf(0) }
    val currentArtwork = artworks[currentIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // 1. Frame Gambar Kuda (Artwork Wall)
        ArtworkWall(
            imageRes = currentArtwork.imageRes,
            title = currentArtwork.title,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 2. Kotak Informasi (Artwork Descriptor)
        ArtworkDescriptor(
            title = currentArtwork.title,
            story = currentArtwork.story,
            activePeriod = currentArtwork.activePeriod,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 3. Tombol Navigasi (Display Controller)
        DisplayController(
            onPreviousClick = {
                currentIndex = if (currentIndex > 0) {
                    currentIndex - 1
                } else {
                    artworks.size - 1
                }
            },
            onNextClick = {
                currentIndex = if (currentIndex < artworks.size - 1) {
                    currentIndex + 1
                } else {
                    0
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ArtworkWall(
    imageRes: Int,
    title: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.shadow(
            elevation = 8.dp,
            shape = RoundedCornerShape(12.dp)
        ),
        color = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(12.dp)
    ) {
        Box(
            modifier = Modifier.padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
        }
    }
}

@Composable
fun ArtworkDescriptor(
    title: String,
    story: String,
    activePeriod: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Judul Kuda
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(6.dp))

            // Cerita / Story
            Text(
                text = story,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Periode Aktif
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Periode Aktif: ",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = activePeriod,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun DisplayController(
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Button(
            onClick = onPreviousClick,
            modifier = Modifier
                .weight(1f)
                .height(48.dp)
        ) {
            Text(text = "Previous", fontSize = 16.sp)
        }

        Button(
            onClick = onNextClick,
            modifier = Modifier
                .weight(1f)
                .height(48.dp)
        ) {
            Text(text = "Next", fontSize = 16.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpaceAppPreview() {
    MaterialTheme {
        ArtSpaceApp()
    }
}