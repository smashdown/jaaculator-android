package com.hechikasoft.jaaculator.presentation.memberlist.composable

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hechikasoft.jaaculator.R
import com.hechikasoft.jaaculator.data.entity.MemberEntity
import com.hechikasoft.jaaculator.presentation.common.CommonAppBar
import com.hechikasoft.jaaculator.presentation.memberlist.MemberListContract
import com.hechikasoft.jaaculator.ui.theme.JaaculatorTheme
import com.hechikasoft.jaaculator.ui.theme.Red
import com.hechikasoft.jaaculator.ui.theme.defaultTextColor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


@Preview(showBackground = true)
@Composable
fun MemberListPreview() {
    JaaculatorTheme {
        MemberListScreen(
            uiStateFlow = MutableStateFlow(exampleUiState),
            navigateToProfile = { }
        )
    }
}

@Composable
fun MemberListScreen(
    uiStateFlow: StateFlow<MemberListContract.State>,
    navigateToProfile: (String) -> Unit,
    onAddMemberClickListener: () -> Unit = { }
) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddMemberClickListener,
                backgroundColor = Red,
                contentColor = Color.White
            ) {
                Icon(Icons.Filled.Add, "")
            }
        },
        topBar = {
            TopAppBar(
                title = { Text("Add Members", color = defaultTextColor) },
                backgroundColor = Color.White,
                elevation = 0.dp
            )
        },
    ) {
        MemberListContent(
            uiStateFlow = uiStateFlow,
            navigateToProfile = navigateToProfile
        )
    }
}

/**
 * Entry point for a conversation screen.
 *
 * @param uiState [ConversationUiState] that contains messages to display
 * @param navigateToProfile User action when navigation to a profile is requested
 * @param modifier [Modifier] to apply to this layout node
 * @param onNavIconPressed Sends an event up when the user clicks on the menu
 */
@Composable
fun MemberListContent(
    uiStateFlow: StateFlow<MemberListContract.State>,
    navigateToProfile: (String) -> Unit
) {
    val uiState = uiStateFlow.collectAsState().value
    val authorMe = "authorMe" // stringResource(R.string.author_me)
    val timeNow = "now" // stringResource(id = R.string.now)

    val scrollState = rememberScrollState()
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
//            MutatePriority.UserInput(
//                onMessageSent = { content ->
////                    uiState.addMessage(
////                        Message(authorMe, content, timeNow)
////                    )
//                },
//                scrollState = scrollState,
//                // Use navigationBarsWithImePadding(), to move the input panel above both the
//                // navigation bar, and on-screen keyboard (IME)
//                // modifier = Modifier.navigationBarsWithImePadding(),
//            )
//            BasicTextField(
//                value = "textFieldValue",
//                onValueChange = { //onTextChanged(it)
//                },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(start = 16.dp)
////                    .align(Alignment.CenterStart)
//                    .onFocusChanged { state ->
////                        if (lastFocusState != state) {
////                            onTextFieldFocused(state == FocusState.Active)
////                        }
////                        lastFocusState = state
//                    },
////                keyboardOptions = KeyboardOptions(
////                    keyboardType = keyboardType,
////                    imeAction = ImeAction.Send
////                ),
//                maxLines = 1,
//                cursorBrush = SolidColor(LocalContentColor.current),
//                textStyle = LocalTextStyle.current.copy(color = LocalContentColor.current)
//            )
            Box(modifier = Modifier.fillMaxSize()) {
                Column(Modifier.fillMaxSize()) {
                    Members(
                        members = uiState.members,
                        navigateToProfile = navigateToProfile,
                        modifier = Modifier.weight(1f),
                        scrollState = scrollState
                    )
                }
            }
        }
    }
}

@Composable
fun ChannelNameBar(
    channelName: String,
    channelMembers: Int,
    modifier: Modifier = Modifier,
    onNavIconPressed: () -> Unit = { }
) {
    CommonAppBar(
        modifier = modifier,
        onNavIconPressed = onNavIconPressed,
        title = {
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Channel name
                Text(
                    text = channelName,
                    style = MaterialTheme.typography.subtitle1
                )
                // Number of members
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        text = "members", //stringResource(R.string.members, channelMembers),
                        style = MaterialTheme.typography.caption
                    )
                }
            }
        },
        actions = {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                // Search icon
                Icon(
                    imageVector = Icons.Outlined.Search,
                    modifier = Modifier
                        .clickable(onClick = {}) // TODO: Show not implemented dialog.
                        .padding(horizontal = 12.dp, vertical = 16.dp)
                        .height(24.dp),
                    contentDescription = "search" // stringResource(id = R.string.search)
                )
                // Info icon
                Icon(
                    imageVector = Icons.Outlined.Info,
                    modifier = Modifier
                        .clickable(onClick = {}) // TODO: Show not implemented dialog.
                        .padding(horizontal = 12.dp, vertical = 16.dp)
                        .height(24.dp),
                    contentDescription = "info" // stringResource(id = R.string.info)
                )
            }
        }
    )
}

