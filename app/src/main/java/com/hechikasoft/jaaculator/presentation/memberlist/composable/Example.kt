package com.hechikasoft.jaaculator.presentation.memberlist.composable

import com.hechikasoft.jaaculator.data.entity.MemberEntity
import com.hechikasoft.jaaculator.presentation.memberlist.MemberListContract

private val initialMessages = listOf(
    MemberEntity(
        name = "me",
//        "Compose newbie: I’ve scourged the internet for tutorials about async data loading " +
//                "but haven’t found any good ones. What’s the recommended way to load async " +
//                "data and emit composable widgets?",
//        "8:03 PM"
    ),
    MemberEntity(
        name = "John Glenn",
//        "Compose newbie as well, have you looked at the JetNews sample? Most blog posts end up " +
//                "out of date pretty fast but this sample is always up to date and deals with async " +
//                "data loading (it's faked but the same idea applies) \uD83D\uDC49" +
//                "https://github.com/android/compose-samples/tree/master/JetNews",
//        "8:04 PM"
    ),
    MemberEntity(
        name = "Taylor Brooks",
//        "@aliconors Take a look at the `Flow.collectAsState()` APIs",
//        "8:05 PM"
    ),
    MemberEntity(
        name = "Taylor Brooks",
//        "You can use all the same stuff",
//        "8:05 PM"
    ),
    MemberEntity(
        name = "me",
//        "Thank you!",
//        "8:06 PM",
//        R.drawable.sticker
    ),
    MemberEntity(
        name = "me",
//        "Check it out!",
//        "8:07 PM"
    )
)

val exampleUiState = MemberListContract.State(
    channelName = "#composers",
    channelMembers = 42,
    members = initialMessages,
    randomNumberState = MemberListContract.RandomNumberState.Idle
)

///**
// * Example colleague profile
// */
//val colleagueProfile = ProfileScreenState(
//    userId = "12345",
//    photo = R.drawable.someone_else,
//    name = "Taylor Brooks",
//    status = "Away",
//    displayName = "taylor",
//    position = "Senior Android Dev at Openlane",
//    twitter = "twitter.com/taylorbrookscodes",
//    timeZone = "12:25 AM local time (Eastern Daylight Time)",
//    commonChannels = "2"
//)
//
///**
// * Example "me" profile.
// */
//val meProfile = ProfileScreenState(
//    userId = "me",
//    photo = R.drawable.ali,
//    name = "Ali Conors",
//    status = "Online",
//    displayName = "aliconors",
//    position = "Senior Android Dev at Yearin\nGoogle Developer Expert",
//    twitter = "twitter.com/aliconors",
//    timeZone = "In your timezone",
//    commonChannels = null
//)
