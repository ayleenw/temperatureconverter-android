package de.naemiluna.temperatureconverter_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.naemiluna.temperatureconverter.TemperatureConverter
import de.naemiluna.temperatureconverter_android.ui.theme.TemperatureConverterTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TemperatureConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ConverterView()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConverterView() {
    var inputText by remember { mutableStateOf("") }
    var outputText by remember { mutableStateOf("") }

    // Create a Column layout
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Input field for numbers
        OutlinedTextField(
            value = inputText,
            onValueChange = {
                inputText = it
            },
            label = { Text("Enter numbers") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Convert button

        Button(onClick = {
            val celsiusValue = inputText.toDoubleOrNull()
            if (celsiusValue != null) {
                val fahrenheit = TemperatureConverter.celsiusToFahrenheit(celsiusValue)
                outputText = fahrenheit.toString()
            } else {
                outputText = "Invalid input"
            }
        }) {
            Text("Convert")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Output field
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color.Gray),
        ) {
            Text(
                text = outputText,
                fontSize = 18.sp,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TemperatureConverterTheme {
        ConverterView()
    }
}