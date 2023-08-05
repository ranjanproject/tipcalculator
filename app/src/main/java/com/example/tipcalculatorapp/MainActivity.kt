package com.example.tipcalculatorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.ceil

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

@Composable
fun TipCalculator(modifier: Modifier = Modifier){

    /**
     * used delegate concept
     * amount is being used in two views
     * this concept is called @StateHoisting
     */
    var amount by remember {
       mutableStateOf("0")
    }

    var percentage by remember {
        mutableStateOf("0")
    }

    var isChecked by remember {
        mutableStateOf(false)
    }

    Column(verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally) {

        val modifier1 = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp)

        Text(text = "Calculate Tip", modifier = modifier1, textAlign = TextAlign.Left)

        Spacer(modifier = Modifier.padding(10.dp))

        EditTextLayout(value = amount, onValueChanged = {amount = it},
            R.string.bill_amt,
            R.drawable.ic_launcher_background,modifier1)

        Spacer(modifier = Modifier.padding(10.dp))

        EditTextLayout(value = percentage, onValueChanged = {percentage = it},
            R.string.tip_percentage,
            R.drawable.ic_launcher_foreground, modifier1)

        Spacer(modifier = Modifier.padding(10.dp))

        RoundValue(value = isChecked, onChecked = {isChecked = it}, modifier1)

        Spacer(modifier = Modifier.padding(10.dp))

        Text(text = stringResource(id = R.string.tip_amt)+ getCalculatedTip(amount, percentage, isChecked),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier =   Modifier.padding(start = 20.dp, end = 20.dp)
        )
    }
}

/**
 * EditTextLayout is now StateLess
 * It is not storing its own state
 * state is being passed by other composable function
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTextLayout(value: String,onValueChanged: (String)->Unit,
                   label: Int, leadingIcon: Int, modifier: Modifier){
    TextField(value = value,
        onValueChange = onValueChanged,
        modifier = modifier,
        singleLine = true,
        label = { Text(text = stringResource(id = label))},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), leadingIcon = {Icon(
            painter = painterResource(id = leadingIcon),
            contentDescription = null, modifier = Modifier.width(30.dp).height(30.dp)
        )})
}

@Composable
fun RoundValue(value: Boolean, onChecked: (Boolean)-> Unit, modifier: Modifier){
    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = stringResource(id = R.string.round_up_tip), textAlign = TextAlign.Center)
        Switch(checked = value, onCheckedChange = onChecked)
    }
}

fun getCalculatedTip(value: String, percentage: String, roundOffEnabled: Boolean): Float{
    return if(value.isNotEmpty() && percentage.isNotEmpty()) {
        var x = percentage.toFloat() * value.toFloat() / 100f
        if(roundOffEnabled) x = ceil(x.toDouble()).toFloat()
        return x
    }else 0.0f
}

@Preview(showBackground = true)
@Composable
fun TipCalculatorPreview() {
    TipCalculatorAppTheme {
        TipCalculator()
    }
}