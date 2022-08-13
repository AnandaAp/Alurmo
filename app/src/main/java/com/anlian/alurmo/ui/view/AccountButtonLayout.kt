package com.anlian.alurmo.ui.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.anlian.alurmo.R
import com.anlian.alurmo.viewmodel.SignInViewModel
import com.anlian.alurmo.viewmodel.SignUpViewModel

@Composable
fun AccountButtonLayout(
    btnLabel: String,
    lowerRowText: String,
    signUpViewModel: SignUpViewModel = hiltViewModel(),
    signInViewModel: SignInViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (
            upperBtn,
            rowText,
            googleBtn,
            lineOne,
            lineTwo,
            lowerBtn
        ) = createRefs()
        Button(
            onClick = {
                when(btnLabel){
                    context.resources.getString(R.string.sign_up_label) -> {
                        signUpViewModel
                    }
                    context.resources.getString(R.string.sign_in_label) -> {
                        signInViewModel
                    }
                }
            },
            modifier = Modifier.constrainAs(upperBtn) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            },
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = btnLabel,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Text(
            modifier = Modifier.constrainAs(rowText) {
                top.linkTo(upperBtn.bottom, margin = 20.dp)
                start.linkTo(upperBtn.start)
                end.linkTo(upperBtn.end)
            },
            text = stringResource(id = R.string.or_label),
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
        Divider(
            thickness = 1.dp,
            color = Color.Gray,
            modifier = Modifier.constrainAs(lineOne) {
                top.linkTo(rowText.top)
                bottom.linkTo(rowText.bottom)
                start.linkTo(parent.start)
                end.linkTo(rowText.start, margin = 10.dp)
                width = Dimension.fillToConstraints
            }
        )
        Divider(
            thickness = 1.dp,
            color = Color.Gray,
            modifier = Modifier.constrainAs(lineTwo) {
                top.linkTo(rowText.top)
                bottom.linkTo(rowText.bottom)
                start.linkTo(rowText.end, margin = 10.dp)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }
        )
        OutlinedButton(
            onClick = { },
            modifier = Modifier.constrainAs(googleBtn) {
                top.linkTo(rowText.bottom, margin = 20.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            },
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(
                width = 2.dp,
                color = MaterialTheme.colorScheme.primary
            )
        ) {
            Icon(
                painter = painterResource(id = R.drawable.google),
                contentDescription = "Google Icon"
            )
            Spacer(Modifier.width(10.dp))
            Text(
                text = stringResource(id = R.string.google_btn_label, btnLabel),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.primary
            )
        }
        Row(
            modifier = Modifier.constrainAs(lowerBtn) {
                top.linkTo(googleBtn.bottom, margin = 20.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.wrapContent
            },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = lowerRowText,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
            )
            TextButton(
                onClick = { },
            ) {
                Text(
                    text = if (btnLabel == stringResource(id = R.string.sign_up_label))
                                stringResource(id = R.string.sign_in_label)
                           else stringResource(id = R.string.sign_up_label),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}