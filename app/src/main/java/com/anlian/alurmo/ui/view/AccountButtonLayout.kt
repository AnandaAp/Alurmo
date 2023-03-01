package com.anlian.alurmo.ui.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.anlian.alurmo.R
import com.anlian.alurmo.models.Account
import com.anlian.alurmo.ui.states.AccountState
import com.anlian.alurmo.ui.states.AuthState
import com.anlian.alurmo.ui.states.InputState
import com.anlian.alurmo.viewmodel.BaseAuthViewModel
import com.anlian.alurmo.viewmodel.SignInViewModel
import com.anlian.alurmo.viewmodel.SignUpViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun AccountButtonLayout(
    code: AuthState,
    lowerRowText: String,
    account: Account,
    navController: NavHostController,
    signUpViewModel: SignUpViewModel = hiltViewModel(),
    signInViewModel: SignInViewModel = hiltViewModel()
) {
    var viewModel = hiltViewModel<BaseAuthViewModel>()
    val state = viewModel.inputState.collectAsState()
    when (code) {
        is AuthState.SignUp -> viewModel = signUpViewModel
        is AuthState.SignIn -> viewModel = signInViewModel
        else -> Unit
    }
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
                viewModel.changeState(!viewModel.btnClick.value)
                CoroutineScope(Dispatchers.IO).launch {
                    delay(2000)
                    viewModel.proceedAuth(
                        account = account,
                        code = code,
                        provider = AccountState.Email
                    )
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
            when (state.value) {
                is InputState.Loading -> {
                    CircularProgressBar(
                        initialValue = 0f,
                        targetValue = 1f,
                        size = 16.dp
                    )
                    Text(
                        text = state.value.emailState.toString(),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                is InputState.Result -> Text(
                    text = code.message,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                InputState.Unspecified -> Text(
                    text = code.message,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
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
                text = stringResource(id = R.string.google_btn_label, code.message),
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
                onClick = { viewModel.navigate(code.route.route, navController) },
            ) {
                Text(
                    text = code.message,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}