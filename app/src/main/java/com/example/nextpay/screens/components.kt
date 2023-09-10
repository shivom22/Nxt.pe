package com.example.nextpay.screens

import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nextpay.R
import com.example.nextpay.model.MandateDetails
import com.example.nextpay.ui.theme.fontfamily
import com.example.nextpay.viewmodel.MandateViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneNumberField(
   onclick:()->Unit
) {
    val mandatedetails: MandateViewModel = viewModel()
    val title: State<MandateDetails?> = mandatedetails.mandatedetails.collectAsState()
    val numcc =
        title.value?.page_items?.get(1)?.paymentoptions?.get(0)?.configuration?.num_cc.toString()
    val numpalceholder =
        title.value?.page_items?.get(1)?.paymentoptions?.get(0)?.configuration?.num_placeholder.toString()
    val otpplaceholder =
        title.value?.page_items?.get(1)?.paymentoptions?.get(0)?.configuration?.otp_placeholer.toString()
    val statictxt =
        title.value?.page_items?.get(1)?.paymentoptions?.get(0)?.configuration?.sending_static_txt.toString()
    val send =
        title.value?.page_items?.get(1)?.paymentoptions?.get(0)?.configuration?.resend_txt.toString()
    val sendotp =
        title.value?.page_items?.get(1)?.paymentoptions?.get(0)?.configuration?.send_otp_txt.toString()
    val verifyotp =
        title.value?.page_items?.get(1)?.paymentoptions?.get(0)?.configuration?.verify_otp_txt.toString()

    var isError by rememberSaveable { mutableStateOf(false) }
    var otptnvisible by rememberSaveable { mutableStateOf(false) }
    var otp = rememberSaveable { mutableStateOf("") }
    var countryCode by rememberSaveable { mutableStateOf("") }
    var phoneNumber = rememberSaveable { mutableStateOf("") }
    var otperror by rememberSaveable { mutableStateOf(false) }
    var otpenable by rememberSaveable { mutableStateOf(true) }

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.55f)
                    .height(45.dp)
                    .padding(end = 0.dp)
                    .border(
                        width = 0.4.dp,
                        brush = Brush.horizontalGradient(
                            listOf(Color(0xFF707070), Color(0xFF707070))
                        ),
                        shape = RoundedCornerShape(10.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(0.27f),
                    value = countryCode,
                    onValueChange = { countryCode = it.take(4) },
                    placeholder = {
                        Text(
                            text = numcc,
                            style = TextStyle(
                                fontSize = 13.sp,
                                fontFamily = fontfamily,
                                fontWeight = FontWeight.Normal
                            )
                        )
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Transparent,
                        cursorColor = Color.Black,
                        disabledBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent

                    ),
                    enabled = false,
                    keyboardActions = KeyboardActions(
                        onNext = {}
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone,
                        imeAction = ImeAction.Next
                    )

                )
                VerticalDivider(modifier = Modifier.padding(top = 12.dp, bottom = 12.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(0.7f),
                    value = phoneNumber.value,
                    placeholder = {
                        Text(
                            text = numpalceholder,
                            style = TextStyle(
                                fontSize = 13.sp,
                                fontFamily = fontfamily,
                                fontWeight = FontWeight.Normal
                            )
                        )
                    },
                    onValueChange = {
                        phoneNumber.value = it
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Transparent,
                        disabledPlaceholderColor = Color.Transparent,
                        unfocusedTextColor = if(isError) Color.Red else Color.Black,
                        unfocusedBorderColor = Color.Transparent,
                        cursorColor = Color.Black,
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            isError =
                                !(phoneNumber.value.isNotEmpty() && Patterns.PHONE.matcher(phoneNumber.value)
                                    .matches() && phoneNumber.value.length == 10)
                            if (!isError) {
                                otptnvisible = true
                            }
                        }
                    ),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone,
                        imeAction = ImeAction.Next
                    )

                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.50f)
                    .height(45.dp)
                    .padding(end = 0.dp)
                    .border(
                        width = 0.4.dp,
                        brush = Brush.horizontalGradient(
                            listOf(Color(0xFF707070), Color(0xFF707070))
                        ),
                        shape = RoundedCornerShape(10.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(1f),
                    value = otp.value,
                    placeholder = {
                        Text(
                            text = otpplaceholder,
                            style = TextStyle(
                                fontSize = 13.sp,
                                fontFamily = fontfamily,
                                fontWeight = FontWeight.Normal
                            )
                        )
                    },
                    onValueChange = { otp.value = it },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedTextColor = if(otperror) Color.Red else Color.Black,
                        disabledPlaceholderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        cursorColor = Color.Black,
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                           otperror = !(otp.value.isNotEmpty()&&otp.value.length==6)
                        }
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone,
                        imeAction = ImeAction.Next
                    )
                )
            }
        }
        if (isError || otperror) {
            Row(
                modifier = Modifier.padding(start = 28.dp, top = 6.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable._055461_bxs_error_icon),
                    contentDescription = null
                )
                if (isError) {
                    Text(
                        text = "Invalid Number",
                        color = Color.Red,
                        modifier = Modifier.padding(top = 4.dp, start = 8.dp),
                        style = TextStyle(
                            fontFamily = fontfamily,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Normal
                        )
                    )
                }
                if (otperror) {
                    Text(
                        text = "Invalid OTP",
                        color = Color.Red,
                        modifier = Modifier.padding(top = 4.dp, start = 8.dp),
                        style = TextStyle(
                            fontFamily = fontfamily,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Normal
                        )
                    )
                }
            }
        }
        Row(
            Modifier
                .padding(top = 10.dp, bottom = 20.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = buildAnnotatedString {
                    append(statictxt)
                    withStyle(
                        style = SpanStyle(
                            fontFamily = fontfamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 9.sp,
                            color = Color(0XFF3482F5)
                        )
                    ) {
                        append(" $send")
                    }
                },
                style = TextStyle(
                    fontFamily = fontfamily,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Normal
                ),
                modifier = Modifier.padding(end = 20.dp)
            )
            if (!otptnvisible) {
                Button(
                    onClick = {

                    },
                    modifier = Modifier
                        .heightIn(42.dp)
                        .padding(end = 23.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0XFFFF8800)
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = sendotp,
                        style = TextStyle(
                            fontFamily = fontfamily,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Normal
                        )
                    )
                }
            }
            if (otptnvisible) {
                Button(
                    onClick = {
                             onclick()
                    },
                    enabled = otpenable,
                    modifier = Modifier
                        .heightIn(42.dp)
                        .padding(end = 23.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0XFFFF8800)
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = verifyotp,
                        style = TextStyle(
                            fontFamily = fontfamily,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Normal
                        )
                    )
                }
            }
        }
    }
}