const val ConversationTestTag = "ConversationTestTag"

@Composable
fun Members(
    members: List<MemberEntity>,
    navigateToProfile: (String) -> Unit,
    scrollState: ScrollState,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()
    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .testTag(ConversationTestTag)
                .fillMaxWidth()
                .verticalScroll(scrollState, reverseScrolling = true)
        ) {
            val authorMe = "authorMe" // stringResource(id = R.string.author_me)
            Spacer(modifier = Modifier.height(64.dp))
            members.forEachIndexed { index, content ->
                val prevAuthor = members.getOrNull(index - 1)?.name
                val nextAuthor = members.getOrNull(index + 1)?.name
                val isFirstMessageByAuthor = prevAuthor != content.name
                val isLastMessageByAuthor = nextAuthor != content.name

                // Hardcode day dividers for simplicity
//                if (index == 0) {
//                    DayHeader("20 Aug")
//                } else if (index == 4) {
//                    DayHeader("Today")
//                }

                Member(
                    onAuthorClick = {
                        navigateToProfile(content.name)
                    },
                    memberEntity = content,
                    isUserMe = content.name == authorMe,
                    isFirstMessageByAuthor = isFirstMessageByAuthor,
                    isLastMessageByAuthor = isLastMessageByAuthor
                )
            }
        }
        // Jump to bottom button shows up when user scrolls past a threshold.
        // Convert to pixels:
        val jumpThreshold = with(LocalDensity.current) {
            JumpToBottomThreshold.toPx()
        }

        // Apply the threshold:
        val jumpToBottomButtonEnabled = scrollState.value > jumpThreshold

//        JumpToBottom(
//            // Only show if the scroller is not at the bottom
//            enabled = jumpToBottomButtonEnabled,
//            onClicked = {
//                scope.launch {
//                    scrollState.animateScrollTo(0)
//                }
//            },
//            modifier = Modifier.align(Alignment.BottomCenter)
//        )
    }
}

@Composable
fun Member(
    onAuthorClick: () -> Unit,
    memberEntity: MemberEntity,
    isUserMe: Boolean,
    isFirstMessageByAuthor: Boolean,
    isLastMessageByAuthor: Boolean
) {
    // TODO: get image from msg.author
    val painter = if (isUserMe) {
        painterResource(id = R.drawable.ic_launcher_background)
    } else {
        painterResource(id = R.drawable.ic_launcher_background)
    }
    val borderColor = if (isUserMe) {
        MaterialTheme.colors.primary
    } else {
        MaterialTheme.colors.secondary
    }

    val spaceBetweenAuthors = if (isFirstMessageByAuthor) Modifier.padding(top = 8.dp) else Modifier
    Row(modifier = spaceBetweenAuthors) {
        if (isFirstMessageByAuthor) {
            // Avatar
            Image(
                modifier = Modifier
                    .clickable(onClick = onAuthorClick)
                    .padding(horizontal = 16.dp)
                    .size(42.dp)
                    .border(1.5.dp, borderColor, CircleShape)
                    .border(3.dp, MaterialTheme.colors.surface, CircleShape)
                    .clip(CircleShape)
                    .align(Alignment.Top),
                painter = painter,
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )
        } else {
            // Space under avatar
            Spacer(modifier = Modifier.width(74.dp))
        }
//        AuthorAndTextMessage(
//            msg = msg,
//            isFirstMessageByAuthor = isFirstMessageByAuthor,
//            isLastMessageByAuthor = isLastMessageByAuthor,
//            modifier = Modifier
//                .padding(end = 16.dp)
//                .weight(1f)
//        )
    }
}

