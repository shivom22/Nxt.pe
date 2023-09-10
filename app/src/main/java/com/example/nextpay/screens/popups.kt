package com.example.nextpay.screens
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nextpay.R
import com.example.nextpay.model.MandateDetails
import com.example.nextpay.viewmodel.MandateViewModel
import kotlinx.coroutines.delay

@Composable
fun CountdownTimerDialog() {
    val mandatedetails: MandateViewModel = viewModel()
    val title: State<MandateDetails?> = mandatedetails.mandatedetails.collectAsState()
    val initialSeconds = title.value?.status_check?.wait_time!!.toLong()
    val txt = title.value?.status_check?.approve_success_txt
    val txt2 = title.value?.status_check?.approve_request_txt
    var showIcon by remember { mutableStateOf(false) }
    var remainingSeconds by remember { mutableStateOf(initialSeconds) }
    val iconSize by animateDpAsState(
        targetValue = if (showIcon) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = ""
    )

    LaunchedEffect(Unit) {
        run {
            delay(450)
            showIcon = true
        }
    }
    Dialog(onDismissRequest = {}) {
        Card(
            colors = CardDefaults.cardColors(Color.White, Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(16.dp, 24.dp, 16.dp, 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (remainingSeconds > 0) {
                    Text(
                        text = String.format("00:%2d", remainingSeconds),
                        fontSize = 24.sp,
                        modifier = Modifier.fillMaxWidth(),
                        color = Color(0xFFF26722),
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                    LaunchedEffect(remainingSeconds) {
                        while (remainingSeconds > 0) {
                            delay(1000)
                            remainingSeconds --
                        }
                        showIcon = true
                    }
                    Text(
                        text = txt2.toString(),
                        fontSize = 15.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 12.dp)
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable._930264_check_complete_done_green_success_icon),
                        contentDescription = null,
                        modifier = Modifier.size(iconSize)
                    )
                    Text(
                        text = txt.toString(),
                        fontSize = 15.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 12.dp)
                    )
                }
            }
        }
    }
}
