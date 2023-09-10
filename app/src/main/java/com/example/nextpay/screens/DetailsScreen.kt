package com.example.nextpay.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.nextpay.R
import com.example.nextpay.model.MandateDetails
import com.example.nextpay.ui.theme.fontfamily
import com.example.nextpay.viewmodel.MandateViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DetailCard() {
    val mandatedetails: MandateViewModel = viewModel()
    val details: State<MandateDetails?> = mandatedetails.mandatedetails.collectAsState()
    val message = details.value?.page_items?.get(0)?.mandate_details?.message.toString()
    val limit=details.value?.page_items?.get(0)?.mandate_details ?.details?.get(2)?.value.toString()
    val detailItems = details.value?.page_items?.get(0)?.mandate_details ?.details?: emptyList()
    val keyValuePairs = mutableListOf<Pair<String, String>>()
    for (item in detailItems) {
        val key = item.key
        val value = item.value
        keyValuePairs.add(Pair(key, value))
    }
    Card(
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(top = 80.dp, start = 15.dp, end = 15.dp)
            .background(Color.White, shape = RoundedCornerShape(10.dp)),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        FlowRow (
            maxItemsInEachRow = 2,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            keyValuePairs.forEachIndexed { index, pair ->
                KeyValueRow(keyValuePair = pair)
                if (index % 2 == 1 && index < keyValuePairs.size - 1) {
                    Divider(
                        modifier = Modifier.padding(start = 20.dp),
                        color = Color.Gray,
                        thickness = 0.dp
                    )
                }
            }
        }
        Divider(
            modifier = Modifier.padding(start = 20.dp),
            color = Color.Gray,
            thickness = 0.dp
        )
        Note(message = message, limit = limit)
    }
}

@Composable
fun KeyValueRow(keyValuePair: Pair<String, String>?) {
    keyValuePair?.let { pair ->
        Row(
            modifier = Modifier
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${pair.first} - ",
                modifier = Modifier.padding(start = 20.dp),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = fontfamily
                )
            )
            Text(
                text = pair.second,
                modifier = Modifier.padding(end=10.dp),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = fontfamily
                )
            )
        }
    }
}

@Composable
fun Note(message:String,limit:String){
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
           containerColor =  Color(0xFFfff2e4 ),
            contentColor =  Color(0XFF525252)
        ),
        shape = RoundedCornerShape(9.dp),

    ) {
        Row {
            Icon(painter = painterResource(
                id = R.drawable.info_62
            ),
                contentDescription =  null,
                modifier = Modifier
                    .padding(top = 16.dp, start = 10.dp, bottom = 16.dp)
                    .height(15.dp)
                    .width(16.dp),
                tint = Color(0XFFF08D34)
            )
            Text(text = buildAnnotatedString {
                append(message)
                append(" The Limit is upto ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(limit) }
                                             },
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .padding(top = 13.dp, start = 6.dp, end = 10.dp, bottom = 13.dp)
                    .fillMaxWidth(),
                style = TextStyle(
                 fontFamily =    fontfamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    letterSpacing = 0.sp,
                )
            )
        }
    }
}

@Composable
fun PaymentButtons(
    onclick : ()->Unit
) {
    val mandatedetails: MandateViewModel = viewModel()
    val paymenrtoptions: State<MandateDetails?> = mandatedetails.mandatedetails.collectAsState()
    val detailItems = paymenrtoptions.value?.page_items?.get(1)?.paymentoptions?: emptyList()


    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        detailItems.forEach { url ->
            val path = remember(url) {
                url
            }
            Button(
                colors= ButtonDefaults.buttonColors(
                    containerColor = Color.White,

                ),
                onClick = { onclick() },
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(vertical = 4.dp, horizontal = 2.dp),
                modifier = Modifier
                    .size(100.dp)
                    .shadow(elevation = 8.dp, ambientColor = Color.Transparent)) {
                Box {
                    Image(
                        painter = rememberAsyncImagePainter(path.icon),
                        contentDescription = "wallet",
                        modifier = Modifier
                            .wrapContentSize()
                            .wrapContentHeight()
                            .wrapContentWidth()
                            .fillMaxSize()
                            .padding(4.dp),
                        contentScale = ContentScale.Fit
                    )
                }
            }
        }
    }
}




