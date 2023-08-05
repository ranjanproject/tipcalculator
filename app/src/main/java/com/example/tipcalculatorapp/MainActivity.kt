package com.example.tipcalculatorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.tipcalculatorapp.ui.theme.TipCalculatorAppTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.lang.NumberFormatException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipCalculatorAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TipCalculator()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TipCalculator(modifier: Modifier = Modifier){

    var amount by remember {
       mutableStateOf("0")
    }

    Column(verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Text(text = "Calculate Tip", modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp), textAlign = TextAlign.Left)
        Spacer(modifier = Modifier.padding(10.dp))

        TextField(value = amount, onValueChange = {
            amount = it
        }, modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp),
        singleLine = true,
        label = { Text(text = "Bill Amount")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))

        Spacer(modifier = Modifier.padding(10.dp))

        Text(text = "Tip Amount: $"+ getCalculatedTip(amount),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier =   Modifier.padding(start = 20.dp, end = 20.dp)
        )
    }
}

fun getCalculatedTip(value: String): Float{
    return if(value.isNotEmpty()) {
        0.15f * value.toFloat()
    }else 0.0f
}

@Preview(showBackground = true)
@Composable
fun TipCalculatorPreview() {
    TipCalculatorAppTheme {
        TipCalculator()
    }
}