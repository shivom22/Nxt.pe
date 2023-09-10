package com.example.nextpay.screens
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nextpay.R
import com.example.nextpay.model.MandateDetails
import com.example.nextpay.ui.theme.fontfamily
import com.example.nextpay.viewmodel.MandateViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MandateDetailScreen() {
    val mandatedetails: MandateViewModel = viewModel()
    val title: State<MandateDetails?> = mandatedetails.mandatedetails.collectAsState()
    val txt = title.value?.page_title.toString()
    val paymentopt = title.value?.page_items?.get(1)?.title.toString()
    val payusing = title.value?.page_items?.get(2)?.title.toString()
    val txt1 = title.value?.page_items?.get(2)?.customer_linked_account?.options?.get(0)?.text.toString()
    val sheetState = rememberModalBottomSheetState(
        confirmValueChange = { false }
    )
    var isVisible by rememberSaveable { mutableStateOf(false) }
    var isVerifed by rememberSaveable { mutableStateOf(false) }
    var dailogvisible by rememberSaveable {
        mutableStateOf(false)
    }
    Surface(
        color = Color.White
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    modifier = Modifier.shadow(10.dp),
                    title = {
                        Text(
                            text = txt,
                            style = TextStyle(
                                fontFamily = fontfamily,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 18.sp,
                                lineHeight = 25.sp
                            ),
                            modifier = Modifier.width(142.dp)
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { /* do something */ }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBackIosNew,
                                tint = Color(0xFFFF8800),
                                contentDescription = "Localized description"
                            )
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.White
                    )
                )
            }
        ) { _ ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .background(color = Color.White),
            ) {
                DetailCard()
                Spacer(modifier = Modifier.heightIn(29.dp))
                Text(
                    text = paymentopt,
                    modifier = Modifier.padding(start = 25.dp),
                    style = TextStyle(
                        fontFamily = fontfamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 17.sp,
                        lineHeight = 25.sp,
                    )
                )
                Spacer(modifier = Modifier.heightIn(29.dp))
                PaymentButtons(onclick = { isVisible = true })
                Spacer(modifier = Modifier.heightIn(29.dp))
                if (isVerifed){
                Text(
                    text = payusing,
                    modifier = Modifier.padding(start = 25.dp),
                    style = TextStyle(
                        fontFamily = fontfamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 17.sp,
                        lineHeight = 25.sp,
                    )
                )
                    Surface (
                        shadowElevation = 3.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .padding(start = 20.dp, end = 20.dp, top = 10.dp),
                        color = Color.White,
                        shape = RoundedCornerShape(10.dp)
                    ){
                        Row(
                            modifier = Modifier
                                .border(
                                    width = 0.4.dp,
                                    brush = Brush.horizontalGradient(
                                        listOf(Color(0xFFF08D34), Color(0xFFF08D34))
                                    ),
                                    shape = RoundedCornerShape(10.dp)
                                ),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Box{
                                Image( painter = painterResource(id =  R.drawable.path_252),
                                    contentDescription =null ,
                                    modifier = Modifier.padding(top=5.dp, bottom = 5.dp, start = 10.dp))
                                Text(text = txt1,
                                    modifier = Modifier.padding(start = 35.dp,top = 7.dp),
                                    style = TextStyle(
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        fontFamily = fontfamily
                                    )
                                )
                            }
                            IconButton(onClick = { dailogvisible = !dailogvisible },

                                ) {
                                Icon(imageVector = Icons.Default.ArrowForwardIos, contentDescription = null)
                            }
                        }
                    }
                }
                if (isVisible) {
                    ModalBottomSheet(
                        modifier = Modifier.fillMaxWidth(),
                        onDismissRequest = { isVisible = false },
                        sheetState = sheetState,
                        dragHandle = { BottomSheetDefaults.DragHandle(
                              color = Color.Transparent
                        )},
                        shape = RectangleShape,
                        containerColor = Color.White
                    ) {
                        Column {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box {
                                    Image(
                                        modifier = Modifier.padding(start = 20.dp),
                                        painter = painterResource(id = R.drawable.path_252),
                                        contentDescription = null
                                    )
                                    Text(
                                        text = "Airtel Money",
                                        style = TextStyle(
                                            fontFamily = fontfamily,
                                            fontSize = 17.sp,
                                            fontWeight = FontWeight.Normal
                                        ),
                                        modifier = Modifier.padding(start = 50.dp)
                                    )
                                }
                                IconButton(onClick = { isVisible = !isVisible  },
                                    modifier = Modifier.padding(end = 10.dp)) {
                                        Icon(imageVector =  Icons.Filled.KeyboardArrowUp,
                                            contentDescription =  null )
                                }
                            }
                            Spacer(modifier = Modifier.heightIn(16.dp))
                            Row (
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                PhoneNumberField {
                                    isVisible = false
                                    isVerifed = true
                                }
                            }

                        }
                    }
                }
                if (dailogvisible){
                    CountdownTimerDialog()
                }
            }
        }
    }
}