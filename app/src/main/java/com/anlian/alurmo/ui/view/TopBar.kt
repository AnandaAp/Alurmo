package com.anlian.alurmo.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@Composable
fun TopBar(title: String?) {
    val darkMode = isSystemInDarkTheme()
    ConstraintLayout(modifier = Modifier
        .fillMaxWidth()
        .background(color = MaterialTheme.colorScheme.primary)
    ) {
        val(text, backBtn, questionBtn) = createRefs()

        Text(
            text = title!!.uppercase(),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.constrainAs(text){
                top.linkTo(parent.top, margin = 13.dp)
                bottom.linkTo(parent.bottom, margin = 13.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
//            color = if(darkMode) MaterialTheme.colorScheme.onPrimary else Color.White
        )
        IconButton(
            onClick = { },
            modifier = Modifier.constrainAs(backBtn){
                top.linkTo(text.top)
                bottom.linkTo(text.bottom)
                start.linkTo(parent.start)
                end.linkTo(text.start, margin = 100.dp)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back Button"
            )
        }
        IconButton(
            onClick = {  },
            modifier = Modifier.constrainAs(questionBtn){
                top.linkTo(text.top)
                bottom.linkTo(text.bottom)
                start.linkTo(text.end, margin = 100.dp)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            }
        ){
            Icon(
                imageVector = Icons.Default.QuestionMark,
                contentDescription = "Question Button"
            )
        }
    }
}