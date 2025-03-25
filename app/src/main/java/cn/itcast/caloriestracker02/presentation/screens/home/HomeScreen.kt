package cn.itcast.caloriestracker02.presentation.screens.home

// 正确导入示例（根据你的包名调整）
import cn.itcast.caloriestracker02.R
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.TextButton
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import android.Manifest
import androidx.core.content.PermissionChecker.PERMISSION_GRANTED
import cn.itcast.caloriestracker02.presentation.viewModels.HomeViewModel
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    var selectedDate by remember { mutableIntStateOf(11) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .systemBarsPadding()
            .background(Color(0xFFF7F7F7))
    ) {
        //标题
        Text(
            text = "Calories Tracker",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2E7D32)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )

        val today = remember { LocalDate.now()}

        //周日起选择器
        WeekDateSelector(today, onDateSelected = {})

        //营养数据卡片
        NutritionSection()

        //饮食提示区
        RecentMealsPrompt(viewModel)

        //底部导航
        AppBottomNavigation()
    }
}

@Composable
private fun AppBottomNavigation() {
    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = NavigationBarDefaults.Elevation
    ) {
        // 仅保留首页按钮
        NavigationBarItem(
            selected = true, // 固定选中状态
            onClick = { /* 可保留空实现或添加点击反馈 */ },
            icon = {
                Icon(
                    imageVector = Icons.Filled.Home, // 始终显示填充图标
                    contentDescription = "Home"
                )
            },
            label = { Text("Home") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.primary,
                selectedTextColor = MaterialTheme.colorScheme.primary,
                unselectedIconColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
                unselectedTextColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecentMealsPrompt(viewModel: HomeViewModel) {
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }

    // 动态权限处理
    var photoUri by remember { mutableStateOf<Uri?>(null) }

    // 照片选择器（系统级组件）
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(maxItems = 5),
        onResult = { uris ->
            uris.takeIf { it.isNotEmpty() }?.let(viewModel::handleSelectedUris)
        }
    )

    // 拍照功能（MediaStore 方案）
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) viewModel.handleCameraImage(photoUri)
    }


    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            createImageUri(context)?.let { uri ->
                photoUri = uri
                cameraLauncher.launch(uri)
            }
        }
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = MaterialTheme.shapes.large
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Recently eaten",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.fillMaxWidth()
//                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.height(12.dp))
            Text(
                text = "You haven't uploaded any food\nStart tracking today's meals by taking\na quick pictures",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(20.dp))
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
                )
            {
                FilledIconButton(
                    onClick = {
                        //显示操作选择对话框
                        showDialog = true
                    },
                    colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    ),
                    modifier = Modifier.size(56.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Add, // M3 图标风格[4](@ref)
                        contentDescription = "Upload",
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }
    }
    // 对话框控制
    if (showDialog) {
        PhotoSelectionDialog(
            onDismiss = { showDialog = false },
            onCameraClick = {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    createImageUri(context)?.let { uri ->
                        photoUri = uri
                        cameraLauncher.launch(uri)
                    }
                } else {
                    if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PERMISSION_GRANTED) {
                        createImageUri(context)?.let { uri ->
                            photoUri = uri
                            cameraLauncher.launch(uri)
                        }
                    } else {
                        cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
                    }
                }
            },
            onGalleryClick = {
                photoPickerLauncher.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
            }
        )
    }
}

// 创建 MediaStore 图片 URI
private fun createImageUri(context: Context): Uri? {
    val contentValues = ContentValues().apply {
        put(MediaStore.Images.Media.DISPLAY_NAME, "MEAL_IMG_${System.currentTimeMillis()}")
        put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
    }
    return context.contentResolver.insert(
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        contentValues
    )
}

