package com.anlian.alurmo.ui.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.anlian.alurmo.ui.navigation.AuthRoute
import com.anlian.alurmo.viewmodel.WelcomeViewModel

@Composable
fun WelcomePage(navController: NavHostController) {
    val viewModel = hiltViewModel<WelcomeViewModel>()

    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (appName, teamName, slogan, doodle, signInBtn, signUpBtn, appVersion) = createRefs()

        Text(
            text = stringResource(id = R.string.app_name),
            fontSize = 48.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 31.2.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier.constrainAs(appName) {
                top.linkTo(parent.top, margin = 58.dp)
                start.linkTo(parent.start, margin = 45.dp)
                end.linkTo(parent.end, margin = 45.dp)
                width = Dimension.fillToConstraints
            }
        )
        Text(
            text = stringResource(id = R.string.team_name, "Oleh"),
            fontSize = 20.sp,
            lineHeight = 13.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier.constrainAs(teamName) {
                top.linkTo(appName.bottom)
                start.linkTo(appName.start)
                end.linkTo(appName.end)
                width = Dimension.fillToConstraints
            }
        )
        Text(
            text = stringResource(id = R.string.slogan),
            fontSize = 40.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Start,
            lineHeight = 40.sp,
            modifier = Modifier.constrainAs(slogan) {
                top.linkTo(teamName.bottom, margin = 28.dp)
                start.linkTo(teamName.start)
                end.linkTo(teamName.end)
                width = Dimension.fillToConstraints
                height = Dimension.wrapContent
            }
        )
        Image(
            painter = painterResource(id = R.drawable.welcome_page_doodle),
            contentDescription = "doodle",
            Modifier.constrainAs(doodle) {
                top.linkTo(slogan.bottom, margin = 28.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
        Button(
            onClick = {
                viewModel.navigate(
                    AuthRoute.SignUp.route,
                    navController
                )
            },
            modifier = Modifier.constrainAs(signUpBtn) {
                top.linkTo(doodle.bottom, margin = 28.dp)
                start.linkTo(parent.start, margin = 16.dp)
                end.linkTo(parent.end, margin = 16.dp)
                width = Dimension.fillToConstraints
            }
        ) {
            Text(
                text = stringResource(id = R.string.sign_up_label),
                fontSize = 16.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Button(
            onClick = {
                viewModel.navigate(
                    AuthRoute.SignIn.route,
                    navController
                )
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = MaterialTheme.colorScheme.primary
            ),
            border = BorderStroke(
                width = 2.dp,
                color = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier.constrainAs(signInBtn) {
                top.linkTo(signUpBtn.bottom, margin = 20.dp)
                start.linkTo(signUpBtn.start)
                end.linkTo(signUpBtn.end)
                width = Dimension.fillToConstraints
            }
        ) {
            Text(
                text = stringResource(id = R.string.sign_in_label),
                fontSize = 16.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Text(
            text = stringResource(id = R.string.version_label),
            textAlign = TextAlign.Center,
            modifier = Modifier.constrainAs(appVersion) {
                top.linkTo(signInBtn.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }
        )
    }
}