//@Composable
//fun AuthorAndTextMessage(
//    msg: Message,
//    isFirstMessageByAuthor: Boolean,
//    isLastMessageByAuthor: Boolean,
//    modifier: Modifier = Modifier
//) {
//    Column(modifier = modifier) {
//        if (isFirstMessageByAuthor) {
//            AuthorNameTimestamp(msg)
//        }
//        ChatItemBubble(msg, isLastMessageByAuthor)
//        if (isLastMessageByAuthor) {
//            // Last bubble before next author
//            Spacer(modifier = Modifier.height(8.dp))
//        } else {
//            // Between bubbles
//            Spacer(modifier = Modifier.height(4.dp))
//        }
//    }
//}
//
//@Composable
//private fun AuthorNameTimestamp(msg: Message) {
//    // Combine author and timestamp for a11y.
//    Row(modifier = Modifier.semantics(mergeDescendants = true) {}) {
//        Text(
//            text = msg.author,
//            style = MaterialTheme.typography.subtitle1,
//            modifier = Modifier
//                .alignBy(LastBaseline)
//                .paddingFrom(LastBaseline, after = 8.dp) // Space to 1st bubble
//        )
//        Spacer(modifier = Modifier.width(8.dp))
//        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
//            Text(
//                text = msg.timestamp,
//                style = MaterialTheme.typography.caption,
//                modifier = Modifier.alignBy(LastBaseline)
//            )
//        }
//    }
//}
//
//private val ChatBubbleShape = RoundedCornerShape(0.dp, 8.dp, 8.dp, 0.dp)
//private val LastChatBubbleShape = RoundedCornerShape(0.dp, 8.dp, 8.dp, 8.dp)
//
//@Composable
//fun DayHeader(dayString: String) {
//    Row(
//        modifier = Modifier
//            .padding(vertical = 8.dp, horizontal = 16.dp)
//            .height(16.dp)
//    ) {
//        DayHeaderLine()
//        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
//            Text(
//                text = dayString,
//                modifier = Modifier.padding(horizontal = 16.dp),
//                style = MaterialTheme.typography.overline
//            )
//        }
//        DayHeaderLine()
//    }
//}
//
//@Composable
//private fun RowScope.DayHeaderLine() {
//    Divider(
//        modifier = Modifier
//            .weight(1f)
//            .align(Alignment.CenterVertically),
//        color = MaterialTheme.colors.onSurface.copy(alpha = 0.12f)
//    )
//}
//
//@Composable
//fun ChatItemBubble(
//    message: Message,
//    lastMessageByAuthor: Boolean
//) {
//
//    val backgroundBubbleColor =
//        if (MaterialTheme.colors.isLight) {
//            Color(0xFFF5F5F5)
//        } else {
//            MaterialTheme.colors.elevatedSurface(2.dp)
//        }
//
//    val bubbleShape = if (lastMessageByAuthor) LastChatBubbleShape else ChatBubbleShape
//    Column {
//        Surface(color = backgroundBubbleColor, shape = bubbleShape) {
//            ClickableMessage(
//                message = message
//            )
//        }
//
//        message.image?.let {
//            Spacer(modifier = Modifier.height(4.dp))
//            Surface(color = backgroundBubbleColor, shape = bubbleShape) {
//                Image(
//                    painter = painterResource(it),
//                    contentScale = ContentScale.Fit,
//                    modifier = Modifier.size(160.dp),
//                    contentDescription = stringResource(id = R.string.attached_image)
//                )
//            }
//        }
//    }
//}
//
//@Composable
//fun ClickableMessage(message: Message) {
//    val uriHandler = LocalUriHandler.current
//
//    val styledMessage = messageFormatter(text = message.content)
//
//    ClickableText(
//        text = styledMessage,
//        style = MaterialTheme.typography.body1.copy(color = LocalContentColor.current),
//        modifier = Modifier.padding(8.dp),
//        onClick = {
//            styledMessage
//                .getStringAnnotations(start = it, end = it)
//                .firstOrNull()
//                ?.let { annotation ->
//                    when (annotation.tag) {
//                        SymbolAnnotationType.LINK.name -> uriHandler.openUri(annotation.item)
//                        // TODO(yrezgui): Open profile screen when click PERSON tag
//                        //  (e.g. @aliconors)
//                        else -> Unit
//                    }
//                }
//        }
//    )
//}


@Preview
@Composable
fun DayHeaderPrev() {
    // DayHeader("Aug 6")
}

private val JumpToBottomThreshold = 56.dp

private fun ScrollState.atBottom(): Boolean = value == 0