// 对话框组件
@Composable
private fun PhotoSelectionDialog(
    onDismiss: () -> Unit,
    onCameraClick: () -> Unit,
    onGalleryClick: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.add_meal_photo)) },
        text = { Text(stringResource(R.string.select_photo_source)) },
        confirmButton = {
            Column(Modifier.fillMaxWidth()) {
                Button(
                    onClick = onCameraClick,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(stringResource(R.string.take_photo))
                }
                Spacer(Modifier.height(8.dp))
                Button(
                    onClick = onGalleryClick,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(stringResource(R.string.choose_from_gallery))
                }
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.cancel))
            }
        }
    )
}


@Composable
private fun NutritionSection() {
    Column(modifier = Modifier.padding(16.dp)) {
        // 卡路里卡片
        NutritionCard(
            title = "Calories left",
            value = "2188",
            color = MaterialTheme.colorScheme.primaryContainer, // 使用 M3 语义色
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 三大营养素行
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp) // M3 推荐间距方式
        ) {
            NutritionCard(
                title = "Protein left",
                value = "125g",
                color = MaterialTheme.colorScheme.secondaryContainer,
                modifier = Modifier.weight(1f)
            )
            NutritionCard(
                title = "Carbs left",
                value = "285g",
                color = MaterialTheme.colorScheme.tertiaryContainer,
                modifier = Modifier.weight(1f)
            )
            NutritionCard(
                title = "Fats left",
                value = "60g",
                color = MaterialTheme.colorScheme.errorContainer,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun NutritionCard(
    title: String,
    value: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = color.copy(alpha = 0.2f), // M3 背景色
            contentColor = color // 自动适配文字颜色
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = MaterialTheme.shapes.medium // M3 中等圆角
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .widthIn(min = 80.dp), // 最小宽度保证可读性
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.headlineMedium, // M3 标题样式
                fontWeight = FontWeight.Bold
            )
            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium, // M3 标签样式
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
        }
    }
}
@Composable
fun WeekDateSelector(
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit
) {
    val currentDate = remember { LocalDate.now() }
    val weekDays = remember { generateWeekDates(currentDate) }

    LazyRow(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        items(items = weekDays, key = { it.date }) { day ->
            DateItem(
                date = day.date,
                dayOfWeek = day.dayOfWeek,
                isSelected = day.date == selectedDate,
                onClick = { onDateSelected(day.date) }
            )
            Spacer(modifier = Modifier.width(4.dp))
        }
    }
}

@Composable
private fun DateItem(
    date: LocalDate,
    dayOfWeek: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val containerColor = if (isSelected) {
        MaterialTheme.colorScheme.primaryContainer
    } else {
        Color.Transparent
    }

    val textColor = if (isSelected) {
        MaterialTheme.colorScheme.onPrimaryContainer
    } else {
        MaterialTheme.colorScheme.onSurface
    }

    Surface(
        modifier = Modifier
            .clip(CircleShape)
            .clickable { onClick() },
        color = containerColor,
        shadowElevation = if (isSelected) 4.dp else 0.dp
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = dayOfWeek,
                style = MaterialTheme.typography.labelSmall,
                color = textColor.copy(alpha = 0.8f)
            )
            Text(
                text = date.dayOfMonth.toString(),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                color = textColor
            )
        }
    }
}

private data class WeekDay(val date: LocalDate, val dayOfWeek: String)

private fun generateWeekDates(baseDate: LocalDate): List<WeekDay> {
    val startOfWeek = baseDate.with(DayOfWeek.MONDAY) // 以周一作为周开始
    return (0 until 7).map { offset ->
        val date = startOfWeek.plusDays(offset.toLong())
        WeekDay(
            date = date,
            dayOfWeek = date.dayOfWeek.getDisplayName(
                TextStyle.SHORT,
                Locale.getDefault()
            ).first().toString()
        )
    }
}

// 预览
@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    val mockViewModel = HomeViewModel(
    ).apply {
    }
    MaterialTheme {
        HomeScreen(viewModel = mockViewModel)
    }
}