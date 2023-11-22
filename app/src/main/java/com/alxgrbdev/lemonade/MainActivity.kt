package com.alxgrbdev.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.alxgrbdev.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonApp()
            }
        }
    }
}

@Composable
fun LemonApp() {
    // Current step the app is displaying (remember allows the state to be retained
    // across recompositions).
    var currentStep by remember { mutableStateOf(1) }

    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when (currentStep) {
            1 -> {
                LemonCard(
                    actionText = stringResource(id = R.string.lemon_tree),
                    image = painterResource(id = R.drawable.lemon_tree),
                    onImageClicked = { currentStep++ }
                )
            }
            2 -> {
                LemonCard(
                    actionText = stringResource(id = R.string.lemon),
                    image = painterResource(id = R.drawable.lemon_squeeze),
                    onImageClicked = { currentStep++ }
                )
            }
            3 -> {
                LemonCard(
                    actionText = stringResource(id = R.string.glass_of_lemonade),
                    image = painterResource(id = R.drawable.lemon_drink),
                    onImageClicked = { currentStep++ }
                )
            }
            4 -> {
                LemonCard(
                    actionText = stringResource(id = R.string.empty_glass),
                    image = painterResource(id = R.drawable.lemon_restart),
                    onImageClicked = { currentStep = 1 }
                )
            }
        }
    }
}

@Composable
fun LemonCard(
    actionText: String,
    image: Painter,
    onImageClicked: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Button(
            onClick = onImageClicked,
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.button_corner_radius)),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
        ) {
            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier
                    .width(dimensionResource(R.dimen.button_image_width))
                    .height(dimensionResource(R.dimen.button_image_height))
                    .padding(dimensionResource(R.dimen.button_interior_padding))
            )
        }
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_vertical)))
        Text(
            text = actionText,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        LemonApp()
    }
}