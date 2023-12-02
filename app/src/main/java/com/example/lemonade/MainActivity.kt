package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonadeMain()
            }
        }
    }
}

@Composable
fun LemonadeMain(modifier: Modifier = Modifier) {

    var nClicks by remember { mutableStateOf(1) }
    var clicksLemon by remember { mutableStateOf(0) }
    Text(
        text = stringResource(id = R.string.app_name),
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = modifier
            .background(color = Color.Yellow)
            .fillMaxWidth()
            .padding(25.dp)
    )
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        when (nClicks) {
            1 -> ImageAndDescription(
                idImage = R.drawable.lemon_tree,
                idDescriptionImage = R.string.lemon_tree_desc,
                idDescriptionText = R.string.lemon_tree,
                clicks = {
                    nClicks = 2
                    clicksLemon = (2..4).random()
                }
            )

            2 -> ImageAndDescription(
                idImage = R.drawable.lemon_squeeze,
                idDescriptionImage = R.string.lemon_desc,
                idDescriptionText = R.string.lemon_tap,
                clicks = {
                    clicksLemon = when {
                        clicksLemon > 0 -> clicksLemon - 1
                        else -> clicksLemon
                    }
                    if (clicksLemon == 0) {
                        nClicks = 3
                    }
                }
            )

            3 -> ImageAndDescription(
                idImage = R.drawable.lemon_drink,
                idDescriptionImage = R.string.lemon_drink_desc,
                idDescriptionText = R.string.lemon_drink,
                clicks = {
                    nClicks = 4

                }
            )

            4 -> ImageAndDescription(
                idImage = R.drawable.lemon_restart,
                idDescriptionImage = R.string.empty_glass_desc,
                idDescriptionText = R.string.empty_glass,
                clicks = {
                    nClicks = 1
                }
            )
        }
    }
}

@Composable
fun ImageAndDescription(
    modifier: Modifier = Modifier,
    idImage: Int,
    idDescriptionImage: Int,
    idDescriptionText: Int,
    clicks: () -> Unit
) {
    Button(
        onClick = clicks,
        //30dp porque cuadra mas con lo que muestra la imagen del codelab
        shape = RoundedCornerShape(30.dp),
        //Oro tipo de color por la misma razon que el borde y porque con el color que
        //daba el codelab, la imagen 4 no se veia
        colors = ButtonDefaults.buttonColors(containerColor = Color(186, 239, 209))
    ) {
        Image(
            painter = painterResource(id = idImage),
            contentDescription = stringResource(id = idDescriptionImage),
            modifier = modifier
                .wrapContentSize()
        )
    }
    Spacer(modifier = modifier.height(16.dp))

    Text(
        text = stringResource(id = idDescriptionText),
        fontSize = 18.sp,
        modifier = modifier
    )

}

@Preview(showBackground = true)
@Composable
fun LemonadePreview() {
    LemonadeTheme {
        LemonadeMain()
    }
}