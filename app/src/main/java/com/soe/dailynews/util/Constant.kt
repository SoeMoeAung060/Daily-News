package com.soe.dailynews.util

import com.soe.dailynews.domain.model.Article
import com.soe.dailynews.domain.model.Source


const val FIVE_MINUTES_MILLIS = 5 * 60 * 1000L

//const val API_KEY = "8a727eb9628a42f59d916eb01171d009" //ahshan060
//const val API_KEY = "c87cb8312c1548c2a43c6507f46bcc54" //ahshan061
const val API_KEY = "9c6950b562ca4e17b1fc32902fc5807d" //softwareshack
const val BASE_URL = "https://newsapi.org/v2/"
const val NEWS_DB_NAME = "news_db_name"

const val PER_PAGE_SIZE = 5


val SOURCES = listOf(
    "the-verge",
    "vice-news",
    "wired",
    "bbc-news",
    "abc-news",
    "cnn",
    "reuters",
    "al-jazeera-english",
    "the-new-york-times",
    "the-guardian",
    "vox",
    "polygon",
    "ign",
    "cnet",
    "forbes",
    "business-insider",
    "esquire"
)


val CATEGORY_LIST = arrayListOf(
    "Health",
    "Business",
    "Technology",
    "Entertainment",
    "Science",
    "Sports"
)


val dummyArticle = Article(
    author = "Author",
    title = "This is title. This is title. This is title. This is title.",
    description = "This is a description. This is a description. This is a description. This is a description. This is a description. This is a description. ",
    content = "This is a content. This is a content. This is a content. This is a content. This is a content. This is a content. This is a content. This is a content. This is a content. This is a content. This is a content. This is a content. This is a content. This is a content. This is a content. This is a content. This is a content. This is a content. This is a content. This is a content. This is a content. This is a content. This is a content. This is a content. This is a content. This is a content. This is a content. This is a content. This is a content. This is a content. This is a content. This is a content. This is a content. This is a content. This is a content. This is a content. This is a content. This is a content. This is a content. This is a content. This is a content. This is a content. This is a content. This is a content. This is a content. This is a content. This is a content. This is a content. This is a content. This is a content. This is a content. This is a content. This is a content. This is a content. This is a content. This is a content. ",
    publishedAt = "2 hours ago",
    source = Source(id = "", name = "Wired"),
    url = "https://www.wired.com/story/the-worlds-biggest-bitcoin-mine-is-rattling-this-texas-oil-town/",
    urlToImage = "https://media.wired.com/photos/66c5ecc5724cee849e3104da/191:100/w_1280,c_limit/WIRED_BTC_EC_B-Elena-Chudoba.jpg"
)
