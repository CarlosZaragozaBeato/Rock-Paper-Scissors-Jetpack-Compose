package com.example.rock_paper_scissors_app.ui.feature_login.presentation.utils

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import com.example.rock_paper_scissors_app.ui.theme.LocalSpacing
import com.example.rock_paper_scissors_app.ui.theme.spock

@Composable
fun TitleComponent(
    text:String,
    alignment: Alignment = Alignment.Center,
    modifier: Modifier = Modifier) {
    Box(modifier = modifier
        .fillMaxWidth()
        .fillMaxHeight(.2f),
        contentAlignment = alignment){
        Text(text,
            style = MaterialTheme.typography.h3,
            color = MaterialTheme.colors.primary,
            fontWeight = FontWeight.SemiBold)
    }
}

@Composable
fun GameButton(
    text:String,
    modifier: Modifier = Modifier,
    onAction:() -> Unit
) {
    Button(onClick= {onAction.invoke()},
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary,),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = LocalSpacing.current.medium),
            shape = RectangleShape){
        Text(text,
            color = MaterialTheme.colors.background,
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.SemiBold)
    }
}

@Composable
fun TextfieldCustom(
    texto:String,
    placeholder:String,
    modifier:Modifier = Modifier,
    onTextChange:(String) -> Unit,

) {
    TextField(value = texto,
        onValueChange = { if(texto.length<=8){ onTextChange.invoke(it) } },
        placeholder = {Text(placeholder)},
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = LocalSpacing.current.medium),
        shape = RectangleShape,
        maxLines = 1
    )
}

@Composable
fun ButtonSection(onNavigate:() -> Unit,
                  option:String,
                  text:String,
                  onClick: () -> Unit) {
    Column() {
        GameButton(text = text) {onClick.invoke() }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Text(option)
            TextButton(onClick = {onNavigate.invoke()}) {
                Text("Click Here",
                    color = spock,
                    fontWeight = FontWeight.SemiBold)
            }
        }
    }
}