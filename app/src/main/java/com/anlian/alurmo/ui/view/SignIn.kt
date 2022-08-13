package com.anlian.alurmo.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.anlian.alurmo.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignIn() {
    val email = remember { mutableStateOf("") }
    val pass = remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val togglePass = remember { mutableStateOf(false) }

    Scaffold(
        topBar = { TopBar(title = stringResource(id = R.string.sign_in_label))}
    ) {
        ConstraintLayout(
            modifier = Modifier
                .padding(it)
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxSize()
        ) {
            val (
                doodle,
                upperField,
                forgotPassBtn,
                lowerField
            ) = createRefs()

            Image(
                painter = painterResource(id = R.drawable.login_doodle),
                contentDescription = "Sign In Doodle",
                modifier = Modifier.constrainAs(doodle) {
                    top.linkTo(parent.top, margin = 30.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )
            Box(
                modifier = Modifier.constrainAs(upperField) {
                    top.linkTo(doodle.bottom, margin = 24.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
            ){
                InputField(
                    email = email,
                    pass = pass,
                    focusManager = focusManager,
                    togglePass = togglePass
                )
            }
            Box(
                modifier = Modifier
                    .constrainAs(lowerField) {
                        bottom.linkTo(parent.bottom, margin = 34.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    }
            ) {
                AccountButtonLayout(
                    btnLabel = stringResource(id = R.string.sign_in_label),
                    lowerRowText = stringResource(id = R.string.sign_up_attention_label)
                )
            }
            TextButton(
                onClick = {  },
                modifier = Modifier.constrainAs(forgotPassBtn) {
                    bottom.linkTo(lowerField.top, margin = 5.dp)
                    end.linkTo(parent.end)
                }
            ) {
                Text(
                    text = stringResource(id = R.string.forgot_pass_btn),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}