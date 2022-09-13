package com.example.shortnews.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.shortnews.Components.DataOrException
import com.example.shortnews.Components.dataTimeFormat
import com.example.shortnews.Models.Article
import com.example.shortnews.Models.News
import com.example.shortnews.R
import com.example.shortnews.Screens.MainScreen.MainViewModel
import com.google.accompanist.pager.*

@OptIn(ExperimentalPagerApi::class)
@Composable
fun mainScreen(navController: NavController) {

    val state = rememberPagerState()


    val mainViewModel : MainViewModel = hiltViewModel()
    val newsData = produceState<DataOrException<News, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)) {
        value = mainViewModel.getTopNews("in")
    }.value

    if (newsData.data == null){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(modifier = Modifier.size(100.dp), color = Color.Blue)
            Text(text = "Loading Shorts", fontSize = 25.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(10.dp))
        }
    }else if (newsData.data!=null) {

        activity(newsData,state)
       
//        LaunchedEffect(state.currentPage) {
//            state.scroll(scrollPriority = MutatePriority.Default, block = {
//
//            })
//
//        }

            
        }
    }

@OptIn(ExperimentalPagerApi::class)
@Composable
fun activity(newsData: DataOrException<News, Boolean, Exception>, state: PagerState) {
    androidx.compose.material.Surface(modifier = Modifier.fillMaxSize()) {
        VerticalPager(count = 15,state = state) {page->

            newsCard(state, newsData.data!!.articles[page])
        }
            
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun newsCard(state: PagerState, data: Article) {


        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 30.dp, horizontal = 10.dp),
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            elevation = 10.dp

        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 20.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                if (data.urlToImage != null) {

                    Image(
                        painter = rememberAsyncImagePainter(model = data.urlToImage),
                        contentDescription = "image",
                        Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "image",
                        Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                    )
                }

                Card(
                    Modifier
                        .fillMaxWidth(), shape = RoundedCornerShape(CornerSize(15.dp))){

                    Text(
                        text = "${data.title}",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = data.source.name.toString(),
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = dataTimeFormat(date = data.publishedAt),
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier.padding(5.dp)
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Card(
                    Modifier
                        .height(200.dp)
                        .fillMaxWidth(), shape = RoundedCornerShape(CornerSize(15.dp))) {
                   LazyColumn(){
                        item {

                            Text(
                                text = "${data.description}",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Normal,
                                modifier = Modifier.padding(10.dp)
                            )
                        }
                   }
                }


                val uriHandler = LocalUriHandler.current


                Column(Modifier.fillMaxWidth(),verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.CenterHorizontally) {


                    Card(modifier = Modifier
                        .padding(10.dp)
                        .clickable {
                            uriHandler.openUri(data.url)
                        }
                        .fillMaxWidth()
                        .height(50.dp),
                        shape = RoundedCornerShape(CornerSize(10.dp)),
                        backgroundColor = Color(0xFF9CA2A1)
                    ) {
                        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

                            Text(
                                text = "Click to read more",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Normal
                            )
                        }

                    }
                }
            }
        }


}